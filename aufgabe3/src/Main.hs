{-# LANGUAGE OverloadedStrings #-}
{-# LANGUAGE TemplateHaskell #-}
{-# LANGUAGE RankNTypes #-}

module Main where

import System.Environment
import System.IO.Unsafe (unsafePerformIO)
import System.IO.Error

import Control.Monad (void)

import Lens.Micro
import Lens.Micro.TH

import qualified Graphics.Vty as V
import qualified Brick.Widgets.Edit as E
import qualified Brick.Types as T
import qualified Brick.Main as M

import Renderer

data Name = Editor
          deriving (Ord, Show, Eq)

data St =
  St { _fpath :: String
     , _editor :: E.Editor Name }

makeLenses ''St

drawUI :: St -> [T.Widget Name]
drawUI st = [ui]
    where
        ui = E.renderEditor True (st^.editor)

appEvent :: St -> V.Event -> T.EventM Name (T.Next St)
appEvent st (V.EvKey V.KEsc []) = M.halt st
appEvent st (V.EvKey (V.KChar 's') [V.MCtrl]) = doSave st
appEvent st ev = M.continue =<< T.handleEventLensed st editor E.handleEditorEvent ev

doSave :: St -> T.EventM Name (T.Next St)
doSave st = 
  case st^.fpath of
    "" -> askFilePathAndSave st
    _ -> save st
  where
    askFilePathAndSave st' = M.suspendAndResume $ do
      fp <- readAndSave st'
      return $ st' & fpath .~ fp

    readAndSave st' = do
      putStrLn "File path:"
      fp <- getLine
      catchIOError ((writeFile fp $ content st') >> return fp) (\_ -> putStrLn "Invalid file!!" >> readAndSave st)

    save st' = unsafePerformIO $ do
      writeFile (st'^.fpath) $ content st'
      return $ M.continue st'

    content st' = joinLines $ E.getEditContents $ st'^.editor

initialState :: String -> String -> St
initialState fp content = 
  St fp (E.editor Editor (render . joinLines) Nothing content)

joinLines :: [String] -> String
joinLines [] = ""
joinLines (l:[]) = l
joinLines (l:ls) = l ++ "\n" ++ joinLines ls

app :: M.App St V.Event Name
app =
    M.App { M.appDraw = drawUI
          , M.appChooseCursor = M.showFirstCursor
          , M.appHandleEvent = appEvent
          , M.appStartEvent = return
          , M.appAttrMap = const markupMap
          , M.appLiftVtyEvent = id
          }

main :: IO ()
main = do
  args <- getArgs

  fp <- case args of
    [] -> return ""
    _ -> return $ head args

  content <- catchIOError (readFile fp) (\_ -> return "")

  void $ M.defaultMain app (initialState fp content)
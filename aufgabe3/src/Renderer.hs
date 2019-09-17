{-# LANGUAGE OverloadedStrings #-}
module Renderer where

import qualified Graphics.Vty as V

import qualified Brick.Types as T
import Brick.Widgets.Core ((<+>), (<=>), str, emptyWidget)
import Brick.Markup (markup, (@?))
import Brick.Util (fg, bg)
import Brick.AttrMap (attrMap, AttrMap)

import Data.List.Split (splitWhen)
import Data.Text (pack)

import Text.Parsec.Error

import Lexer
import Parser

markupMap :: AttrMap
markupMap = attrMap V.defAttr
  [ ("error", bg V.red)
  , ("warning", bg V.yellow)
  , ("comment", fg $ V.rgbColor (80 :: Integer) (80 :: Integer) (80 :: Integer)) -- GRAY
  , ("dots", fg V.cyan)
  , ("at", fg V.brightMagenta)
  , ("guard", fg V.blue)
  , ("string", fg V.green)
  , ("normal", fg V.brightWhite)
  ]

render :: String -> T.Widget n
render txt =
    let toks = tokenize txt
        tree' = parseLang toks
        
        highlights = case tree' of
            Right tree -> inspect tree
            Left err -> ([], filter (\t -> (errorPos err) == (pos t)) toks)

        tokLines = splitWhen (\t -> tokType t == NL) toks
        widgetLines = (map . map) (\t -> renderToken t highlights) tokLines
        finalLines = map mergeHoriz widgetLines
    in
        mergeVert finalLines
    where
        renderToken tok (warns, errs) =
            if isErr tok errs then
                markup $ (pack $ text tok) @? "error"
            else if elem tok warns then
                markup $ (pack $ text tok) @? "warning"
            else if tokType tok == COMMENT then
                markup $ (pack $ text tok) @? "comment"
            else if tokType tok == DOTS then
                markup $ (pack $ text tok) @? "dots"
            else if tokType tok == AT then
                markup $ (pack $ text tok) @? "at"
            else if elem (tokType tok) [RBRACK, LBRACK] then
                markup $ (pack $ text tok) @? "guard"
            else if tokType tok == STRING then
                markup $ (pack $ text tok) @? "string"
            else
                markup $ (pack $ text tok) @? "normal"

        isErr tok errToks = (elem tok errToks) || (elem (tokType tok) [ERR_STRING])

        mergeHoriz [] = str "\n"
        mergeHoriz ws = foldl (<+>) emptyWidget ws -- HBox
        
        mergeVert ws = foldl (<=>) emptyWidget ws --  VBox

        inspect tree = ([], [])--TODO inspeciton of selected variable occurences, inspection of potential link matches
module Parser
    ( Pool(..)
    , Entities(..)
    , Entity(..)
    , GuardedEntity(..)
    , Links(..)
    , Link(..)
    , Tokems(..)
    , Tokem(..)
    , Reference(..)
    , parseLang
    ) where

import Text.Parsec hiding (token, anyToken, satisfy, noneOf)
import Lexer

data Pool = Pool Entities
    deriving (Show, Eq)
   
data Entities = Ents [[Entity]]
    deriving (Show, Eq)
    
data Entity = EntLink Links (Maybe GuardedEntity)
    deriving (Show, Eq)
    
data GuardedEntity = GuardEnt Entities (Maybe Token)
    deriving (Show, Eq)
    
data Links = Lnks [[Link]]
    deriving (Show, Eq)
    
data Link = LinkTok Tokems (Maybe Reference)
    deriving (Show, Eq)
    
data Tokems = Tokms [Tokem] -- Tokem since Token already taken
    deriving (Show, Eq)
    
data Tokem = StrTok Token
             | PoolTok Pool
    deriving (Show, Eq)
    
data Reference = Ref Token RefAt
    deriving (Show, Eq)
    
data RefAt = RefTok Token
             | RefPool Pool
    deriving (Show, Eq)

type Parser a = Parsec [Token] () a

parseLang :: [Token] -> Either ParseError Pool
parseLang toks = parse pool "" $ filter (not.ignored) toks
    where
        ignored t = elem (tokType t) [WS, NL, COMMENT]

pool :: Parser Pool
pool = between (token LCURLY) (token RCURLY) entities >>= \ents -> return $ Pool ents

entities :: Parser Entities
entities = many entity `sepBy` (token SEMICOL) >>= \ents -> return $ Ents ents --

entity:: Parser Entity
entity = do
            lnks <- links --list of links
            ent <- optionMaybe guardEnt -- optional entitites surrounded with guards and optional dots
            return $ EntLink lnks ent

guardEnt :: Parser GuardedEntity
guardEnt = between (token LBRACK) (token RBRACK) (guardEnt') 
    where
        guardEnt' = do
            et  <- entities
            dots <- optionMaybe (token DOTS)
            return $ GuardEnt et dots

links :: Parser Links
links = many1 link `sepBy1` (token AMPS)  >>= \lnks -> return $ Lnks lnks --

link :: Parser Link
link = do
            tks <- tokems --list of tokens
            ref <- optionMaybe reference -- optional reference prefixed with @
            return $ LinkTok tks ref
            
reference :: Parser Reference
reference = do
    at <- token AT
    name <- refAt
    return $ Ref at name
    
refAt :: Parser RefAt
refAt = (token BOUND >>= \t -> return $ RefTok t)
    <|> (pool >>= \b -> return $ RefPool b)
 
tokems :: Parser Tokems
tokems = many1 tokem >>= \tkms -> return $ Tokms tkms

tokem :: Parser Tokem
tokem = (token STRING >>= \t -> return $ StrTok t)
    <|> (token UNBOUND >>= \t -> return $ StrTok t)
    <|> (token BOUND >>= \t -> return $ StrTok t)
    <|> (token ID >>= \t -> return $ StrTok t)
    <|> (pool >>= \b -> return $ PoolTok b)


token :: TokenType -> Parser Token
token tt = satisfy (\t -> tokType t == tt)

satisfy :: (Token -> Bool) -> Parser Token
satisfy f = tokenPrim (\t -> text t) 
                      advance
                      (\t -> if f t then Just t else Nothing)
    where
        advance _ _ ((Token _ _ p) : _) = p
        advance p _ [] = p
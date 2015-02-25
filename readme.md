Project "Crazy Eights Game"
==============================================================
Version: 1.0
Author:  Andrew Proshkin (zx80live@gmail.com, skype: zx80live)


Requirements
-----------------------------
- Scala: 2.11.1
- JRE:   8
- SBT:   0.13.5 (or Typesafe Activator 1.2.2)


Build and tests
-----------------------------
Build and testing:
    `> sbt update clean compile test`
    or
    `> activator update clean compile test`

Run game:
    `> sbt run`
    or
    `> activator run`
    
    
Game console interface
-----------------------------
The game supports follows prompt commands:

  system commands:
     
    > help|h|?          - print help
    > about             - about version, author
    > rules|r           - print game rules
    > new [count]       - new game with players, example "new 2"
    > exit|e|quit|q|end - end of game and exit
  
  game commands:
  
    > pass|p            - pass current move
    > status|stat|st    - print game status
    > suggest|sg        - suggest preferred move (if exists)
    > A♠,5♦,K♥,4♥,☆     - comma-separated cards for discard (user's move)
    > draw|d            - request for getting cards from stockpile
    > exit|quit|e|q     - exit from game
    

Example: create game for two players:
    `> new 2`

Game supports 1 to 4 players.
    
       
Cards DSL:
-----------------------------
You can enter in command prompt:
    
    `5♠,5♦,5♥,5♣,★ , ☆ ` - unicode cards
       
    `5S,5B,5H,5C,WJ,BJ`   - ASCII-codes

Full codes see: `com.zx80live.examples.crazyeights.cards.CardsHelper`
 
Game AI
-----------------------------
AI released by Crazy8MOvePatterns that find preferred moves for current game state.

Game logging
-----------------------------
Game logs locate in `/logs/crazy-eights.log`
   
   
Used techniques, scala features
------------------------------
  
In the project used follows scala-features:
    - pattern matching
    - immutable objects (where is possible)
    - implicit conversions
    - string interpolations
    - string interpolations with Regex
    - mixing traits
    - higher-order functions (lambdas)
    - functional combinators for monadic types (collections, etc)
    - currying
    - partial functions and them combinations
    - recursion (without @tailrec)
    - for comprehension (as well as pipes)
    - akka actors
 

Short release description
-----------------------------

Development focus on priority:
    1. Game API:
      1.1 Card Model API, support 54 cards deck. (API must be moved into external sbt lib)
          - @see com.zx80live.examples.crazyeights.cards:
            -- dsl.ConversionUtils._ allows to convert interpolated string to Card implicitly

             

      1.1.2 Convenient cards DSL - based on implicit conversion interpolated strings
        to Card Model API. Support card unicode representation.

          ```example {{{
            val card:Option[Card] = card"A♠"
            val cards:Option[List[Card]] = card"A♠,5♦,K♥,4♥,☆"
          }}}```

    2) Game Logic API
        - @see com.zx80live.examples.crazyeights.cards.rules
        - //TODO create another rules for other games like "CrazyEights"
        
    3) Game API test coverage: full coverage for core API. But into akka infrastructure probably 
    can some be bugs.
    
    4) Unfortunately, refactoring infrastructure of game actors is not completed. That code contains
    a lot of duplicated, dirty or non-optimized code.
    
    5) Game API can be implemented in follow architectures:
       - backend server for REST clients
    
    

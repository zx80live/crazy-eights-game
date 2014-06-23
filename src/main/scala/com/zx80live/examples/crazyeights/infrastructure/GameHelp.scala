package com.zx80live.examples.crazyeights.infrastructure

/**
 *
 * @author Andrew Proshkin
 */
trait GameHelp {

  def printAbout(): Unit = {
    println("Andrew Proshkin, zx80live@gmail.com".stripMargin)
  }

  def printTerms():Unit = {
    println(
      """
        |Game terms:
        |----------------------------------------------------------------
        |
        | deck         - standard 54-cards deck
        | stockpile    - TODO describe
        | discardpile  - TODO describe
        | current card - top card of discardpile
        | special card - any eights or joker cards
        | workspace    - game workspace consists of deck, stockpile,
        |                discardpile and implements game rules
        | draw         - player's request for getting card from stockpile
        | discard      - player's move, if move cards are correct, them
        |                will be put into discardpile
        | pass         - TODO describe
        |
      """.stripMargin)
  }

  def printHelp(): Unit = {
    println(
      """
        |system commands:
        |----------------------------------------------------------------
        | help|h|?          - print help
        |
        | terms|t           - print game terms
        |
        | rules|r           - print game rules
        |
        | about             - print about
        |
        | exit|e|quit|q|end - end of game and exit
        |
        | game commands:
        |----------------------------------------------------------------
        | new [count]       - new game with players, example "new 2"
        |
        | status|stat|st    - print game status
        |
        | cards             - print your cards
        |
        | discard [cards]   - discard cards to game workspace (your move)
        |
        | draw              - request for getting cards from stockpile
        |
      """.stripMargin)
  }

  def printRules(): Unit = {
    println(
      """
        |Rules of the Crazy Eights
        |---------------------------
        |
        |In preparation, each player is dealt 8 cards from a 52-card deck. The remaining cards constitute
        |the stockpile from which players will draw when required. The top card on the stockpile is put face
        |up on the table and it becomes the discard pile. The game begins with the first player, typically to
        |the left of the dealer.
        |
        |On each player’s turn she should either discard an appropriate card onto the discard pile, or draw
        |cards one by one from the stockpile until she comes up with a suitable card to play. Playable cards
        |are determined by the top card on the discard stack, by its rank and suit; i.e. one can only discard
        |a card with the same rank or suit as the topmost card on the discard pile. If a player does not have
        |an appropriate card to play and the stockpile is empty, she passes.
        |
        |There are special cards that can be played regardless of the discard stack; including 8 of any
        |suit, which can be used regardless of the discard stack and allows the player to name a suit
        |(without rank) with which the next player should continue. The next player can then play a card of
        |that suit, or play a special card. The other special card is the Joker, which allows the next player to
        |play any card she chooses.
        |
        |The game ends when a player finishes all her cards, or the stockpile is empty and no more cards
        |are playable. The player with the fewest number of cards wins.
        |
      """.stripMargin)
  }
}

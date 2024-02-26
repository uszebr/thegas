package game.base.party

import game.base.signal.ISignalScore
import game.base.signal.Signal
import game.base.signal.SignalsScoreBase
import groovyjarjarantlr4.v4.runtime.misc.NotNull
import player.Player

/**
 * Party is a series of Rounds between two players
 */
class Party {

    @NotNull
    Player playerLeft
    @NotNull
    Player playerRight

    private boolean partyFinished = false

    @NotNull
    RoundLine roundLine

    // entity with rules for calculating scores based on different Signal combination
    private ISignalScore scoreCalculatingStrategy

    void setScoreCalculatingStrategy(ISignalScore scoreCalculatingStrategy) {
        this.scoreCalculatingStrategy = scoreCalculatingStrategy
    }

    Party(Player playerLeft, Player playerRight, RoundLine roundLine) {
        this.playerLeft = playerLeft
        this.playerRight = playerRight
        this.roundLine = roundLine
        setScoreCalculatingStrategy(new SignalsScoreBase())
    }

    boolean isPartyFinished() {
        return partyFinished
    }

    void playParty() {
        if (partyFinished) {
            throw new Exception("Party is already finished")
        }

        while (roundLine.canGetRound()) {
            //Getting signals from players before new round created
            // Unmodified rounds send to player to prevent cheating
            // all rounds in the provided List are finished and can not be reset
            Signal signalLeft = playerLeft.getSignal(roundLine.getUnmodifiedRounds())
            Signal signalRight = playerRight.getSignal(roundLine.getUnmodifiedRounds())
            Round round = roundLine.getNewRound()
            round.signalLeft = signalLeft
            round.signalRight = signalRight
            playRound(round)
            //todo might remove this check
            if (!round.isRoundFinished()) {
                throw new Exception("Round is not finished after play")
            }
        }

        partyFinished = true
    }


    void playRound(Round round) {
        if (round.roundFinished) {
            throw new Exception("Round is already finished")
        }
        if (round.signalLeft == null || round.signalRight == null) {
            throw new Exception("Signals are not provided left: ${round.signalLeft} right: ${round.signalRight}")
        }

        round.setRoundScore(scoreCalculatingStrategy.calculateScore(round.signalLeft, round.signalRight))
        // moved to setRoundScore for now
        //   round.finishRound()
    }


}

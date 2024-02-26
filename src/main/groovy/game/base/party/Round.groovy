package game.base.party

import game.base.signal.RoundScore
import game.base.signal.Signal
import groovy.transform.PackageScope
import player.PlayerPosition

/**
 * Interaction between two players
 * Each Player provides signal
 * After signal is received, Round calculates scores for each player using SignalScore
 */
class Round {
    private Signal signalLeft
    private Signal signalRight

    private RoundScore roundScore


    private boolean roundFinished = false

    /**
     * Getting own player signal
     * @param position
     * @return
     */
    Signal getMySignal(PlayerPosition myPosition) {
        if(myPosition == null) {
            throw new IllegalArgumentException("Position can not be null")
        }
        if(myPosition == PlayerPosition.LEFT) {
            return signalLeft
        }
        if(myPosition == PlayerPosition.RIGHT) {
            return signalRight
        }
        throw new IllegalArgumentException("Position can be LEFT or RIGHT")
    }

    Signal getOpponentSignal(PlayerPosition myPosition){
    if(myPosition == null) {
        throw new IllegalArgumentException("Position can not be null")
    }
    if(myPosition == PlayerPosition.LEFT) {
        return signalRight
    }
    if(myPosition == PlayerPosition.RIGHT) {
        return signalLeft
    }
    throw new IllegalArgumentException("Position can be LEFT or RIGHT")
}

    void setSignalLeft(Signal signalLeft) {
        if (roundFinished) {
            throw new Exception("Round is already finished")
        }
        if (this.signalLeft != null) {
            throw new Exception("Left Signal can be set only once")
        }
        this.signalLeft = signalLeft
    }

    void setSignalRight(Signal signalRight) {
        if (roundFinished) {
            throw new Exception("Round is already finished")
        }
        if (this.signalRight != null) {
            throw new Exception("Right Signal can be set only once")
        }
        this.signalRight = signalRight
    }

    Signal getSignalLeft() {
        return signalLeft
    }

    Signal getSignalRight() {
        return signalRight
    }

    int getScoreRight() {
        return roundScore.scoreRight
    }

    int getScoreLeft() {
        return roundScore.scoreLeft
    }
    /**
     * Can Set score results of Round only once.. after that round is finished
     * @param roundScore
     */
    void setRoundScore(RoundScore roundScore) {
        if (isRoundFinished()) {
            throw new Exception("Round is already finished")
        }
        if (this.roundScore != null) {
            throw new Exception("roundScore can be set only once")
        }
        this.roundScore = roundScore
        finishRound()
    }


    boolean isRoundFinished() {
        return roundFinished
    }

    @PackageScope
    void finishRound() {
        if(signalLeft == null || signalRight == null) {
            throw new Exception("Signals are not provided")
        }
        if(roundScore == null) {
            throw new Exception("RoundScore is not provided")
        }
        roundFinished = true
    }


    String signalsToString() {
        return "$signalLeft$signalRight"
    }

    String scoresToString() {
        return "$scoreLeft$scoreRight"
    }
}

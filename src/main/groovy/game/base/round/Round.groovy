package game.base.round

import game.base.signal.Signal

/**
 * Interaction between two players
 * Each Player provides signal
 * After signal is received, Round calculates scores for each player using SignalScore
 */
class Round {
    Signal signalLeft
    Signal signalRight

    private int scoreLeft
    private int scoreRight

    private boolean roundFinished = false


    void play() {
        if (roundFinished) {
            throw new Exception("Round is already finished")
        }
        if (signalLeft == null || signalRight == null) {
            throw new Exception("Signals are not provided left: ${signalLeft} right: ${signalRight}")
        }
        //todo
        // player interaction and score calculation
        // scoreLeft = 0
        // scoreRight = 0
        //todo
        roundFinished = true
    }

    int getScoreLeft() {
        return scoreLeft
    }

    int getScoreRight() {
        return scoreRight
    }

}

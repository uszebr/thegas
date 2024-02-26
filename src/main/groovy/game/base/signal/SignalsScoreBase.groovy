package game.base.signal

/**
 * Implementation of score strategy for round
 */
class SignalsScoreBase implements ISignalScore {
    @Override
    RoundScore calculateScore(Signal signalLeft, Signal signalRight) {

        if (signalLeft == null || signalRight == null) {
            throw new Exception("Signals can not be null left: ${signalLeft} right: ${signalRight}")
        }
        if (signalLeft == Signal.GREEN && signalRight == signalRight.GREEN) {
            return new RoundScore(5, 5)
        }
        if (signalLeft == Signal.RED && signalRight == signalRight.RED) {
            return new RoundScore(1, 1)
        }
        if (signalLeft == Signal.GREEN && signalRight == signalRight.RED) {
            return new RoundScore(-2, 2)
        }
        if (signalLeft == Signal.RED && signalRight == signalRight.GREEN) {
            return new RoundScore(2, -2)
        }
        throw new Exception("Can not find conditions for provided signals")
    }


}

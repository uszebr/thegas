package player.strategy

import game.base.party.Round
import game.base.signal.Signal
import player.PlayerPosition

class CommonStrategies {

    static final Random random = new Random()

    static Closure alwaysGreen = { List<Round> rounds, PlayerPosition myPosition ->
        return Signal.GREEN
    }

    static Closure alwaysRed = { List<Round> rounds, PlayerPosition myPosition ->
        return Signal.RED
    }

    static Closure alwaysRandom = { List<Round> rounds, PlayerPosition myPosition ->
        return random.nextBoolean() ? Signal.GREEN : Signal.RED
    }

    static Closure startGreenRepeatOpponent = { List<Round> rounds, PlayerPosition myPosition ->
        if (rounds.size() == 0) {
            return alwaysGreen(rounds, myPosition)
        }
        return  rounds.getLast().getOpponentSignal(myPosition)
    }
}

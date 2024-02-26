package player

import game.base.party.Round
import game.base.signal.Signal

interface IPlayerSignal {
    Signal getSignal(List<Round> rounds,PlayerPosition position)
}
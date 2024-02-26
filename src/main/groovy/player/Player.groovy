package player

import game.base.party.Round
import game.base.signal.Signal
import player.strategy.CommonStrategies

class Player implements IPlayerSignal {

    Closure strategyFunc = CommonStrategies.alwaysGreen


    @Override
    Signal getSignal(List<Round> rounds, PlayerPosition position) {
        Signal signal = strategyFunc(rounds)
        if(signal==null){
            throw new Exception("Signal can not be null after applying strategy")
        }
        return signal
    }

    void setStrategyFunc(Closure strategyFunc) {
        if (strategyFunc == null) {
            throw new Exception("Player Strategy can not be null")
        }
        this.strategyFunc = strategyFunc
    }
}

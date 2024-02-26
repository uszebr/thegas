package game.base.signal

enum Signal {
    GREEN("Cooperation", '+' as char),
    RED("Confrontation", '^' as char);

    final String description
    final char s

    Signal(String description, char shortRepresentation) {
        this.description = description
        this.s  = shortRepresentation
    }


    @Override
    public String toString() {
        return "$s"
    }

    static Signal getOpposite(Signal signal){
        if(signal == null){
            throw new IllegalArgumentException("Signal can not be null")
        }
       if(signal == GREEN){
           return RED
       }else if(signal == RED){
           return GREEN
       }
       throw new IllegalArgumentException("Unknown signal")
    }
}
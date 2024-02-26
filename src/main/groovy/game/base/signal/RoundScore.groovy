package game.base.signal

class RoundScore {

    int scoreLeft

    int scoreRight

    RoundScore(int scoreLeft, int scoreRight) {
        this.scoreLeft = scoreLeft
        this.scoreRight = scoreRight
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (o == null || getClass() != o.class) return false

        RoundScore that = (RoundScore) o
        if (scoreLeft != that.scoreLeft) return false
        if (scoreRight != that.scoreRight) return false
        return true
    }

    int hashCode() {
        int result
        result = scoreLeft.hashCode()
        result = 31 * result + scoreRight.hashCode()
        return result
    }
}

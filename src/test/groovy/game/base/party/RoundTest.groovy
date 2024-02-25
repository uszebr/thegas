package game.base.party

import game.base.signal.Signal
import org.testng.Assert
import org.testng.annotations.Test

class RoundTest {

    public static final String NO_SIGNAL_MESSAGE_PART = "Signals are not provided left:"

    @Test
    void testPositive() {
        Round round = new Round()
        Assert.assertFalse(round.isRoundFinished())
        round.setSignalLeft(Signal.GREEN)
        round.setSignalRight(Signal.GREEN)
        round.play()
        Assert.assertTrue(round.isRoundFinished())

    }

    @Test
    void testNegativeOneSignalMissing() {
        Round round = new Round()
        Assert.assertFalse(round.isRoundFinished())
        round.setSignalLeft(Signal.GREEN)
        try {
            round.play()
            Assert.assertTrue(false, "Exception should be thrown")
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage().contains(NO_SIGNAL_MESSAGE_PART))
        }
        Assert.assertFalse(round.isRoundFinished())
    }

    @Test
    void testNegativeBothSignalMissing() {
        Round round = new Round()
        Assert.assertFalse(round.isRoundFinished())
        try {
            round.play()
            Assert.assertTrue(false, "Exception should be thrown")
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage().contains(NO_SIGNAL_MESSAGE_PART))
        }
        Assert.assertFalse(round.isRoundFinished())
    }
}

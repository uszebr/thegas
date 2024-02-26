package game.base.party

import game.base.signal.RoundScore
import game.base.signal.Signal
import org.testng.Assert
import org.testng.annotations.Test
import player.Player
import player.PlayerPosition

class RoundTest {

    public static final String NO_SIGNAL_MESSAGE_PART = "Signals are not provided"

    @Test
    void testPositive() {
        Round round = new Round()
        Assert.assertFalse(round.isRoundFinished())
        round.setSignalLeft(Signal.GREEN)
        round.setSignalRight(Signal.GREEN)
        Party party = new Party(new Player(), new Player(), new RoundLine(1, 1))
        party.playRound(round)
        Assert.assertTrue(round.isRoundFinished())

    }

    @Test
    void testNegativeOneSignalMissing() {
        Round round = new Round()
        Assert.assertFalse(round.isRoundFinished())
        round.setSignalLeft(Signal.GREEN)
        try {
            round.finishRound()
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
            round.finishRound()
            Assert.assertTrue(false, "Exception should be thrown")
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage().contains(NO_SIGNAL_MESSAGE_PART))
        }
        Assert.assertFalse(round.isRoundFinished())
    }

    @Test
    void testPositiveMySignal() {
        Round round = new Round()
        round.setSignalRight(Signal.GREEN)
        Assert.assertTrue(round.getMySignal(PlayerPosition.RIGHT) == Signal.GREEN)
        round.setSignalLeft(Signal.RED)
        Assert.assertTrue(round.getMySignal(PlayerPosition.RIGHT) == Signal.GREEN)
        Assert.assertTrue(round.getMySignal(PlayerPosition.LEFT) == Signal.RED)
    }

    @Test
    void testNegativeMySignal() {
        Round round = new Round()
        Assert.assertTrue(round.getMySignal(PlayerPosition.RIGHT) == null)
        Assert.assertTrue(round.getMySignal(PlayerPosition.LEFT) == null)
        round.setSignalRight(Signal.GREEN)
        Assert.assertTrue(round.getMySignal(PlayerPosition.LEFT) == null)
    }

    @Test
    void testPositiveOpponentSignal() {
        Round round = new Round()
        round.setSignalRight(Signal.GREEN)
        round.setSignalLeft(Signal.RED)
        Assert.assertTrue(round.getOpponentSignal(PlayerPosition.RIGHT) == Signal.RED)
        Assert.assertTrue(round.getOpponentSignal(PlayerPosition.LEFT) == Signal.GREEN)
    }

    @Test
    void testNegativeOpponentSignal() {
        Round round = new Round()
        Assert.assertTrue(round.getOpponentSignal(PlayerPosition.RIGHT) == null)
        Assert.assertTrue(round.getOpponentSignal(PlayerPosition.LEFT) == null)
        round.setSignalRight(Signal.GREEN)
        Assert.assertTrue(round.getOpponentSignal(PlayerPosition.RIGHT) == null)
    }

    @Test
    void testMySignalNull() {
        Round round = new Round()
        round.setSignalRight(Signal.GREEN)
        round.setSignalLeft(Signal.GREEN)
        try {
            round.getMySignal(null)
            Assert.assertTrue(false, "Exception should be thrown")
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage() == "Position can not be null")
        }
    }

    @Test
    void testOpponentSignalNull() {
        Round round = new Round()
        round.setSignalRight(Signal.GREEN)
        round.setSignalLeft(Signal.GREEN)
        try {
            round.getOpponentSignal(null)
            Assert.assertTrue(false, "Exception should be thrown")
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage() == "Position can not be null")
        }
    }

    @Test
    void testSetLeftSignalTwice() {
        Round round = new Round()
        round.setSignalLeft(Signal.GREEN)
        try {
            round.setSignalLeft(Signal.GREEN)
            Assert.assertTrue(false, "Exception should be thrown")
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage() == "Left Signal can be set only once")
        }
    }

    @Test
    void testSetRightSignalTwice() {
        Round round = new Round()
        round.setSignalRight(Signal.GREEN)
        try {
            round.setSignalRight(Signal.GREEN)
            Assert.assertTrue(false, "Exception should be thrown")
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage() == "Right Signal can be set only once")
        }
    }

    @Test
    void testSetRoundScoreTwice() {
        Round round = new Round()
        int score0Left = 0
        int score0Right = 1
        RoundScore roundScore0 = new RoundScore(score0Left, score0Right)
        round.setSignalRight(Signal.GREEN)
        round.setSignalLeft(Signal.GREEN)

        round.setRoundScore(roundScore0)
        def actualLeftScore = round.getScoreLeft()
        def actualRightScore = round.getScoreRight()
        Assert.assertTrue(actualLeftScore == score0Left, "score left: ${actualLeftScore} expected: ${score0Left}")
        Assert.assertTrue(actualRightScore == score0Right, "score right: ${actualRightScore} expected: ${score0Right}")
        RoundScore roundScore1 = new RoundScore(2, 3)
        try {
            round.setRoundScore(roundScore1)
            Assert.assertTrue(false, "Exception should be thrown")
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage() == "Round is already finished", "${e.getMessage()}")
        }
    }
}

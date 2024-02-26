package game.base.signal

import org.testng.Assert
import org.testng.annotations.Test

class SignalsScoreBaseTest {

    @Test
    void testPositive() {
        def testData = [
                [Signal.GREEN, Signal.GREEN, 5, 5],
                [Signal.RED, Signal.RED, 1, 1],
                [Signal.GREEN, Signal.RED, -2, 2],
                [Signal.RED, Signal.GREEN, 2, -2]
        ]
        for (data in testData) {
            //  println(data)
            def result = new SignalsScoreBase().calculateScore(data[0], data[1])
            Assert.assertTrue(result.scoreLeft == data[2], "score left: ${result.scoreLeft} expected: ${data[2]}")
            Assert.assertTrue(result.scoreRight == data[3], "score right: ${result.scoreRight} expected: ${data[3]}")
        }
    }

    @Test
    void testNegative() {
        def testData = [
                [Signal.GREEN, null, "Signals can not be null left:"],
                [null, Signal.GREEN, "Signals can not be null left:"],
                [null, null, "Signals can not be null left:"],
        ]

        for (data in testData) {
         //   println(data)
            try {
                RoundScore result = new SignalsScoreBase().calculateScore(data[0], data[1])
                Assert.assertTrue(false, "Exception should be thrown")
            } catch (Exception e) {
                Assert.assertTrue(e.getMessage().contains(data[2]))
            }
        }
    }
}

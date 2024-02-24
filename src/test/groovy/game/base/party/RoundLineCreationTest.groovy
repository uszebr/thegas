package game.base.party

import org.testng.Assert
import org.testng.annotations.Test

class RoundLineCreationTest {
    @Test
    void testPositive() {
        def testData = [
                [min: 100, max: 200, expectedMin: 100, expectedMax: 200],
                [min: 200, max: 200, expectedMin: 200, expectedMax: 200],
                [min: 0, max: 0, expectedMin: 0, expectedMax: 0],
                [min: 1, max: 1, expectedMin: 1, expectedMax: 1],
                [min: null, max: 200, expectedMin: RoundLine.MIN_PARTY_ROUNDS_DEFAULT, expectedMax: 200],
                [min: 100, max: null, expectedMin: 100, expectedMax: RoundLine.MAX_PARTY_ROUNDS_DEFAULT]
        ]
        for (test in testData) {
            RoundLine roundLine = new RoundLine(test.min, test.max)
            Assert.assertTrue(roundLine.minPartyRounds == test.expectedMin)
            Assert.assertTrue(roundLine.maxPartyRounds == test.expectedMax)
        }
    }

    @Test
    void testNegative() {
        def testData = [
                [min: -10, max: 200, message: "min and max party rounds must be positive"],
                [min: -10, max: -200, message: "min and max party rounds must be positive"],
                [min: 100, max: -200, message: "min and max party rounds must be positive"],
                [min: 100, max: 30, message: "min party rounds must be less or equals than max party rounds"],
                [min: 10000, max: 9999, message: "min party rounds must be less or equals than max party rounds"],
                [min: 30, max: 0, message: "min party rounds must be less or equals than max party rounds"],
        ]
        for (test in testData) {
            def messageExpected = test.message
            try {
                RoundLine roundLine = new RoundLine(test.min, test.max)
                Assert.assertTrue(false, "Exception should be thrown")
            } catch (Exception e) {
                def messageActual = e.getMessage()
                Assert.assertTrue(messageActual == messageExpected)
            }
        }
    }

    @Test
    void testNumberOfRounds() {
        //number of generated rounds is within Min Max interval
        def testData = [
                [min: 100, max: 200],
                [min: 1, max: 2],
                [min: 1, max: 1],
                [min: 0, max: 0],
                [min: 120, max: 120],
                [min: 120, max: 121],
                [min: 99999 , max: 99999],
        ]
        for (test in testData) {
            RoundLine roundLine = new RoundLine(test.min, test.max)
            Assert.assertTrue(roundLine.minPartyRounds >= test.min)
            Assert.assertTrue(roundLine.maxPartyRounds <= test.max)
        }
    }


}

package game.base.party

import org.testng.Assert
import org.testng.annotations.Test

class RoundLineIterationTest {

    @Test
    void testPositive() {
        def testData = [
                [min: 100, max: 200,],
                [min: 1000, max: 1001,],
                [min: 100, max: 200,],
                [min: 200, max: 200,],
                [min: 0, max: 0,],
                [min: 1, max: 1,],
                [min: RoundLine.MIN_PARTY_ROUNDS_DEFAULT, max: 200,],
                [min: RoundLine.MIN_PARTY_ROUNDS_DEFAULT, max: RoundLine.MIN_PARTY_ROUNDS_DEFAULT,],
                [min: 100, max: RoundLine.MAX_PARTY_ROUNDS_DEFAULT],
                [min: RoundLine.MAX_PARTY_ROUNDS_DEFAULT, max: RoundLine.MAX_PARTY_ROUNDS_DEFAULT],
                [min: RoundLine.MIN_PARTY_ROUNDS_DEFAULT, max: RoundLine.MAX_PARTY_ROUNDS_DEFAULT,]

        ]
        for (test in testData) {
            RoundLine roundLine = new RoundLine(test.min, test.max)
            def iterator = roundLine.iterator()
            int i = 0
            while (iterator.hasNext()) {
                def round = iterator.next()
                i++
            }
            Assert.assertTrue(i >= test.min, "min: ${test.min} max: ${test.max} i: ${i}")
            Assert.assertTrue(i <= test.max, "min: ${test.min} max: ${test.max} i: ${i}")
        }
    }

}

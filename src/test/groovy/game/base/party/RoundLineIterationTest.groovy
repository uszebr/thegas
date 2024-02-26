package game.base.party


import org.testng.Assert
import org.testng.annotations.Test

class RoundLineIterationTest {

    @Test
    void testPositive() {
        def testData = [
                [min: 100, max: 200],
                [min: 1000, max: 1001],
                [min: 100, max: 200],
                [min: 200, max: 200],
                [min: 200, max: 201],
                [min: 0, max: 0],
                [min: 1, max: 1],
                [min: RoundLine.MIN_PARTY_ROUNDS_DEFAULT, max: 200],
                [min: RoundLine.MIN_PARTY_ROUNDS_DEFAULT, max: RoundLine.MIN_PARTY_ROUNDS_DEFAULT],
                [min: 100, max: RoundLine.MAX_PARTY_ROUNDS_DEFAULT],
                [min: RoundLine.MAX_PARTY_ROUNDS_DEFAULT, max: RoundLine.MAX_PARTY_ROUNDS_DEFAULT],
                [min: RoundLine.MIN_PARTY_ROUNDS_DEFAULT, max: RoundLine.MAX_PARTY_ROUNDS_DEFAULT]

        ]
        for (test in testData) {
            RoundLine roundLine = new RoundLine(test.min, test.max)
            def meta = roundLine.metaClass
            def privateRoundQuantity = meta.getProperty(roundLine, "roundQuantity")

            Assert.assertTrue(privateRoundQuantity >= test.min, "min: ${test.min} max: ${test.max} privateRoundQuantity: ${privateRoundQuantity}")
            Assert.assertTrue(privateRoundQuantity <= test.max, "min: ${test.min} max: ${test.max} privateRoundQuantity: ${privateRoundQuantity}")
        }
    }

}

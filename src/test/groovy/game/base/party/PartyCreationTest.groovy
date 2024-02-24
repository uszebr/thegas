package game.base.party

import org.testng.annotations.Test

class PartyCreationTest {

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
    }
}
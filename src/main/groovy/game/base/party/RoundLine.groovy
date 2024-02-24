package game.base.party

import game.base.round.Round

/**
 * Can set max and min quantity of rounds in the RoundLine, every time party will have random rounds quantity
 */
class RoundLine implements Iterable<Round> {
    Round[] rounds

    static final int MIN_PARTY_ROUNDS_DEFAULT = 180
    static final int MAX_PARTY_ROUNDS_DEFAULT = 220
    static final Random random = new Random()

    Integer minPartyRounds
    Integer maxPartyRounds

    // not shown to player
    private Integer roundQuantity
    private Integer currentRoundIndex


    RoundLine(Integer minPartyRounds, Integer maxPartyRounds) {
        this.minPartyRounds = minPartyRounds != null ? minPartyRounds : MIN_PARTY_ROUNDS_DEFAULT
        this.maxPartyRounds = maxPartyRounds != null ? maxPartyRounds : MAX_PARTY_ROUNDS_DEFAULT
        if (this.minPartyRounds < 0 || this.maxPartyRounds < 0) {
            throw new IllegalArgumentException("min and max party rounds must be positive")
        }
        if (this.minPartyRounds > this.maxPartyRounds) {
            throw new IllegalArgumentException("min party rounds must be less or equals than max party rounds")
        }
        setUp()
    }

    void setUp() {
        roundQuantity = generateRoundQuantity()
        currentRoundIndex = 0
        rounds = new Round[roundQuantity]
        // filling up rounds
        for (int i = 0; i < roundQuantity; i++) {
            rounds[i] = new Round()
        }
    }


    Integer getCurrentRoundIndex() {
        return currentRoundIndex
    }

    private generateRoundQuantity() {
        if (minPartyRounds == maxPartyRounds) {
            return  minPartyRounds
        }
        roundQuantity = random.nextInt(maxPartyRounds - minPartyRounds) + minPartyRounds
    }

    @Override
    Iterator<Round> iterator() {
        return rounds.iterator()
    }

}

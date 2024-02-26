package game.base.party
/**
 * Can set max and min quantity of rounds in the RoundLine, every time party will have random rounds quantity
 */
class RoundLine {
    List<Round> rounds

    static final int MIN_PARTY_ROUNDS_DEFAULT = 180
    static final int MAX_PARTY_ROUNDS_DEFAULT = 220
    static final Random random = new Random()

    // Included and min and max
    Integer minPartyRounds
    Integer maxPartyRounds

    // not shown to player
    //todo switch to private after debug!!
    private Integer roundQuantity


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
        rounds = new ArrayList<>()
    }

    boolean canGetRound() {
        return rounds.size() < roundQuantity
    }

    Round getNewRound() {
        if (!canGetRound()) {
            throw new Exception("Party can't get new round. Rounds quantity: [${rounds.size()}] rounds possible: [${roundQuantity}]")
        }
        if (rounds.size() > 0 && !rounds.last().isRoundFinished()) {
            throw new Exception("Party can't get new round. Last round is not finished")
        }
        Round round = new Round()
        rounds.add(round)
        return round
    }


    private generateRoundQuantity() {
        if (minPartyRounds == maxPartyRounds) {
            return minPartyRounds
        }
        roundQuantity = random.nextInt(maxPartyRounds - minPartyRounds + 1) + minPartyRounds
    }

    String preparePrintableResult() {
        StringBuilder sb0 = new StringBuilder()
        StringBuilder sb1 = new StringBuilder()
        for (Round round in rounds) {
            sb0.append(round.signalsToString())
            sb0.append('|')
            sb1.append(round.scoresToString())
            sb1.append('|')
        }
        return sb0.toString() + '\n' + sb1.toString()
    }

    List<Round> getUnmodifiedRounds() {
        return Collections.unmodifiableList(this.rounds)
    }
}

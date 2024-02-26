package game.base.party

import org.testng.Assert
import org.testng.annotations.Test
import player.Player

class RoundLinePrintingTest {

    @Test
    void testPrintResult() {

        Player playerOne = new Player()
        Player playerTwo = new Player()

        RoundLine roundLine = new RoundLine(10, 15)
        Party party = new Party(playerOne, playerTwo, roundLine)

        Assert.assertFalse(party.isPartyFinished())
        party.playParty()
        def meta = roundLine.metaClass
        Integer privateRoundQuantity = (Integer) meta.getProperty(roundLine, "roundQuantity")
//        println("RoundQuantity: ${roundLine.roundQuantity}")
//        println("RoundQuantityPrivate: ${privateRoundQuantity}")
//        println("Roundssize: ${roundLine.rounds.size()}")
        Assert.assertTrue(privateRoundQuantity == roundLine.rounds.size(), "RoundQuantity: ${privateRoundQuantity} Roundssize: ${roundLine.rounds.size()}")
        String roundLineStr = roundLine.preparePrintableResult()

        // Checking separator quantity in roundLineStr
        int count = (roundLineStr =~ /\|/).size()
        Assert.assertTrue(count == privateRoundQuantity * 2, "count: ${count} roundQuantity: ${privateRoundQuantity}")
       // println(roundLineStr)
    }
}

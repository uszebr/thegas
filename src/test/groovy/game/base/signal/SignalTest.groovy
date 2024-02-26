package game.base.signal

import org.junit.Assert
import org.testng.annotations.Test

class SignalTest {

    @Test
    void test() {
        def signal = Signal.GREEN
        Assert.assertTrue(signal.toString()=="+")
        def signal1 = Signal.RED
        Assert.assertTrue(signal1.toString()=="^")
    }
}

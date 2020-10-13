import org.junit.Assert;
import org.junit.Test;

public class MathTest {

    @Test
    public void shouldSwapSign(){
        int a = 1;
        a = Math.negateExact(a);
        Assert.assertEquals(a, -1);
        a = Math.negateExact(a);
        Assert.assertEquals(a, 1);
    }

}

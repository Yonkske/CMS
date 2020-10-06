package backend;


import org.junit.Assert;
import org.junit.Test;

public class BackendTest {

    @Test
    public void test1() {
        Assert.assertEquals(3, new Backend().add(1,2));
    }

}

package backend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BackendTest {

    @Test
    public void testAdd() {
        Assertions.assertEquals(3, new Backend().add(1,2));
    }

    @Test
    public void testAdd1() {
        Assertions.assertNotEquals(2, new Backend().add(1,2));
    }
}

package lancereally;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringBufferAndCharArrayTest {
    static StringBuffer str = new StringBuffer("We Are Happy");
    @Test
    public void replaceSpace() {
        assertEquals("We%20Are%20Happy",StringBufferAndCharArray.replaceSpace(str));
    }
}
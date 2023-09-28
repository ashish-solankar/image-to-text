package com.imagetotext;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class MainTest {
    
    @Test
    public void checkFindTextWorking()
    {
        String text =  TextFinder.findText("/workspaces/image-to-text/finder/src/test/resources/test.png");
        assertNotNull(text);
    }
}

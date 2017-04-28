package uk.me.hofmann.permutator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CamelCaseStringShortenerTest {

    @Before
    public void setUp() {
    }

    @Test
    public void shorten() throws Exception {
        String result = CamelCaseStringShortener.shorten("CamelCaseStringShortener");

        Assert.assertEquals("CCSS", result);
    }

    @Test
    public void shortenEmptyInput() throws Exception {
        String result = CamelCaseStringShortener.shorten("");

        Assert.assertEquals("", result);
    }

    @Test
    public void shortenLowerCaseFirstChar() throws Exception {
        String result = CamelCaseStringShortener.shorten("camelCaseStringShortener");

        Assert.assertEquals("cCSS", result);
    }
}

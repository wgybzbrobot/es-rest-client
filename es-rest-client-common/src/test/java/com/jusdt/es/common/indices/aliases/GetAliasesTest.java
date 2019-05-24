package com.jusdt.es.common.indices.aliases;

import java.util.Arrays;
import org.junit.Test;

import com.jusdt.es.common.client.config.ElasticSearchVersion;
import com.jusdt.es.common.indices.aliases.GetAliases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class GetAliasesTest {

    @Test
    public void testBasicUriGeneration() {
        GetAliases getAliases = new GetAliases.Builder().addIndex("twitter").build();

        assertEquals("GET", getAliases.getRestMethodName());
        assertEquals("twitter/_alias", getAliases.getURI(ElasticSearchVersion.UNKNOWN));
    }

    @Test
    public void testBasicUriGenerationWithAliases() {
        GetAliases getAliases = new GetAliases.Builder().addIndex("twitter").addAlias("alias").build();

        assertEquals("GET", getAliases.getRestMethodName());
        assertEquals("twitter/_alias/alias", getAliases.getURI(ElasticSearchVersion.UNKNOWN));
    }

    @Test
    public void testBasicUriGenerationWithMultipleAliases() {
        GetAliases getAliases = new GetAliases.Builder().addIndex("twitter").addAliases(Arrays.asList(new String[]{"alias1", "alias2"})).build();

        assertEquals("GET", getAliases.getRestMethodName());
        assertEquals("twitter/_alias/alias1,alias2", getAliases.getURI(ElasticSearchVersion.UNKNOWN));
    }

    @Test
    public void equalsReturnsTrueForSameIndex() {
        GetAliases getAliases1 = new GetAliases.Builder().addIndex("twitter").build();
        GetAliases getAliases1Duplicate = new GetAliases.Builder().addIndex("twitter").build();

        assertEquals(getAliases1, getAliases1Duplicate);
    }

    @Test
    public void equalsReturnsFalseForDifferentIndex() {
        GetAliases getAliases1 = new GetAliases.Builder().addIndex("twitter").build();
        GetAliases getAliases2 = new GetAliases.Builder().addIndex("myspace").build();

        assertNotEquals(getAliases1, getAliases2);
    }

}
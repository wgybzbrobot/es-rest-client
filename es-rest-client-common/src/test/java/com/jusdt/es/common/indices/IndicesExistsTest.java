package com.jusdt.es.common.indices;

import org.junit.Test;

import com.jusdt.es.common.client.config.ElasticSearchVersion;
import com.jusdt.es.common.indices.IndicesExists;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author ferhat sobay
 */
public class IndicesExistsTest {

    @Test
    public void testBasicUriGeneration() {
        IndicesExists indicesExists = new IndicesExists.Builder("twitter").build();
        assertEquals("HEAD", indicesExists.getRestMethodName());
        assertEquals("twitter", indicesExists.getURI(ElasticSearchVersion.UNKNOWN));
    }

    @Test
    public void equalsReturnsTrueForSameDestination() {
        IndicesExists indicesExists1 = new IndicesExists.Builder("twitter").build();
        IndicesExists indicesExists1Duplicate = new IndicesExists.Builder("twitter").build();

        assertEquals(indicesExists1, indicesExists1Duplicate);
    }

    @Test
    public void equalsReturnsFalseForDifferentDestination() {
        IndicesExists indicesExists1 = new IndicesExists.Builder("twitter").build();
        IndicesExists indicesExists2 = new IndicesExists.Builder("myspace").build();

        assertNotEquals(indicesExists1, indicesExists2);
    }


}

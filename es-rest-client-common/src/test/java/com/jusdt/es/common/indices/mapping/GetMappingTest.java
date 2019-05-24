package com.jusdt.es.common.indices.mapping;

import org.junit.Test;

import com.jusdt.es.common.client.config.ElasticsearchVersion;
import com.jusdt.es.common.indices.mapping.GetMapping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class GetMappingTest {

    @Test
    public void testBasicUriGeneration() {
        GetMapping getMapping = new GetMapping.Builder().addIndex("twitter").build();

        assertEquals("GET", getMapping.getRestMethodName());
        assertEquals("twitter/_mapping", getMapping.getURI(ElasticsearchVersion.UNKNOWN));
    }

    @Test
    public void equalsReturnsTrueForSameIndex() {
        GetMapping getMapping1 = new GetMapping.Builder().addIndex("twitter").build();
        GetMapping getMapping1Duplicate = new GetMapping.Builder().addIndex("twitter").build();

        assertEquals(getMapping1, getMapping1Duplicate);
    }

    @Test
    public void equalsReturnsFalseForDifferentIndex() {
        GetMapping getMapping1 = new GetMapping.Builder().addIndex("twitter").build();
        GetMapping getMapping2 = new GetMapping.Builder().addIndex("myspace").build();

        assertNotEquals(getMapping1, getMapping2);
    }

}
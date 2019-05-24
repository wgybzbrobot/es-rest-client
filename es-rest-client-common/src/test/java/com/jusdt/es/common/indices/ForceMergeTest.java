package com.jusdt.es.common.indices;

import org.junit.Test;

import com.jusdt.es.common.client.config.ElasticsearchVersion;
import com.jusdt.es.common.indices.ForceMerge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ForceMergeTest {

    @Test
    public void testBasicUriGeneration() {
    	ForceMerge forceMerge = new ForceMerge.Builder().addIndex("twitter").build();

        assertEquals("POST", forceMerge.getRestMethodName());
        assertEquals("twitter/_forcemerge", forceMerge.getURI(ElasticsearchVersion.UNKNOWN));
    }

    @Test
    public void equalsReturnsTrueForSameIndex() {
        ForceMerge forceMerge1 = new ForceMerge.Builder().addIndex("twitter").build();
        ForceMerge forceMerge1Duplicate = new ForceMerge.Builder().addIndex("twitter").build();

        assertEquals(forceMerge1, forceMerge1Duplicate);
    }

    @Test
    public void equalsReturnsFalseForDifferentIndex() {
        ForceMerge forceMerge1 = new ForceMerge.Builder().addIndex("twitter").build();
        ForceMerge forceMerge2 = new ForceMerge.Builder().addIndex("myspace").build();

        assertNotEquals(forceMerge1, forceMerge2);
    }

}
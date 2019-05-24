package com.jusdt.es.common.core;

import org.junit.Test;

import com.jusdt.es.common.client.config.ElasticSearchVersion;
import com.jusdt.es.common.core.Cat;

import static org.junit.Assert.assertEquals;

public class CatAllocationBuilderTest {
    @Test
    public void shouldSetApplicationJsonHeader() {
        Cat cat = new Cat.AllocationBuilder().build();
        assertEquals("application/json", cat.getHeader("accept"));
        assertEquals("application/json", cat.getHeader("content-type"));
    }

    @Test
    public void shouldGenerateValidUriWhenIndexNotGiven() {
        Cat cat = new Cat.AllocationBuilder().build();
        assertEquals("_cat/allocation", cat.getURI(ElasticSearchVersion.UNKNOWN));
    }

    @Test
    public void shouldGenerateValidUriWhenSingleNodeGiven() {
        Cat cat = new Cat.AllocationBuilder().addNode("testNode").build();
        assertEquals("_cat/allocation/testNode", cat.getURI(ElasticSearchVersion.UNKNOWN));
    }

    @Test
    public void shouldGenerateValidUriWhenNodesGiven() {
        Cat cat = new Cat.AllocationBuilder().addNode("testNode1").addNode("testNode2").build();
        assertEquals("_cat/allocation/testNode1%2CtestNode2", cat.getURI(ElasticSearchVersion.UNKNOWN));
    }

    @Test
    public void shouldGenerateValidUriWhenParameterGiven() {
        Cat cat = new Cat.AllocationBuilder().setParameter("v", "true").build();
        assertEquals("_cat/allocation?v=true", cat.getURI(ElasticSearchVersion.UNKNOWN));
    }

    @Test
    public void shouldGenerateValidUriWhenHeadersParameterGiven() {
        Cat cat = new Cat.AllocationBuilder().setParameter("h", "shards,disk.indices,disk.used").build();
        assertEquals("_cat/allocation?h=shards%2Cdisk.indices%2Cdisk.used", cat.getURI(ElasticSearchVersion.UNKNOWN));
    }
}

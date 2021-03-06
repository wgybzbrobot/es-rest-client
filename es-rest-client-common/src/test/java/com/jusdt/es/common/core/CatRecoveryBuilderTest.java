package com.jusdt.es.common.core;

import org.junit.Test;

import com.jusdt.es.common.client.config.ElasticSearchVersion;
import com.jusdt.es.common.core.Cat;

import static org.junit.Assert.assertEquals;

public class CatRecoveryBuilderTest {
    @Test
    public void shouldSetApplicationJsonHeader() {
        Cat cat = new Cat.RecoveryBuilder().build();
        assertEquals("application/json", cat.getHeader("accept"));
        assertEquals("application/json", cat.getHeader("content-type"));
    }

    @Test
    public void shouldGenerateValidUriWhenIndexNotGiven() {
        Cat cat = new Cat.RecoveryBuilder().build();
        assertEquals("_cat/recovery/_all", cat.getURI(ElasticSearchVersion.UNKNOWN));
    }

    @Test
    public void shouldGenerateValidUriWhenIndexGiven() {
        Cat cat = new Cat.RecoveryBuilder().addIndex("testIndex").build();
        assertEquals("_cat/recovery/testIndex", cat.getURI(ElasticSearchVersion.UNKNOWN));
    }

    @Test
    public void shouldGenerateValidUriWhenParameterGiven() {
        Cat cat = new Cat.RecoveryBuilder().setParameter("v", "true").build();
        assertEquals("_cat/recovery/_all?v=true", cat.getURI(ElasticSearchVersion.UNKNOWN));
    }
}

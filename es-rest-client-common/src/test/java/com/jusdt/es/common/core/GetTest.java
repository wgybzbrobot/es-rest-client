package com.jusdt.es.common.core;

import org.junit.Test;

import com.jusdt.es.common.client.config.ElasticSearchVersion;
import com.jusdt.es.common.core.Get;

import static org.junit.Assert.assertEquals;

/**
 * @author Dogukan Sonmez
 */


public class GetTest {

    @Test
    public void getDocument() {
        Get get =  new Get.Builder("twitter", "1").type("tweet").build();
        assertEquals("GET", get.getRestMethodName());
        assertEquals("twitter/tweet/1", get.getURI(ElasticSearchVersion.UNKNOWN));
    }
}

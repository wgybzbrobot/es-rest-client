package com.jusdt.es.common.core;

import org.junit.Test;

import com.jusdt.es.common.client.config.ElasticSearchVersion;
import com.jusdt.es.common.core.Update;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Dogukan Sonmez
 */
public class UpdateTest {

    @Test
    public void updateDocumentWithoutDoc(){
        Update update = new Update.Builder(new Object()).index("twitter").type("tweet").id("1").build();
        assertEquals("POST", update.getRestMethodName());
        assertEquals("twitter/tweet/1/_update", update.getURI(ElasticSearchVersion.UNKNOWN));
    }

    @Test
    public void equalsReturnsTrueForSamePayload(){
        Update update1 = new Update.Builder("payload1").index("twitter").type("tweet").id("1").build();
        Update update1Duplicate = new Update.Builder("payload1").index("twitter").type("tweet").id("1").build();

        assertEquals(update1, update1Duplicate);
    }

    @Test
    public void equalsReturnsFalseForDifferentPayload(){
        Update update1 = new Update.Builder("payload1").index("twitter").type("tweet").id("1").build();
        Update update2 = new Update.Builder("payload2").index("twitter").type("tweet").id("1").build();

        assertNotEquals(update1, update2);
    }

}

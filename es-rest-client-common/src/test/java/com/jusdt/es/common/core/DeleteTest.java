package com.jusdt.es.common.core;

import org.junit.Test;

import com.jusdt.es.common.client.config.ElasticsearchVersion;
import com.jusdt.es.common.core.Delete;
import com.jusdt.es.common.params.Parameters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Dogukan Sonmez
 */


public class DeleteTest {

    @Test
    public void equals() {
        Delete deleteTweet = new Delete.Builder("1")
                .index("twitter")
                .type("tweet")
                .build();
        Delete deleteTweetDuplicate = new Delete.Builder("1")
                .index("twitter")
                .type("tweet")
                .build();
        assertEquals(deleteTweet, deleteTweetDuplicate);
    }

    @Test
    public void equalsReturnsFalseForDifferentIds() {
        Delete deleteFirstTweet = new Delete.Builder("1")
                .index("twitter")
                .type("tweet")
                .build();
        Delete deleteThirdTweet = new Delete.Builder("3")
                .index("twitter")
                .type("tweet")
                .build();
        assertNotEquals(deleteFirstTweet, deleteThirdTweet);
    }

    @Test
    public void deleteDocument() {
        Delete delete = new Delete.Builder("1")
                .index("twitter")
                .type("tweet")
                .build();
        assertEquals("DELETE", delete.getRestMethodName());
        assertEquals("twitter/tweet/1", delete.getURI(ElasticsearchVersion.UNKNOWN));
    }

    @Test
    public void deleteDocumentWithVersion() {
        Delete delete = new Delete.Builder("1")
                .index("twitter")
                .type("tweet")
                .setParameter(Parameters.VERSION, 1)
                .build();
        assertEquals("DELETE", delete.getRestMethodName());
        assertEquals("twitter/tweet/1?version=1", delete.getURI(ElasticsearchVersion.UNKNOWN));
    }
}

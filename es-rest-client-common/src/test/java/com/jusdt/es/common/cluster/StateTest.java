package com.jusdt.es.common.cluster;

import org.junit.Test;

import com.jusdt.es.common.client.config.ElasticSearchVersion;
import com.jusdt.es.common.cluster.State;

import static org.junit.Assert.assertEquals;

/**
 * @author cihat keser
 */
public class StateTest {

    @Test
    public void testUriGeneration() {
        State action = new State.Builder().build();
        assertEquals("/_cluster/state", action.getURI(ElasticSearchVersion.UNKNOWN));
    }

    @Test
    public void testUriGenerationWithOptionalFields() {
        State action = new State.Builder()
                .withBlocks()
                .withMetadata()
                .build();
        assertEquals("/_cluster/state/blocks,metadata", action.getURI(ElasticSearchVersion.UNKNOWN));
    }

}

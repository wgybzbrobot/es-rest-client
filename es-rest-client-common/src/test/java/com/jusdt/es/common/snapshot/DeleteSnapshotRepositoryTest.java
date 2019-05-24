package com.jusdt.es.common.snapshot;

import org.junit.Test;

import com.jusdt.es.common.client.config.ElasticSearchVersion;
import com.jusdt.es.common.snapshot.DeleteSnapshotRepository;

import static org.junit.Assert.assertEquals;

/**
 * @author happyprg(hongsgo@gmail.com)
 */
public class DeleteSnapshotRepositoryTest {
    @Test
    public void testRepository() {
        String repository = "leeseohoo";

        DeleteSnapshotRepository deleteSnapshotRepository = new DeleteSnapshotRepository.Builder(repository).build();
        assertEquals("DELETE", deleteSnapshotRepository.getRestMethodName());
        assertEquals("/_snapshot/leeseohoo", deleteSnapshotRepository.getURI(ElasticSearchVersion.UNKNOWN));
    }
}

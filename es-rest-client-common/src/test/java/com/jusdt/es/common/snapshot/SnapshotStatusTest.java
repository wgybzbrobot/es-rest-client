package com.jusdt.es.common.snapshot;

import org.junit.Test;

import com.jusdt.es.common.client.config.ElasticSearchVersion;
import com.jusdt.es.common.snapshot.SnapshotStatus;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * @author happyprg(hongsgo@gmail.com)
 */
public class SnapshotStatusTest {

    private String repository = "leeseohoo";
    private String snapshot = "leeseola";
    private String snapshot2 = "kangsungjeon";

    @Test
    public void testSnapshotSingleName() {
        SnapshotStatus snapshotStatus = new SnapshotStatus.Builder(repository).addSnapshot(snapshot).build();
        assertEquals("GET", snapshotStatus.getRestMethodName());
        assertEquals("/_snapshot/leeseohoo/leeseola/_status", snapshotStatus.getURI(ElasticSearchVersion.UNKNOWN));
    }

    @Test
    public void testSnapshotMultipleNames() {
        SnapshotStatus snapshotStatus = new SnapshotStatus.Builder(repository).addSnapshot(Arrays.asList(snapshot, snapshot2)).build();
        assertEquals("/_snapshot/leeseohoo/leeseola,kangsungjeon/_status", snapshotStatus.getURI(ElasticSearchVersion.UNKNOWN));
    }
}

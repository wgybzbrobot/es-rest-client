package com.jusdt.es.common.cluster;

import com.google.gson.Gson;
import com.jusdt.es.common.client.config.ElasticSearchVersion;
import com.jusdt.es.common.cluster.Reroute;
import com.jusdt.es.common.cluster.reroute.RerouteAllocateReplica;
import com.jusdt.es.common.cluster.reroute.RerouteCancel;
import com.jusdt.es.common.cluster.reroute.RerouteCommand;
import com.jusdt.es.common.cluster.reroute.RerouteMove;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RerouteTest {

    @Test
    public void reroute() throws JSONException {
        List<RerouteCommand> moveCommands = new LinkedList<RerouteCommand>();
        moveCommands.add(new RerouteMove("index1", 1, "node1", "node2"));
        moveCommands.add(new RerouteCancel("index2", 1, "node2", true));
        moveCommands.add(new RerouteAllocateReplica("index3", 1, "node3"));

        Reroute reroute = new Reroute.Builder(moveCommands).build();
        assertEquals("/_cluster/reroute", reroute.getURI(ElasticSearchVersion.UNKNOWN));
        assertEquals("POST", reroute.getRestMethodName());

        String expectedData = "{ \"commands\": [" +
                "{ \"move\": { \"index\": \"index1\", \"shard\": 1, \"from_node\": \"node1\", \"to_node\": \"node2\" } }, " +
                "{ \"cancel\": { \"index\": \"index2\", \"shard\": 1, \"node\": \"node2\", \"allow_primary\": true } }," +
                "{ \"allocate_replica\": { \"index\": \"index3\", \"shard\": 1, \"node\": \"node3\" } }" +
                "] }";
        JSONAssert.assertEquals(expectedData, reroute.getData(new Gson()), false);
    }

}
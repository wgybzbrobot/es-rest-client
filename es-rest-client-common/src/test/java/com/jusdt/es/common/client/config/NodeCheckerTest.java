package com.jusdt.es.common.client.config;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.jusdt.es.common.action.Action;
import com.jusdt.es.common.client.ESClient;
import com.jusdt.es.common.client.QueryResult;
import com.jusdt.es.common.client.config.ClientConfig;
import com.jusdt.es.common.client.config.CouldNotConnectException;
import com.jusdt.es.common.client.config.NodeChecker;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class NodeCheckerTest {

    ClientConfig clientConfig;
    ESClient jestClient;

    @Before
    public void setUp() throws Exception {
        clientConfig = new ClientConfig.Builder("http://localhost:9200")
                .discoveryEnabled(true)
                .discoveryFrequency(1l, TimeUnit.SECONDS)
                .build();

        jestClient = mock(ESClient.class);
    }

    @Test
    public void testWithResolvedWithoutHostnameAddressWithCustomScheme() throws Exception {
        NodeChecker nodeChecker = new NodeChecker(jestClient, new ClientConfig.Builder("http://localhost:9200")
                .discoveryEnabled(true)
                .discoveryFrequency(1l, TimeUnit.SECONDS)
                .defaultSchemeForDiscoveredNodes("https")
                .build());

        QueryResult result = new QueryResult(new Gson());
        result.setJsonMap(ImmutableMap.<String, Object>of(
                "ok", "true",
                "nodes", ImmutableMap.of(
                        "node_name", ImmutableMap.of(
                                "http_address", "inet[/192.168.2.7:9200]"
                        )
                )
        ));
        result.setSucceeded(true);
        when(jestClient.execute(isA(Action.class))).thenReturn(result);

        nodeChecker.runOneIteration();

        verify(jestClient).execute(isA(Action.class));
        ArgumentCaptor<LinkedHashSet> argument = ArgumentCaptor.forClass(LinkedHashSet.class);
        verify(jestClient).setServers(argument.capture());
        verifyNoMoreInteractions(jestClient);

        Set servers = argument.getValue();
        assertEquals(1, servers.size());
        assertEquals("https://192.168.2.7:9200", servers.iterator().next());
    }

    @Test
    public void testEsVersion5() throws Exception {
        NodeChecker nodeChecker = new NodeChecker(jestClient, clientConfig);

        QueryResult result = new QueryResult(new Gson());
        result.setJsonMap(ImmutableMap.<String, Object>of(
                "ok", "true",
                "nodes", ImmutableMap.of(
                        "node_name", ImmutableMap.of(
                                "version",      "5.0.1",
                                "http",             ImmutableMap.of("publish_address",  "192.168.2.7:9200")
                        )
                )
        ));
        result.setSucceeded(true);
        when(jestClient.execute(isA(Action.class))).thenReturn(result);

        nodeChecker.runOneIteration();

        verify(jestClient).execute(isA(Action.class));
        ArgumentCaptor<LinkedHashSet> argument = ArgumentCaptor.forClass(LinkedHashSet.class);
        verify(jestClient).setServers(argument.capture());
        verifyNoMoreInteractions(jestClient);

        Set servers = argument.getValue();
        assertEquals(1, servers.size());
        assertEquals("http://192.168.2.7:9200", servers.iterator().next());
    }

    @Test
    public void testWithResolvedWithoutHostnameAddress() throws Exception {
        NodeChecker nodeChecker = new NodeChecker(jestClient, clientConfig);

        QueryResult result = new QueryResult(new Gson());
        result.setJsonMap(ImmutableMap.<String, Object>of(
                "ok", "true",
                "nodes", ImmutableMap.of(
                        "node_name", ImmutableMap.of(
                                "http_address", "inet[/192.168.2.7:9200]"
                        )
                )
        ));
        result.setSucceeded(true);
        when(jestClient.execute(isA(Action.class))).thenReturn(result);

        nodeChecker.runOneIteration();

        verify(jestClient).execute(isA(Action.class));
        ArgumentCaptor<LinkedHashSet> argument = ArgumentCaptor.forClass(LinkedHashSet.class);
        verify(jestClient).setServers(argument.capture());
        verifyNoMoreInteractions(jestClient);

        Set servers = argument.getValue();
        assertEquals(1, servers.size());
        assertEquals("http://192.168.2.7:9200", servers.iterator().next());
    }

    @Test
    public void testWithResolvedWithHostnameAddress() throws Exception {
        NodeChecker nodeChecker = new NodeChecker(jestClient, clientConfig);

        QueryResult result = new QueryResult(new Gson());
        result.setJsonMap(ImmutableMap.<String, Object>of(
                "ok", "true",
                "nodes", ImmutableMap.of(
                        "node_name", ImmutableMap.of(
                                "http_address", "inet[searchly.com/192.168.2.7:9200]"
                        )
                )
        ));
        result.setSucceeded(true);
        when(jestClient.execute(isA(Action.class))).thenReturn(result);

        nodeChecker.runOneIteration();

        verify(jestClient).execute(isA(Action.class));
        ArgumentCaptor<LinkedHashSet> argument = ArgumentCaptor.forClass(LinkedHashSet.class);
        verify(jestClient).setServers(argument.capture());
        verifyNoMoreInteractions(jestClient);

        Set servers = argument.getValue();
        assertEquals(1, servers.size());
        assertEquals("http://192.168.2.7:9200", servers.iterator().next());
    }

    @Test
    public void testWithUnresolvedAddress() throws Exception {
        NodeChecker nodeChecker = new NodeChecker(jestClient, clientConfig);

        QueryResult result = new QueryResult(new Gson());
        result.setJsonMap(ImmutableMap.<String, Object>of(
                "ok", "true",
                "nodes", ImmutableMap.of(
                        "node_name", ImmutableMap.of(
                                "http_address", "inet[192.168.2.7:9200]"
                        )
                )
        ));
        result.setSucceeded(true);
        when(jestClient.execute(isA(Action.class))).thenReturn(result);

        nodeChecker.runOneIteration();

        verify(jestClient).execute(isA(Action.class));
        ArgumentCaptor<LinkedHashSet> argument = ArgumentCaptor.forClass(LinkedHashSet.class);
        verify(jestClient).setServers(argument.capture());
        verifyNoMoreInteractions(jestClient);

        Set servers = argument.getValue();
        assertEquals(1, servers.size());
        assertEquals("http://192.168.2.7:9200", servers.iterator().next());
    }

    @Test
    public void testWithInvalidUnresolvedAddress() throws Exception {
        NodeChecker nodeChecker = new NodeChecker(jestClient, clientConfig);

        QueryResult result = new QueryResult(new Gson());
        result.setJsonMap(ImmutableMap.<String, Object>of(
                "ok", "true",
                "nodes", ImmutableMap.of(
                        "node_name", ImmutableMap.of(
                                "http_address", "inet[192.168.2.7:]"
                        )
                )
        ));
        result.setSucceeded(true);
        when(jestClient.execute(isA(Action.class))).thenReturn(result);

        nodeChecker.runOneIteration();

        verify(jestClient).execute(isA(Action.class));
        ArgumentCaptor<LinkedHashSet> argument = ArgumentCaptor.forClass(LinkedHashSet.class);
        verify(jestClient).setServers(argument.capture());
        verifyNoMoreInteractions(jestClient);

        Set servers = argument.getValue();
        assertEquals(0, servers.size());
    }

    @Test
    public void testWithInvalidResolvedAddress() throws Exception {
        NodeChecker nodeChecker = new NodeChecker(jestClient, clientConfig);

        QueryResult result = new QueryResult(new Gson());
        result.setJsonMap(ImmutableMap.<String, Object>of(
                "ok", "true",
                "nodes", ImmutableMap.of(
                        "node_name", ImmutableMap.of(
                                "http_address", "inet[gg/192.168.2.7:]"
                        )
                )
        ));
        result.setSucceeded(true);
        when(jestClient.execute(isA(Action.class))).thenReturn(result);

        nodeChecker.runOneIteration();

        verify(jestClient).execute(isA(Action.class));
        ArgumentCaptor<LinkedHashSet> argument = ArgumentCaptor.forClass(LinkedHashSet.class);
        verify(jestClient).setServers(argument.capture());
        verifyNoMoreInteractions(jestClient);

        Set servers = argument.getValue();
        assertEquals(0, servers.size());
    }

    @Test
    public void testNodesInfoExceptionUsesBootstrapServerList() throws Exception {
        NodeChecker nodeChecker = new NodeChecker(jestClient, clientConfig);

        when(jestClient.execute(isA(Action.class))).thenThrow(Exception.class);

        nodeChecker.runOneIteration();

        verify(jestClient).execute(isA(Action.class));
        ArgumentCaptor<LinkedHashSet> argument = ArgumentCaptor.forClass(LinkedHashSet.class);
        verify(jestClient).setServers(argument.capture());
        verifyNoMoreInteractions(jestClient);

        Set servers = argument.getValue();
        assertEquals(1, servers.size());
        assertEquals("http://localhost:9200", servers.iterator().next());
    }

    @Test
    public void testNodesInfoFailureUsesBootstrapServerList() throws Exception {
        NodeChecker nodeChecker = new NodeChecker(jestClient, clientConfig);
        QueryResult result = new QueryResult(new Gson());
        result.setSucceeded(false);
        when(jestClient.execute(isA(Action.class))).thenReturn(result);

        nodeChecker.runOneIteration();

        verify(jestClient).execute(isA(Action.class));
        ArgumentCaptor<LinkedHashSet> argument = ArgumentCaptor.forClass(LinkedHashSet.class);
        verify(jestClient).setServers(argument.capture());
        verifyNoMoreInteractions(jestClient);

        Set servers = argument.getValue();
        assertEquals(1, servers.size());
        assertEquals("http://localhost:9200", servers.iterator().next());
    }

    @Test
    public void testNodesInfoExceptionRemovesServerFromList() throws Exception {
        NodeChecker nodeChecker = new NodeChecker(jestClient, clientConfig);

        QueryResult result = new QueryResult(new Gson());
        result.setJsonMap(ImmutableMap.<String, Object>of(
                "ok", "true",
                "nodes", ImmutableMap.of(
                        "node1", ImmutableMap.of(
                                "http_address", "inet[/192.168.2.7:9200]"),
                        "node2", ImmutableMap.of(
                                "http_address", "inet[/192.168.2.8:9200]"),
                        "node3", ImmutableMap.of(
                                "http_address", "inet[/192.168.2.9:9200]"))));
        result.setSucceeded(true);
        when(jestClient.execute(isA(Action.class))).thenReturn(result);
        nodeChecker.runOneIteration();

        verify(jestClient).execute(isA(Action.class));
        ArgumentCaptor<LinkedHashSet> argument = ArgumentCaptor.forClass(LinkedHashSet.class);
        verify(jestClient).setServers(argument.capture());
        verify(jestClient).execute(isA(Action.class));

        Set servers = argument.getValue();
        assertEquals(3, servers.size());

//      	when(jestClient.execute(isA(Action.class))).thenThrow(new HttpHostConnectException(
//            new ConnectException(), new HttpHost("192.168.2.7", 9200, "http"), null));
        when(jestClient.execute(isA(Action.class))).thenThrow(new CouldNotConnectException("http://192.168.2.7:9200", new IOException("Test HttpHostException")));
        nodeChecker.runOneIteration();

        verify(jestClient, times(2)).execute(isA(Action.class));
        verify(jestClient, times(2)).setServers(argument.capture());
        verifyNoMoreInteractions(jestClient);

        servers = argument.getValue();
        assertEquals(2, servers.size());
        Iterator serversItr = servers.iterator();
        assertEquals("http://192.168.2.8:9200", serversItr.next());
        assertEquals("http://192.168.2.9:9200", serversItr.next());

        // fail at the 2nd node
        //      when(jestClient.execute(isA(Action.class))).thenThrow(new HttpHostConnectException(
        //            new ConnectException(), new HttpHost("192.168.2.8", 9200, "http"), null));
        when(jestClient.execute(isA(Action.class))).thenThrow(new CouldNotConnectException("http://192.168.2.8:9200", new IOException("Test HttpHostException")));
        nodeChecker.runOneIteration();

        verify(jestClient, times(3)).execute(isA(Action.class));
        verify(jestClient, times(3)).setServers(argument.capture());
        verifyNoMoreInteractions(jestClient);

        servers = argument.getValue();
        assertEquals(1, servers.size());
        serversItr = servers.iterator();
        assertEquals("http://192.168.2.9:9200", serversItr.next());

        // fail at the last node, fail back to bootstrap
//      	when(jestClient.execute(isA(Action.class))).thenThrow(new HttpHostConnectException(
//            new ConnectException(), new HttpHost("192.168.2.9", 9200, "http"), null));
        when(jestClient.execute(isA(Action.class))).thenThrow(new CouldNotConnectException("http://192.168.2.9:9200", new IOException("Test HttpHostException")));
        nodeChecker.runOneIteration();

        verify(jestClient, times(4)).execute(isA(Action.class));
        verify(jestClient, times(4)).setServers(argument.capture());
        verifyNoMoreInteractions(jestClient);

        servers = argument.getValue();
        assertEquals(1, servers.size());
        serversItr = servers.iterator();
        assertEquals("http://localhost:9200", serversItr.next());
    }

    @Test
    public void testNodesInfoExceptionBeforeNodesDiscovered() throws Exception {
        NodeChecker nodeChecker = new NodeChecker(jestClient, clientConfig);
        when(jestClient.execute(isA(Action.class))).thenThrow(new CouldNotConnectException("http://localhost:9200", new IOException("Test HttpHostException")));

        nodeChecker.runOneIteration();

        assertNotNull(nodeChecker.discoveredServerList);
        assertEquals(0, nodeChecker.discoveredServerList.size());
	}

}

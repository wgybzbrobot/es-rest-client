package com.jusdt.es.common.cluster;

import org.junit.Test;

import com.jusdt.es.common.client.config.ElasticsearchVersion;
import com.jusdt.es.common.cluster.TasksInformation;

import static org.junit.Assert.*;

public class TasksInformationTest {

    @Test
    public void testUriGeneration() {
        TasksInformation action = new TasksInformation.Builder().build();
        assertEquals("_tasks", action.getURI(ElasticsearchVersion.UNKNOWN));
    }

    @Test
    public void testUriGenerationSpecificTask() {
        TasksInformation action = new TasksInformation.Builder().task("node_id:task_id").build();
        assertEquals("_tasks/node_id:task_id", action.getURI(ElasticsearchVersion.UNKNOWN));
    }
}
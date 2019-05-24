package com.jusdt.es.common.cluster.reroute;

import java.util.Map;

public interface RerouteCommand {
    String getType();

    Map<String, Object> getData();
}

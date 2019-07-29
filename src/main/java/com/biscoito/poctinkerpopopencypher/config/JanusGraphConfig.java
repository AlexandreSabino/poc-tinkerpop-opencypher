package com.biscoito.poctinkerpopopencypher.config;

import org.apache.tinkerpop.gremlin.process.traversal.AnonymousTraversalSource;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JanusGraphConfig {

    @Bean
    public GraphTraversalSource graph() throws Exception {
        return AnonymousTraversalSource.traversal().withRemote("conf/remote-graph.properties");
    }
}

package com.biscoito.poctinkerpopopencypher;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.opencypher.gremlin.client.CypherGremlinClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class PocTinkerpopOpencypherApplication {

    public static void main(String[] args) {
        SpringApplication.run(PocTinkerpopOpencypherApplication.class, args);
    }

    @Autowired
    private GraphTraversalSource g;

    @PostConstruct
    public void test() {
        executeQuery();
        executeQuery();
        executeQuery();
    }

    private void executeQuery() {
        BaseConfiguration baseConfiguration = new BaseConfiguration();
        baseConfiguration.setProperty("hosts", "localhost");
        baseConfiguration.setProperty("port", 8182);

        Cluster cluster = Cluster.open(baseConfiguration);
        final Client client = cluster.connect();
        String cypher = "MATCH (g:god)-[:father]-(n) RETURN g.name as nameFather, n.name as nameSon";


        CypherGremlinClient translatingGremlinClient = CypherGremlinClient.translating(client);
        List<Map<String, Object>> gremlinResults = translatingGremlinClient.submit(cypher).all();
        System.out.println(gremlinResults);
    }
}

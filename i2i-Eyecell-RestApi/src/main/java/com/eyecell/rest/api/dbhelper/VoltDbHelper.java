package com.eyecell.rest.api.dbhelper;

import org.voltdb.client.Client;
import org.voltdb.client.ClientConfig;
import org.voltdb.client.ClientFactory;
import java.io.IOException;

public class VoltDbHelper {

    private final String dbUrl = "34.159.58.171";
    private final int port = 49153;
    public Client client() throws IOException {
        Client client;
        ClientConfig config;
        config = new ClientConfig("advent", "xYZZy");
        client = ClientFactory.createClient(config);
        client.createConnection(dbUrl, port);
        return client;

    }
}

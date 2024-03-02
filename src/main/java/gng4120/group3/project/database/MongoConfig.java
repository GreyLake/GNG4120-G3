package gng4120.group3.project.database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import java.util.Collection;
import java.util.Collections;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${MONGODB_DB}")
    private String DB;

    @Value("${MONGODB_HOST}")
    private String HOST;

    @Value("${MONGODB_PORT}")
    private String PORT;

    @Value("${MONGODB_USER}")
    private String USER;

    @Value("${MONGODB_PASSWORD}")
    private String PASS;

    @Override
    protected String getDatabaseName() {
        return DB;
    }

    @Override
    public MongoClient mongoClient() {
        /* This code apparently fails to connect to the database:
         * ConnectionString connectionString = new ConnectionString("mongodb://" + HOST + ":" + PORT + "/" + DB + "?authSource=admin");
         * MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
         *         .applyConnectionString(connectionString)
         *         .credential(MongoCredential.createCredential(USER, DB, PASS.toCharArray()))
         *         .build();
         */

        ConnectionString connectionString = new ConnectionString("mongodb://" + USER + ":" + PASS + "@" + HOST + ":" + PORT + "/" + DB + "?authSource=admin");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Override
    public Collection getMappingBasePackages() {
        return Collections.singleton("gng4120.group3.project");
    }
}
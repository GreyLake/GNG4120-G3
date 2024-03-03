package gng4120.group3.project.database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.MongoSocketException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.internal.MongoClientImpl;
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

    private boolean isMongoDBAvailable = false;

    @Override
    protected String getDatabaseName() {
        return DB;
    }

    @Override
    public MongoClient mongoClient() {

        /* This code apparently USED TO fail to connect to the database: */
        // Original: + "?authSource=admin"
        ConnectionString connectionString = new ConnectionString("mongodb://" + HOST + ":" + PORT + "/" + DB);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .credential(MongoCredential.createScramSha1Credential(USER, "admin", PASS.toCharArray()))
                .build();

        /* This code Works locally but not remotely.
        ConnectionString connectionString = new ConnectionString("mongodb://" + USER + ":" + PASS + "@" + HOST + ":" + PORT + "/" + DB + "?authSource=admin");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        */

        MongoClient mongoClient = null;
        try {
            mongoClient = MongoClients.create(mongoClientSettings);
            isMongoDBAvailable = true;
        } catch (MongoSocketException ignored){}
        return mongoClient;
    }

    public boolean isMongoDBAvailable(){
        return  isMongoDBAvailable;
    }

    @Override
    public Collection getMappingBasePackages() {
        return Collections.singleton("gng4120.group3.project");
    }
}
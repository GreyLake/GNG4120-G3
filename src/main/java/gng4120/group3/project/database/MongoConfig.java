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
        /* This code apparently fails to connect to the database:
         * ConnectionString connectionString = new ConnectionString("mongodb://" + HOST + ":" + PORT + "/" + DB + "?authSource=admin");
         * MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
         *         .applyConnectionString(connectionString)
         *         .credential(MongoCredential.createCredential(USER, DB, PASS.toCharArray()))
         *         .build();
         */

        MongoClient mongoClient = null;
        try {
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString("mongodb://" + HOST + ":" + PORT))
                    .build();
            mongoClient = MongoClients.create(settings);
            // Attempt to connect to the database
            MongoDatabase db = mongoClient.getDatabase(DB);
            db.listCollectionNames().first(); // Try executing a simple operation
            isMongoDBAvailable = true;
        } catch (Exception e) {
            // Handle MongoDB not being available
            System.out.println("MongoDB is not available.");
            isMongoDBAvailable = false;
        } finally {
            if (mongoClient != null) {
                mongoClient.close();
            }
        }
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
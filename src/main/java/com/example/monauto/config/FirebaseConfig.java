package com.example.monauto.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

   /* @Value("${firebase.config.path}")
    private String configPath;

    @Value("${firebase.storage.bucket-name}")
    private String bucketName;*/
   @Value("${FIREBASE_CONFIG_JSON}")
   private String firebaseConfigJson;

    @Value("${FIREBASE_STORAGE_BUCKET}")
    private String bucketName;

    @PostConstruct
    public void initialize() throws IOException {
        /*InputStream serviceAccount = getClass().getClassLoader()
                .getResourceAsStream(configPath);*/
        InputStream serviceAccount = new ByteArrayInputStream(firebaseConfigJson.getBytes());

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket(bucketName)
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
    }

    @Bean
    public StorageClient storageClient() {
        return StorageClient.getInstance();
    }
}

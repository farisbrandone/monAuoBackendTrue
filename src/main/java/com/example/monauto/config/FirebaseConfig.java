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

    @Value("${FIREBASE_PROJECT_ID}")
    private String projectId;

    @Value("${FIREBASE_CLIENT_EMAIL}")
    private String clientEmail;

    @Value("${FIREBASE_CLIENT_ID}")
    private String clientId;

    @Value("${FIREBASE_AUTH_URI}")
    private String authUri;

    @Value("${FIREBASE_TOKEN_URI}")
    private String tokenUri;

    @Value("${FIREBASE_AUTH_PROVIDER}")
    private String authProvider;

    @Value("${FIREBASE_CLIENT_X509}")
    private String clientX509;

    @Value("${FIREBASE_PRIVATE_KEY}")
    private String privateKey;

    @Value("${FIREBASE_PRIVATE_KEY_ID}")
    private String privateKeyId;

    @Value("${FIREBASE_STORAGE_BUCKET}")
    private String bucketName;

    @PostConstruct
    public void initialize() throws IOException {
        // Créez le JSON de configuration dynamiquement
        String firebaseConfigJson = String.format(
                "{\"type\":\"service_account\",\"project_id\":\"%s\",\"private_key\":\"%s\",\"client_email\":\"%s\",\"client_id\":\"%s\",\"private_key_id\":\"%s\",\"auth_uri\":\"%s\",\"token_uri\":\"%s\",\"auth_provider_x509_cert_url\":\"%s\",\"client_x509_cert_url\":\"%s\",\"universe_domain\":\"googleapis.com\"}",
                projectId,
                privateKey.replace("\\n", "\n"),  // Important pour les sauts de ligne
                clientEmail,
                clientId,
                privateKeyId,
                authUri,
                tokenUri,
                authProvider,
                clientX509

        );
System.out.println(firebaseConfigJson);
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


package com.example.monauto.service;

import com.example.monauto.utils.GetPathFromUrl;
import com.google.cloud.storage.Blob;
import com.google.firebase.cloud.StorageClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class StorageService implements IStorageService {

    @Value("${firebase.storage.bucket-name}")
    private String bucketName;

    private final StorageClient storageClient;

    public StorageService(StorageClient storageClient) {
        this.storageClient = storageClient;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Blob blob = storageClient.bucket(bucketName)
                .create(fileName, file.getBytes(), file.getContentType());
        return blob.signUrl(365, TimeUnit.DAYS).toString();
    }

    public byte[] downloadFile(String fileName) {
        Blob blob = storageClient.bucket(bucketName).get(fileName);
        return blob.getContent();
    }

    public boolean deleteFile(String fileName) {
        String path= GetPathFromUrl.extractPathFromUrl(fileName);
        System.out.println(path);
      return  storageClient.bucket(bucketName).get(path).delete();
    }

    public Map<String, String> uploadMultipleFiles(MultipartFile[] files) throws IOException {
        Map<String, String> results = new HashMap<>();

        for (MultipartFile file : files) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Blob blob = storageClient.bucket(bucketName)
                    .create(fileName, file.getBytes(), file.getContentType());
            String url = blob.signUrl(365, TimeUnit.DAYS).toString();
            results.put(file.getOriginalFilename(), url);
        }

        return results;
    }
}

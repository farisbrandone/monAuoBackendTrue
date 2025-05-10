package com.example.monauto.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface IStorageService {
    public String uploadFile(MultipartFile file) throws IOException;
    public boolean deleteFile(String fileName);
    public byte[] downloadFile(String fileName);
    public Map<String, String> uploadMultipleFiles(MultipartFile[] files) throws IOException;
}

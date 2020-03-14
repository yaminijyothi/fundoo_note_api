package com.bridgelabz.fundoonotes.controller;
/*
 * author :yamini
 * */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoonotes.implementation.AmazonS3ClientServiceImpl;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProfileController {

    @Autowired
    private AmazonS3ClientServiceImpl amazonS3ClientService;
    
   //upload file
    @PostMapping("/file/upload")
    public Map<String, String> uploadFile(@RequestPart(value = "file") MultipartFile file)
    {
        this.amazonS3ClientService.uploadFileToS3Bucket(file, true);

        Map<String, String> response = new HashMap<>();
        response.put("message", "file [" + file.getOriginalFilename() + "] uploading request submitted successfully.");

        return response;
    }

    @DeleteMapping("file/remove")
    public Map<String, String> deleteFile(@RequestParam("file_name") String fileName)
    {
        this.amazonS3ClientService.deleteFileFromS3Bucket(fileName);

        Map<String, String> response = new HashMap<>();
        response.put("message", "file [" + fileName + "] removing request submitted successfully.");

        return response;
    }
}
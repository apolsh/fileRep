package com.wasd.filerep.storage;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

public interface ContentStorage {

    void saveFile(String bucketName, String fileName, InputStream fileStream, long fileSize);

    InputStream downloadFile(String bucketName, String fileName);

    void deleteFile(String bucketName, String fileName);

    List<String> getBucketNames();

    void createBucket(String bucketName);

    void removeBucket(String bucketName);

}

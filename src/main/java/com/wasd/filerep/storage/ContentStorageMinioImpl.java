package com.wasd.filerep.storage;

import io.minio.*;
import io.minio.messages.Bucket;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ContentStorageMinioImpl implements ContentStorage {

    MinioClient minioClient;

    @Autowired
    public ContentStorageMinioImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public void saveFile(String bucketName, String fileName, InputStream fileStream, long fileSize){
        PutObjectArgs objectArgs = PutObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .stream(fileStream, fileSize,-1 )
                .build();

        try{
            minioClient.putObject(objectArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public InputStream downloadFile(String bucketName, String fileName){
        GetObjectArgs getObjectArgs = GetObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName.toString())
                .build();

        InputStream object = null;
        try {
            object = minioClient.getObject(getObjectArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    public void deleteFile(String bucketName, String fileName){
        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName.toString())
                    .build());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<String> getBucketNames(){
        List<String> bucketNames = new ArrayList<>();
        try{
            List<Bucket> buckets = minioClient.listBuckets();
            for(Bucket bucket : buckets){
                bucketNames.add(bucket.name());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return bucketNames;
    }

    public void createBucket(String bucketName){
        try{
            minioClient.makeBucket(
                    MakeBucketArgs.builder()
                            .bucket(bucketName)
                            .build());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeBucket(String bucketName){
        try {
            minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

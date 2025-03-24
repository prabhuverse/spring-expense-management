package com.expense.mgmt.infrastructure.repository.s3;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import com.expense.mgmt.domain.model.dto.spring.FileInfo;
import com.expense.mgmt.domain.model.repository.ExpenseFileObjectRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3ExpenseFileRepository implements ExpenseFileObjectRepository {

    private final S3AsyncClient s3AsyncClient;

    private final static String BUCKET = "spring-expense-management-pr";


    @Override
    public FileInfo uploadFile(String expenseFile, MultipartFile file) {
        String fileName = expenseFile;
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(BUCKET)
                .key(fileName)
                .contentType(file.getContentType())
                .build();

        try {
            PutObjectResponse objResponse = s3AsyncClient.putObject(
                    putObjectRequest,
                    AsyncRequestBody.fromBytes(file.getBytes())
            ).join();
            return FileInfo.builder().name(fileName).path(fileName).size(Long.valueOf(file.getSize()).intValue()).type(file.getContentType()).build();
        } catch (Exception e) {
            log.error("unable to file {} cause ", expenseFile, e);
        }
        return null;
    }
}

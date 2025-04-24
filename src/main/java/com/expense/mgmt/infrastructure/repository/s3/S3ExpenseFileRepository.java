package com.expense.mgmt.infrastructure.repository.s3;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.core.async.AsyncResponseTransformer;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import com.expense.mgmt.domain.model.dto.FileInfo;
import com.expense.mgmt.domain.model.dto.FileStreamInfo;
import com.expense.mgmt.domain.model.repository.ExpenseFileObjectRepository;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.InputStream;
import java.util.concurrent.CompletableFuture;

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

    @SneakyThrows
    public FileStreamInfo downloadFile(String path) {
        HeadObjectRequest headObjectRequest = HeadObjectRequest.builder()
                .bucket(BUCKET)
                .key(path)
                .build();

        HeadObjectResponse response = s3AsyncClient.headObject(headObjectRequest).get();

        GetObjectRequest request = GetObjectRequest.builder()
                .key(path).bucket(BUCKET)
                .build();

        ResponseBytes stream = s3AsyncClient.getObject(request, AsyncResponseTransformer.toBytes()).get();
        return FileStreamInfo.builder()
                .bytes(stream.asByteArray()).path(path)
                .contentLength(response.contentLength().intValue())
                .contentType(response.contentType())
                .build();
    }


    @SneakyThrows
    public Mono<FileStreamInfo> downloadFileAsync(String path) {
        HeadObjectRequest headObjectRequest = HeadObjectRequest.builder()
                .bucket(BUCKET)
                .key(path)
                .build();

        GetObjectRequest request = GetObjectRequest.builder()
                .key(path).bucket(BUCKET)
                .build();


        Mono<HeadObjectResponse> headMono = Mono.fromFuture(() -> s3AsyncClient.headObject(headObjectRequest));

        Mono<ResponseBytes> getMono = Mono.fromFuture(() -> s3AsyncClient.getObject(request, AsyncResponseTransformer.toBytes()));

        return Mono.zip(headMono, getMono).map(tuple -> {

            HeadObjectResponse head = tuple.getT1();
            ResponseBytes<GetObjectResponse> stream = tuple.getT2();
            return FileStreamInfo.builder()
                    .path(path)
                    .contentLength(head.contentLength().intValue())
                    .contentType(head.contentType())
                    .bytes(stream.asByteArray())
                    .build();
        });
    }
}

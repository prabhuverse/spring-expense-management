package com.expense.mgmt.domain.model.repository;

import com.expense.mgmt.domain.model.dto.FileInfo;
import com.expense.mgmt.domain.model.dto.FileStreamInfo;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

public interface ExpenseFileObjectRepository {

    FileInfo uploadFile(String expenseFile, MultipartFile file);

    FileStreamInfo downloadFile(String path);

    Mono<FileStreamInfo> downloadFileAsync(String path);
}

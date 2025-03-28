package com.expense.mgmt.domain.model.repository;

import com.expense.mgmt.domain.model.dto.spring.FileInfo;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

public interface ExpenseFileObjectRepository {

    Mono<FileInfo> uploadFile(String expenseFile, MultipartFile file);
}

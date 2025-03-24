package com.expense.mgmt.domain.model.repository;

import com.expense.mgmt.domain.model.dto.spring.FileInfo;
import org.springframework.web.multipart.MultipartFile;

public interface ExpenseFileObjectRepository {

    FileInfo uploadFile(String expenseFile, MultipartFile file);
}

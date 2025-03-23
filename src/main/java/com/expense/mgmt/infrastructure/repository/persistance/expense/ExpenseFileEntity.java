package com.expense.mgmt.infrastructure.repository.persistance.expense;


import com.expense.mgmt.domain.model.dto.spring.FileInfo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;


@Entity
@Table(name = "expense_file")
public class ExpenseFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(nullable = false, columnDefinition = "json")
    private FileInfo info;

    @Version
    @Column
    private Integer version;

    @CreationTimestamp
    @Column
    private LocalDateTime createdOn;

    @UpdateTimestamp
    @Column
    private LocalDateTime updateOn;

}

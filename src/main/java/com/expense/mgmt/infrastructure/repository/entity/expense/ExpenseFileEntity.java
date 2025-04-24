package com.expense.mgmt.infrastructure.repository.entity.expense;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.expense.mgmt.domain.model.dto.FileInfo;
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

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "ExpenseFileEntity")
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
    @Column(name = "createdOn", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDateTime createdOn;

    @UpdateTimestamp
    @Column(name = "updatedOn", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDateTime updatedOn;

}

package com.expense.mgmt.infrastructure.repository.persistance.expense;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.expense.mgmt.domain.model.dto.spring.FileInfo;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Table("expense_file")
public class ExpenseFileEntity {

    @Id
    private Long id;

    @Column
    private FileInfo info;

    @Column
    private Integer version;

    @Column("created_on")
    private LocalDateTime createdOn;

    @Column("updated_on")
    private LocalDateTime updatedOn;

}

package com.expense.mgmt.infrastructure.repository.persistance.expense;


import com.expense.mgmt.domain.model.ExpenseCategory;
import com.expense.mgmt.infrastructure.repository.persistance.group.GroupEntity;
import com.expense.mgmt.infrastructure.repository.persistance.user.UserEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table("expenses")
public class ExpenseEntity {

    @Id
    private Long id;

    @Column
    private ExpenseCategory category;

    @Column("created_on")
    private LocalDate createdOn;

    @Column
    private String description;

    @Column
    private BigDecimal amount;


    //@JsonIgnore
    @MappedCollection(idColumn = "user_id")
    private UserEntity user;

    @MappedCollection(idColumn = "file_id")
    private ExpenseFileEntity file;


    @Column
    private Integer version;

    //@ManyToOne
    @MappedCollection(idColumn = "group_id")
    private GroupEntity group;
}

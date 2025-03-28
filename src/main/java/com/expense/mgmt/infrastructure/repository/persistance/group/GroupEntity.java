package com.expense.mgmt.infrastructure.repository.persistance.group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.expense.mgmt.infrastructure.repository.persistance.expense.ExpenseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table("group")
public class GroupEntity {

    @Id
    private Long id;

    @Column
    private String name;

    @Column("created_on")
    private LocalDateTime createdOn;

    @JsonIgnore
    @MappedCollection(idColumn = "expense_id")
    private List<ExpenseEntity> expenses;

    @Column
    private Integer version;

}

package com.expense.mgmt.infrastructure.repository.persistance.user;


import com.expense.mgmt.infrastructure.repository.persistance.expense.ExpenseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.AllArgsConstructor;


import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString(exclude = {"expenses"})
@Table(name = "expenses")
public class UserEntity {

    @Id
    private Long id;

    @Column("email")
    private String email;

    @Column
    private String password;

    @Column
    private String name;

    @JsonIgnore
    @MappedCollection(idColumn = "user_id")
    private List<ExpenseEntity> expenses;

    @Column("created_on")
    private LocalDateTime createdOn;

    @Column("updated_on")
    private LocalDateTime updateOn;

    @Column("version")
    private Integer version;
}


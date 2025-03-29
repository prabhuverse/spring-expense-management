package com.expense.mgmt.infrastructure.repository.entity.group;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.expense.mgmt.infrastructure.repository.entity.expense.ExpenseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"expenses"})
@Table(name = "expense_group")
@Entity(name = "GroupEntity")
@EqualsAndHashCode(exclude = "expenses")
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false, length = 50)
    private String name;

    @Column(name = "created_on", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    @CreationTimestamp
    private LocalDateTime createdOn;

    //@JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    private List<ExpenseEntity> expenses = new ArrayList<>();

    @Version
    @Column
    private Integer version;
}

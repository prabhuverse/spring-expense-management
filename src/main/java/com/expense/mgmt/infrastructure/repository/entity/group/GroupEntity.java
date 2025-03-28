package com.expense.mgmt.infrastructure.repository.entity.group;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.expense.mgmt.infrastructure.repository.entity.expense.ExpenseEntity;
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
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "expense_group")
@Entity(name = "group")
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false, length = 50)
    private String name;

    @Column(name = "created_on", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    @CreationTimestamp
    private LocalDateTime createdOn;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    private List<ExpenseEntity> expenses;

    @Version
    @Column
    private Integer version;
}

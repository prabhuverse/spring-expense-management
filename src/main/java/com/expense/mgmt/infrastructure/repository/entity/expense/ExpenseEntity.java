package com.expense.mgmt.infrastructure.repository.entity.expense;


import com.expense.mgmt.domain.model.ExpenseCategory;
import com.expense.mgmt.infrastructure.repository.entity.group.GroupEntity;
import com.expense.mgmt.infrastructure.repository.entity.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.AllArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "ExpenseEntity")
@Table(name = "expenses")
@Getter
@Setter
public class ExpenseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ExpenseCategory category;

    @CreationTimestamp
    @Column(name = "createdOn", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate createdOn;

    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @Column(nullable = false, precision = 10, scale = 3)
    private BigDecimal amount;


    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id")
    private ExpenseFileEntity file;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupEntity group;

    @Version
    @Column
    private Integer version;


}

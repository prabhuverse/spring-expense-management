package com.expense.mgmt.infrastructure.repository.entity.user;


import com.expense.mgmt.infrastructure.repository.entity.expense.ExpenseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Version;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import lombok.AllArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "UserEntity")
@Getter
@Setter
@Builder
@ToString(exclude = {"expenses"})
@EqualsAndHashCode(exclude = {"expenses"})
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, updatable = false)
    private String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<ExpenseEntity> expenses;

    @CreationTimestamp
    @Column
    private LocalDateTime createdOn;

    @UpdateTimestamp
    @Column
    private LocalDateTime updateOn;

    @Column
    private LocalDate dateOfBirth;

    @Version
    @Column
    private Integer version;
}


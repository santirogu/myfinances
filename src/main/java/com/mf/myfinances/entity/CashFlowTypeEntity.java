package com.mf.myfinances.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;

@Entity
@Table
@AllArgsConstructor(staticName = "builder")
@NoArgsConstructor
@Getter
@Setter
public class CashFlowTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cashFlowTypeId;
    @Column(name = "name", nullable = false, length = 60)
    private String name;
}

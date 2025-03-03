package com.otcp.accounting.user.entity;

import com.otcp.accounting.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"user\"")
public class User extends BaseEntity {
    private String username;
}

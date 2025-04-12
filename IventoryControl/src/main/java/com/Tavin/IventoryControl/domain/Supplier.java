package com.Tavin.IventoryControl.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tb_supplier")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String phone;

    private String email;

    private String cnpj;

    @CreatedDate
    private LocalDate created;

    @LastModifiedDate
    private LocalDate updated;
}

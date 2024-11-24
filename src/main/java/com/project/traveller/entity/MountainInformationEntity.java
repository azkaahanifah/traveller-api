package com.project.traveller.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "mountain")
@Getter
@Setter
public class MountainInformationEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String mountainName;

    @Column(name = "description")
    private String description;

    @Column(name = "location")
    private String location;

    @Column(name = "mdpl")
    private Integer mdpl;

    @Column(name = "rate")
    private String rate;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at")
    private OffsetDateTime modifiedAt;
}

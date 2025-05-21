package com.example.monauto.entity;

import com.example.monauto.enumFile.RoleUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data @NoArgsConstructor
@AllArgsConstructor @Builder
public class Role {

    @Id
    private String id;

    // other fields

    /*@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/
    @Enumerated(EnumType.STRING)
    private RoleUser roleName;

    public void MyEntity() {
        this.id = UUID.randomUUID().toString();
    }
}

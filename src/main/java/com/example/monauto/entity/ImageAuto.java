package com.example.monauto.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor @Builder
public class ImageAuto {

    @Id
    private String id;
    /*@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/
    @Column(length = 1000)
    private String url;
    @ManyToOne
    @JsonIgnore
    private Auto auto;

    public void MyEntity() {
        this.id = UUID.randomUUID().toString();
    }
}

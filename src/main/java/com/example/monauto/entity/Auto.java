package com.example.monauto.entity;

import com.example.monauto.enumFile.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@Builder
public class Auto {

    @Id
    private String id;

    // other fields


   /* @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/

    private String code;
    @Column(length = 1000)
    private String carteGriseUrl;
    @Column(length = 1000)
    private String pvControleTechniqueUrl;
    private Float prix;

    @Enumerated(EnumType.STRING)
    private Devise devise;
    private String marques;
    private String typesCarrosserie;
    private Date anneeDeFabrication;
    private String categorie;
    @Enumerated(EnumType.STRING)
    private TypeCarburant typeCarburant;
    @Enumerated(EnumType.STRING)
    private TypeMoteur typeMoteur;
    private Date dateOfCreated;
    private Date dateOfModified;
    private Float kilometrage;
    private String kilometrageUnit;
    @Enumerated(EnumType.STRING)
    private TypeTransmission typeTransmission;
    private Date lastMaintenanceDate;
    private String typeDeTrainConducteur;
    private int nbreDePlace;
    private int nbreDePorte;
    @Enumerated(EnumType.STRING)
    private StatusOfAuto statusOfAuto;
    private String villeDuBien;
    private String paysDuBien;
    @ManyToOne
    private  Seller seller;
    private boolean acceptsTerms;
    private String immatriculation;
    private String model;
    private String couleurExt;
    private String couleurInt;
    private Float conso100kmAutoRoute;
    private Float conso100kmVille;
    private Float tailleDuMoteur;
    @Column(length = 10000)
    private String descriptionAuto;
   private String climatisation;
    @OneToMany(mappedBy = "auto", cascade = CascadeType.ALL)
    private Collection<ImageAuto> imagesAuto=new ArrayList<>();


    public void MyEntity() {
        this.id = UUID.randomUUID().toString();
    }


}

package com.example.monauto.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
@Data @NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {

    @NotBlank(message = "Nom is required")
    private String nom;
    @NotBlank(message = "Prenom is required")
    private String prenom;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    private String telephone;
    private String adresse;
    private String description;
    private Boolean activeState;
    //@NotBlank(message = "Ville requis")
    private String ville;
    //@NotBlank(message = "Pays requis")
    private String country;
    private String telephoneWhatsapp;
    private String typeSeller;
    private String typeSellerIdentificationDoc;
    private String identificationDocumentFile;
    private String dateOfModified;

}

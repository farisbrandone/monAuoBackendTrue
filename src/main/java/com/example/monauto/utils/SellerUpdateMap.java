package com.example.monauto.utils;

import com.example.monauto.DTO.AutoUpdateDto;
import com.example.monauto.DTO.UserUpdateDTO;
import com.example.monauto.entity.Auto;
import com.example.monauto.entity.Seller;
import com.example.monauto.enumFile.*;

import java.time.Instant;

public class SellerUpdateMap {
    private Seller seller;
    private UserUpdateDTO sellerDTO;

    public SellerUpdateMap(Seller seller, UserUpdateDTO sellerDTO) {
        this.seller = seller;
        this.sellerDTO = sellerDTO;
    }

    public  Seller getSellerWithSellerUpdateDto() {
        seller.setNom(sellerDTO.getNom());
        seller.setPrenom(sellerDTO.getPrenom());
        seller.setEmail(sellerDTO.getEmail());
        seller.setTelephone(sellerDTO.getTelephone());
        seller.setTypeSeller(TypeSeller.valueOf(sellerDTO.getTypeSeller()));
        seller.setCountry(sellerDTO.getCountry());
        seller.setIdentificationDocumentFile(sellerDTO.getIdentificationDocumentFile());
        seller.setTelephoneWhatsapp(sellerDTO.getTelephoneWhatsapp());
        seller.setVille(sellerDTO.getVille());
        seller.setDescription(sellerDTO.getDescription());
        seller.setDateOfCreated(Instant.parse(sellerDTO.getDateOfModified()));
        seller.setActiveState(sellerDTO.getActiveState());
        seller.setTypeSellerIdentificationDoc(TypeSellerIdentificationDoc.valueOf(sellerDTO.getTypeSellerIdentificationDoc()));
       seller.setAdresse(sellerDTO.getAdresse());
        return this.seller;
    }
}

package com.example.monauto.service;

import com.example.monauto.DTO.AutoPostDto;
import com.example.monauto.DTO.AutoUpdateDto;
import com.example.monauto.entity.Auto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface IAutoService {
    public void initAuto();
    public void initSeller();
    public Auto addOneAuto(AutoPostDto auto);
    public Auto updateOneAuto(AutoUpdateDto auto);
    public Page<Auto> searchCars(
            String marque,
            String typeCarrosserie,
            String typeTransmission,
            Float kilometrageMin,
            Float kilometrageMax,
            String typeMoteur,
            String typeCarburant,
            String couleur,
            Float prixMin,
            Float prixMax,
            Date anneeMin,
            Date anneeMax,
            String ville,
            String keyword,
            Pageable pageable
    );
}

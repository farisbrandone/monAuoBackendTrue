package com.example.monauto.dao;

import com.example.monauto.entity.Auto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.math.BigDecimal;
import java.util.Date;


@RepositoryRestResource
@CrossOrigin("*")
public interface AutoRepository  extends JpaRepository<Auto, Long>, JpaSpecificationExecutor<Auto> {

    @Query("SELECT c FROM Auto c WHERE " +
            "(:marques IS NULL OR LOWER(c.marques) LIKE LOWER(CONCAT('%', :marques, '%'))) AND " +
            "(:typesCarrosserie IS NULL OR LOWER(c.typesCarrosserie) LIKE LOWER(CONCAT('%', :typesCarrosserie, '%'))) AND " +
            "(:typeTransmission IS NULL OR LOWER(c.typeTransmission) LIKE LOWER(CONCAT('%', :typeTransmission, '%'))) AND " +
            "(:kilometrageMin IS NULL OR c.kilometrage >= :kilometrageMin) AND " +
            "(:kilometrageMax IS NULL OR c.kilometrage <= :kilometrageMax) AND " +
            "(:typeMoteur IS NULL OR LOWER(c.typeMoteur.toString()) LIKE LOWER(CONCAT('%', :typeMoteur, '%'))) AND " +
            "(:typeCarburant IS NULL OR LOWER(c.typeCarburant.toString()) LIKE LOWER(CONCAT('%', :typeCarburant, '%'))) AND " +
            "(:selectedColor IS NULL OR LOWER(c.couleurExt) LIKE LOWER(CONCAT('%', :selectedColor, '%'))) AND " +
            "(:PrixMin IS NULL OR c.prix >= :PrixMin) AND " +
            "(:PrixMax IS NULL OR c.prix <= :PrixMax) AND " +
            "(:anneeMin IS NULL OR c.annee >= :anneeMin) AND " +  // New: Year filter (min)
            "(:anneeMax IS NULL OR c.annee <= :anneeMax) AND " +  // New: Year filter (max)
            "(:villeDuBien IS NULL OR LOWER(c.ville) LIKE LOWER(CONCAT('%', :villeDuBien, '%'))) AND " +  // New: City filter
            "(:keyword IS NULL OR " +
            "LOWER(c.marques) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.typesCarrosserie) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.typeTransmission) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.typeCarburant.toString()) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.typeMoteur.toString()) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Auto> searchCars(
            @Param("marques") String marques,
            @Param("typesCarrosserie") String typesCarrosserie,
            @Param("anneeMin") Date anneeMin,
            @Param("anneeMax") Date anneeMax,
            @Param("kilometrageMin") Float kilometrageMin,
            @Param("kilometrageMax") Float kilometrageMax,
            @Param("PrixMin") Float PrixMin,
            @Param("PrixMax") Float PrixMax,
            @Param("typeMoteur") String typeMoteur,
            @Param("typeCarburant") String typeCarburant,
            @Param("typeTransmission") String typeTransmission,
            @Param("selectedColor") String selectedColor,
            @Param("villeDuBienr") String villeDuBien,
            @Param("keyword") String keyword,
            Pageable pageable
    );

}

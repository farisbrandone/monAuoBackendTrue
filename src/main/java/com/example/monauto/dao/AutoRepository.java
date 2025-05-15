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
            "(:marques IS NULL OR c.marques LIKE %:marques%) AND " +
            "(:typesCarrosserie IS NULL OR c.typesCarrosserie LIKE %:typesCarrosserie%) AND " +
            "(:typeTransmission IS NULL OR c.typeTransmission LIKE %:typeTransmission%) AND " +
            "(:kilometrageMin IS NULL OR c.kilometrage >= CAST(:kilometrageMin AS float)) AND " +
            "(:kilometrageMax IS NULL OR c.kilometrage <= CAST(:kilometrageMax AS float)) AND " +
            "(:typeMoteur IS NULL OR c.typeMoteur.toString() LIKE %:typeMoteur%) AND " +
            "(:typeCarburant IS NULL OR c.typeCarburant.toString() LIKE %:typeCarburant%) AND " +
            "(:selectedColor IS NULL OR c.couleurExt LIKE %:selectedColor%) AND " +
            "(:PrixMin IS NULL OR c.prix >= CAST(:PrixMin AS float)) AND " +
            "(:PrixMax IS NULL OR c.prix <= CAST(:PrixMax AS float)) AND " +
            "(:keyword IS NULL OR " +
            " c.marques LIKE %:keyword% OR " +
            " c.typesCarrosserie LIKE %:keyword% OR " +
            " c.typeTransmission.toString() LIKE %:keyword% OR " +
            " c.typeCarburant.toString() LIKE %:keyword% OR " +
            " c.typeMoteur.toString() LIKE %:keyword%)")
    Page<Auto> searchCars( @Param("marques") String marques,
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
                           @Param("keyword") String keyword,
                           Pageable pageable);

}

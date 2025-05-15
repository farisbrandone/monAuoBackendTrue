package com.example.monauto.utils;

import com.example.monauto.entity.Auto;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class AutoSpecifications {

    // Filter by marque (brand)
    public static Specification<Auto> hasMarque(String marque) {
        return (root, query, cb) ->
                marque == null ? null : cb.like(cb.lower(root.get("marques")), "%" + marque.toLowerCase() + "%");
    }

    // Filter by carrosserie type
    public static Specification<Auto> hasTypeCarrosserie(String typeCarrosserie) {
        return (root, query, cb) ->
                typeCarrosserie == null ? null : cb.like(cb.lower(root.get("typesCarrosserie")), "%" + typeCarrosserie.toLowerCase() + "%");
    }

    // Filter by transmission type
    public static Specification<Auto> hasTypeTransmission(String typeTransmission) {
        return (root, query, cb) ->
                typeTransmission == null ? null : cb.like(cb.lower(root.get("typeTransmission")), "%" + typeTransmission.toLowerCase() + "%");
    }

    // Filter by min/max mileage
    public static Specification<Auto> hasKilometrageBetween(Float min, Float max) {
        return (root, query, cb) -> {
            if (min == null && max == null) return null;
            if (min == null) return cb.lessThanOrEqualTo(root.get("kilometrage"), max);
            if (max == null) return cb.greaterThanOrEqualTo(root.get("kilometrage"), min);
            return cb.between(root.get("kilometrage"), min, max);
        };
    }

    // Filter by motor type (enum)
    public static Specification<Auto> hasTypeMoteur(String typeMoteur) {
        return (root, query, cb) ->
                typeMoteur == null ? null : cb.equal(root.get("typeMoteur").as(String.class), typeMoteur.toUpperCase());
    }

    // Filter by fuel type (enum)
    public static Specification<Auto> hasTypeCarburant(String typeCarburant) {
        return (root, query, cb) ->
                typeCarburant == null ? null : cb.equal(root.get("typeCarburant").as(String.class), typeCarburant.toUpperCase());
    }

    // Filter by color
    public static Specification<Auto> hasCouleur(String couleur) {
        return (root, query, cb) ->
                couleur == null ? null : cb.like(cb.lower(root.get("couleurExt")), "%" + couleur.toLowerCase() + "%");
    }

    // Filter by price range
    public static Specification<Auto> hasPrixBetween(Float min, Float max) {
        return (root, query, cb) -> {
            if (min == null && max == null) return null;
            if (min == null) return cb.lessThanOrEqualTo(root.get("prix"), max);
            if (max == null) return cb.greaterThanOrEqualTo(root.get("prix"), min);
            return cb.between(root.get("prix"), min, max);
        };
    }

    // Filter by year range (Date)
    public static Specification<Auto> hasAnneeBetween(Date min, Date max) {
        return (root, query, cb) -> {
            if (min == null && max == null) return null;
            if (min == null) return cb.lessThanOrEqualTo(root.get("annee"), max);
            if (max == null) return cb.greaterThanOrEqualTo(root.get("annee"), min);
            return cb.between(root.get("annee"), min, max);
        };
    }

    // Filter by city
    public static Specification<Auto> hasVille(String ville) {
        return (root, query, cb) ->
                ville == null ? null : cb.like(cb.lower(root.get("ville")), "%" + ville.toLowerCase() + "%");
    }

    // Global keyword search (marque, typeCarrosserie, typeTransmission, etc.)
    public static Specification<Auto> hasKeyword(String keyword) {
        return (root, query, cb) -> {
            if (keyword == null) return null;
            String likeKeyword = "%" + keyword.toLowerCase() + "%";
            return cb.or(
                    cb.like(cb.lower(root.get("marques")), likeKeyword),
                    cb.like(cb.lower(root.get("typesCarrosserie")), likeKeyword),
                    cb.like(cb.lower(root.get("typeTransmission")), likeKeyword),
                    cb.like(root.get("typeCarburant").as(String.class), likeKeyword),
                    cb.like(root.get("typeMoteur").as(String.class), likeKeyword)
            );
        };
    }
}
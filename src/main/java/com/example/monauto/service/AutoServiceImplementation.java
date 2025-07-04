package com.example.monauto.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.monauto.DTO.AutoPostDto;
import com.example.monauto.DTO.AutoUpdateDto;
import com.example.monauto.DTO.ImageClass;
import com.example.monauto.dao.AutoRepository;
import com.example.monauto.dao.ImageAutoRepository;
import com.example.monauto.dao.SellerRepository;
import com.example.monauto.entity.Auto;
import com.example.monauto.entity.ImageAuto;
import com.example.monauto.entity.Seller;
import com.example.monauto.enumFile.TypeCarburant;
import com.example.monauto.randomGenerateValue.RandomString;
import com.example.monauto.sec.JwtUtils;
import com.example.monauto.utils.AutoMap;
import com.example.monauto.utils.AutoSpecifications;
import com.example.monauto.utils.AutoUpdateMap;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

@Service
@Transactional
public class AutoServiceImplementation implements IAutoService {

   private final AutoRepository autoRepository;
   private final SellerRepository sellerRepository;
   private final ImageAutoRepository imageAutoRepository;


    public AutoServiceImplementation(AutoRepository autoRepository, SellerRepository sellerRepository, AutoRepository autoRepository2, ImageAutoRepository imageAutoRepository) {
        this.autoRepository = autoRepository;
        this.sellerRepository = sellerRepository;

        this.imageAutoRepository = imageAutoRepository;
    }

    @Override
    public void initSeller() {
      /*  Stream.of("Faris", "Hedie", "Willy", "Ines", "Martial", "Doline").forEach(user -> {

            Seller seller = new Seller();
            RandomString randomString = new RandomString();
            seller.setNom(user);
            seller.setTypeSeller(randomString.RandomSellerMethod());
            sellerRepository.save(seller);
        });*/

    }

    @Override
    public Auto addOneAuto(AutoPostDto auto) {
        try {


            Auto auto1 = new Auto();
            auto1.MyEntity();
            AutoMap autoMap=new AutoMap(auto1, auto);
            Auto autoSend=autoMap.getAutoWithAutoPostDto();

            Collection<ImageAuto> imageAutoCollection=new ArrayList<>();
            Collection<ImageAuto> imageAutoCollection2=new ArrayList<>();
            System.out.println("doudou0");
            for (String imageAuto1 : auto.getImagesAuto()) {

                ImageAuto imageAuto=new ImageAuto();
                imageAuto.MyEntity();
                imageAuto.setUrl(imageAuto1);
                ImageAuto imageAuto2= imageAutoRepository.save(imageAuto);
                imageAutoCollection.add(imageAuto2);

            }
            System.out.println("doudou1");
            String myToken=auto.getUserToken();
            Algorithm algorithm = Algorithm.HMAC256(JwtUtils.SECRET);
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(myToken);
            String email = decodedJWT.getSubject();
            Seller seller=sellerRepository.findSellerByEmail(email);
            autoSend.setSeller(seller);
            System.out.println("doudou2");
            autoSend.setImagesAuto(imageAutoCollection);
            Auto myAuto=autoRepository.save(autoSend);
            System.out.println("doudou3");
            for (ImageAuto image : imageAutoCollection) {
                image.setAuto(myAuto);
                ImageAuto imageAuto2= imageAutoRepository.save(image);
                imageAutoCollection2.add(imageAuto2);

            }
            System.out.println("doudou4");
            myAuto.setImagesAuto(imageAutoCollection2);
            return autoRepository.save(myAuto);

        } catch (Exception e) {
            System.out.println("doudou-1");
            throw new RuntimeException(e);
        }


    }

    @Override
    public void initAuto() {
       /* sellerRepository.findAll().forEach(seller -> {
            Stream.of("Audi", "Toyota", "Mercedes", "BMW", "Honda", "Ford", "Renaud", "Citroen", "Nissan").forEach(auto -> {
                Auto autoEntity = new Auto();
                RandomString randomString = new RandomString();
                autoEntity.setMarques(auto);
                autoEntity.setTypeCarburant(randomString.RandomStringMethod());
                autoEntity.setSeller(seller);
                autoRepository.save(autoEntity);
            });
        });*/
    }


    @Override
    public Auto updateOneAuto(AutoUpdateDto auto) {
        System.out.println(auto.toString());
        Auto auto1 = autoRepository.findById(auto.getId()).orElse(null);
        AutoUpdateMap autoMap=new AutoUpdateMap(auto1, auto);
        Auto autoSend=autoMap.getAutoWithAutoPostDto();

        Collection<ImageAuto> imageAutoCollection=new ArrayList<>();
       /* Collection<ImageAuto> imageAutoCollection2=new ArrayList<>();
        Collection<ImageAuto> imageAutoCollection3=auto1.getImagesAuto();*/
        System.out.println("doudou"+"coco");
        assert auto1 != null;

        for (ImageAuto val : auto1.getImagesAuto()) {
           System.out.println(val.getUrl());
        }
        for (ImageAuto imageAuto : autoSend.getImagesAuto()) {

            ImageClass myAuto= Arrays.stream(auto.getImagesAuto()).filter(imageClass -> imageClass.getId().equals(imageAuto.getId())).findFirst().get();
            imageAuto.setUrl(myAuto.getUrl());
            System.out.println(imageAuto.getUrl()+"tonton");
            //ImageAuto imageAuto2= imageAutoRepository.save(imageAuto);
            imageAutoCollection.add(imageAuto);
        }


       /* for (String imageAuto1 : auto.getImagesAuto()) {

            ImageAuto imageAuto=new ImageAuto();
            imageAuto.setUrl(imageAuto1);
            ImageAuto imageAuto2= imageAutoRepository.save(imageAuto);
            imageAutoCollection.add(imageAuto2);

        }*/
        //String myToken=auto.getUserToken();
        //Algorithm algorithm = Algorithm.HMAC256(JwtUtils.SECRET);
        //JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        //DecodedJWT decodedJWT = jwtVerifier.verify(myToken);
        //String email = decodedJWT.getSubject();
        //Seller seller=sellerRepository.findSellerByEmail(email);
        System.out.println("seller"+"coco");
        //autoSend.setSeller(seller);
        autoSend.setImagesAuto(imageAutoCollection);
        System.out.println("nono");
        return autoRepository.save(autoSend);

        /*for (ImageAuto image : imageAutoCollection) {
            image.setAuto(myAuto);
            ImageAuto imageAuto2= imageAutoRepository.save(image);
            imageAutoCollection2.add(imageAuto2);

        }*/
       /* System.out.println(imageAutoCollection2+"vovo");
        myAuto.setImagesAuto(imageAutoCollection2);
        System.out.println(auto1.getImagesAuto());*/
        //return autoRepository.save(myAuto);

    }


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
    ) {

        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "dateOfCreated")
        );

        Specification<Auto> spec = Specification
                .where(AutoSpecifications.hasMarque(marque))
                .and(AutoSpecifications.hasTypeCarrosserie(typeCarrosserie))
                .and(AutoSpecifications.hasTypeTransmission(typeTransmission))
                .and(AutoSpecifications.hasKilometrageBetween(kilometrageMin, kilometrageMax))
                .and(AutoSpecifications.hasTypeMoteur(typeMoteur))
                .and(AutoSpecifications.hasTypeCarburant(typeCarburant))
                .and(AutoSpecifications.hasCouleur(couleur))
                .and(AutoSpecifications.hasPrixBetween(prixMin, prixMax))
                .and(AutoSpecifications.hasAnneeBetween(anneeMin, anneeMax))
                .and(AutoSpecifications.hasVille(ville))
                .and(AutoSpecifications.hasKeyword(keyword));

        return autoRepository.findAll(spec, pageable);
    }




}






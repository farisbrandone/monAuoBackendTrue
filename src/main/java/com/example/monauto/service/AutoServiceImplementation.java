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
import com.example.monauto.utils.AutoUpdateMap;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.stream.Stream;


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
        Auto auto1 = new Auto();
        AutoMap autoMap=new AutoMap(auto1, auto);
        Auto autoSend=autoMap.getAutoWithAutoPostDto();

        Collection<ImageAuto> imageAutoCollection=new ArrayList<>();
        Collection<ImageAuto> imageAutoCollection2=new ArrayList<>();
        for (String imageAuto1 : auto.getImagesAuto()) {
             System.out.println("ZOOZOZ");
            System.out.println(imageAuto1);
            ImageAuto imageAuto=new ImageAuto();
            imageAuto.setUrl(imageAuto1);
            ImageAuto imageAuto2= imageAutoRepository.save(imageAuto);
            imageAutoCollection.add(imageAuto2);

        }
        String myToken=auto.getUserToken();
        Algorithm algorithm = Algorithm.HMAC256(JwtUtils.SECRET);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(myToken);
        String email = decodedJWT.getSubject();
        Seller seller=sellerRepository.findSellerByEmail(email);
        autoSend.setSeller(seller);
        autoSend.setImagesAuto(imageAutoCollection);
        Auto myAuto=autoRepository.save(autoSend);
        for (ImageAuto image : imageAutoCollection) {
            image.setAuto(myAuto);
            ImageAuto imageAuto2= imageAutoRepository.save(image);
            imageAutoCollection2.add(imageAuto2);

        }
        myAuto.setImagesAuto(imageAutoCollection2);
       return autoRepository.save(myAuto);

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
        Auto auto1 = autoRepository.findById(Long.parseLong(auto.getId())).orElse(null);
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




}






package com.example.monauto.web;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.monauto.DTO.*;
import com.example.monauto.dao.AutoRepository;
import com.example.monauto.dao.ImageAutoRepository;
import com.example.monauto.dao.SellerRepository;
import com.example.monauto.entity.Auto;
import com.example.monauto.entity.Role;
import com.example.monauto.entity.Seller;
import com.example.monauto.sec.JwtUtils;
import com.example.monauto.service.*;
import com.example.monauto.utils.Convertion;
import com.example.monauto.utils.SearchCarSpecification;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class SellerRestController {
    private final SellerService sellerService;
    private final SellerRepository sellerRepository;
    private final AutoRepository autoRepository;
    private final IRoleService roleService;
    private final IAutoService autoService;
    private final ImageAutoRepository imageAutoRepository;
    private final IStorageService firebaseStorageService; // or FirebaseStorageService

    public SellerRestController(SellerService sellerService, SellerRepository sellerRepository, AutoRepository autoRepository, RoleServiceImpl roleService, IAutoService autoService, ImageAutoRepository imageAutoRepository, IStorageService firebaseStorageService) {
        this.sellerService = sellerService;
        this.sellerRepository = sellerRepository;
        this.autoRepository = autoRepository;
        this.roleService = roleService;
        this.autoService = autoService;
        this.imageAutoRepository = imageAutoRepository;
        this.firebaseStorageService = firebaseStorageService;
    }



    @GetMapping("/newsearch")
    public ResponseEntity<PaginatedCarResponse> search(
            @RequestParam(required = false) String marques,
            @RequestParam(required = false) String typesCarrosserie,
            @RequestParam(required = false) String typeTransmission,
            @RequestParam(required = false) String kilometrageMin,
            @RequestParam(required = false) String kilometrageMax,
            @RequestParam(required = false) String typeMoteur,
            @RequestParam(required = false) String typeCarburant,
            @RequestParam(required = false) String couleur,
            @RequestParam(required = false) String PrixMin,
            @RequestParam(required = false) String PrixMax,
            @RequestParam(required = false) String anneeMin,
            @RequestParam(required = false) String anneeMax,
            @RequestParam(required = false) String ville,
            @RequestParam(required = false) String keyword,
            Pageable pageable
    ) {


        Date anneeMins= anneeMin!=null ?Convertion.convertStringToDate( anneeMin):null;
        Date anneeMaxs=anneeMax!=null ? Convertion.convertStringToDate( anneeMax):null;
        Float kilometrageMins=kilometrageMin!=null? Convertion.convertStringToFloat(kilometrageMin):null;
        Float kilometrageMaxs=kilometrageMax!=null? Convertion.convertStringToFloat(kilometrageMax):null;
        Float PrixMins=PrixMin!=null? Convertion.convertStringToFloat(PrixMin):null;
        Float PrixMaxs=PrixMax!=null? Convertion.convertStringToFloat(PrixMax):null;




        Page<Auto> result= autoService.searchCars(
                marques, typesCarrosserie, typeTransmission,
                kilometrageMins, kilometrageMaxs, typeMoteur, typeCarburant,
                couleur, PrixMins, PrixMaxs, anneeMins, anneeMaxs, ville, keyword, pageable
        );
        List<Auto> autos = new ArrayList<>();
        if (result != null && result.hasContent()) {  // Double vérification
            autos = new ArrayList<>(result.getContent());

        }
        assert result != null;
        PaginatedCarResponse response = new PaginatedCarResponse(
                autos,
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages(),
                result.isLast()
        );
        return ResponseEntity.ok(response);
    }



 /*   @GetMapping("/newsearchbb")
    public ResponseEntity<PaginatedCarResponse> newsearchCars(
            @ModelAttribute SearchDto criteria,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

       System.out.println(criteria.toString()+"lalalala1");
        String marques=criteria.getMarques();
        String typesCarrosserie=criteria.getTypesCarrosserie();
        Date anneeMin= criteria.getAnneeMin()!=null ?Convertion.convertStringToDate( criteria.getAnneeMin()):null;
        Date anneeMax=criteria.getAnneeMax()!=null ? Convertion.convertStringToDate( criteria.getAnneeMax()):null;
        Float kilometrageMin=criteria.getKilometrageMin()!=null? Convertion.convertStringToFloat(criteria.getKilometrageMin()):null;
        Float kilometrageMax=criteria.getKilometrageMax()!=null? Convertion.convertStringToFloat(criteria.getKilometrageMax()):null;
        Float PrixMin=criteria.getPrixMin()!=null? Convertion.convertStringToFloat(criteria.getPrixMin()):null;
        Float PrixMax=criteria.getPrixMin()!=null? Convertion.convertStringToFloat(criteria.getPrixMax()):null;
        String typeMoteur=criteria.getTypeMoteur();
        String typeCarburant=criteria.getTypeCarburant();
        String typeTransmission=criteria.getTypeTransmission();
        String keyword= criteria.getKeyWord();
        String selectedColor=criteria.getSelectedColor();
        String villeDuBien=criteria.getVilleDuBien();
        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        Page<Auto> result = autoRepository.searchCars(
               marques,
                typesCarrosserie,
                anneeMin,
                anneeMax,
                kilometrageMin,
                kilometrageMax,
                PrixMin,
                PrixMax,
                typeMoteur,
                typeCarburant,
                typeTransmission,
                selectedColor,
                villeDuBien,
                keyword,
                pageable
        );
        List<Auto> autos = new ArrayList<>();
        if (result != null && result.hasContent()) {  // Double vérification
            autos = new ArrayList<>(result.getContent());

        }
        PaginatedCarResponse response = new PaginatedCarResponse(
                autos,
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages(),
                result.isLast()
        );
        return ResponseEntity.ok(response);
    }*/



    @GetMapping("/search")
    public ResponseEntity<PaginatedCarResponse> searchCars(@ModelAttribute SearchDto criteria, @RequestParam(defaultValue = "0") int page,

                                                 @RequestParam(defaultValue = "10") int size,
                                                 @RequestParam(defaultValue = "id") String sortBy,
                                                 @RequestParam(defaultValue = "asc") String direction) {

        System.out.println(criteria.toString());

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        Page<Auto> carPage = autoRepository.findAll(SearchCarSpecification.withCriteria(criteria), pageable);
        PaginatedCarResponse response = new PaginatedCarResponse(
                carPage.getContent(),
                carPage.getNumber(),
                carPage.getSize(),
                carPage.getTotalElements(),
                carPage.getTotalPages(),
                carPage.isLast()
        );
        System.out.println(response);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/addRole")
    public Collection<Role> addRole(@RequestBody Collection<String> role) {
        return roleService.addRole(role);

    }


    @PostMapping(path = "/addAuto")
    public Auto addAuto(@RequestBody AutoPostDto autoPost) {

        return autoService.addOneAuto(autoPost);
    }

    @PutMapping("/updateAuto")
    public Auto updateAuto(@RequestBody AutoUpdateDto autoPost) {
         System.out.println(autoPost.toString()+"poupou");
        return autoService.updateOneAuto(autoPost);
    }


 @PostMapping(path = "/signup")
 public ResponseEntity<Seller> signup(@RequestBody SignupRequest seller) {

        try {

             Seller seller1=sellerService.registerUser(seller);
            return ResponseEntity.ok(seller1);
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }

 }


 @GetMapping("/confirm")
 public ResponseEntity<Seller> confirm(@RequestParam("token") String token) {

        try {
            Seller seller=sellerService.verifySeller(token);
            System.out.println("verified1 : "+seller.getCountry());

            System.out.println("verified2 : "+seller.getEmail());
            return ResponseEntity.ok(seller);
        } catch (RuntimeException e) {
            System.out.println("verified3 : ");
            return ResponseEntity.badRequest().body(null);
        }

 }


    @GetMapping(path = "/refreshToken")
    public void refreshToken(HttpServletResponse response, HttpServletRequest request) {
        String refreshToken = request.getHeader("Authorization");

        if (refreshToken != null && refreshToken.startsWith("Bearer ")) {
            try {
                refreshToken = refreshToken.substring(7);
                System.out.println(refreshToken+"toutou");
                Algorithm algorithm = Algorithm.HMAC256(JwtUtils.SECRET);
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                System.out.println("toutou2");
                DecodedJWT decodedJWT = jwtVerifier.verify(refreshToken);
                System.out.println("toutou3");
                String email = decodedJWT.getSubject();
                System.out.println("email for refreshToken: "+email);
                Seller seller= sellerService.loadSellerByEmail(email);
                String jwtAccessToken = JWT.create()
                        .withSubject(seller.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis()+5*60*1000))
                        .withIssuer(request.getRequestURL().toString()) /*celui à l'origine du token*/
                        .withClaim("roles", seller.getRoleSeller().stream()
                                .map(ga ->ga.getRoleName()).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> idToken = new HashMap<>();
                idToken.put("access-token", jwtAccessToken);
                idToken.put("refresh-token", refreshToken);
                response.setContentType("application/json");

                /*objectMapper() utiliser pour sérialiser un map en json*/
                new ObjectMapper().writeValue(response.getOutputStream(), idToken);

            } catch (Exception e) {
                response.setHeader("error-message", e.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }else {
            throw new RuntimeException("Refresh token is invalid");
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        try {
            sellerService.initiatePasswordReset(request.getEmail());
            return ResponseEntity.ok("Password reset link has been sent to your email");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordDTO request) {
        try {
            sellerService.resetPassword(request.getToken(), request.getNewPassword());
            return ResponseEntity.ok("Password has been reset successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/validate-reset-token")
    public ResponseEntity<?> validateResetToken(@RequestParam String token) {
        try {
            Seller seller = sellerRepository.findSellerByResetToken(token);

                    if (seller==null) {
                        return ResponseEntity.badRequest().body("Invalid token");
                    }

            if (seller.getResetTokenCreationDate().isBefore(LocalDateTime.now().minusHours(24))) {
                throw new RuntimeException("Reset token has expired");
            }

            return ResponseEntity.ok("Token is valid");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/sendContact")
    public ResponseEntity<?> sendContact(@Valid @RequestBody ContactDTO request) {
        try {
            sellerService.sendContactEmailForAdmin(request);
            return ResponseEntity.ok("Email has been send successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteImageAuto/{id}")
    public ResponseEntity<?> deleteImageAuto(@PathVariable Long id) {
        System.out.println("Auto has been deleted successfully" + id);
        try {
          imageAutoRepository.deleteById(id);
            System.out.println("Auto has been deleted successfully111");
          return ResponseEntity.ok("Image has been deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/deleteAuto/{id}")
    public ResponseEntity<?> deleteAuto( @PathVariable String id) {
         try {
             autoRepository.deleteById(Long.parseLong(id));
             System.out.println("Auto has been deleted successfully");
             return ResponseEntity.ok("Auto has been deleted successfully");
         } catch (RuntimeException e) {
             return ResponseEntity.badRequest().body(e.getMessage());
         }
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<Seller> upadeSeller( @PathVariable Long id , @RequestBody UserUpdateDTO updateDTO  ) {
        try {
          Seller seller=sellerService.updateUser(updateDTO, id);

            return ResponseEntity.ok(seller);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser( @PathVariable String id) {
        try {
            sellerRepository.deleteById(Long.parseLong(id));
            System.out.println("seller has been deleted successfully");
            return ResponseEntity.ok("Seller has been deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        Map<String, String> results = new HashMap<>();
        try {
            String fileUrl = firebaseStorageService.uploadFile(file);
            results.put(file.getOriginalFilename(), fileUrl);
            return ResponseEntity.ok(results);
        } catch (IOException e) {
            results.put(file.getOriginalFilename(), "Error: " + e.getMessage());
            return ResponseEntity.badRequest().body(results);
        }

    }

    @GetMapping("/downloadFile")
    public ResponseEntity<byte[]> downloadFile(@RequestParam String fileName) {
        byte[] fileContent = firebaseStorageService.downloadFile(fileName);
        return ResponseEntity.ok()
                .header("Content-Type", "application/octet-stream")
                .body(fileContent);
    }

    @DeleteMapping("/deleteFile")
    public ResponseEntity<Boolean> deleteFile(@RequestParam String fileName) {
        System.out.println(fileName+" is deleted");
    try {
        boolean value= firebaseStorageService.deleteFile(fileName);
        return ResponseEntity.ok(value);
    } catch (RuntimeException e) {
        throw new RuntimeException(e);
    }
    }


    @PostMapping("/upload-multiple")
    public ResponseEntity<Map<String, String>> uploadMultipleFiles(
            @RequestParam("files") MultipartFile[] files) {

        Map<String, String> results = new HashMap<>();

        for (MultipartFile file : files) {
            try {
                String fileUrl = firebaseStorageService.uploadFile(file);
                results.put(file.getOriginalFilename(), fileUrl);
            } catch (IOException e) {
                results.put(file.getOriginalFilename(), "Error: " + e.getMessage());
            }
        }

        return ResponseEntity.ok(results);
    }

}





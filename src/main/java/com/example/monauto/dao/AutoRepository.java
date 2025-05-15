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
public interface AutoRepository  extends JpaRepository<Auto, Long>, JpaSpecificationExecutor<Auto>{

}

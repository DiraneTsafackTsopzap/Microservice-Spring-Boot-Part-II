package com.microservice.compagny_service.JPA_Compagny_Repository;

import com.microservice.compagny_service.Models.Compagny;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository// 1- Placer cette Annotation
public interface JPA_COMPAGNY_REPOSITORY extends JpaRepository<Compagny, String> {

}
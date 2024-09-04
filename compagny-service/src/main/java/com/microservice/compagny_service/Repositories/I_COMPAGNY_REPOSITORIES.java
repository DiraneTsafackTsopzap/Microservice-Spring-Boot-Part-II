package com.microservice.compagny_service.Repositories;


import com.microservice.compagny_service.Models.Compagny;
import com.microservice.compagny_service.Models.Job;

import java.util.List;

public interface I_COMPAGNY_REPOSITORIES {

    List<Compagny> Find_All_Compagny();
    void Create_Compagny(Compagny new_compagny);

    Compagny get_Compagny_By_Id(String id);

    boolean Delete_Compagny_By_Id(String id);

    boolean Update_Compagny(String id, Compagny updatedCompagny);


    boolean isCompagnyExists(String id);

}

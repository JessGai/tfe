package com.tfe.service;

import com.tfe.EnfantRepository;
import com.tfe.ParentRepository;
import com.tfe.dto.StageDescDTO;
import com.tfe.entity.EnfantEntity;
import com.tfe.entity.ParentEntity;
import com.tfe.entity.StageDescEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class TestDataUtils {

    private ParentRepository parentRepository;

    private EnfantRepository enfantRepository;

    public TestDataUtils(ParentRepository parentRepository, EnfantRepository enfantRepository) {
        this.parentRepository = parentRepository;
        this.enfantRepository = enfantRepository;
    }

    public ParentEntity createTestParent(String nomParent){
        ParentEntity parent = new ParentEntity();
        parent.setNomParent(nomParent);
        parent.setAuth0UserId("auth0|test123");
        parent.setPrenomParent("Jean");
        parent.setAdresse("rue blabla");
        parent.setCodePostal(1000);
        parent.setCommune("Bruxelles");
        parent.setEmail("email@mail.com");
        parent.setTelephone1("021234567");
        parent = parentRepository.save(parent);
        return parent;
    }

    public EnfantEntity createChild(ParentEntity parentEntity, String nomEnfant){
        EnfantEntity enfant = new EnfantEntity();
        enfant.setNomEnfant(nomEnfant);
        enfant.setPrenomEnfant("Junior");
        enfant.setDateNaissance(LocalDate.of (2019,6, 12));
        enfant.setParent(parentEntity);
        enfantRepository.save(enfant);
        return enfant;
    }

    public StageDescDTO createStageDescDto(String titre, String theme, String description, int ageMin, int ageMax){
        StageDescDTO stageDescDto = new StageDescDTO();
        stageDescDto.setTitre(titre);
        stageDescDto.setTheme(theme);
        stageDescDto.setDescription(description);
        stageDescDto.setAgeMin(ageMin);
        stageDescDto.setAgeMax(ageMax);
        return stageDescDto;
    }

    public StageDescEntity createStageDesc(String titre, String theme, String description, int ageMin, int ageMax){
        StageDescEntity stageDesc = new StageDescEntity();
        stageDesc.setTitre(titre);
        stageDesc.setTheme(theme);
        stageDesc.setDescription(description);
        stageDesc.setAgeMin(ageMin);
        stageDesc.setAgeMax(ageMax);
        return stageDesc;
    }
}

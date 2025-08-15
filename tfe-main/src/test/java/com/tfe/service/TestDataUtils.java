package com.tfe.service;

import com.tfe.EnfantRepository;
import com.tfe.ParentRepository;
import com.tfe.StageDescRepository;
import com.tfe.StageInstanceRepository;
import com.tfe.dto.StageDescDTO;
import com.tfe.entity.EnfantEntity;
import com.tfe.entity.ParentEntity;
import com.tfe.entity.StageDescEntity;
import com.tfe.entity.StageInstanceEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class TestDataUtils {

    private ParentRepository parentRepository;

    private EnfantRepository enfantRepository;
    private StageInstanceRepository stageInstanceRepository;
    private StageDescRepository stageDescRepository;

    public TestDataUtils(ParentRepository parentRepository, EnfantRepository enfantRepository, StageInstanceRepository stageInstanceRepository, StageDescRepository stageDescRepository) {
        this.parentRepository = parentRepository;
        this.enfantRepository = enfantRepository;
        this.stageInstanceRepository = stageInstanceRepository;
        this.stageDescRepository = stageDescRepository;
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

    public StageDescEntity createStageDesc(){
        StageDescEntity stageDesc = new StageDescEntity();
        stageDesc.setTitre("titre");
        stageDesc.setTheme("theme");
        stageDesc.setDescription("description");
        stageDesc.setAgeMin(4);
        stageDesc.setAgeMax(10);
        stageDesc = stageDescRepository.save(stageDesc);
        return stageDesc;
    }

    public StageInstanceEntity createStageInst(StageDescEntity stageDesc){
        StageInstanceEntity instance = new StageInstanceEntity();
        instance.setStageDesc(stageDesc);
        instance.setDateDebut(LocalDate.now().plusDays(10));
        instance.setDateFin(LocalDate.now().plusDays(15));
        instance.setPrix(100);
        instance.setNbrInscrit(0);
        instance.setNbrParticipant(10);
        instance = stageInstanceRepository.save(instance);
        return instance;
    }
}

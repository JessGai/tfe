package com.tfe.service;

import com.tfe.*;
import com.tfe.dto.PanierDTO;
import com.tfe.dto.PanierFullDTO;
import com.tfe.entity.EnfantEntity;
import com.tfe.entity.ParentEntity;
import com.tfe.entity.StageDescEntity;
import com.tfe.entity.StageInstanceEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ComponentScan(basePackages = {"com.tfe"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableJpaRepositories("com.tfe")
@ActiveProfiles("test")
@Transactional
@Rollback
public class PanierServiceTest {
    @Autowired
    private PanierService panierService;
    @Autowired
    private ParentRepository parentRepository;
    @Autowired
    private EnfantRepository enfantRepository;
    @Autowired
    private StageDescRepository stageDescRepository;
    @Autowired
    private StageInstanceRepository stageInstanceRepository;
    @Autowired
    private PanierRepository panierRepository;
    @Autowired
    private TestDataUtils testDataUtils;

    @Test
    void createAndGetPanier(){
        ParentEntity parentEntity = testDataUtils.createTestParent("Nomparent");
        EnfantEntity enfantEntity = testDataUtils.createChild(parentEntity, "Nomenfant");
        StageDescEntity stageDescEntity = testDataUtils.createStageDesc();
        StageInstanceEntity stageInstEntity = testDataUtils.createStageInst(stageDescEntity);

        PanierDTO panierDto = new PanierDTO();
        panierDto.setIdParent(parentEntity.getIdParent());
        panierDto.setIdEnfant(enfantEntity.getIdEnfant());
        panierDto.setIdStageInstance(stageInstEntity.getIdStageInst());

        PanierDTO savedPanier = panierService.savePanier(panierDto);

        PanierFullDTO panierFullDTO = panierService.getPanierByParentId(parentEntity.getIdParent());

        assertEquals(1, panierFullDTO.getListe().size());
        assertEquals(100, panierFullDTO.getMontantTotal());
        assertEquals(0.0, panierFullDTO.getTauxReduction());

    }

}


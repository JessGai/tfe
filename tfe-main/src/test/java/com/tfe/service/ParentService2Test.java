package com.tfe.service;

import com.tfe.EnfantRepository;
import com.tfe.ParentRepository;
import com.tfe.dto.EnfantDTO;
import com.tfe.dto.ParentWithChildrenDTO;
import com.tfe.entity.EnfantEntity;
import com.tfe.entity.ParentEntity;
import com.tfe.mapper.EnfantMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ComponentScan(basePackages = {"com.tfe"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableJpaRepositories("com.tfe")
@ActiveProfiles("test")
@Transactional
@Rollback
class ParentService2Test {
    @Autowired
    private ParentService parentService;

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private EnfantRepository enfantRepository;
    @Autowired
    private EnfantService enfantService;
    @Autowired
    private TestDataUtils testDataUtils;
    @Test
    void getParentWithChildren() {
        // Creation d'un parent et de son enfant
        String nomEnfant = "Nomenfant";
        String nomParent = "Nomparent";

        ParentEntity parentEntity = testDataUtils.createTestParent(nomParent);
        EnfantEntity enfantEntity = testDataUtils.createChild(parentEntity, nomEnfant);

        ParentWithChildrenDTO dto = parentService.getParentWithChildren("auth0|test123");

        assertNotNull(dto);
        assertEquals(nomParent, dto.getNomParent());
        assertFalse(dto.getEnfants().isEmpty());
        assertEquals(nomEnfant, dto.getEnfants().getFirst().getNomEnfant());
    }

    @Test
    void verifyParentExists() {

        ParentEntity parentEntity = testDataUtils.createTestParent("Nomparent");

        assertNotNull(parentEntity);
        assertTrue(parentService.existsByAuth0UserId("auth0|test123"));
    }
    @Test
    void getChildById(){

        String nomEnfant = "Nomenfant";
        ParentEntity parentEntity = testDataUtils.createTestParent("Nomparent");

        EnfantEntity enfantEntity = testDataUtils.createChild(parentEntity, nomEnfant);

        EnfantDTO dto = enfantService.getEnfantById(enfantEntity.getIdEnfant());

        assertEquals(enfantEntity.getNomEnfant(), dto.getNomEnfant());
    }

}
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
    @Test
    void getParentWithChildren() {
        // Creation d'un parent et de son enfant

        ParentEntity parent = new ParentEntity();
        parent.setNomParent("Test");
        parent.setAuth0UserId("auth0|test123");
        parent.setPrenomParent("Jean");
        parent.setAdresse("rue blabla");
        parent.setCodePostal(1000);
        parent.setCommune("Bruxelles");
        parent.setEmail("email@mail.com");
        parent.setTelephone1("021234567");
        parent = parentRepository.save(parent);

        EnfantEntity enfant = new EnfantEntity();
        enfant.setNomEnfant("Junior");
        enfant.setPrenomEnfant("Junior");
        enfant.setDateNaissance(LocalDate.of (2019,6, 12));
        enfant.setParent(parent);
        enfantRepository.save(enfant);

        ParentWithChildrenDTO dto = parentService.getParentWithChildren("auth0|test123");

        assertNotNull(dto);
        assertEquals("Test", dto.getNomParent());
        assertFalse(dto.getEnfants().isEmpty());
        assertEquals("Junior", dto.getEnfants().getFirst().getNomEnfant());
    }

    @Test
    void verifyParentExists() {
        ParentEntity parent = new ParentEntity();
        parent.setNomParent("Test");
        parent.setAuth0UserId("auth0|test123");
        parent.setPrenomParent("Jean");
        parent.setAdresse("rue blabla");
        parent.setCodePostal(1000);
        parent.setCommune("Bruxelles");
        parent.setEmail("email@mail.com");
        parent.setTelephone1("021234567");
        parent = parentRepository.save(parent);

        assertNotNull(parent);
        assertTrue(parentService.existsByAuth0UserId("auth0|test123"));
    }
    @Test
    void getChildById(){
        ParentEntity parent = new ParentEntity();
        parent.setNomParent("Test");
        parent.setAuth0UserId("auth0|test123");
        parent.setPrenomParent("Jean");
        parent.setAdresse("rue blabla");
        parent.setCodePostal(1000);
        parent.setCommune("Bruxelles");
        parent.setEmail("email@mail.com");
        parent.setTelephone1("021234567");
        parent = parentRepository.save(parent);

        EnfantEntity enfant = new EnfantEntity();
        enfant.setNomEnfant("Junior");
        enfant.setPrenomEnfant("Junior");
        enfant.setDateNaissance(LocalDate.of (2019,6, 12));
        enfant.setParent(parent);
        enfantRepository.save(enfant);

        EnfantDTO dto = enfantService.getEnfantById(enfant.getIdEnfant());

        assertEquals(enfant.getNomEnfant(), dto.getNomEnfant());
    }

}
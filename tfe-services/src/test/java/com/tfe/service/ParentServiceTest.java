package com.tfe.service;

import com.tfe.EnfantRepository;
import com.tfe.ParentRepository;
import com.tfe.dto.ParentWithChildrenDTO;
import com.tfe.entity.EnfantEntity;
import com.tfe.entity.ParentEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest()
@Transactional
class ParentServiceTest {
    @Autowired
    private ParentService parentService;

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private EnfantRepository enfantRepository;
    @Test
    void getParentWithChildren() {
        // Arrange : créer un parent + enfant en base
        ParentEntity parent = new ParentEntity();
        parent.setNomParent("Test");
        parent.setAuth0UserId("auth0|test123");
        parent = parentRepository.save(parent);

        EnfantEntity enfant = new EnfantEntity();
        enfant.setNomEnfant("Junior");
        enfant.setParent(parent);
        enfantRepository.save(enfant);

        // Act : appeler la méthode à tester
        ParentWithChildrenDTO dto = parentService.getParentWithChildren("auth0|test123");

        // Assert : vérifier que le parent est bien récupéré avec ses enfants
        assertNotNull(dto);
        assertEquals("Test", dto.getNomParent());
        assertFalse(dto.getEnfants().isEmpty());
        assertEquals("Junior", dto.getEnfants().get(0).getNomEnfant());
    }

}
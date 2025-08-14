package com.tfe.service;

import com.tfe.StageDescRepository;
import com.tfe.StageInstanceRepository;
import com.tfe.dto.StageDescDTO;
import com.tfe.dto.StageDescWithInstancesDTO;
import com.tfe.dto.StageForCardsDTO;
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

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ComponentScan(basePackages = {"com.tfe"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableJpaRepositories("com.tfe")
@ActiveProfiles("test")
@Transactional
@Rollback
public class StageServiceTest {
    @Autowired
    private StageDescService stageDescService;
    @Autowired
    private StageInstService stageInstService;
    @Autowired
    private StageDescRepository stageDescRepository;
    @Autowired
    private StageInstanceRepository stageInstanceRepository;
    @Autowired
    private TestDataUtils testDataUtils;

    @Test
    void createAndGetStageDescById() {
        String titre = "Titre du stage";
        String theme = "theme";
        String description = "Description du stage";
        int ageMin = 6;
        int ageMax = 8;

        StageDescDTO stageDescDto = testDataUtils.createStageDescDto(titre, theme, description, ageMin, ageMax);

        StageDescDTO savedDto = stageDescService.saveStageDesc(stageDescDto);

        StageDescDTO dto = stageDescService.getStageDescById(savedDto.getIdStageDesc());

        assertNotNull(dto);
        assertEquals(savedDto.getTitre(), dto.getTitre());
        assertEquals(savedDto.getDescription(), dto.getDescription());
        assertEquals(savedDto.getAgeMin(), dto.getAgeMin());
    }

    @Test
    void getStageDescWithInst() {

        StageDescEntity stageDesc1 = new StageDescEntity();
        stageDesc1.setTitre("Titre du stage 1");
        stageDesc1.setTheme("theme 1");
        stageDesc1.setDescription("Description du stage 1");
        stageDesc1.setAgeMin(6);
        stageDesc1.setAgeMax(8);
        stageDesc1 = stageDescRepository.save(stageDesc1);

        StageInstanceEntity instance1 = new StageInstanceEntity();
        instance1.setStageDesc(stageDesc1);
        instance1.setDateDebut(LocalDate.of(2025, 8, 18));
        instance1.setDateFin(LocalDate.of(2025, 8, 22));
        instance1.setPrix(100);
        stageInstanceRepository.save(instance1);

        StageDescEntity stageDesc2 = new StageDescEntity();
        stageDesc2.setTitre("Titre du stage 2");
        stageDesc2.setTheme("theme 2");
        stageDesc2.setDescription("Description du stage 2");
        stageDesc2.setAgeMin(4);
        stageDesc2.setAgeMax(6);
        stageDesc2 = stageDescRepository.save(stageDesc2);

        StageInstanceEntity instance2 = new StageInstanceEntity();
        instance2.setStageDesc(stageDesc2);
        instance2.setDateDebut(LocalDate.of(2025, 8, 25));
        instance2.setDateFin(LocalDate.of(2025, 8, 29));
        instance2.setPrix(150);
        stageInstanceRepository.save(instance2);

        List<StageDescWithInstancesDTO> listsStage = stageDescService.getStageDescWithInstances();

        assertEquals(2, listsStage.size());

        StageDescWithInstancesDTO stage1 = listsStage.stream()
                .filter(s -> s.getTitre().equals("Titre du stage 1"))
                .findFirst()
                .orElseThrow();
        assertEquals(1, stage1.getInstances().size());
        assertEquals(100, stage1.getInstances().getFirst().getPrix());

        StageDescWithInstancesDTO stage2 = listsStage.stream()
                .filter(s -> s.getTitre().equals("Titre du stage 2"))
                .findFirst()
                .orElseThrow();
        assertEquals(1, stage2.getInstances().size());
        assertEquals(150, stage2.getInstances().getFirst().getPrix());

    }

    @Test
    void getAllVisibleCards(){
        StageDescEntity stageDesc1 = new StageDescEntity();
        stageDesc1.setTitre("Titre du stage 1");
        stageDesc1.setTheme("theme 1");
        stageDesc1.setDescription("Description du stage 1");
        stageDesc1.setAgeMin(6);
        stageDesc1.setAgeMax(8);
        stageDesc1 = stageDescRepository.save(stageDesc1);

        StageInstanceEntity instance1 = new StageInstanceEntity();
        instance1.setStageDesc(stageDesc1);
        instance1.setStatut(true);
        instance1.setDateDebut(LocalDate.of(2025, 8, 18));
        instance1.setDateFin(LocalDate.of(2025, 8, 22));
        instance1.setPrix(100);
        stageInstanceRepository.save(instance1);

        StageDescEntity stageDesc2 = new StageDescEntity();
        stageDesc2.setTitre("Titre du stage 2");
        stageDesc2.setTheme("theme 2");
        stageDesc2.setDescription("Description du stage 2");
        stageDesc2.setAgeMin(4);
        stageDesc2.setAgeMax(6);
        stageDesc2 = stageDescRepository.save(stageDesc2);

        StageInstanceEntity instance2 = new StageInstanceEntity();
        instance2.setStageDesc(stageDesc2);
        instance2.setDateDebut(LocalDate.of(2025, 8, 25));
        instance2.setDateFin(LocalDate.of(2025, 8, 29));
        instance2.setPrix(150);
        instance2.setStatut(false);
        stageInstanceRepository.save(instance2);

        List<StageForCardsDTO> listeStage = stageInstService.getAllStageForCards();
        assertEquals(1, listeStage.size());

        StageForCardsDTO stage1 = listeStage.stream()
                .filter(s -> s.getTitre().equals("Titre du stage 1"))
                .findFirst()
                .orElseThrow();

        assertEquals(100, stage1.getPrix());

    }
}

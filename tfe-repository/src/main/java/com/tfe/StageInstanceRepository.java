package com.tfe;

import com.tfe.dto.StageForCardsDTO;
import com.tfe.entity.StageInstanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StageInstanceRepository extends JpaRepository<StageInstanceEntity, Integer> {

    @Query("SELECT s FROM StageInstanceEntity s WHERE s.stageDesc.theme = :theme")
    List<StageInstanceEntity> findByTheme(@Param("theme") String theme);

    //récupérer les instances par ID de description
    List<StageInstanceEntity> findByStageDesc_IdStageDesc(int idStageDesc);

    @Query("SELECT new com.tfe.dto.StageForCardsDTO(" +
           "d.idStageDesc, d.titre, d.theme, d.description, d.ageMin, d.ageMax, " +
           "i.prix, i.dateDebut, i.dateFin, i.nbrParticipant, i.nbrInscrit, i.statut, i.idStageInst) " +
           "FROM StageInstanceEntity i JOIN i.stageDesc d " +
           "WHERE i.statut = true")
    List<StageForCardsDTO> findAllVisibleCards();
}

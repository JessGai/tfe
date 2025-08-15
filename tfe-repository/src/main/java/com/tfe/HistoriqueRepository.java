package com.tfe;

import com.tfe.dto.HistoriqueDTO;
import com.tfe.entity.InscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoriqueRepository extends JpaRepository<InscriptionEntity, Integer> {
    @Query("""
        SELECT new com.tfe.dto.HistoriqueDTO(
            sd.titre,
            sd.theme,
            si.dateDebut,
            si.dateFin,
            e.prenomEnfant,
            e.nomEnfant,
            (si.prix - (si.prix * i.tauxReduction / 100)),
            i.tauxReduction,
            i.dateCreation
        )
        FROM InscriptionEntity i
        JOIN i.enfant e
        JOIN i.stageInstance si
        JOIN si.stageDesc sd
        WHERE e.parent.idParent = :idParent
        ORDER BY i.dateCreation DESC
    """)
    List<HistoriqueDTO> findHistoriqueByParentId(@Param("idParent") int idParent);

}

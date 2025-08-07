package com.tfe;

import com.tfe.entity.InscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface InscriptionRepository extends JpaRepository<InscriptionEntity, Integer> {

    //verification que l'enfant n'est pas deja inscrit a un stage a la meme date:
    boolean existsByEnfantIdEnfantAndStageInstanceDateDebut(int idEnfant, LocalDate dateDebut);
}

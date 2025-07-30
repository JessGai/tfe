package com.tfe;

import com.tfe.entity.InscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscriptionRepository extends JpaRepository<InscriptionEntity, Integer> {
    List<InscriptionEntity> findByTransaction_IdTransaction(int idTransaction);
}

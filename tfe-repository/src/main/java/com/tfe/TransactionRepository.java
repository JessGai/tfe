package com.tfe;

import com.tfe.entity.TransactionEntity;
import com.tfe.enums.TransactionStatut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
    // récupération de la dernière transction du parent et du statut
    Optional<TransactionEntity> findFirstByParent_IdParentAndStatutOrderByDateCreationDesc(int idParent, TransactionStatut statut);

    // transactions du parent connecté
    List<TransactionEntity> findByParent_IdParentOrderByDateCreationDesc(int idParent);

    //Optional<TransactionEntity> findByIdParentAndStatut(int idParent, TransactionStatut statut);
}

package com.tfe;

import com.tfe.entity.PanierEntity;
import com.tfe.entity.ParentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PanierRepository extends JpaRepository<PanierEntity, Integer> {


    List<PanierEntity> findByParentIdParent(int idParent);
    //vérification du panier enfant deja inscrit au stage
    boolean existsByEnfantIdEnfantAndStageInstanceIdStageInst(int idEnfant, int idStageInstance);

    //je recupere la liste d'enfant qui sont dans le panier
    List<PanierEntity> findByEnfantIdEnfant(int idEnfant);

    //suppression par IdParent
    void deleteByParent(ParentEntity parent);

}

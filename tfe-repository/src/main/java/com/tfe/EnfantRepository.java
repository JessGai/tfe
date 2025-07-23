package com.tfe;

import com.tfe.entity.EnfantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnfantRepository extends JpaRepository<EnfantEntity, Integer>  {

    //récup du parent avec sa liste d'enfants
    List<EnfantEntity> findByParent_IdParent(int idParent);
}

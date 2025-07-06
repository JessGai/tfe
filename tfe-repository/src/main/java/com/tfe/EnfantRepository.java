package com.tfe;

import com.tfe.entity.EnfantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnfantRepository extends JpaRepository<EnfantEntity, Integer>  {

}

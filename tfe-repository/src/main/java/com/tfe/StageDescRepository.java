package com.tfe;


import com.tfe.entity.StageDescEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StageDescRepository extends JpaRepository<StageDescEntity, Integer> {
}


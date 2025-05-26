package com.tfe;

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
}

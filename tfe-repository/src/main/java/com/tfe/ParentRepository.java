package com.tfe;

import com.tfe.entity.ParentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<ParentEntity, Long> {
}

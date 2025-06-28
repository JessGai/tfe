package com.tfe;

import com.tfe.entity.ParentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParentRepository extends JpaRepository<ParentEntity, Integer> {

    //chercher parent par authn0 Id
    Optional<ParentEntity> findByAuth0UserId(String auth0UserId);

    // vérif s'il existe déjà, boolean car utilisé dans le if
    boolean existsByAuth0UserId(String auth0UserId);
}

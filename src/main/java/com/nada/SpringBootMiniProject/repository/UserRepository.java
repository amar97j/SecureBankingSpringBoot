package com.nada.SpringBootMiniProject.repository;

import com.nada.SpringBootMiniProject.entity.GuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<GuestEntity, Long> {

    Optional<GuestEntity> findByUsername(String username);
}



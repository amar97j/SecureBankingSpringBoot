package com.nada.SpringBootMiniProject.repository;

import com.nada.SpringBootMiniProject.entity.RoleEntity;
import com.nada.SpringBootMiniProject.util.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    @Query(value = "SELECT * FROM role r where r.title = :title", nativeQuery = true)
    Optional<RoleEntity> findRoleEntityByTitle(String title);
}

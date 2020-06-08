package com.dh.apiadmhospitales.models.repository;

import com.dh.apiadmhospitales.models.entity.Role;
import com.dh.apiadmhospitales.models.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}

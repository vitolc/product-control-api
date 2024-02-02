package com.vitulc.productcontroller.repositories;

import com.vitulc.productcontroller.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

    UserModel findByUsername(String username);
    boolean existsByUsername(String Username);
}

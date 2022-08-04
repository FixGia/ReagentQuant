package fx.fixgia.userms.repository;

import fx.fixgia.userms.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    UserEntity findUserEntityByEmail(String email);

}

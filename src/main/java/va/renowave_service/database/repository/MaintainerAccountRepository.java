package va.renowave_service.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import va.renowave_service.database.entity.MaintainerAccount;

import java.util.Optional;

@Repository
public interface MaintainerAccountRepository extends JpaRepository<MaintainerAccount, Integer> {

    Optional<MaintainerAccount> findByUsername(String username);

}


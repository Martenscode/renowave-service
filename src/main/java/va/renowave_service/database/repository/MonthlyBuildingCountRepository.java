package va.renowave_service.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import va.renowave_service.database.entity.MonthlyBuildingCount;

@Repository
public interface MonthlyBuildingCountRepository extends JpaRepository<MonthlyBuildingCount, Integer> {
}

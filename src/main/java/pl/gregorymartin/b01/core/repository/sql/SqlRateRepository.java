package pl.gregorymartin.b01.core.repository.sql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gregorymartin.b01.core.model.Rate;
import pl.gregorymartin.b01.core.repository.RateRepository;

@Repository
public interface SqlRateRepository extends RateRepository, JpaRepository<Rate, Long> {}

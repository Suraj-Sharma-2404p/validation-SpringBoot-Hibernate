package validation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import validation.model.Reservation;

import java.util.Optional;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation,Integer> {
    Optional<Reservation> findFirstByOrderByIdDesc();

    Optional<Reservation> findByCode(String code);
}

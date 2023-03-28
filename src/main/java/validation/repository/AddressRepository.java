package validation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import validation.model.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address,Integer> {
}

package validation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import validation.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}

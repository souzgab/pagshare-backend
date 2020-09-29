package br.com.payshare.repository;

import br.com.payshare.model.User;
import br.com.payshare.model.UserPf;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}

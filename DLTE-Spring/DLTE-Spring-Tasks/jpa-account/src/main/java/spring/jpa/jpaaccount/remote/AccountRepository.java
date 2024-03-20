package spring.jpa.jpaaccount.remote;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.jpa.jpaaccount.model.Account;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account,String> {

}

package vn.chuyenviet.sdk.web.demo.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import vn.chuyenviet.sdk.web.demo.model.Account;

import java.io.IOException;

@Repository
public interface AccountRepository extends JpaSpecificationExecutor<Account> {
    Account findByUsername(String username);
    Account save(Account account) throws IOException, Exception;

}

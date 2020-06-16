package vn.chuyenviet.sdk.web.demo.service.impl;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.chuyenviet.sdk.web.demo.model.Account;
import vn.chuyenviet.sdk.web.demo.model.AccountLogin;
import vn.chuyenviet.sdk.web.demo.repository.AccountRepository;
import vn.chuyenviet.sdk.web.demo.service.AccountService;
import vn.chuyenviet.sdk.web.demo.utils.ApplicationMessage;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Account save(@NotNull Account account) throws Exception {
        Account accountOld = findByUsername(account.getUsername());
        if (accountOld != null) {
            throw new Exception(ApplicationMessage.EMAIL_DOES_NOT_EXIST);
        }
        String password = passwordEncoder.encode(account.getPassword());
        account.setPassword(password);
        return accountRepository.save(account);
    }

    @Override
    public Account findByUsername(@NotNull String username) {
        return accountRepository.findByUsername(username);
    }

    public Account login(AccountLogin dto) throws Exception {
        Account account = findByUsername(dto.getUsername());
        if (account == null) {
            throw new Exception(ApplicationMessage.USERNAME_PASSWORD_INCORRECT);
        } else if (!passwordEncoder.matches(dto.getPassword(), account.getPassword())) {
            throw new Exception(ApplicationMessage.USERNAME_PASSWORD_INCORRECT);
        }
        return account;
    }
}

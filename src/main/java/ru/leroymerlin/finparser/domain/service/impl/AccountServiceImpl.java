package ru.leroymerlin.finparser.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.leroymerlin.finparser.domain.exception.AccountException;
import ru.leroymerlin.finparser.domain.models.Account;
import ru.leroymerlin.finparser.domain.models.Bank;
import ru.leroymerlin.finparser.domain.models.Client;
import ru.leroymerlin.finparser.domain.repository.AccountRepository;
import ru.leroymerlin.finparser.domain.service.AccountService;
import ru.leroymerlin.finparser.domain.service.TransformationService;
import ru.leroymerlin.finparser.upload.xml.models.AccountXml;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    @Qualifier("domain")
    private TransformationService transformationService;

    @Override
    public Account getByNumber(String number) {
        if (number != null) {
            Account account = accountRepository.findByNumber(number);
            if (account != null) {
                return account;
            }
        }
        return null;
    }

    @Override
    public Account save(AccountXml accountXml, Client client, Bank bank) {
        if (accountXml.getNumber() != null && client != null && bank != null) {
            if (!accountRepository.existsByNumber(accountXml.getNumber())) {
                Account account = transformationService.getAccountFromXml(accountXml, client, bank);
                account = accountRepository.save(account);
                return account;
            } else {
                throw new AccountException("Account with number " + accountXml.getNumber() + "exist in DB");
            }
        }
        throw new AccountException("Number account, client or bank for saving account is not set");
    }

    @Override
    public Account findOrSave(AccountXml accountXml, Client client, Bank bank) {
        if (accountXml != null) {
            Account account = this.getByNumber(accountXml.getNumber());
            if (account == null) {
                account = this.save(accountXml, client, bank);
            }
            return account;
        }
        return null;
    }
}

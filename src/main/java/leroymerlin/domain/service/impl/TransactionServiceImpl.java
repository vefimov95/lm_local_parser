package leroymerlin.domain.service.impl;

import leroymerlin.domain.models.*;
import leroymerlin.domain.repository.TransactionRepository;
import leroymerlin.domain.service.*;
import leroymerlin.upload.xml.models.TransactionXml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

//    @Autowired
    private ClientService clientService;

//    @Autowired
    private BankService bankService;

//    @Autowired
    private AccountService accountService;

//    @Autowired
    private TransactionTypeService transactionTypeService;

//    @Autowired
//    @Qualifier("domain")
    private TransformationService transformationService;

//    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction getByReference(String reference) {
        if (reference != null) {
            Transaction transaction = transactionRepository.findByReference(reference);
            if (transaction != null) {
                return transaction;
            }
        }
        return null;
    }

    @Override
    public TransactionXml save(TransactionXml transactionXml) {
        if (transactionXml != null && transactionXml.getTransactionDetailsXml() != null) {
                // Data Sender
                Client clientSender = clientService.findOrSave(transactionXml.getClientSender());
                transactionXml.getClientSender().setId(clientSender.getId());

                Bank bankSender = bankService.findOrSave(transactionXml.getBankSender());
                transactionXml.getBankSender().setId(bankSender.getId());

                Account accountSender =
                        accountService.findOrSave(transactionXml.getAccountSender(), clientSender, bankSender);
                transactionXml.getAccountSender().setId(accountSender.getId());

                // Data Recipient
                Client clientRecipient = clientService.findOrSave(transactionXml.getClientRecipient());
                transactionXml.getClientRecipient().setId(clientRecipient.getId());

                Bank bankRecipient = bankService.findOrSave(transactionXml.getBankRecipient());
                transactionXml.getBankRecipient().setId(bankRecipient.getId());

                Account accountRecipient =
                        accountService.findOrSave(transactionXml.getAccountRecipient(), clientRecipient, bankRecipient);
                transactionXml.getAccountRecipient().setId(accountRecipient.getId());

                // Transaction Details
                TransactionType transactionType
                        = transactionTypeService.findOrSave(transactionXml.getTransactionTypeXml());
                transactionXml.getTransactionTypeXml().setId(transactionType.getId());

                Transaction transaction = transformationService.getTransactionFromXml(transactionXml.getTransactionDetailsXml(),
                        accountSender, accountRecipient, transactionType);

//                transaction = transactionRepository.save(transaction);

                transactionXml.setId(transaction.getId());
        }
        return transactionXml;
    }

    @Override
    public List<TransactionXml> saveAll(List<TransactionXml> transactionXmlList) {
        for (TransactionXml t: transactionXmlList) {
                t = this.save(t);
        }
        return transactionXmlList;
    }
}

package org.sid.ebankingbackend.services;

import org.sid.ebankingbackend.entities.BankAccount;
import org.sid.ebankingbackend.entities.CurrentAccount;
import org.sid.ebankingbackend.entities.SavingAccount;
import org.sid.ebankingbackend.repositories.BankAccountRpository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BankService {
    @Autowired
    private BankAccountRpository bankAccountRpository;

    public void consulter(){
        BankAccount bankAccount =
                bankAccountRpository.findById("27df3c0b-cdf6-4042-ae82-e1f87e3d34cf").orElse(null);
        if (bankAccount != null) {
            System.out.println("*********************************");
            System.out.println(bankAccount.getId());
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getStatus());
            System.out.println(bankAccount.getCreatedAt());
            System.out.println(bankAccount.getCustomer().getName());
            System.out.println(bankAccount.getClass().getSimpleName());
            if (bankAccount instanceof SavingAccount) {
                System.out.println("Rate: " + ((SavingAccount) bankAccount).getInterestRate());
            } else if (bankAccount instanceof CurrentAccount) {
                System.out.println("Over Draft: " + ((CurrentAccount) bankAccount).getOverDraft());
            }
            bankAccount.getAccountOperations().forEach(op -> {
                System.out.println(op.getType() + "\t" + op.getOperationDate() + "\t" + op.getAmount());
            });
        }
    }
}

package com.paygo.paygo.service.implementation;

import java.time.LocalDate;
import java.util.List;

import com.paygo.paygo.dto.WalletDto;
import com.paygo.paygo.feign.WalletService;
import com.paygo.paygo.utils.Kind;
import org.springframework.stereotype.Service;

import com.paygo.paygo.dto.TransactionDto;
import com.paygo.paygo.entity.Transaction;
import com.paygo.paygo.repository.TransactionRepository;
import com.paygo.paygo.service.TransactionService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final WalletService walletService;

    @Override
    public List < Transaction > getAll ( ) {
        List < Transaction > transactions = transactionRepository.findAll ( );
        if ( transactions != null ) {
            return transactions;
        } else {
            return null;
        }
    }

    @Override
    public String createTransaction ( TransactionDto transactionDto ) {

        //TODO Refactoring for this method
        WalletDto walletDto;

        Boolean transactionDtoIsValide = false;
        if ( transactionDto != null && !transactionDto.equals ( new TransactionDto ( ) ) ) {
            if ( transactionDto.getCin ( ) != null && !transactionDto.getCin ( ).isEmpty ( ) ) {
                if ( transactionDto.getAmount ( ) != null || transactionDto.getAmount ( ) < 0 ) {
                    transactionDtoIsValide = !transactionDtoIsValide;
                } else {
                    return "the amount is null or less than Zero";
                }
            } else {
                return "the cin is null or empty";
            }
        } else {
            return "transaction is null or empty";
        }


        if ( transactionDtoIsValide) {
            walletDto = walletService.getWalletByCin ( transactionDto.getCin ( ) ); //TODO CREATE METHOD IN WALLET SERVICE GETWALLETBYCIN

            if ( walletDto != null && !walletDto.equals ( new WalletDto ( ) ) ) {

                if ( walletDto.getStatus ( ) ) {
                    if (transactionDto.getKind() == Kind.RETRAIT) {
                        if ( transactionDto.getKind() == Kind.RETRAIT && transactionDto.getAmount ( ) <= walletDto.getBalance ( ) && transactionDto.getAmount ( ) <= walletDto.getOverdraftLimit ( ) ) {
                            walletDto.setBalance ( walletDto.getBalance ( ) - transactionDto.getAmount ( ) );
                            try {
                                Transaction transaction = new Transaction(null, transactionDto.getAmount(), transactionDto.getKind(), LocalDate.now(), transactionDto.getCin());
                                
                                transactionRepository.save(transaction);

                                walletService.updateWallet ( walletDto );
                                return "Success";
                            } catch (Exception exception) {
                                return "Exception  is : " + exception.getMessage ( );
                            }
                        } else  {
                            return "amount is not  valide, check the balance or limit";
                        }
                    }

                    if (transactionDto.getKind() == Kind.DEPOSIT) {
                        if (transactionDto.getAmount() != null && transactionDto.getAmount() > 0) {
                            walletDto.setBalance ( walletDto.getBalance ( ) + transactionDto.getAmount ( ) );
                            try {
                                Transaction transaction = new Transaction(null, transactionDto.getAmount(), transactionDto.getKind(), LocalDate.now(), transactionDto.getCin());
                                
                                transactionRepository.save(transaction);
                                
                                walletService.updateWallet ( walletDto );
                                return "Success";
                            } catch (Exception exception) {
                                return "Exception  is : " + exception.getMessage ( );
                            }
                        } else {
                            return "amount is null or less then zero";
                        }
                    }

                } else {
                    return "wallet status is false";
                }

            } else {
                return "Wallet is null or cin is invalide in the wallet service";
            }
        }
        return "else";
    }

    @Override
    public List<Transaction> getTransactionsByCin(String cin) {
        return transactionRepository.findByCin(cin);
    }

}
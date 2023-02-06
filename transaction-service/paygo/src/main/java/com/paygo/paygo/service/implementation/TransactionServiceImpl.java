package com.paygo.paygo.service.implementation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.paygo.paygo.dto.TransactionDto;
import com.paygo.paygo.entity.Transaction;
import com.paygo.paygo.repository.TransactionRepository;
import com.paygo.paygo.service.TransactionService;
import com.paygo.paygo.utils.Kind;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService{
    private final TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getAll() {
        List<Transaction> transactions = transactionRepository.findAll();
        if (transactions != null) {
            return transactions;
        } else {
            return null;
        }
    }

    @Override
    public String createTransaction(TransactionDto transactionDto) {

        

        try {
            if (transactionDto  != null && !transactionDto.equals(new TransactionDto())) {
            
                if (transactionDto.getKind() == Kind.DEPOSIT) {
                    
                    if (transactionDto.getAmount() != null && transactionDto.getAmount() >  0){
    
                        //TODO  get client and get wallet && creating DTO  mapper method
    
                        Transaction transaction = new Transaction();
    
                        transaction.setAmount(transactionDto.getAmount());
                        transaction.setCin(transactionDto.getCin());
                        transaction.setCreatedAt(LocalDate.now());
                        transaction.setKind(transactionDto.getKind());
                        this.transactionRepository.save(transaction);
                    }
                }
            }
            return "transaction listed";
        } catch (Exception e) {
            // TODO: handle exception
            return e.getMessage();
        }
        
    }

    
    
}

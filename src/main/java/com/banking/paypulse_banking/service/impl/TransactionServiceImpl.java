package com.banking.paypulse_banking.service.impl;

import com.banking.paypulse_banking.Exception.InsufficientBalanceException;
import com.banking.paypulse_banking.Exception.NotFoundException;
import com.banking.paypulse_banking.dto.paginated.PaginatedResponseTransaction;
import com.banking.paypulse_banking.dto.request.QrRequestDto;
import com.banking.paypulse_banking.dto.request.TransferRequestDto;
import com.banking.paypulse_banking.dto.response.AccountLookupResponseDto;
import com.banking.paypulse_banking.dto.response.DashboardSummaryResponseDto;
import com.banking.paypulse_banking.dto.response.GetAllTransactionByUserResponseDto;
import com.banking.paypulse_banking.dto.response.TransferResponseDto;
import com.banking.paypulse_banking.entity.Account;
import com.banking.paypulse_banking.entity.Transaction;
import com.banking.paypulse_banking.entity.enums.AccountStatus;
import com.banking.paypulse_banking.entity.enums.TransactionStatus;
import com.banking.paypulse_banking.entity.enums.TransactionType;
import com.banking.paypulse_banking.mapper.TransactionMapper;
import com.banking.paypulse_banking.repo.AccountRepo;
import com.banking.paypulse_banking.repo.TransactionRep;
import com.banking.paypulse_banking.service.TransactionService;
import com.banking.paypulse_banking.util.TransactionReferenceGenerator;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private TransactionRep transactionRepo;

    @Autowired
    private TransactionMapper transactionMapper;

    @Transactional
    @Override
    public TransferResponseDto transferMony(TransferRequestDto transferRequestDto) {


        if (transferRequestDto.getSourceAccount().equals(transferRequestDto.getDestinationAccount())) {
            throw new IllegalArgumentException("Source account and Destination account are the same");
        } else if (transferRequestDto.getAmount() == null || transferRequestDto.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");

        } else {

            Account findSourceAccount = accountRepo.findByAccountNumber(transferRequestDto.getSourceAccount());
            Account findDestinationAccount = accountRepo.findByAccountNumber(transferRequestDto.getDestinationAccount());

            if (findSourceAccount == null) {
                throw new NotFoundException("Source Account not found");

            } else if (findDestinationAccount == null) {
                throw new NotFoundException("Destination Account not found");

            } else {

                if (findSourceAccount.getAccountStatus() != AccountStatus.ACTIVE) {
                    throw new NotFoundException("Source Account is not ACTIVE");

                } else if (findDestinationAccount.getAccountStatus() != AccountStatus.ACTIVE) {
                    throw new NotFoundException("Destination is not ACTIVE");

                } else {

                    if (findSourceAccount.getBalance().compareTo(transferRequestDto.getAmount()) < 0) {
                        throw new InsufficientBalanceException("Insufficient balance in account: " + findSourceAccount.getAccountNumber());

                    } else {

                        //calculations

                        findSourceAccount.setBalance(findSourceAccount.getBalance().subtract(transferRequestDto.getAmount()));
                        findDestinationAccount.setBalance(findDestinationAccount.getBalance().add(transferRequestDto.getAmount()));

                        accountRepo.save(findSourceAccount);
                        accountRepo.save(findDestinationAccount);

                        String reference = generateUniqueTransactionReference();

                        Transaction tr = new Transaction();
                        tr.setTransactionReference(reference);
                        tr.setSourceAccount(findSourceAccount);
                        tr.setDestinationAccount(findDestinationAccount);
                        tr.setAmount(transferRequestDto.getAmount());
                        tr.setType(TransactionType.TRANSFER);
                        tr.setStatus(TransactionStatus.SUCCESS);
                        tr.setDescription(transferRequestDto.getDescription());

                        Transaction transaction = transactionRepo.save(tr);

                        TransferResponseDto transferResponseDto = transactionMapper.transactionToTransactionDto(transaction);
                        return transferResponseDto;

                    }


                }

            }


        }


    }



    @Override
    public PaginatedResponseTransaction getAllTransaction(String accNo, int page, int size) {

        Page<Transaction> transactionPage = transactionRepo.findBySourceAccount_AccountNumber(accNo, PageRequest.of(page, size));

        if (transactionPage.hasContent()) {

            List<GetAllTransactionByUserResponseDto> dtoList =
                    transactionMapper.pageToGetAllTransactionByUserDtoList(transactionPage.getContent());

            return new PaginatedResponseTransaction(
                    dtoList,
                    transactionPage.getTotalElements()
            );

        } else {
            throw new NotFoundException("user not found");
        }

    }

    @Override
    public PaginatedResponseTransaction getTransactionsByDateRange(String accNo, LocalDateTime fromDate, LocalDateTime toDate, int page, int size) {
        Page<Transaction> transactionPage = transactionRepo.findBySourceAccount_AccountNumberAndTimestampBetween(
                accNo,
                fromDate,
                toDate,
                PageRequest.of(page, size)
        );

        if (transactionPage.hasContent()) {

            List<GetAllTransactionByUserResponseDto> dtoList =
                    transactionMapper.pageToGetAllTransactionByUserDtoList(transactionPage.getContent());

            return new PaginatedResponseTransaction(
                    dtoList,
                    transactionPage.getTotalElements()
            );

        } else {
            throw new NotFoundException("No transactions found for the given date range");
        }
    }

    //dashboard summery report
    @Override
    public DashboardSummaryResponseDto getDashboardSummary(String accNo) {

        Account account = accountRepo.findByAccountNumber(accNo);
        if (account == null) {
            throw new NotFoundException("Account not found for number: " + accNo);
        } else {

            BigDecimal totalIncome = transactionRepo.calculateTotalIncome(accNo);
            BigDecimal totalOutcome = transactionRepo.calculateTotalOutcome(accNo);

            totalIncome = (totalIncome != null) ? totalIncome : BigDecimal.ZERO;
            totalOutcome = (totalOutcome != null) ? totalOutcome : BigDecimal.ZERO;

            Page<Transaction> recentPage = transactionRepo
                    .findBySourceAccount_AccountNumberOrDestinationAccount_AccountNumber(accNo, accNo,
                            PageRequest.of(0, 5, Sort.by("timestamp").descending()));

            List<GetAllTransactionByUserResponseDto> recentTransactionsList = new ArrayList<>();
            if (recentPage.hasContent()) {
                recentTransactionsList = transactionMapper.pageToGetAllTransactionByUserDtoList(recentPage.getContent());
            }

            return new DashboardSummaryResponseDto(
                    account.getAccountNumber(),
                    account.getBalance(),
                    totalIncome,
                    totalOutcome,
                    recentTransactionsList
            );
        }
    }




    //save-transaction-genarate-reference
    private String generateUniqueTransactionReference() {
        String reference;
        do {
            reference = TransactionReferenceGenerator.generateReference();
        } while (transactionRepo.existsByTransactionReference(reference));
        return reference;
    }
}

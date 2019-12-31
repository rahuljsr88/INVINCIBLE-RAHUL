package com.bankstatementprocessor.application.service;

import com.bankstatementprocessor.application.model.ResultTransactionRecord;
import com.bankstatementprocessor.application.model.TransactionRecord;
import com.bankstatementprocessor.application.model.TransactionStatusType;
import com.bankstatementprocessor.application.model.ViewParameterType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Rahul
 * 		   This service is used for processing the result output and filters the input based on the view parameter provided
 */

@Service
public class ResultGeneratorService {

    private List<TransactionRecord> findDuplicateReferenceRecords(List<TransactionRecord> records) {
        List<TransactionRecord> duplicateReferencerecords = new ArrayList<>();
        Set<TransactionRecord> recordsSet = new HashSet<>();
        records.stream().filter(Objects::nonNull).forEach(record -> {
            if (!recordsSet.add(record)) {
                duplicateReferencerecords.add(record);
            }
        });
        return duplicateReferencerecords;
    }

    private List<TransactionRecord> findInvalidMutations(List<TransactionRecord> records) {

        List<TransactionRecord> invalidMutationsRecords = new ArrayList<>();
        records.stream().filter(Objects::nonNull).forEach(record -> {

            double sum = formatAmount(record.getStartBalance() + record.getMutation());
            double balance = formatAmount(record.getEndBalance());
            if (Double.compare(sum, balance) != 0) {
                invalidMutationsRecords.add(record);
            }
        });
        return invalidMutationsRecords;
    }

    public List<ResultTransactionRecord> prepareReport(List<TransactionRecord> records, final ViewParameterType view) {

        List<ResultTransactionRecord> results = new ArrayList<>();
        List<TransactionRecord> duplicateReferenceRecords = findDuplicateReferenceRecords(records);
        List<TransactionRecord> invalidMutationsRecords = findInvalidMutations(records);
        records.stream()
                .filter(Objects::nonNull)
                .forEach(transactionRecord -> {
                    ResultTransactionRecord result = new ResultTransactionRecord();
                    result.setReferenceNum(transactionRecord.getReference());
                    StringBuffer description = new StringBuffer();
                    if (duplicateReferenceRecords.contains(transactionRecord)) {
                        result.setTransactionStatusType(TransactionStatusType.DUPLICATE_REFERENCE_NUMBER);
                        description.append(transactionRecord.toString());
                    }
                    if (invalidMutationsRecords.contains(transactionRecord)) {
                        result.setTransactionStatusType(TransactionStatusType.INVALID_MUTATION);
                        description.append(transactionRecord.toString());
                    }
                    if (description.length() == 0) {
                        result.setTransactionStatusType(TransactionStatusType.OK);
                        description.append(transactionRecord.toString());
                    }
                    result.setDescription(description.toString());
                    results.add(result);
                });

        return  filterResults(results, view);
    }

    private List<ResultTransactionRecord> filterResults(List<ResultTransactionRecord> results, final ViewParameterType view){

        if(view == ViewParameterType.INVALID) {
            return results.stream().filter(Objects::nonNull)
                    .filter(resultTransactionRecord -> TransactionStatusType.DUPLICATE_REFERENCE_NUMBER == resultTransactionRecord.getTransactionStatusType() ||
                            TransactionStatusType.INVALID_MUTATION == resultTransactionRecord.getTransactionStatusType())
                    .collect(Collectors.toList());
        }
        else if(view == ViewParameterType.VALID) {
            return results.stream().filter(Objects::nonNull)
                    .filter(s->s.getTransactionStatusType() == TransactionStatusType.OK)
                    .collect(Collectors.toList());
        }
        else if(view == ViewParameterType.ALL) {
            return results;
        }
        else {return null;}
    }


    private static double formatAmount(double value) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}

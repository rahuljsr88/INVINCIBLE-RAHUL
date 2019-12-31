package com.bankstatementprocessor.application.service;

import com.bankstatementprocessor.application.model.ResultTransactionRecord;
import com.bankstatementprocessor.application.model.TransactionRecord;
import com.bankstatementprocessor.application.model.TransactionStatusType;
import com.bankstatementprocessor.application.model.ViewParameterType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.StrictAssertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RunWith(MockitoJUnitRunner.class)
public class ResultGeneratorServiceTest {
    @InjectMocks
    ResultGeneratorService resultGeneratorService;

    @Test
    public void testAllTransactionsViewParameter(){
        TransactionRecord testRecord1 = new TransactionRecord(1001, "NL27SNSB0917829871", "test1", 9.99, +1.00, 10.99);
        TransactionRecord testRecord2 = new TransactionRecord(1002, "NL27SNSB0917829872", "test2", 19.99, -1.00, 18.99);
        TransactionRecord testRecord3 = new TransactionRecord(1003, "NL27SNSB0917829873", "test3", 29.99, +2.00, 31.99);
        TransactionRecord testRecord4 = new TransactionRecord(1004, "NL27SNSB0917829874", "test4", 39.99, -2.00, 37.99);
        List<TransactionRecord> transactionRecordList = new ArrayList<>();
        transactionRecordList.add(testRecord1);
        transactionRecordList.add(testRecord2);
        transactionRecordList.add(testRecord3);
        transactionRecordList.add(testRecord4);
        List<ResultTransactionRecord> resultTransactionRecordList = resultGeneratorService.prepareReport(transactionRecordList, ViewParameterType.ALL);
        assertThat(resultTransactionRecordList).isNotNull();
        assertThat(resultTransactionRecordList.size()).isEqualTo(4);
        assertThat(resultTransactionRecordList.stream().filter(Objects::nonNull).map(s->s.getTransactionStatusType()).filter(Objects::nonNull).filter(s-> TransactionStatusType.OK == s).collect(Collectors.toList()).size()).isEqualTo(4);
        assertThat(resultTransactionRecordList.stream().filter(Objects::nonNull).map(s->s.getTransactionStatusType()).filter(Objects::nonNull).filter(s-> TransactionStatusType.INVALID_MUTATION == s).collect(Collectors.toList()).size()).isEqualTo(0);
        assertThat(resultTransactionRecordList.stream().filter(Objects::nonNull).map(s->s.getTransactionStatusType()).filter(Objects::nonNull).filter(s-> TransactionStatusType.DUPLICATE_REFERENCE_NUMBER == s).collect(Collectors.toList()).size()).isEqualTo(0);
    }

    @Test
    public void testInvalidTransactionsViewParameter(){
        TransactionRecord testRecord1 = new TransactionRecord(1001, "NL27SNSB0917829871", "test1", 9.99, +1.00, 11.99);
        TransactionRecord testRecord2 = new TransactionRecord(1002, "NL27SNSB0917829872", "test2", 19.99, -1.00, 18.99);
        TransactionRecord testRecord3 = new TransactionRecord(1003, "NL27SNSB0917829873", "test3", 29.99, +2.00, 31.99);
        TransactionRecord testRecord4 = new TransactionRecord(1003, "NL27SNSB0917829874", "test4", 39.99, -2.00, 37.99);
        List<TransactionRecord> transactionRecordList = new ArrayList<>();
        transactionRecordList.add(testRecord1);
        transactionRecordList.add(testRecord2);
        transactionRecordList.add(testRecord3);
        transactionRecordList.add(testRecord4);
        List<ResultTransactionRecord> resultTransactionRecordList = resultGeneratorService.prepareReport(transactionRecordList, ViewParameterType.INVALID);
        assertThat(resultTransactionRecordList).isNotNull();
        assertThat(resultTransactionRecordList.size()).isEqualTo(3);
        assertThat(resultTransactionRecordList.stream().filter(Objects::nonNull).map(s->s.getTransactionStatusType()).filter(Objects::nonNull).filter(s-> TransactionStatusType.OK == s).collect(Collectors.toList()).size()).isEqualTo(0);
        assertThat(resultTransactionRecordList.stream().filter(Objects::nonNull).map(s->s.getTransactionStatusType()).filter(Objects::nonNull).filter(s-> TransactionStatusType.INVALID_MUTATION == s).collect(Collectors.toList()).size()).isEqualTo(1);
        assertThat(resultTransactionRecordList.stream().filter(Objects::nonNull).map(s->s.getTransactionStatusType()).filter(Objects::nonNull).filter(s-> TransactionStatusType.DUPLICATE_REFERENCE_NUMBER == s).collect(Collectors.toList()).size()).isEqualTo(2);
    }

    @Test
    public void testValidTransactionsViewParameter(){
        TransactionRecord testRecord1 = new TransactionRecord(1001, "NL27SNSB0917829871", "test1", 9.99, +1.00, 11.99);
        TransactionRecord testRecord2 = new TransactionRecord(1002, "NL27SNSB0917829872", "test2", 19.99, -1.00, 18.99);
        TransactionRecord testRecord3 = new TransactionRecord(1003, "NL27SNSB0917829873", "test3", 29.99, +2.00, 31.99);
        TransactionRecord testRecord4 = new TransactionRecord(1003, "NL27SNSB0917829874", "test4", 39.99, -2.00, 37.99);
        List<TransactionRecord> transactionRecordList = new ArrayList<>();
        transactionRecordList.add(testRecord1);
        transactionRecordList.add(testRecord2);
        transactionRecordList.add(testRecord3);
        transactionRecordList.add(testRecord4);
        List<ResultTransactionRecord> resultTransactionRecordList = resultGeneratorService.prepareReport(transactionRecordList, ViewParameterType.VALID);
        assertThat(resultTransactionRecordList).isNotNull();
        assertThat(resultTransactionRecordList.size()).isEqualTo(1);
        assertThat(resultTransactionRecordList.stream().filter(Objects::nonNull).map(s->s.getTransactionStatusType()).filter(Objects::nonNull).filter(s-> TransactionStatusType.OK == s).collect(Collectors.toList()).size()).isEqualTo(1);
        assertThat(resultTransactionRecordList.stream().filter(Objects::nonNull).map(s->s.getTransactionStatusType()).filter(Objects::nonNull).filter(s-> TransactionStatusType.INVALID_MUTATION == s).collect(Collectors.toList()).size()).isEqualTo(0);
        assertThat(resultTransactionRecordList.stream().filter(Objects::nonNull).map(s->s.getTransactionStatusType()).filter(Objects::nonNull).filter(s-> TransactionStatusType.DUPLICATE_REFERENCE_NUMBER == s).collect(Collectors.toList()).size()).isEqualTo(0);
    }
}

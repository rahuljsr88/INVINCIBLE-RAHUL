package com.bankstatementprocessor.application.service;

import com.bankstatementprocessor.application.model.ResultTransactionRecord;
import com.bankstatementprocessor.application.model.TransactionRecord;
import com.bankstatementprocessor.application.model.ViewParameterType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class BankStatementProcessorServiceTest {

    @InjectMocks
    private BankStatementProcessorService bankStatementProcessorService;
    @Mock
    private ResultGeneratorService resultGeneratorService;

    @Test
    public void test_invalidFileFormat(){

        File[] files = {new File("file1.abc"),new File("file2.def")};
        List transactionRecords = new ArrayList<TransactionRecord>();
        List<ResultTransactionRecord> resultTransactionRecords = bankStatementProcessorService.process(files, ViewParameterType.ALL);
        verify(resultGeneratorService, times(0)).prepareReport(transactionRecords,ViewParameterType.ALL);
        assertThat(resultTransactionRecords).isNull();
    }

}

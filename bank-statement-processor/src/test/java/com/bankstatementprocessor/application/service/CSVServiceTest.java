package com.bankstatementprocessor.application.service;

import com.bankstatementprocessor.application.model.TransactionRecord;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CSVServiceTest {
    List<TransactionRecord> lst = null;

    @Before
    public void setUp() {
        lst = new ArrayList<>();
        lst = new ArrayList<>();
        TransactionRecord testRecord1 = new TransactionRecord(1001, "NL27SNSB0917829871", "test1", 9.99, +1.00, 10.99);
        lst.add(testRecord1);
        TransactionRecord testRecord2 = new TransactionRecord(1002, "NL27SNSB0917829872", "test2", 19.99, -1.00, 18.99);
        lst.add(testRecord2);
        TransactionRecord testRecord3 = new TransactionRecord(1003, "NL27SNSB0917829873", "test3", 29.99, +2.00, 31.99);
        lst.add(testRecord3);
        TransactionRecord testRecord4 = new TransactionRecord(1004, "NL27SNSB0917829874", "test4", 39.99, -2.00, 37.99);
        lst.add(testRecord4);
    }

    @Test
    public void testProcessDocument() throws IOException {
        CsvDocumentProcessorService mock = mock(CsvDocumentProcessorService.class);
        when(mock.processDocument()).thenReturn(lst);
        assertEquals(mock.processDocument().size(), 4);
    }
}

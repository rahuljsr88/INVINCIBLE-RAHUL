package com.bankstatementprocessor.application.service;

import com.bankstatementprocessor.application.model.TransactionRecord;


import java.io.IOException;
import java.util.List;

public interface DocumentProcessor {
    List<TransactionRecord> processDocument() throws IOException;
}

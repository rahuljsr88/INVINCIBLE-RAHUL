package com.bankstatementprocessor.application.service;

import com.bankstatementprocessor.application.model.TransactionRecord;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Rahul
 * 		   This service is used for processing the CSV files
 */

public class CsvDocumentProcessorService implements DocumentProcessor{

    private File file;
    static Logger logger = Logger.getLogger(CsvDocumentProcessorService.class.getName());
    private static final String REFERENCE = "Reference";
    private static final String ACCOUNT_NUMBER = "Account Number";
    private static final String DESCRIPTION = "Description";
    private static final String START_BALANCE = "Start Balance";
    private static final String MUTATION = "Mutation";
    private static final String END_BALANCE = "End Balance";

    public CsvDocumentProcessorService(File file) {
        this.file = file;
    }

    public List<TransactionRecord> processDocument() {
        List<TransactionRecord> transactionRecords = new ArrayList<TransactionRecord>();
        try {
            Iterable<CSVRecord> csvRecords = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(new FileReader(this.file));
            for (CSVRecord record : csvRecords){
                logger.info("THe csv record is "+record);
                TransactionRecord transactionRecord = new TransactionRecord();
                transactionRecord.setReference(Integer.parseInt(record.get(REFERENCE)));
                transactionRecord.setAccountNumber(record.get(ACCOUNT_NUMBER));
                transactionRecord.setDescripton(record.get(DESCRIPTION));
                transactionRecord.setStartBalance(Double.parseDouble(record.get(START_BALANCE)));
                transactionRecord.setMutation(Double.parseDouble(record.get(MUTATION)));
                transactionRecord.setEndBalance(Double.parseDouble(record.get(END_BALANCE)));
                transactionRecords.add(transactionRecord);
            }

        } catch (FileNotFoundException e) {
            logger.info("There is no file exists with name :" + this.file.getName());
        } catch (IOException e ) {
            logger.info("Exception occured while reading the file :" + this.file.getName());
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception occured while reading the file :" + this.file.getName());
        }
        return transactionRecords;
    }

}

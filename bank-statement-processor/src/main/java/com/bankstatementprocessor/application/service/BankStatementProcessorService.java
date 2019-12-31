package com.bankstatementprocessor.application.service;

import com.bankstatementprocessor.application.exception.ParsingNotAvailableException;
import com.bankstatementprocessor.application.model.ReportTransactionList;
import com.bankstatementprocessor.application.model.ResultTransactionRecord;
import com.bankstatementprocessor.application.model.TransactionRecord;
import com.bankstatementprocessor.application.model.ViewParameterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Rahul
 * 		   This service is the main service for this application
 *         It is being invoked by the controller when the user hits the application endpoint
 *         This service also invokes the ResultGeneratorService for processing results based on view parameter provided
 */

@Service
public class BankStatementProcessorService {

    @Autowired
    private ResultGeneratorService resultGeneratorService;
    private static final String RESULT_FILE_NAME = "report.xml";
    private static final String FILE_TYPE_CSV = ".csv";
    private static final String FILE_TYPE_XML = ".xml";
    private static final String DATE_TIME_FORMAT = "dd-MM-yyyy-HH-mm-ss";
    static Logger logger = Logger.getLogger(DocumentProcessor.class.getName());

    private List<TransactionRecord> processDocument(File file) throws ParsingNotAvailableException{
        DocumentProcessor processor = null;
        String fileName = file.getName();
        List<TransactionRecord> transactionRecords = null;
        if(isXMLFile(fileName)) {
            processor = new XmlDocumentProcessorService(file);
            transactionRecords = processFile(processor);
        } else if(isCSVFile(fileName)) {
            processor = new CsvDocumentProcessorService(file);
            transactionRecords = processFile(processor);
        } else {
            throw new ParsingNotAvailableException("Processing is not supported yet. Please check the file type. ");
        }
        return transactionRecords;
    }

    public List<ResultTransactionRecord> process(File[] files, final ViewParameterType viewParameterType) {
        List<TransactionRecord> transactionRecordsFinal = new ArrayList<>();
        List<ResultTransactionRecord> resultTransactionRecords = null;
        try{
            for (File file : files) {
                if (file.isFile()) {
                    List<TransactionRecord> transactionRecordsFile = processDocument(file);
                    if(transactionRecordsFile != null && !transactionRecordsFile.isEmpty()){
                        transactionRecordsFinal.addAll(transactionRecordsFile);
                    }
                }
            }
            if(transactionRecordsFinal!= null && !transactionRecordsFinal.isEmpty()){
                resultTransactionRecords = resultGeneratorService.prepareReport(transactionRecordsFinal, viewParameterType);
                if(!resultTransactionRecords.isEmpty()){
                    generateReportXML(resultTransactionRecords);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return resultTransactionRecords;
    }

    private boolean isCSVFile(String fileName){
        return fileName.endsWith(FILE_TYPE_CSV);
    }

    private boolean isXMLFile(String fileName){
        return fileName.endsWith(FILE_TYPE_XML);
    }

    private List<TransactionRecord> processFile(DocumentProcessor processor){
        List<TransactionRecord> transactionRecords = null;
        try{
            transactionRecords = processor.processDocument();
        }
        catch (Exception e){
            logger.info("Exception occured while processing document");
        }
        return transactionRecords;
    }

    private void generateReportXML(List<ResultTransactionRecord> resultTransactionRecords){
        ReportTransactionList reportTransactionList = new ReportTransactionList();
        reportTransactionList.setResultTransactions(resultTransactionRecords);
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(ReportTransactionList.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            LocalDateTime localDate = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
            localDate.format(formatter);
            jaxbMarshaller.marshal(reportTransactionList, new File(localDate.format(formatter)+ "_" + RESULT_FILE_NAME));
        }
        catch (JAXBException jbe){
            logger.info("JAXBException occured while preparing XML report document"+jbe.getMessage());
            jbe.printStackTrace();
        }
        catch(Exception e){
            logger.info("Exception occured while preparing XML report document"+e.getMessage());
        }
    }

}


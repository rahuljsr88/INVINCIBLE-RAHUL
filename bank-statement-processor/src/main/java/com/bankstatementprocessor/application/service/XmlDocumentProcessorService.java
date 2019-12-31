package com.bankstatementprocessor.application.service;

import com.bankstatementprocessor.application.model.TransactionRecord;
import com.bankstatementprocessor.application.model.TransactionRecordsList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Rahul
 * 		   This service is used for processing the XML files
 */

public class XmlDocumentProcessorService implements DocumentProcessor{

    private File file;
    static Logger logger = Logger.getLogger(DocumentProcessor.class.getName());

    public XmlDocumentProcessorService(File file) {
        this.file = file;
    }

    public List<TransactionRecord> processDocument() {
        List<TransactionRecord> records = new ArrayList<>();

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(TransactionRecordsList.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            TransactionRecordsList lstRecord = (TransactionRecordsList) jaxbUnmarshaller.unmarshal(this.file);
            records = lstRecord.getRecords();
        } catch (JAXBException e) {
            logger.info("JAXBException occured while processing the xml file :" + this.file.getName());
            e.printStackTrace();
        } catch (Exception e) {
            logger.info("Exception occured while processing the xml file :" + this.file.getName());
            e.printStackTrace();
        }
        return records;
    }
}

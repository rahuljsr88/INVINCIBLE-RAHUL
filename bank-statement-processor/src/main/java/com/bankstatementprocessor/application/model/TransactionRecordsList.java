package com.bankstatementprocessor.application.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Rahul
 * 		   This model object represents the TransactionList used for unmarshalling/marshalling the transaction records from xml file
 */

@XmlRootElement(name = "records")
@XmlAccessorType(XmlAccessType.FIELD)
public class TransactionRecordsList {

    @XmlElement(name = "record")
    private List<TransactionRecord> records = null;

    public List<TransactionRecord> getRecords() {
        return this.records;
    }

    public void setRecords(List<TransactionRecord> records) {
        this.records = records;
    }
}

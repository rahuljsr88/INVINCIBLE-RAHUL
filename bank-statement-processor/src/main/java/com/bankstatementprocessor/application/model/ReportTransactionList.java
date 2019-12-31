package com.bankstatementprocessor.application.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Rahul
 * 		   This model object represents the Result Transaction List used for creating the report xml from result transactions
 */

@XmlRootElement(name = "results")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReportTransactionList {
    @XmlElement(name = "result")
    private List<ResultTransactionRecord> resultTransactionRecordList = null;

    public List<ResultTransactionRecord> getResultTransactions() {
        return resultTransactionRecordList;
    }

    public void setResultTransactions(List<ResultTransactionRecord> resultTransactions) {
        this.resultTransactionRecordList = resultTransactions;
    }
}

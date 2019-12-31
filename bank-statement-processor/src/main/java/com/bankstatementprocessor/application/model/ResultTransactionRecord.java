package com.bankstatementprocessor.application.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * @author Rahul
 * 		   This model object represents the result transactions after processing and filtering the input files
 */

@Getter
@Setter
@XmlRootElement(name = "result")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResultTransactionRecord {

    private int referenceNum;
    private TransactionStatusType transactionStatusType;
    String description;

    @Override
    public String toString() {
        return "ResultTransactionRecord [referenceNum=" + referenceNum + ", description=" + description + "]";
    }
}

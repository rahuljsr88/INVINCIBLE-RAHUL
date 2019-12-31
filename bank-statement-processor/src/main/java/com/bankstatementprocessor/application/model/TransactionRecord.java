package com.bankstatementprocessor.application.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Rahul
 * 		   This model object represents the individual transaction record
 *         Transaction reference number is assumed to be unique and hence its
 *         used for equals() and hashcode() contract
 */

@XmlRootElement(name = "record")
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRecord {
    int referenceNum;
    String accountNumber;
    String description;
    double startBalance;
    double mutation;
    double endBalance;

    public int getReference() {
        return referenceNum;
    }

    @XmlAttribute(name = "reference")
    public void setReference(int reference) {
        this.referenceNum = reference;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getStartBalance() {
        return startBalance;
    }

    public void setStartBalance(double startBalance) {
        this.startBalance = startBalance;
    }

    public double getMutation() {
        return mutation;
    }

    public void setMutation(double mutation) {
        this.mutation = mutation;
    }

    public String getDescripton() {
        return description;
    }

    public void setDescripton(String descripton) {
        this.description = descripton;
    }

    public double getEndBalance() {
        return endBalance;
    }

    public void setEndBalance(double endBalance) {
        this.endBalance = endBalance;
    }


    @Override
    public String toString() {
        return "Record [reference=" + referenceNum + ", accountNumber=" + accountNumber + ", startBalance="
                + startBalance + ", mutation=" + mutation + ", descripton=" + description + ", endBalance=" + endBalance
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + referenceNum;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TransactionRecord other = (TransactionRecord) obj;
        if (referenceNum != other.referenceNum)
            return false;
        return true;
    }
}

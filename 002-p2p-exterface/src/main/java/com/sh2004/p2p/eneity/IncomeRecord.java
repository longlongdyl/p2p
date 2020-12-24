package com.sh2004.p2p.eneity;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Table(name = "b_income_record")
@NameStyle(Style.camelhump)
public class IncomeRecord implements Serializable {

    private static final long serialVersionUID = 1058588418635264229L;
    @Id
    private Integer id;

    private Integer uid;

    private Integer loanId;

    private Integer bidId;

    private Double bidMoney;

    private Date incomeDate;

    private Double incomeMoney;

    private Integer incomeStatus;

    private LoanInfo loanInfo;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public LoanInfo getLoanInfo() {
        return loanInfo;
    }

    public void setLoanInfo(LoanInfo loanInfo) {
        this.loanInfo = loanInfo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public Integer getBidId() {
        return bidId;
    }

    public void setBidId(Integer bidId) {
        this.bidId = bidId;
    }

    public Double getBidMoney() {
        return bidMoney;
    }

    public void setBidMoney(Double bidMoney) {
        this.bidMoney = bidMoney;
    }

    public Date getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(Date incomeDate) {
        this.incomeDate = incomeDate;
    }

    public Double getIncomeMoney() {
        return incomeMoney;
    }

    public void setIncomeMoney(Double incomeMoney) {
        this.incomeMoney = incomeMoney;
    }

    public Integer getIncomeStatus() {
        return incomeStatus;
    }

    public void setIncomeStatus(Integer incomeStatus) {
        this.incomeStatus = incomeStatus;
    }

    @Override
    public String toString() {
        return "IncomeRecord{" +
                "id=" + id +
                ", uid=" + uid +
                ", loanId=" + loanId +
                ", bidId=" + bidId +
                ", bidMoney=" + bidMoney +
                ", incomeDate=" + incomeDate +
                ", incomeMoney=" + incomeMoney +
                ", incomeStatus=" + incomeStatus +
                ", loanInfo=" + loanInfo +
                '}';
    }
}
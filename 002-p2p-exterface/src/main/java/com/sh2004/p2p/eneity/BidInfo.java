package com.sh2004.p2p.eneity;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
@Table(name = "b_bid_info")
@NameStyle(Style.camelhump)
public class BidInfo implements Serializable {
    private static final long serialVersionUID = -2062132515566626438L;
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private User user;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private Integer loanId;

    private Integer uid;

    private Double bidMoney;

    private Date bidTime;

    private Integer bidStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getBidMoney() {
        return bidMoney;
    }

    public void setBidMoney(Double bidMoney) {
        this.bidMoney = bidMoney;
    }

    public Date getBidTime() {
        return bidTime;
    }

    public void setBidTime(Date bidTime) {
        this.bidTime = bidTime;
    }

    public Integer getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(Integer bidStatus) {
        this.bidStatus = bidStatus;
    }
}
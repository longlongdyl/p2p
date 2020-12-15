package com.sh2004.p2p.eneity;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "u_finance_account")
@NameStyle(Style.camelhump)
public class FinanceAccount implements Serializable {
    private static final long serialVersionUID = 7084558216341439917L;
    @Id
    private Integer id;

    private Integer uid;

    private Double availableMoney;

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

    public Double getAvailableMoney() {
        return availableMoney;
    }

    public void setAvailableMoney(Double availableMoney) {
        this.availableMoney = availableMoney;
    }
}
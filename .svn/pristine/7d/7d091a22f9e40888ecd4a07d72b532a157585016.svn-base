package com.egean.cq_bus.IC.domain;

import com.egean.cq_bus.domain.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author shiyinzhi
 */
@Entity
@Table(name = "debitgprsdetailtbbb")
@IdClass(DebitGprsDetailtbbb.class)
public class DebitGprsDetailtbbb extends BaseEntity implements Serializable {

    /**
     *
     */
    @Id
    @Column(name = "debitsettleseq", length = 50)
    private String debitsettleseq;

    /**
     *
     */
    @Column(name = "cardid", length = 50)
    private String cardid;

    /**
     *
     */
    @Column(name = "debitdate")
    private Date debitdate;

    /**
     *
     */
    @Column(name = "POSID", length = 50)
    private String POSID;

    /**
     *
     */
    @Column(name = "debitmoney", length = 11)
    private int debitmoney;

    /**
     *
     */
    @Column(name = "CARDKIND", length = 20)
    private String CARDKIND;

    /**
     *
     */
    @Id
    @Column(name = "ORGCODE", length = 10)
    private String ORGCODE;

    /**
     *
     */
    @Column(name = "adddatetime")
    private Date adddatetime;

    /**
     *
     */
    @Column(name = "TXNTYPE", length = 11)
    private int TXNTYPE;

    /**
     *
     */
    @Column(name = "POSOPRID", length = 100)
    private String POSOPRID;

    /**
     *
     */
    @Column(name = "UPBUSID", length = 100)
    private String UPBUSID;

    /**
     *
     */
    @Column(name = "UPLINEID", length = 100)
    private String UPLINEID;

    @Transient
    private String startTime;
    @Transient
    private String endTime;

    public String getDebitsettleseq() {
        return debitsettleseq;
    }

    public void setDebitsettleseq(String debitsettleseq) {
        this.debitsettleseq = debitsettleseq;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public Date getDebitdate() {
        return debitdate;
    }

    public void setDebitdate(Date debitdate) {
        this.debitdate = debitdate;
    }

    public String getPOSID() {
        return POSID;
    }

    public void setPOSID(String POSID) {
        this.POSID = POSID;
    }

    public int getDebitmoney() {
        return debitmoney;
    }

    public void setDebitmoney(int debitmoney) {
        this.debitmoney = debitmoney;
    }

    public String getCARDKIND() {
        return CARDKIND;
    }

    public void setCARDKIND(String CARDKIND) {
        this.CARDKIND = CARDKIND;
    }

    public String getORGCODE() {
        return ORGCODE;
    }

    public void setORGCODE(String ORGCODE) {
        this.ORGCODE = ORGCODE;
    }

    public Date getAdddatetime() {
        return adddatetime;
    }

    public void setAdddatetime(Date adddatetime) {
        this.adddatetime = adddatetime;
    }

    public int getTXNTYPE() {
        return TXNTYPE;
    }

    public void setTXNTYPE(int TXNTYPE) {
        this.TXNTYPE = TXNTYPE;
    }

    public String getPOSOPRID() {
        return POSOPRID;
    }

    public void setPOSOPRID(String POSOPRID) {
        this.POSOPRID = POSOPRID;
    }

    public String getUPBUSID() {
        return UPBUSID;
    }

    public void setUPBUSID(String UPBUSID) {
        this.UPBUSID = UPBUSID;
    }

    public String getUPLINEID() {
        return UPLINEID;
    }

    public void setUPLINEID(String UPLINEID) {
        this.UPLINEID = UPLINEID;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}

package com.egean.cq_bus.project.domain;

import com.egean.cq_bus.domain.BaseEntityRmote;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

/**
 * @author shiyinzhi
 */
@Entity
@Table(name = "bus_depart_request")
public class BusDepartRequest extends BaseEntityRmote {


    /**
     *请求参数拼接字符串
     */
    @Column(name = "request_paramet")
    private String requestParamet;

    /**
     * 请求编码
     */
    @Column(name = "request_id")
    private String requestId;

    public String getRequestParamet() {
        return requestParamet;
    }

    public void setRequestParamet(String requestParamet) {
        this.requestParamet = requestParamet;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}

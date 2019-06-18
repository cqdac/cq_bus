package com.egean.cq_bus.domain;

public class Result {
    private String requestId;

    private String error;

    public Result(String requestId, String error) {
        this.requestId = requestId;
        this.error = error;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

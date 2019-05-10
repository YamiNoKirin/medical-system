package com.cluntraru.service.authority;

public class RequestRunnable implements Runnable {
    RequestType requestType;
    Object[] args;

    public RequestRunnable(RequestType requestType, Object... args) {
        this.requestType = requestType;
        this.args = args;
    }

    public void run() {
        ManagementAuthority.getInstance().makeRequest(requestType, args);
    }
}

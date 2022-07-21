package com.imorochi.chasqui.infrastructure.utils;

import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

@Contract(threading = ThreadingBehavior.IMMUTABLE)
public class NoopHostnameVerifier implements HostnameVerifier {

    public static final NoopHostnameVerifier INSTANCE = new NoopHostnameVerifier();
    @Override
    public boolean verify(String hostname, SSLSession session) {
        return true;
    }

    @Override
    public String toString() {
        return "NO_OP";
    }
}

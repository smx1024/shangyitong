package com.sx.yygh.order.api;

import com.alipay.api.CertAlipayRequest;

//@Data
//@Configuration

//@ConfigurationProperties(prefix = "alipay")
public class AlipayConfig extends CertAlipayRequest {

    private String serverUrl;
    private String appId;
    private String privateKey;
    private String format;
    private String charset;
    private String signType;
    private String certPath;
    private String alipayPublicCertPath;
    private String rootCertPath;
    private String encryptor;
    private String encryptType;
    private String proxyHost;
    private int    proxyPort;
    private    String alipayPublicKey;

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        this.alipayPublicKey = alipayPublicKey;
    }

    @Override
    public String getServerUrl() {
        return serverUrl;
    }

    @Override
    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    @Override
    public String getAppId() {
        return appId;
    }

    @Override
    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    public String getPrivateKey() {
        return privateKey;
    }

    @Override
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    @Override
    public String getFormat() {
        return format;
    }

    @Override
    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public String getCharset() {
        return charset;
    }

    @Override
    public void setCharset(String charset) {
        this.charset = charset;
    }

    @Override
    public String getSignType() {
        return signType;
    }

    @Override
    public void setSignType(String signType) {
        this.signType = signType;
    }

    @Override
    public String getCertPath() {
        return certPath;
    }

    @Override
    public void setCertPath(String certPath) {
        this.certPath = certPath;
    }

    @Override
    public String getAlipayPublicCertPath() {
        return alipayPublicCertPath;
    }

    @Override
    public void setAlipayPublicCertPath(String alipayPublicCertPath) {
        this.alipayPublicCertPath = alipayPublicCertPath;
    }

    @Override
    public String getRootCertPath() {
        return rootCertPath;
    }

    @Override
    public void setRootCertPath(String rootCertPath) {
        this.rootCertPath = rootCertPath;
    }

    @Override
    public String getEncryptor() {
        return encryptor;
    }

    @Override
    public void setEncryptor(String encryptor) {
        this.encryptor = encryptor;
    }

    @Override
    public String getEncryptType() {
        return encryptType;
    }

    @Override
    public void setEncryptType(String encryptType) {
        this.encryptType = encryptType;
    }

    @Override
    public String getProxyHost() {
        return proxyHost;
    }

    @Override
    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    @Override
    public int getProxyPort() {
        return proxyPort;
    }

    @Override
    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }
}

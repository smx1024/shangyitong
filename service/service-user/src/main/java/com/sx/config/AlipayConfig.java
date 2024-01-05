package com.sx.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

//@Data
//@Configuration
@Service
@Data
@ConfigurationProperties(prefix = "alipay")
public class AlipayConfig extends CertAlipayRequest {

    //支付宝网管
    String serverUrl;
//    String serverUrl = "https://openapi.alipay.com/gateway.do";
    //alipay.appId
//    @Value("${alipay.appId}")
    String appId;
    //私钥
//    String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC2B1EbO1rrWFzS5S1UA0cvvqs7E8fM9Jrxdq6xHWRF3xg59X0AjGU1FZrO60287/t09CGodtUKQ3sY4Mfwz4cjP43lASXOUXPI5hUtYKiltBz7WkcROhs0ZEAxunwnLAvIJhZCcIQv0J79MOnb1O8221uABtA5+NPkJrY8q486V5iokKoaFwIo3BZILLYkg5M6VsQpMWeSmW03j/w+KS/syfA/vkHFYcwCdSnhW5YSF//inArH3pkH0Lfl3pN9BuQsSgU0OAtqEbfIrErYKU5DELkuhDZqrdoAYVrZI8k9fpXUAY8AF9RQnVgsvmQJJ63kSeOHx4g0KCDvh7FjtFm3AgMBAAECggEAMd9nVXK5wml/ZTjtZmtPgcFX4OPWhk0udqHx0w0egBZT6O1dxlyxDg5RUAggUHfgCXcqEaIdVKaQf4x2u3/gosOy+N+LoC+o7Y48lyY4js3+cs+VUR7+fShKflV91q22vKvSxXlWflNGfFB+goCg2gLX2wgC3R9Tztrca1sF7xHBBKnBLGN1ffpvHsABKyAHNpN+/Ldm2aRk4Kn9Wdh4S+6uiel5zgee6NspZ+AJIy7tYhUJ9/krVobaPN+WoL9iq+WGJi2c5jrTTj79vhtWq5qjIjaMVpStZDNETRlYv3FzyetuLtffRJqiVUoeZXSewAqCNIvskom3Mv9/yXw7oQKBgQD/e0QApjm++RIdRhAPcENJtqm8Uum7AdEXSzgFBRdo069sGTBUSRenSAiYWrjYL8cgHCBcTIlq94EILt5vR/gYpgzkuBG4DWNvQdTJEblRn7OOMVME33Q89I/gW2+6YmudjkALyAEH+jriqPvuEwI7UaSQd8ljKNRZd1QVk/fNywKBgQC2ZeOW8eNH165pkLHz0ZqHlL7qSxjNke8UNieFHJYcHxsqUpR9bpUfTcFzivMF7AKCWT1KuZAZmEriloaB18mvXNVc/wh0uc/Z4GaIt4mjdgH/aERuxrDqw2IJvapk19lh1z8HxweZO7mrorgzPpYTU39eXhdV00r42Ip65GFmRQKBgHdkgLQY+pWPynJZwrmkSh3RumLjEI2vXKqZIVXfOkBn5OwMnXRWMDKhzDp+8zE8qr8Lxee8cN/HSMqtNJAmvv+t4gqlJsqvnw9oYWuR19wYWQ4TaOu3DZhK5k4ADSe6RPZ9etrGngy/WPOB6zIoOfvEvLYy9K9dblddF7N50e2fAoGBAI21aXXrpj451prTvT4F+f4eVZmoELQeDlZwoJGtnP5qOGvQTueI07jHa2X+bUOLkoN9G76Dnu4TwRJ8CjK02VAyWt5ukj5F4rOOwXq0xe3GkFTDyG8cVXV/qRqSTeEdRhna5nB/dGrb2Sv9NwkxNd38ADfr+EtI5rWhXy2NZBLVAoGBAMjIV51bMDXyZqsE0WvzZKFEekYqo6D5crs5AH0s9xquZiLE2xBRA4yu1WXVyeeFGMx70LGAsTaaQn4sHidsk9tlqcc/3XjCNH7bqlTRvkaEuQQkfdrR//+XwWc8reK8FR6YfsXMuahdaO0MbUQ4G1FxH4dcuCAIVCV2XTzYc4LI";
//    String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnFeXYCE8ThilqgmnuCWaZktpmJzDlAQ9vJRzVL6MXp2bmcvGMV9hotEYTm+T4AMa9JVxye5g6SxqQyemniX0AcT4/uXY+7t5TlvSM7yeQRzt8FIc9mX8xvAbWVan7Njg1mWbGnhOjLOLsUtj3dvge1U17M7OIqFSMdF5LVqGpU3OiDRA5exHQVY/zm0DC2iJ70bC0E41EGP4A7eUI7PeUsh8GrqmVlhyxqzonnRIpFX9AKotCjh+WO1i78vaHp0leJcchjvxxaC0VcF7Sm5wvYpcL74gkBqAeG3Og6fJUYF6vyt6pbHRABi7MPUJMjD5Gp7yNnrUUcvc456o4tynOQIDAQAB";
    String privateKey;
    String alipayPublicKey;
    String format = "json";
    //支付宝公钥
//    String  alipayPublicKey;
    String charset = "UTF-8";
    String signType = "RSA2";

//    private String certPath;//应用公钥证书路径
//    private String alipayPublicCertPath;
//    private String rootCertPath;//支付宝根证书路径
//    private String encryptor;
//    private String encryptType;
//    private String proxyHost;
//    private int proxyPort;

    @Bean
    public AlipayClient getAlipayClient() throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, "json", "UTF-8", alipayPublicKey, "RSA2");
        return alipayClient;
    }
}

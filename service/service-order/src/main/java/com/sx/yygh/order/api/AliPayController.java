package com.sx.yygh.order.api;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sx.commonutil.result.Result;
import com.sx.yygh.oss.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

@RestController
@RequestMapping("/@CrossOriginder/weixin")
public class AliPayController {

    @Autowired
    private FileService fileService;

    @RequestMapping("createNative/{orderId}")
    public Result<Object> pay(@PathVariable("orderId") String  id, HttpServletResponse servletResponse) throws IOException, WriterException {

//        String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDINUybDzm5sxbvaxRVqKT1aqwINv1uJHWYBWTqRV3d0NV1MBihbF3bs+qItLYxukO743qzrS9L6RawMQbEy+szB5B0Y2d9J0ONK6ye5SeD5I90Rzqh/wy9G10AdIp3ZAuWeSbk/39Liyyt6dHWpkxe14fASifH6MSwaxg9FNhSfhXc1TV2mR/eu4UveHkHFIdNL/kbVpE55onf9sz6i/f0ail3kKNZWekBS+6ZkUnK7uI5v+dMSnTqA2SI13Tikw3CIOhCEy0DEB6DFg7GGhaUm2k9EJ6b7+cn3V4WLd0JSPxtrpJIvUGmEndN2kX6G8u5JkNMsJU8GksXCF4YdNX9AgMBAAECggEAOA3cNenMYrbGrB/R4K3ICcJlCiPDTww8NGUGPSVQw6MNsLLXopKBsNqtxrq57DBXeIqXNyk8/cb0CD6hw4XIg1AR3NRcDElq+2KIATDtoFrk40xyGRscyPGZV2BJyGWlbOrcve7fCBqeVs8JZkzHQyKbKnZYrvr33uENN0TX0DhoQ7ve+57DvrO1eVQd8nhm3j9210LpeXQdeuNMRA8k+SckpRaxn6Hrnobu9VvQsduwq9ylHy3+MWKvoZ8bJJGNlCgFoZsEkdkRXK53BY6gGACpxW6oEMPceP6w3o+q2OAIXjq9S7S45NcphFAgIapcfcMf5XZzez3gNtWhHo9hLQKBgQDlnbsXtsiimWIC5bkB8RCfpn5GIe8o2RCWkvrmGQ1+4nMEWagNn61Qbcl0DsAy87wH93LdQRKG/gmdp85Rp8tbMha6xSpsdrz2T2BSHtvDt/6yYNCnLVpV6XlYtqDTYs0wcwK2eulQNQT1Kljw8iXyxe1NyZYyCp8v1RIH3G1zKwKBgQDfNoUS8ZyR5iMGaQiKWFnNBa/M6g982rIb/3GG0XxWfmHvKY2xhsji9eOzjw+tBXws5resIyThTdzTqyFDb0jcRLTBGi2Eh5082qFw+piN5YtuaGOM553LqVsxAryUVjUEL94iHUvu9tzlRd+OSrZhAWcdtnfrqMbp6hxJQbpndwKBgCG8fAmu7GmfeZwNXpJfdd7PGox5Xec1hU9qRvzZv3beFKIV/jAKPu+rQccQh8K0tvLAs5q4fd1ho84LIrAypBlBEb/dSi9nfrn9zZwjNozaEszolp+JdVVcGV0kcBq+0IEG1OY0xuTm9VSEJU85dMPnuscMEEpH7z2a46xOAbLDAoGAJPe8lmSZj7hTuK4QkgkL5l64vPFO21jFAsoNnUx4kUKhKaDyLKKsBpq+uXBKIle0NyOG6+VuKDpjBlNjUO3PXVurrAeP3lEvg3KaJkH6nhWUQBbd1epOQfqsdbOEU/TrFuRun6vGda9A6l/mJITPK8W1tEASIF91giwZvzA5lQUCgYAkdlpHey/JxNmKrp0fg/GzAmoY9MLFXdxx+qDTL9J60QZc12pGA9Oxzik2dAlXw3SpqKocJUcbYQjzN/8+sGQeKFyCBKM4t1zfalaMPdVU6JJCLZLZ/AbL2aGFf3ElLqh3bZGD2+yY68gOirdyQsPz9PA2ZH5jIJN5k/TnAzJJTw==";
//        String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAur79JmPv1KGARi1h5dTPzKztc7EKnERFCacxm/Rt9SqJqOr+i3iXwRfHr12cE6UxBQgzEo8lkED7OBri0KJvhQ2x/DAjHERxu2mHB1QalnZwFeAjFac8MiN1V+f67d4xiiYhKxoifAGw2/xDcHixFpncNFvZ6KooeimoYfpeC8m4uNqLrkZEAeLMVy20L4bx4CzBSoTFU4hQAQJosFxwU0S6IUivDCNeMis3WRsxzhNow7h80NhZdSGgyqn/PCqX06eRqtf+pn17ext2SheElR7si7rIyF0+nct4i7c+gS2OKYn+TfKBjehOijMP75CVgDnLlNRQIrxkug8e44W7RQIDAQAB";
//
//       String serverUrl="https://openapi-sandbox.dl.alipaydev.com/gateway.do";
//
//        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, "2021000122674485", privateKey, "json", "UTF-8", alipayPublicKey, "RSA2");
//        AlipayTradeCreateRequest request = new AlipayTradeCreateRequest();
//        AlipayTradeCreateModel model = new AlipayTradeCreateModel();
//        model.setOutTradeNo("20150320010101001");
//        model.setTotalAmount("88.88");
//        model.setSubject("Iphone6 16G");
//        request.setBizModel(model);
//        AlipayTradeCreateResponse response = null;
//        try {
//            response = alipayClient.execute(request);
//        } catch (AlipayApiException e) {
//            e.printStackTrace();
//        }
//        System.out.println(response.getBody());
//        if (response.isSuccess()) {
//            System.out.println("调用成功");
//            System.out.println("调用成功");
//            String string = JSONObject.toJSONString(response);
//            HashMap<String, String> resultMap = JSONObject.parseObject(string, HashMap.class);
//            HashMap<Object, Object> map = new HashMap<>();
////            map.put("orderId", orderId);
////            map.put("totalFee", order.getAmount());
//            map.put("resultCode", resultMap.get("result_code"));
//            map.put("codeUrl", resultMap.get("code_url")); //二维码地址
//            return "";
//        } else {
//            System.out.println("调用失败");
//            return "";
//            // sdk版本是"4.38.0.ALL"及以上,可以参考下面的示例获取诊断链接
//            // String diagnosisUrl = DiagnosisUtils.getDiagnosisUrl(response);
//            // System.out.println(diagnosisUrl);
//        }
//    }
        String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDINUybDzm5sxbvaxRVqKT1aqwINv1uJHWYBWTqRV3d0NV1MBihbF3bs+qItLYxukO743qzrS9L6RawMQbEy+szB5B0Y2d9J0ONK6ye5SeD5I90Rzqh/wy9G10AdIp3ZAuWeSbk/39Liyyt6dHWpkxe14fASifH6MSwaxg9FNhSfhXc1TV2mR/eu4UveHkHFIdNL/kbVpE55onf9sz6i/f0ail3kKNZWekBS+6ZkUnK7uI5v+dMSnTqA2SI13Tikw3CIOhCEy0DEB6DFg7GGhaUm2k9EJ6b7+cn3V4WLd0JSPxtrpJIvUGmEndN2kX6G8u5JkNMsJU8GksXCF4YdNX9AgMBAAECggEAOA3cNenMYrbGrB/R4K3ICcJlCiPDTww8NGUGPSVQw6MNsLLXopKBsNqtxrq57DBXeIqXNyk8/cb0CD6hw4XIg1AR3NRcDElq+2KIATDtoFrk40xyGRscyPGZV2BJyGWlbOrcve7fCBqeVs8JZkzHQyKbKnZYrvr33uENN0TX0DhoQ7ve+57DvrO1eVQd8nhm3j9210LpeXQdeuNMRA8k+SckpRaxn6Hrnobu9VvQsduwq9ylHy3+MWKvoZ8bJJGNlCgFoZsEkdkRXK53BY6gGACpxW6oEMPceP6w3o+q2OAIXjq9S7S45NcphFAgIapcfcMf5XZzez3gNtWhHo9hLQKBgQDlnbsXtsiimWIC5bkB8RCfpn5GIe8o2RCWkvrmGQ1+4nMEWagNn61Qbcl0DsAy87wH93LdQRKG/gmdp85Rp8tbMha6xSpsdrz2T2BSHtvDt/6yYNCnLVpV6XlYtqDTYs0wcwK2eulQNQT1Kljw8iXyxe1NyZYyCp8v1RIH3G1zKwKBgQDfNoUS8ZyR5iMGaQiKWFnNBa/M6g982rIb/3GG0XxWfmHvKY2xhsji9eOzjw+tBXws5resIyThTdzTqyFDb0jcRLTBGi2Eh5082qFw+piN5YtuaGOM553LqVsxAryUVjUEL94iHUvu9tzlRd+OSrZhAWcdtnfrqMbp6hxJQbpndwKBgCG8fAmu7GmfeZwNXpJfdd7PGox5Xec1hU9qRvzZv3beFKIV/jAKPu+rQccQh8K0tvLAs5q4fd1ho84LIrAypBlBEb/dSi9nfrn9zZwjNozaEszolp+JdVVcGV0kcBq+0IEG1OY0xuTm9VSEJU85dMPnuscMEEpH7z2a46xOAbLDAoGAJPe8lmSZj7hTuK4QkgkL5l64vPFO21jFAsoNnUx4kUKhKaDyLKKsBpq+uXBKIle0NyOG6+VuKDpjBlNjUO3PXVurrAeP3lEvg3KaJkH6nhWUQBbd1epOQfqsdbOEU/TrFuRun6vGda9A6l/mJITPK8W1tEASIF91giwZvzA5lQUCgYAkdlpHey/JxNmKrp0fg/GzAmoY9MLFXdxx+qDTL9J60QZc12pGA9Oxzik2dAlXw3SpqKocJUcbYQjzN/8+sGQeKFyCBKM4t1zfalaMPdVU6JJCLZLZ/AbL2aGFf3ElLqh3bZGD2+yY68gOirdyQsPz9PA2ZH5jIJN5k/TnAzJJTw==";
        String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAur79JmPv1KGARi1h5dTPzKztc7EKnERFCacxm/Rt9SqJqOr+i3iXwRfHr12cE6UxBQgzEo8lkED7OBri0KJvhQ2x/DAjHERxu2mHB1QalnZwFeAjFac8MiN1V+f67d4xiiYhKxoifAGw2/xDcHixFpncNFvZ6KooeimoYfpeC8m4uNqLrkZEAeLMVy20L4bx4CzBSoTFU4hQAQJosFxwU0S6IUivDCNeMis3WRsxzhNow7h80NhZdSGgyqn/PCqX06eRqtf+pn17ext2SheElR7si7rIyF0+nct4i7c+gS2OKYn+TfKBjehOijMP75CVgDnLlNRQIrxkug8e44W7RQIDAQAB";
        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setServerUrl("https://openapi-sandbox.dl.alipaydev.com/gateway.do");
        alipayConfig.setAppId("2021000122674485");
        alipayConfig.setPrivateKey(privateKey);
        alipayConfig.setFormat("json");
        alipayConfig.setAlipayPublicKey(alipayPublicKey);
        alipayConfig.setCharset("UTF8");
        alipayConfig.setSignType("RSA2");
        AlipayClient alipayClient = null;
        alipayClient = new DefaultAlipayClient(alipayConfig.getServerUrl(), alipayConfig.getAppId(), alipayConfig.getPrivateKey(), alipayConfig.getFormat(), alipayConfig.getCharset(), alipayConfig.getAlipayPublicKey(), alipayConfig.getSignType());
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();

        model.setOutTradeNo(id);
        model.setTotalAmount("88.88");
        model.setSubject("Iphone6 16G");
        request.setBizModel(model);

        AlipayTradePrecreateResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        System.out.println(response.getBody());
        if (response.isSuccess()) {
            System.out.println("调用成功");
            String string = JSONObject.toJSONString(response);
            HashMap<String, String> resultMap = JSONObject.parseObject(string, HashMap.class);
            HashMap<Object, Object> map = new HashMap<>();
//            map.put("orderId", orderId);
//            map.put("totalFee", order.getAmount());

            String qrCode = resultMap.get("qrCode");
            String filePath = writeToServletFile(qrCode);

            map.put("orderId", id);
            map.put("codeUrl",qrCode);
            System.out.println(resultMap.get("qrCode"));
            return Result.ok(map);
        } else {
            System.out.println("调用失败");
            return Result.fail();
        }

    }

    public  String writeToServletFile(String contents) {
        try {
            Map<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF8");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE,
                    200, 200, hints);

            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            // 将 BufferedImage 转换为 byte 数组
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", baos);
            byte[] bytes = baos.toByteArray();

// 将 byte 数组封装成 MultipartFile 对象
            MultipartFile multipartFile = new MockMultipartFile("file",
                    "filename.jpg", "image/jpeg", new ByteArrayInputStream(bytes));
            String httpath = fileService.upload(multipartFile);
            return httpath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}




package com.sx.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayOpenAppQrcodeCreateModel;
import com.alipay.api.request.AlipayOpenAppQrcodeCreateRequest;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoAuthRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoAuthResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.sx.commonutil.helper.JwtHelper;
import com.sx.service.UserInfoService;
import com.sx.yygh.model.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller

@RequestMapping("/admin/user")
public class AliOauthLoginController {
    @Autowired
    private UserInfoService userInfoSerice;
    @Autowired
//    AlipayConfig alipayConfig;


    @ResponseBody
    @RequestMapping(value = "login")
    public String AliLogin() throws Exception {
        AlipayClient alipayClient = getAlipayClient();
        AlipayUserInfoAuthRequest request = new AlipayUserInfoAuthRequest();
        AlipayOpenAppQrcodeCreateRequest request1 = new AlipayOpenAppQrcodeCreateRequest();

        AlipayOpenAppQrcodeCreateModel model = new AlipayOpenAppQrcodeCreateModel();
        request.setBizContent("{" +
                "      \"scopes\":[" +
                "        \"auth_base\"" +
                "      ]," +
                "\"state\":\"init\"" +
                "  }");
        AlipayUserInfoAuthResponse response = null;
        try {
            response = alipayClient.pageExecute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        //获取需提交的form表单

        String submitFormData = response.getBody();
        System.out.println(submitFormData);
        return submitFormData;
    }


    @RequestMapping("callback")
    public String callback(@RequestParam(value = "auth_code") String code) throws Exception {

        AlipayClient alipayClient = getAlipayClient();
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setCode(code);
        request.setGrantType("authorization_code");
        request.setRefreshToken("201208134b203fe6c11548bcabd8da5bb087a83b");
        AlipaySystemOauthTokenResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        System.out.println(response.getBody());
        if (response.isSuccess()) {
            System.out.println("调用成功1");
            String accessToken = response.getAccessToken();
            String userId = response.getUserId();
            UserInfo userInfo = userInfoSerice.getById(userId);
            if (ObjectUtils.isEmpty(userInfo)) {
                getUserBytoken(accessToken);
            } else {
                //查到登录人信息，直接登录

            }

            return null;
        } else {
            System.out.println("调用失败1");
            return null;
        }
    }

    public String getUserBytoken(String accessToken) throws Exception {
        AlipayClient alipayClient = getAlipayClient();
        AlipayUserInfoShareRequest request = new AlipayUserInfoShareRequest();
        AlipayUserInfoShareResponse response = null;
        try {
            response = alipayClient.execute(request, accessToken);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if (response.isSuccess()) {
            System.out.println("调用成功2");
            String userId = response.getUserId();
            //昵称
            String nickName = response.getNickName();
            //头像
            String avatar = response.getAvatar();

            UserInfo userInfo = new UserInfo();
            userInfo.setOpenid(userId);
            userInfo.setNickName(nickName);
            userInfo.setStatus(1);
            //保存数据
            userInfoSerice.save(userInfo);

            //返回name和token字符串
            Map<String, String> map = new HashMap<>();
            String name = userInfo.getName();
            if (StringUtils.isEmpty(name)) {
                name = userInfo.getNickName();
            }
            if (StringUtils.isEmpty(name)) {
                name = userInfo.getPhone();
            }
            map.put("name", name);

            //判断userInfo是否有手机号，如果手机号为空，返回openid
            //如果手机号不为空，返回openid值是空字符串
            //前端判断：如果openid不为空，绑定手机号，如果openid为空，不需要绑定手机号
            if (StringUtils.isEmpty(userInfo.getPhone())) {
                map.put("openid", userInfo.getOpenid());
            } else {
                map.put("openid", "");
            }
            //使用jwt生成token字符串
            String token = JwtHelper.createToken(userInfo.getId(), name);
            map.put("token", token);

            //TODO:跳转到前端页面
//            return "redirect:" + ConstantWxPropertiesUtils.YYGH_BASE_URL + "/weixin/callback?token="+map.get("token")+ "&openid="+map.get("openid")+"&name="+URLEncoder.encode(map.get("name"),"utf-8");

            return "a";
        } else {
            System.out.println("调用失败2");
            return null;
        }
    }

    public AlipayClient getAlipayClient() throws Exception {
        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCPD+1fRuCCuB6kTRZTAE7YSQDD4NXHBbP2Ni3cSXK3OhRyz/+HPF9K/G/LSYxroTfgD9EA5Cevzl+8Sh2dDma8/KenYrC93dd28JFTDn6Os37Q/LrA2ilwdfwoYFxai1GHkqFS1KbqJAz5I4aLTjqI3QsMjJDtvxD5DBsX4fP5l/qrtVcvxHrSgbO5kXDGDUx+wsN29cNOk27N41eqOH86mlJpqAOeFC+TkfbA50MiQj3S/lM5609r5wSoZeAhZ1DLui5VQQkyVZ7y2Tm9ymFKbFzbgWmYg9Izc9gpWe2JBncN8KaGWxMtXVKuZCC6aFFq5uMC0s2Ks8WMV2zNea1JAgMBAAECggEASrKLQ4MzeK2AOlORw3bWtDGx95SuqoM8yuLRIUMsZaUqxiP6jPcl3nZePOvDKPN9xD0d6PQ4EKCV9i9QtQ+u7yCQvWJXXFu3w/+qMY7Q39uy7JX5QPboSjMgnUhrEE/MGxihqSklmf2G1JMLDjOjdpDL0xqWFbz8tLzwbJDrHbfH8/3ROys3vxg0u2YS84+SCkJ+bMgAYs5WZ7mBQqSn4E5LbPCvGlY4oyYLN/tzccrSfKAnDAEE9ywR/f9R80e2Xls2VncRB/XyhbIwaK5nkDFKvr1FL5uY1GYMpWrg+1qW195c7tKO3AJWYeOdZq9b4inlMKV1z3Cdk/8e3yM33QKBgQDIxTHNhMnYpmCrNd4RuioN+D7mrUQSf/sAkLLISZ5r5uy++/TNAXRX/xeHfhFl2CnNwqJfMHNOkDYilWsD6e3s4o9vK/6bXuzIAi0f3UofX+h160p3YsQu/cu516fbJbhi2gBeyoVSAUuBCqbmd/8LHKrl8yRtbn1dyI4KY5B1nwKBgQC2asTDz+NWbDLXAbVX/KT+91udptnRlW0NizJz8zgx26fNaenqbEF/IHB7WbznYyP+gMhBAK2GNaUZVHfdSDJ+S+wZ6xZl9n046roTQc2edjpqHSd7pf+dGt4K8l2sU8rKGRJtDmEOrk0gitlifb8h4/zWPLStvl1nog5a1VNkFwKBgDCxmxWkiiwQlkuQIg8cnsyKk/7wsRzni2Iktifbqx+yf2MGyuXZ33TkOrmy+tc/qUvtU+JQZvb8BRU4Nh9Z4knc3TryaasXR8hutrEOw8Z+RWwsKABypPBcv8eQJXcCXAklpIdZCSLNi5l9kuM84U+5oT1qTkB4VtGjLcgZQ9UzAoGBAJ3RM1hhM8KiKz3eiYCM4EaBxG5ZWts61rU/9y9fff+MlNlfR3N7oxKUAxyEn6zkFdDqo9Ww+29AXAzVCjqUarLfhm1J+P/Px7iFQuTEKlts187UWENecw39n5qC4s3jVG+ST2GwbgHghHOiDL5sIf71GtpSfNJP/slY5+hn5RSTAoGAWPVzD9lqptFR24hMSIg16zPwlm+u48oezdvPAe3224LBD70stC2bTqKwqQaQYcDgqf4U6n61ojGuIwq75iXA9oGJDlEPAfBnRkGpaYlkwlyT1agBVp9WDrGYQCVI4AMk9hR34HGIcPhpRpjo4Q6nGF0/HMq1K63YLvrWh39iKJ8=";
        String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnFeXYCE8ThilqgmnuCWaZktpmJzDlAQ9vJRzVL6MXp2bmcvGMV9hotEYTm+T4AMa9JVxye5g6SxqQyemniX0AcT4/uXY+7t5TlvSM7yeQRzt8FIc9mX8xvAbWVan7Njg1mWbGnhOjLOLsUtj3dvge1U17M7OIqFSMdF5LVqGpU3OiDRA5exHQVY/zm0DC2iJ70bC0E41EGP4A7eUI7PeUsh8GrqmVlhyxqzonnRIpFX9AKotCjh+WO1i78vaHp0leJcchjvxxaC0VcF7Sm5wvYpcL74gkBqAeG3Og6fJUYF6vyt6pbHRABi7MPUJMjD5Gp7yNnrUUcvc456o4tynOQIDAQAB";
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "2021004117619151", privateKey, "json", "GBK", alipayPublicKey, "RSA2");
        return alipayClient;
    }


}

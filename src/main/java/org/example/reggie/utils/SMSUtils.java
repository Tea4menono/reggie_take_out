package org.example.reggie.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Value;

/**
 * 短信发送工具类
 */
public class SMSUtils {

    @Value("${reggie.accessKey}")
    private static String accessKey;


    @Value("${reggie.accessSecret}")
    private static String accessSecret;

    /**
     * 发送短信
     *
     * @param signName     签名
     * @param templateCode 模板
     * @param phoneNumbers 手机号
     * @param param        参数
     */
    public static void sendMessage(String signName, String templateCode, String phoneNumbers, String param) {
        Dotenv dotenv = Dotenv.load();
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", dotenv.get("ACCESS_KEY"), dotenv.get("ACCESS_PASSWORD"));
        IAcsClient client = new DefaultAcsClient(profile);

        SendSmsRequest request = new SendSmsRequest();
        request.setSysRegionId("cn-hangzhou");
        request.setPhoneNumbers(phoneNumbers);
        request.setSignName(signName);
        request.setTemplateCode(templateCode);
        request.setTemplateParam("{\"code\":\"" + param + "\"}");
        try {
            SendSmsResponse response = client.getAcsResponse(request);
            System.out.println(response);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}

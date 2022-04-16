package com.msy.hotel.controller;

import com.msy.hotel.vo.ResCode;
import com.msy.hotel.vo.ResponseModel;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@CrossOrigin
public class CommonController {

    @RequestMapping("/getCode")
    public ResponseModel getCode(String phone){
            try {
                Credential cred = new Credential("AKIDLSN3btY7wUorPP9SBdojNTJuKI7XYZkn", "2ymaEiwYr43o35MkLiohOJ0c6NhqnZu1");
                ClientProfile clientProfile = new ClientProfile();
                SmsClient client = new SmsClient(cred, "", clientProfile);
                SendSmsRequest req = new SendSmsRequest();
                String appid = "1400290787";
                req.setSmsSdkAppid(appid);
                String sign = "会写程序的猪";
                req.setSign(sign);
                String templateID = "482510";
                req.setTemplateID(templateID);
                String[] phoneNumbers = {"+86"+phone};
                req.setPhoneNumberSet(phoneNumbers);
                String str="012345678998765432101234567890";
                Random rn=new Random();
                StringBuffer sb=new StringBuffer();
                for(int i=0;i<4;i++){
                    sb.append(str.charAt(rn.nextInt(str.length())));
                }

                String[] templateParams = {sb.toString()};
                req.setTemplateParamSet(templateParams);
                SendSmsResponse res = client.SendSms(req);

                // 输出 JSON 格式的字符串回包
                System.out.println(SendSmsResponse.toJsonString(res));
                System.out.println(res);
                // 可以取出单个值，您可以通过官网接口文档或跳转到 response 对象的定义处查看返回字段的定义
//            System.out.println(res.getRequestId());
                if(res.getSendStatusSet()[0].getCode().equals("Ok")){
                    return ResponseModel.success(ResCode.SUCCESS,sb);
                }
            } catch (TencentCloudSDKException e) {
                e.printStackTrace();
            }
            return ResponseModel.fail(ResCode.ERROR);
    }
}

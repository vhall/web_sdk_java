import com.jayway.jsonpath.JsonPath;
import vhall.User;
import vhall.Webinar;

import java.io.File;
import java.util.Date;
import java.util.HashMap;

public class TestSdk {
    public static void main(String[] args)
    {
        HashMap<String, Object> configMap = new HashMap<String, Object>();
        configMap.put("app_key", "dc16c76acfd9b0a82fb3f3039d7bcfe7"); // app_key 请像技服人员申请
        configMap.put("secret_key", "1052ffd2b5c33283e90093dbbe706607");  // secret_key 请像技服人员申请
        configMap.put("request_domain", "http://t.e.vhall.com");

        Webinar webinarObj = new Webinar(configMap);
        User userObj = new User(configMap);

        HashMap<String, Object> paramCreate = new HashMap<String, Object>();
        HashMap<String, Object> paramActiveImage = new HashMap<String, Object>();
        HashMap<String, Object> paramFetch = new HashMap<String, Object>();
        HashMap<String, Object> paramList = new HashMap<>();
        HashMap<String, Object> paramCreateUser = new HashMap<String, Object>();
        HashMap<String, Object> paramCurrent = new HashMap<String, Object>();

        paramCreate.put("start_time", "123456789");
        paramCreate.put("subject", "take a test");

        paramCreateUser.put("third_user_id", "third_sdk_test_2");
        paramCreateUser.put("pass", "gaoyansing");
        paramCreateUser.put("local_head", new File("").getAbsolutePath() + "/resources/vhall.png");
        paramCurrent.put("begintime", "2016-7-12 10:12:11");
        paramCurrent.put("endtime", "2017-5-12 11:12:11");
        paramCurrent.put("user_id", "461");


        try {
            String resultList = userObj.register(paramCreateUser);
            String result = userObj.update(paramCreateUser);
            userObj.dump(resultList);
            userObj.dump(result);

            String concurrentAudience = userObj.concurrentAudience(paramCurrent);
            String concurrentDate = userObj.concurrentDate(paramCurrent);
            userObj.dump(concurrentAudience);
            userObj.dump(concurrentDate);

            String resultCreateUser = userObj.register(paramCreateUser);
            webinarObj.dump(resultCreateUser);

            String resultCreate = webinarObj.create(paramCreate);
//            // 请使用绝对路径获取文件
            paramActiveImage.put("image", new File("").getAbsolutePath() + "/resources/vhall.png");
            paramActiveImage.put("webinar_id", JsonPath.read(resultCreate, "$.data"));
//
            String resultActiveImage = webinarObj.activeimage(paramActiveImage);
//
            paramFetch.put("webinar_id", JsonPath.read(resultCreate, "$.data"));
            paramFetch.put("fields", "subject, start_time");
//
            String resultFetch = webinarObj.fetch(paramFetch);
//
            webinarObj.dump(JsonPath.read(resultCreate, "$.data"));
            webinarObj.dump(JsonPath.read(resultActiveImage, "$.data"));
            webinarObj.dump(JsonPath.read(resultFetch, "$.data"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

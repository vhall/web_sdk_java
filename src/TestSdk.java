import com.jayway.jsonpath.JsonPath;
import vhall.Webinar;

import java.io.File;
import java.util.Date;
import java.util.HashMap;

public class TestSdk {
    public static void main(String[] args)
    {
        HashMap<String, Object> configMap = new HashMap<String, Object>();
        configMap.put("app_key", "xxxxxxxxxx"); // app_key 请像技服人员申请
        configMap.put("secret_key", "xxxxxxxxxx");  // secret_key 请像技服人员申请
        configMap.put("request_domain", "http://e.vhall.com");

        Webinar webinarObj = new Webinar(configMap);

        HashMap<String, Object> paramCreate = new HashMap<String, Object>();
        HashMap<String, Object> paramActiveImage = new HashMap<String, Object>();
        HashMap<String, Object> paramFetch = new HashMap<String, Object>();
        HashMap<String, Object> paramList = new HashMap<>();

        paramCreate.put("start_time", webinarObj.toString(new Date().getTime()).substring(0,10)); // 时间固定格式为10位长度时间戳
        paramCreate.put("subject", "take a test again");

        try {
            String resultList = webinarObj.list(paramList);
            webinarObj.dump(resultList);

            String resultCreate = webinarObj.create(paramCreate);
            // 请使用绝对路径获取文件
            paramActiveImage.put("image", new File("").getAbsolutePath() + "/resources/vhall.png");
            paramActiveImage.put("webinar_id", JsonPath.read(resultCreate, "$.data"));

            String resultActiveImage = webinarObj.activeimage(paramActiveImage);

            paramFetch.put("webinar_id", JsonPath.read(resultCreate, "$.data"));
            paramFetch.put("fields", "subject, start_time");

            String resultFetch = webinarObj.fetch(paramFetch);

            webinarObj.dump(JsonPath.read(resultCreate, "$.data"));
            webinarObj.dump(JsonPath.read(resultActiveImage, "$.data"));
            webinarObj.dump(JsonPath.read(resultFetch, "$.data"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

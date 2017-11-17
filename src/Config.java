import java.util.HashMap;

public class Config {

    /**
     * 获取当前配置文件
     * @return
     */
    public static HashMap getConfig()
    {
        HashMap<String, Object> configMap = new HashMap<String, Object>();
        configMap.put("app_key", "dc16c76acfd9b0a82fb3f3039d7bcfe7");
        configMap.put("secret_key", "1052ffd2b5c33283e90093dbbe706607");
        configMap.put("request_domain", "http://d.vhall.com");

        return configMap;
    }

}

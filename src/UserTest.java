import vhall.User;

import java.io.File;
import java.util.HashMap;

public class UserTest {
    private static User userObj;

    public static void main(String[] param)
    {
        userObj = new User(Config.getConfig());

        try {
            // 测试创建
            testCreateUser("third_user_id_100");
            // 测试更新
            testUpdateUser("third_user_id_100");
            // 测试查询用户每天并发数
            testCurrentData();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testCreateUser(String third_user_id) throws Exception
    {
        HashMap<String, Object> paramCreateUser = new HashMap<String, Object>();
        paramCreateUser.put("third_user_id", third_user_id);
        paramCreateUser.put("pass", "gaoyansing");
        paramCreateUser.put("local_head", new File("").getAbsolutePath() + "/resources/vhall.png");

        String resultList = userObj.register(paramCreateUser);
        userObj.dump(resultList);
    }

    public static void testUpdateUser(String third_user_id) throws Exception
    {
        HashMap<String, Object> paramCreateUser = new HashMap<String, Object>();
        paramCreateUser.put("third_user_id", third_user_id);
        paramCreateUser.put("pass", "gaoyansing");
        paramCreateUser.put("local_head", new File("").getAbsolutePath() + "/resources/vhall.png");

        String resultList = userObj.update(paramCreateUser);
        userObj.dump(resultList);
    }

    public static void testCurrentData() throws Exception
    {
        HashMap<String, Object> paramCurrent = new HashMap<String, Object>();
        paramCurrent.put("begintime", "2016-7-12 10:12:11");
        paramCurrent.put("endtime", "2017-5-12 11:12:11");
        paramCurrent.put("user_id", "461");

        String resultCurrentData = userObj.concurrentDate(paramCurrent);
        userObj.dump(resultCurrentData);
    }

}

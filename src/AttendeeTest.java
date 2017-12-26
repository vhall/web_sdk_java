import vhall.Attendee;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashMap;

public class AttendeeTest {
    private static Attendee attendee;

    public static void main(String[] param)
    {
        attendee = new Attendee(Config.getConfig());

        try {
            testAddWhiteList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testAddWhiteList() throws Exception
    {
        HashMap<String, Object> paramAddWhiteList = new HashMap<String, Object>();
        paramAddWhiteList.put("webinar_id", 498964540);

        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i <=10; i++) {
            JSONObject tmpObj = new JSONObject();
            tmpObj.put("name", "name" + i);
            tmpObj.put("industry", "industry" + i);
            tmpObj.put("email", "email@sina.com" + i);
            tmpObj.put("phone", "1811024866" + i);

            jsonArray.put(tmpObj);
        }

        paramAddWhiteList.put("list", jsonArray);
        String result = attendee.addWhitelist(paramAddWhiteList);

        attendee.dump(result);
    }

}

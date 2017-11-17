import com.jayway.jsonpath.JsonPath;
import vhall.Webinar;

import java.io.File;
import java.util.HashMap;

/**
 * Created by NelsonKing on 2017/7/7.
 */
public class WebinarTest {
    private static Webinar webinarObj;

    public static void main(String[] args)
    {
        webinarObj = new Webinar(Config.getConfig());
        try {
            testCreate();
            testReport(483868602);
            testActiveImage(483868602);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testReport(Integer webinarId) throws Exception
    {
        HashMap<String, Object> paramReport = new HashMap<String, Object>();

        paramReport.put("webinar_id", webinarId);
        paramReport.put("time_span", 4);

        String resultList = webinarObj.report(paramReport, true);
        webinarObj.dump(resultList);
    }

    public static Integer testCreate() throws Exception
    {
        HashMap<String, Object> paramCreate = new HashMap<String, Object>();
        paramCreate.put("start_time", "123456789");
        paramCreate.put("subject", "take a test");

        String resultList = webinarObj.create(paramCreate);

        webinarObj.dump(resultList);
        return Webinar.parseInt(JsonPath.read(resultList, "$.data").toString());
    }

    public static void testActiveImage(Integer webinarId) throws Exception
    {
        HashMap<String, Object> paramActiveImage = new HashMap<String, Object>();
        paramActiveImage.put("image", new File("").getAbsolutePath() + "/resources/vhall.png");
        paramActiveImage.put("webinar_id", webinarId);

        String resultActiveImage = webinarObj.activeimage(paramActiveImage);

        webinarObj.dump(JsonPath.read(resultActiveImage, "$.data"));
    }
}

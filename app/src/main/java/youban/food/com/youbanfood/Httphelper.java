package youban.food.com.youbanfood;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mr.wang on 2015/3/18.
 */
public class Httphelper {
    private static int Timeout = 5000;
    public static String getdowntext(String urlstr){
        String resultString = null;
        BufferedReader bufferedReader = null;
        StringBuffer sbuffer = new StringBuffer();
        try{
            URL url = new URL(urlstr);
            HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();
            httpConn.setConnectTimeout(Timeout);
            bufferedReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            while ((resultString = bufferedReader.readLine())!=null){
                sbuffer.append(resultString);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                bufferedReader.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return sbuffer.toString();
    }
}

package youban.food.com.youbanfood;

/**
 * Created by mr.wang on 2015/3/18.
 */
public class Indatetask {
    public String downtext(){
        String alistdate = null;
        String url="http://www.youban.com/music/phone_class.php?type=1";
        String jsonstring = Httphelper.getdowntext(url);
        System.out.println("返回:"+jsonstring);
        return jsonstring;
    }
}


package youban.food.com.youbanfood;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by mr.wang on 2015/3/17.
 */
public class ButtonClick {
    private Activity currentActivity,searchActivity;
    public void searchClick(Activity currentActivity,Activity searchActivity){
        this.currentActivity = currentActivity;
        this.searchActivity = searchActivity;
//        Intent intent = new Intent(currentActivity,searchActivity);

    }
    public void toask(Context mContext,String text){
        Toast.makeText(mContext,text,Toast.LENGTH_SHORT).show();
    }
}

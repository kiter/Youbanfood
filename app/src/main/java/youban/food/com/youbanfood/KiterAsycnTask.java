package youban.food.com.youbanfood;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by mr.wang on 2015/3/19.
 */
public class KiterAsycnTask extends AsyncTask<String,Integer,String> {
    ProgressBar myprogressBar = null;
    Context myContext = null;
    public KiterAsycnTask(ProgressBar progressBar,Context context) {
        myContext = context;
        myprogressBar =progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        myprogressBar.setProgress(0);
        Log.e("Loading Start","loadstart");
//        Toast.makeText(myContext,"loading start...",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(String... params) {
        String ret = null;
        String Begin=params[0];
        String End = params[1];
        Log.e("ASYCN start",Begin);
        for (int i=0;i<10;i++){
            publishProgress(i);
            try {
                Thread.sleep(500);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        Log.e("ASYCN end",End);
        ret="loading end……";
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Toast.makeText(myContext,s,Toast.LENGTH_SHORT).show();
        Log.e("loading bar",s+"||complete");
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        int progress = myprogressBar.getMax()/10*(values[0]+1);
        Log.e("ASYCN Update","Progress"+progress);
        myprogressBar.setProgress(progress);
    }
}

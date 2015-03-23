package youban.food.com.youbanfood;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {
    /*
    * if extends ActionBarActivity,window.feature_no_title  of no avail;
    * */
    private ImageButton searchbtn;
    private ImageView foodlogo;
    private ImageButton backtop;
    private ScrollView findexscr;
    private int index=1;
    private ButtonClick btnclick;
    private TextView tsttext;
    private ProgressBar myprogressBar;
    private TextView asyncupdate;

    private Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String lstring =(String) msg.obj;
            Log.e("通过新线程打印出来的",lstring);
            tsttext.setText(lstring);
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//No title
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        searchbtn = (ImageButton)findViewById(R.id.search_button);
        foodlogo =(ImageView) findViewById(R.id.foodlogo);
        backtop =(ImageButton) findViewById(R.id.backTop);
        backtop.getBackground().setAlpha(100);
        findexscr =(ScrollView) findViewById(R.id.scrollView);
        tsttext =(TextView) findViewById(R.id.texttes);
//        asyncupdate =(TextView) findViewById(R.id.asyncupdate);
        myprogressBar = (ProgressBar)findViewById(R.id.loadtiao);
//        tsttext.setText(R.string.setingtext);
        foodlogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnclick = new ButtonClick();
                btnclick.toask(getApplicationContext(),"this logo");
            }
        });
         searchbtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 btnclick.toask(getApplicationContext(),"this is search");
             }
         });
        backtop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnclick = new ButtonClick();
                btnclick.toask(getApplicationContext(),"this is backTop");
                findexscr.smoothScrollTo(0,0);
            }
        });
        findexscr.setOnTouchListener(new InscrollTml());
        new Thread(){
            @Override
            public void run() {
                //调用新线程返回数据;
                String rundata = new Indatetask().downtext();
                Message msg = new Message();
                msg.what=0;
                msg.obj = rundata;
                mhandler.sendMessage(msg);
                super.run();
            }
        }.start();
        new KiterAsycnTask(myprogressBar,getApplicationContext()).execute("Begin", "End");

       /*退出小图标显示在通行栏的
        int icon = R.drawable.youbanlogo;
        CharSequence tickerText = "NOtification01";
        long when = System.currentTimeMillis();
        Notification notification = new Notification(icon,tickerText,when);
        RemoteViews contentView = new RemoteViews(getPackageName(),R.layout.costom_notification);
        contentView.setImageViewResource(R.id.image,R.drawable.youbanlogo);
        contentView.setTextViewText(R.id.title,"This is Title");
        contentView.setTextViewText(R.id.text,"This is desc");
        notification.contentView = contentView;

        Intent notificationIntent = new Intent(this,MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(MainActivity.this, 0, notificationIntent, 0);
        notification.contentIntent = contentIntent;
        String NS = Context.NOTIFICATION_SERVICE;
        NotificationManager notificationManager =(NotificationManager)getSystemService(NS);
        notificationManager.notify(0,notification);
        */
    }
    //不需要返回的
    public void downtext(){
        String url="http://www.youban.com/music/phone_class.php?type=1";
        String jsonstring = Httphelper.getdowntext(url);
        System.out.println("返回:"+jsonstring);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            ButtonClick mucli = new ButtonClick();
            mucli.toask(this,"click menu seting");
            return true;
        }        return super.onOptionsItemSelected(item);
    }
    private class InscrollTml implements View.OnTouchListener{
        @Override
        public boolean onTouch(View v, MotionEvent mev) {
            switch (mev.getAction()){
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    int movy = v.getScrollY();
                    System.out.print("Y zhi"+movy);
                    if(movy==0){
                        backtop.setVisibility(View.GONE);
                    }else{
                        backtop.setVisibility(View.VISIBLE);
                    }
                    break;
            }
            return false;
        }
    }
}

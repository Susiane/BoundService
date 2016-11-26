package br.com.teste.boundservice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LocalService mService;
    boolean mBound = false;
    private Intent intent;
    public static final int ID_NOTIFICACAO = 123456;
    private static final String PREF_NAME = "MainActivityPreferences";
    private int count1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this, LocalService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

        /*Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("Minha primeira Notificação")
                .setContentText("Que lindo!")
                .setSmallIcon(R.drawable.de_uma_pausa_menu_icon)
                .setContentIntent(pendingIntent)
                .build();



        NotificationManager maneger = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        maneger.notify(ID_NOTIFICACAO,notification);*/

        /*Notification notification = new Notification(R.drawable.icon, getText(R.string.ticker_text),
        System.currentTimeMillis());
Intent notificationIntent = new Intent(this, ExampleActivity.class);
PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
notification.setLatestEventInfo(this, getText(R.string.notification_title),
        getText(R.string.notification_message), pendingIntent);
startForeground(ONGOING_NOTIFICATION_ID, notification);*/


    }

    public void contador(View view){
        SharedPreferences sp1 = getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        count1 = sp1.getInt("count_1",0);
        SharedPreferences.Editor editor = sp1.edit();
        editor.putInt("count_1", count1+1);
        editor.commit();
        Log.d("TAG","count_1 : "+ count1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TAG", "Passeo pelo onStart da Activity");
        // Bind to LocalService

        //startService(intent);

    }



    @Override
    protected void onStop() {
        super.onStop();


    }

    public void iniciaServico(View view) {
        startService(intent);
    }

    public void paraServico(View view){
        if (mBound) {
            unbindService(mConnection);
            stopService(intent);
            mBound = false;
        }
    }


    /** Called when a button is clicked (the button in the layout file attaches to
     * this method with the android:onClick attribute) */
    public void onButtonClick(View v) {
        if (mBound) {
            // Call a method from the LocalService.
            // However, if this call were something that might hang, then this request should
            // occur in a separate thread to avoid slowing down the activity performance.
            int num = mService.getRandomNumber();
            int count = mService.getCount();
            Toast.makeText(this, "number: " + num + " Count :" + count, Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, "number: " + num , Toast.LENGTH_SHORT).show();

        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            LocalService.LocalBinder binder = (LocalService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    public void abreFragment(View view){
        Fragment fragment = new TesteFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame1, fragment).addToBackStack(null).commit();

    }

    public void abreFragmentDois(View view){
        Fragment fragment = new TesteFragment2();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame1, fragment).addToBackStack(null).commit();

    }
}

package br.com.teste.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Susiane on 22/05/2016.
 */
public class CronoService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    /*public static final int PARAR = 0;
    public static final int EMPEZAR = 1;
    public static final int ESTABLECER_TIEMPO = 2;

    private static final String TAG = "BroadcastService";

    public static final String BROADCAST_ACTION = "jose.planilla.mostrartiempo";

    final Messenger mMessenger = new Messenger(new IncomingHandler());

    private final Handler handler = new Handler();
    private Intent intent;
    private long mDec;
    private long mTotalMilis;
    private long mLastMilis;
    private long mElapsedTime;
    private long mCurrentMilis;
    private int mSeconds;
    private int mMin;

    private Runnable sendUpdatesToUI = new Runnable() {
        public void run() {
            mCurrentMilis = System.currentTimeMillis();
            mElapsedTime = mCurrentMilis - mLastMilis;
            mLastMilis = mCurrentMilis;
            mTotalMilis += mElapsedTime;
            DisplayLoggingInfo();
            handler.postDelayed(this, 100); // 0.1 seconds
            Log.d("run()", String.valueOf(mTotalMilis));
        }
    };


    I've developed an own implementation of a Chronometer. I did the follow:

    Create a Service (CronoService), that use a Runnable object that execute the thread. The Runnable will loop each 0.1 secs.
    Messenger Object that will receive messages from Ui to Start, Pause or Resume the Chronometer.
    Intent that will broadcast the time after each loop of the Runnable object.
    The code is:

    public class CronoService extends Service {

        public static final int PARAR = 0;
        public static final int EMPEZAR = 1;
        public static final int ESTABLECER_TIEMPO = 2;

        private static final String TAG = "BroadcastService";

        public static final String BROADCAST_ACTION = "jose.planilla.mostrartiempo";
        final Messenger mMessenger = new Messenger(new IncomingHandler());

        private final Handler handler = new Handler();
        private Intent intent;
        private long mDec;
        private long mTotalMilis;
        private long mLastMilis;
        private long mElapsedTime;
        private long mCurrentMilis;
        private int mSeconds;
        private int mMin;

        private Runnable sendUpdatesToUI = new Runnable() {
            public void run() {
                mCurrentMilis = System.currentTimeMillis();
                mElapsedTime = mCurrentMilis - mLastMilis;
                mLastMilis = mCurrentMilis;
                mTotalMilis += mElapsedTime;
                DisplayLoggingInfo();
                handler.postDelayed(this, 100); // 0.1 seconds
                Log.d("run()", String.valueOf(mTotalMilis));
            }
        };

        private void DisplayLoggingInfo() {
            String tiempo;

            mDec = mTotalMilis % 1000;
            mDec = mDec / 100;
            mSeconds = (int) (mTotalMilis / 1000);
            mMin = mSeconds / 60;
            mSeconds = mSeconds % 60;
            tiempo = "" + mDec;

            if(mSeconds < 10)
                tiempo = ":0" + mSeconds + "." + tiempo;
            else
                tiempo = ":" + mSeconds + "." + tiempo;

            if(mMin < 10 )
                tiempo = "0" + mMin + tiempo;
            else
                tiempo = mMin + tiempo;

            intent.putExtra("tiempo", tiempo);
            intent.putExtra("milis", mTotalMilis);
            sendBroadcast(intent);
        }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg){
            switch(msg.what) {
                case PARAR:
                    pause();
                    break;
                case EMPEZAR:
                    resume();
                    break;
                case ESTABLECER_TIEMPO:
                    mTotalMilis = (Long) msg.obj;
                    break;
                default:
                    super.handleMessage(msg);
            }
        }*/
}

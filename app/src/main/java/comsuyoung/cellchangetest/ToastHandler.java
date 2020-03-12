package comsuyoung.cellchangetest;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

public class ToastHandler {
    private Context mContext;
    private Handler mHandler;
    private PhoneStateListener phoneStateListener;

    public ToastHandler(Context _context) {
        this.mContext = _context;
        this.mHandler = new Handler();
    }

    private void runRunnable(final Runnable _runnable) {
        Thread thread = new Thread() {
            public void run() {
                mHandler.post(_runnable);
            }
        };

        thread.start();
        thread.interrupt();
        thread = null;
    }


    public void showToast(final int _resID, final int _duration) {
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // Get the text for the given resource ID
                String text = mContext.getResources().getString(_resID);

                Toast.makeText(mContext, text, _duration).show();
            }
        };

        runRunnable(runnable);
    }

    public void showToast(final CharSequence _text, final int _duration) {
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Toast.makeText(mContext, _text, _duration).show();
            }
        };

        runRunnable(runnable);
    }
}
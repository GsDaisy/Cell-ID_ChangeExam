package comsuyoung.cellchangetest;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.telephony.CellInfo;
import android.telephony.CellInfoLte;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;

public class PhoneStateListener extends android.telephony.PhoneStateListener {

    private Context context;
    private final int events = android.telephony.PhoneStateListener.LISTEN_CELL_LOCATION | android.telephony.PhoneStateListener.LISTEN_CELL_INFO | android.telephony.PhoneStateListener.LISTEN_CALL_STATE;
    TelephonyManager telephonyManager;

    public PhoneStateListener(Context context) {
        this.context = context;
    }

    @Override
    public void onCellLocationChanged(CellLocation location) {
        Log.i("리스너", "온체인지리스너");
        super.onCellLocationChanged(location);

        int cid = 0;
        int lac = 0;

        if (location != null) {
            if (location instanceof GsmCellLocation) {
                cid = ((GsmCellLocation) location).getCid();
                lac = ((GsmCellLocation) location).getLac();
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            telephonyManager.requestCellInfoUpdate(AsyncTask.SERIAL_EXECUTOR, new TelephonyManager.CellInfoCallback() {
                @Override
                public void onCellInfo(@NonNull List<CellInfo> cellInfo) {
                    try {
                        //Log.d(TAG, "onCellInfoChanged ! callback ");
                        if (cellInfo.get(0) instanceof CellInfoLte) {
                            CellInfoLte cellInfoLte = (CellInfoLte) cellInfo.get(0);
                            if (cellInfoLte != null) {
                                //Log.d(TAG, " onCellInfoChanged callback : LTE : " + cellInfoLte.getCellIdentity().getCi() + " | " + " | " + cellInfoLte.isRegistered());
                                ToastHandler toastHandler = new ToastHandler(context);
                                toastHandler.showToast(" onCellInfoChanged callback : LTE : " + cellInfoLte.getCellIdentity().getCi() + " | " + " | " + cellInfoLte.isRegistered(), Toast.LENGTH_LONG);
                            } else {
                                // Log.d(TAG, " onCellInfoChanged callback : null");
                                ToastHandler toastHandler = new ToastHandler(context);
                                toastHandler.showToast(" onCellInfoChanged callback : null", Toast.LENGTH_LONG);

                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        String cellBase = Integer.toString(lac) + "-" + Integer.toString(cid);

        ToastHandler toastHandler = new ToastHandler(context);
        toastHandler.showToast(cellBase, Toast.LENGTH_LONG);

        //Toast.makeText(getBaseContext(), cellBase, Toast.LENGTH_LONG).show();
        Log.v("logg", "cell:" + cellBase);

    }

    @Override
    public void onCellInfoChanged(List<CellInfo> cellInfo) {
        super.onCellInfoChanged(cellInfo);
        Log.d("info", "onCellInfoChanged ! ");
        ToastHandler toastHandler = new ToastHandler(context);
        toastHandler.showToast("onCellInfoChanged", Toast.LENGTH_LONG);
    }

    public void start(Context context) {
        Log.i("리스너", "스타트");

        telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        CellLocation.requestLocationUpdate();
        telephonyManager.listen(this, events);

    }

}

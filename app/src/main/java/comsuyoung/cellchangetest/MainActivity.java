package comsuyoung.cellchangetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION}, 1);
        }

//        final TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//
//        int events = PhoneStateListener.LISTEN_CELL_LOCATION;
//        telephonyManager.listen(phoneStateListener, events);
        Intent intent = new Intent(getApplicationContext(), TestService.class);
        startService(intent);

    }


//    private final PhoneStateListener phoneStateListener = new PhoneStateListener() {
//
//        @Override
//        public void onCellLocationChanged(CellLocation location) {
//            super.onCellLocationChanged(location);
//
//            int cid = 0;
//            int lac = 0;
//
//            if (location != null) {
//                if (location instanceof GsmCellLocation) {
//                    cid = ((GsmCellLocation) location).getCid();
//                    lac = ((GsmCellLocation) location).getLac();
//                }
//            }
//
//            String cellBase = Integer.toString(lac)+"-"+Integer.toString(cid);
//
//            Toast.makeText(getBaseContext(), cellBase, Toast.LENGTH_LONG).show();
//            Log.v("logg", "cell:"+cellBase);
//
//
//        }
//    };
}

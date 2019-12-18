package tugas.besar.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.Result;
import com.google.zxing.integration.android.IntentIntegrator;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import tugas.besar.R;
import tugas.besar.SharedPreference;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    SharedPreference sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        sharedPrefManager = new SharedPreference(this);
        setContentView(mScannerView);
    }
    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        try {
            Log.v("TAG", rawResult.getText()); // Prints scan results
            String scanResult = rawResult.getBarcodeFormat().toString();
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("scan", "result");
            intent.putExtra("sresult", rawResult.getText());
            startActivity(intent);
            sharedPrefManager.saveSPString(SharedPreference.SCAN_RESULT, rawResult.getText());
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle("Scan Result");
//            builder.setMessage(rawResult.getText());
//            AlertDialog alert1 = builder.create();
//            alert1.show();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplication(), "ERROR, TRY AGAIN !",Toast.LENGTH_SHORT);
        }

    }

}

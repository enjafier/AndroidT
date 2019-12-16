package tugas.besar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import tugas.besar.R;

public class ResultActivity extends AppCompatActivity {

    TextView teksResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        teksResult = findViewById(R.id.teksResult);

        Bundle result = getIntent().getExtras();
        String scanResult = result.getString("sresult");
        teksResult.setText("Hasil : "+scanResult);
    }
}

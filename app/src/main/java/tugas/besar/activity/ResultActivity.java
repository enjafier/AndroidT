package tugas.besar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import tugas.besar.MainActivity;
import tugas.besar.R;

public class ResultActivity extends AppCompatActivity {

    TextView teksResult;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        teksResult = findViewById(R.id.teksResult);
        back = findViewById(R.id.back_button);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
                finish();
            }
        });

        Bundle result = getIntent().getExtras();
        String scanResult = result.getString("sresult");
        teksResult.setText("Hasil : "+scanResult);
    }
}

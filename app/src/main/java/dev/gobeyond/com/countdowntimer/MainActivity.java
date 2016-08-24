package dev.gobeyond.com.countdowntimer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private EditText etHours;
    private EditText etMinutes;
    private EditText etSeconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.btnStart);
        etHours = (EditText) findViewById(R.id.etHours);
        etMinutes = (EditText) findViewById(R.id.etMinutes);
        etSeconds = (EditText) findViewById(R.id.etSeconds);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startHours = etHours.getText().toString();
                String startMinutes = etMinutes.getText().toString();
                String startSeconds = etSeconds.getText().toString();

                long countDownMillis = TimeUnit.HOURS.toMillis(Long.valueOf(startHours))
                        + TimeUnit.MINUTES.toMillis(Long.valueOf(startMinutes))
                        + TimeUnit.SECONDS.toMillis(Long.valueOf(startSeconds));

                Utility.putLong(getApplicationContext(), Utility.KEY_COUNT_DOWN_MILLIS, countDownMillis);
            }
        });
    }
}

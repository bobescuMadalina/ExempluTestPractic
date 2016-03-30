package ro.pub.cs.systems.eim.practicaltest01;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ro.pub.cs.systems.eim.practicaltest01.practicaltest01.R;

public class PracticalTest01SecondaryActivity extends AppCompatActivity {
    Button okButton, cancelButton;
    TextView textPressedButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_secondary);

        Intent intent = getIntent();
        String numberOfClicks = intent.getStringExtra("number_of_clicks");
        if (!TextUtils.isEmpty(numberOfClicks)) {
            initViews(numberOfClicks);
        } else {
            initViews("");
        }

        setListeners();




    }

    private void initViews(String numberOfClicks) {
        okButton = (Button) findViewById(R.id.ok_button);
        cancelButton = (Button) findViewById(R.id.cancel_button);

        textPressedButtons = (TextView) findViewById(R.id.text_view);
        textPressedButtons.setText(numberOfClicks);
    }

    private void setListeners() {
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK, null);
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED, null);
                finish();
            }
        });
    }

}

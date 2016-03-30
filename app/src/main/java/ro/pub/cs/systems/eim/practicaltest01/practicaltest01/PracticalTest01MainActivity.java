package ro.pub.cs.systems.eim.practicaltest01.practicaltest01;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class PracticalTest01MainActivity extends AppCompatActivity {

    private static final String FIRST_VALUE_SAVED = "first_value_saved";
    private static final String SECOND_VALUE_SAVED= "second_value_saved";

    private Button firstButton, secondButton;
    private EditText firstEditText, secondEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_main);

        initViews();

        addListeners();

    }

    private void initViews() {
        firstButton = (Button) findViewById(R.id.first_button);
        secondButton = (Button) findViewById(R.id.second_button);

        firstEditText = (EditText) findViewById(R.id.first_edit_text);
        secondEditText = (EditText) findViewById(R.id.second_edit_text);
    }

    private void addListeners() {
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numberString = firstEditText.getText().toString();
                firstEditText.setText(String.valueOf(Integer.valueOf(numberString) + 1));
            }
        });

        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numberString = secondEditText.getText().toString();
                secondEditText.setText(String.valueOf(Integer.valueOf(numberString) + 1));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_practical_test01_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            firstEditText.setText(savedInstanceState.getString(FIRST_VALUE_SAVED, ""));
            secondEditText.setText(savedInstanceState.getString(SECOND_VALUE_SAVED, ""));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(FIRST_VALUE_SAVED, firstEditText.getText().toString());
        outState.putString(SECOND_VALUE_SAVED, secondEditText.getText().toString());

    }
}

package ro.pub.cs.systems.eim.practicaltest01.practicaltest01;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ro.pub.cs.systems.eim.practicaltest01.MyService;
import ro.pub.cs.systems.eim.practicaltest01.PracticalTest01SecondaryActivity;

public class PracticalTest01MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String FIRST_VALUE_SAVED = "first_value_saved";
    private static final String SECOND_VALUE_SAVED= "second_value_saved";
    private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;

    IntentFilter intentFilter = new IntentFilter();


    private Button firstButton, secondButton, navigateToNext;
    private EditText firstEditText, secondEditText;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("APP_TAG", intent.getStringExtra("message"));
        }
    };

    private boolean serviceNotStart = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_main);

        initViews();

        addListeners();

        intentFilter.addAction(Constants.MY_ACTION);

    }

    private void initViews() {
        firstButton = (Button) findViewById(R.id.first_button);
        secondButton = (Button) findViewById(R.id.second_button);
        navigateToNext = (Button) findViewById(R.id.navigate_to_next);

        firstEditText = (EditText) findViewById(R.id.first_edit_text);
        secondEditText = (EditText) findViewById(R.id.second_edit_text);
    }

    private void addListeners() {
        firstButton.setOnClickListener(this);

        secondButton.setOnClickListener(this);

        navigateToNext.setOnClickListener(this);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        String numberString1 = firstEditText.getText().toString();
        String numberString2 = secondEditText.getText().toString();
        switch(v.getId()) {
            case R.id.first_button:
                verifyAndStartService(numberString1, numberString2);
                firstEditText.setText(String.valueOf(Integer.valueOf(numberString1) + 1));
                break;
            case R.id.second_button:
                verifyAndStartService(numberString1, numberString2);
                secondEditText.setText(String.valueOf(Integer.valueOf(numberString2) + 1));
                break;
            case R.id.navigate_to_next:
                Intent intent = new Intent(getApplicationContext(), PracticalTest01SecondaryActivity.class);
                intent.putExtra("number_of_clicks", String.valueOf(Integer.valueOf(firstEditText.getText().toString()) +
                        Integer.valueOf(secondEditText.getText().toString())));
                startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
        }
    }


    public void verifyAndStartService(String str1, String str2) {
        if ((Integer.valueOf(str1) + Integer.valueOf(str2) > 6) &&
                serviceNotStart) {
            Intent intent = new Intent(getApplicationContext(), MyService.class);
            intent.putExtra("first_value", str1);
            intent.putExtra("second_value", str2);
            getApplicationContext().startService(intent);
            serviceNotStart = false;
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(broadcastReceiver);
        super.onPause();
    }
}

package edu.usf.andres.hacker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init the textView to display data
        TextView txtDisplay = findViewById(R.id.txtDisplay);
        String DataBundle = "";

        // get the app data send on bundle
        Bundle bundle = getIntent().getExtras();

        // loop through all the keys in the bundle

        for (String key: bundle.keySet()) {

            // get object by key
            // we define object because it may be text or image
            Object value = new Object();
            try {
                value = bundle.get(key);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            // get all keys
            DataBundle += String.format("%s %s (%s)", key, value.toString(), value.getClass().getName());
        }

        txtDisplay.setText(DataBundle);
    }
}

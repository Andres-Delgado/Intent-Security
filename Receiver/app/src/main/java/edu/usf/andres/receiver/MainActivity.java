package edu.usf.andres.receiver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // define the display text view
        TextView txtView = findViewById(R.id.txtDisplay);

        // get the app data send on bundle

        Bundle b = getIntent().getExtras();

        // display the key that has the data
        txtView.setText(b.getString("Comment"));
    }
}

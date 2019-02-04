package edu.usf.andres.sender;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //define send button
        Button sendButton = findViewById(R.id.sendButton);

        // init comment edit text
        final EditText etComment = findViewById(R.id.etComment);

        sendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //set the package that we want to run
                Intent intent = getPackageManager().getLaunchIntentForPackage("edu.usf.andres.Receiver");

                // put the data that we want to send over intent
                intent.putExtra("Comment", etComment.getText().toString());
                startActivity(intent);

            }
        });
    }
}

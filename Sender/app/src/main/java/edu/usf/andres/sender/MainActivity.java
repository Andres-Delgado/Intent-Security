package edu.usf.andres.sender;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //define send button
        Button sendButton = findViewById(R.id.sendButton);

        // init comment edit text
        final EditText etComment = findViewById(R.id.etComment);

        // create secret
        String pw = "andres13andres13";
        final Key secret = new SecretKeySpec(pw.getBytes(), "AES");


        sendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //set the package that we want to run
                Intent intent = getPackageManager().getLaunchIntentForPackage("edu.usf.andres.receiver");

                String commentStr = etComment.getText().toString();
                byte[] cipherText = new byte[0];

                try {
                    cipherText = EncryptMessage(commentStr, secret);
                    //commentStr = new String(cipherText);

                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                }

                // put the data that we want to send over intent
                intent.putExtra("Comment", cipherText);
                startActivity(intent);


            }
        });

    }

    public byte[] EncryptMessage(String msg, Key secret) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secret);
        return cipher.doFinal(msg.getBytes());
    }

}

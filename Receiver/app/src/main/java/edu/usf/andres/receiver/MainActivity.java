package edu.usf.andres.receiver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // define the display text view
        TextView txtView = findViewById(R.id.txtDisplay);

        // get the app data sent on bundle
        Bundle b = getIntent().getExtras();
        byte[] cipherText = b.getByteArray("Comment");
        //byte[] cipherText = getIntent().getByteArrayExtra("Comment");

        // create secret
        String pw = "andres13andres13";
        final Key secret = new SecretKeySpec(pw.getBytes(), "AES");

        // getting String from bundle
        //String msg = b.getString("Comment");
        String msg = "";

        // decrypt string
        try {
            msg = DecryptMessage(cipherText, secret);

        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        txtView.setText(msg);
    }


    public String DecryptMessage(byte[] cipherText, Key secret) throws InvalidKeyException,
            NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException,
            BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secret);
        return new String(cipher.doFinal(cipherText));
    }
}

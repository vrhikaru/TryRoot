package com.mdw.tryroot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart(){
        super.onStart();
        tryRootTest();
    }

    private void tryRootTest(){
        Process p;
        try {
            // Preform su to get root privledges
            p = Runtime.getRuntime().exec("su");

            // Attempt to write a file to a root-only
            DataOutputStream os = new DataOutputStream(p.getOutputStream());
            os.writeBytes("echo \"Do I have root?\" >/system/sd/temporary.txt\n");

            // Close the terminal
            os.writeBytes("exit\n");
            os.flush();
            try {
                p.waitFor();
                if (p.exitValue() != 255) {
                    // TODO Code to run on success
                    toastMessage ("root");
                }
                else {
                    // TODO Code to run on unsuccessful
                    toastMessage("not root");
                }
            } catch (InterruptedException e) {
                // TODO Code to run in interrupted exception
                toastMessage("not root");
            }
        } catch (IOException e) {
            // TODO Code to run in input/output exception
            toastMessage("not root");
        }
    }

    void toastMessage(String msg){
        //利用Toast的靜態函式makeText來建立Toast物件
        Toast toast = Toast.makeText(this,
                msg, Toast.LENGTH_LONG);
        //顯示Toast
        toast.show();
    }
}

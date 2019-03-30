package com.example.moon.watracking;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> names = new ArrayList<String>();
    ArrayList<Integer> count = new ArrayList<>();

    String jsontext;


    EditText editText;
    int flag = 0;

    public void onClick(View view) {
        try {


            InputStream is = this.getResources().openRawResource(R.raw._chat9);
            byte[] buffer = new byte[is.available()];
            while (is.read(buffer) != -1);
            jsontext = new String(buffer);



        } catch (Exception e) {

            Log.e("TAG",e.toString());
        }




        String data = jsontext;
        String[] splitResult = data.split("PM]");
        for (int i = 0; i < splitResult.length; i++) {
            String dataBlock = splitResult[i];
            String[] splitChildResult = dataBlock.split(":");
            String item = splitChildResult[0];
            if (item.contains("joined") || item.contains("added") || item.contains("You") || item.contains("left") || item.contains("removed") || item.contains("created")||item.contains("changed")) {
            }
            else {
                flag = 0;
                for (int j = 0; j < names.size(); j++) {

                    if (names.get(j).equals(item)) {
                        count.set(j, count.get(j) + 1);
                        flag = 1;
                    }
                }
                if (flag == 0) {
                    names.add(item);
                    count.add(1);
                }

            }
        }



        String[] splitResult2 = data.split("AM]");
        for (int i = 0; i < splitResult2.length; i++) {
            String dataBlock = splitResult2[i];
            String[] splitChildResult2 = dataBlock.split(":");
            String item = splitChildResult2[0];
            if (item.contains("joined") || item.contains("added") || item.contains("left") || item.contains("removed") || item.contains("created")||item.contains("changed")) {
            }
            else {
                flag = 0;
                for (int j = 0; j < names.size(); j++) {

                    if (names.get(j).equals(item)) {
                        count.set(j, count.get(j) + 1);
                        flag = 1;
                    }
                }
                if (flag == 0) {
                    names.add(item);
                    count.add(1);
                }

            }
        }

        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(
                "name", // What should I set for this "label"?
                names.toString());
        clipboard.setPrimaryClip(clip);

        Toast.makeText(MainActivity.this, "Saved to clip board", Toast.LENGTH_SHORT).show();


        for (
                int k = 0; k < names.size(); k++)

        {
            Log.i("names", names.get(k));
            Log.i("count", String.valueOf(count.get(k)));
        }


    }

    public void onClick2(View view){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip2 = ClipData.newPlainText(
                "counting", // What should I set for this "label"?
                count.toString());
        clipboard.setPrimaryClip(clip2);


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button=(Button)findViewById(R.id.button);

    }
}

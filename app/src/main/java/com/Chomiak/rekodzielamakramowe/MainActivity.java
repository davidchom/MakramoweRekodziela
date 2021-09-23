
package com.Chomiak.rekodzielamakramowe;

//import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClickZatwierdzenieWyboru(View view){
        Spinner color = (Spinner) findViewById(R.id.wybor);
        String rodzajWyboru = String.valueOf(color.getSelectedItem());

        if(rodzajWyboru.equals("Firana")){
            Intent i = new Intent(this, Firany.class);
            startActivity(i);
        }
        else{
            Intent i = new Intent(this,Zazdrostki.class);
            startActivity(i);
        }
    }
}


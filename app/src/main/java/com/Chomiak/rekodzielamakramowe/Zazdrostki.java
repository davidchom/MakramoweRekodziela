package com.Chomiak.rekodzielamakramowe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Zazdrostki extends AppCompatActivity {

    String messageZ =("Something gones wrong :(");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zazdrostki);
    }

    public static double zaokraglanieCeny(double cena){
        if(cena % 10 <=5){
            return cena = cena - (cena%10) +5;
        }
        else{
            return cena = cena - (cena%10) + 10;
        }
    }


    public void zatwierdzenieZazdrostki(View view){
        EditText pierwszeDaneZ = findViewById(R.id.editTextNumber4);
        EditText drugieDaneZ = findViewById(R.id.editTextNumber5);
        TextView pierwszaLinijkaZ = findViewById(R.id.pierwszaOdpowiedzZazdrostki);
        TextView drugaLinijkaZ = findViewById(R.id.drugaOdpowiedzZazdrostki);
        TextView trzeciaLinijkaZ = findViewById(R.id.trzeciaOdpowiedzZazdrostki);
        TextView czwartaLinijkaZ = findViewById(R.id.czwartaOdpowiedzZazdrostki);
        TextView piataLinijkaZ = findViewById(R.id.piataOdpowiedzZazdrostki);

        double szerokoscZazdrostki = Double.parseDouble(pierwszeDaneZ.getText().toString());
        double dlugoscZazdrostki = Double.parseDouble(drugieDaneZ.getText().toString());

        //Obliczanie wszystkich parametrow uwzglednianych w TextView
        Double ileSztukSznurkaZ = Firany.ileSznurka(szerokoscZazdrostki);
        Double ileDlugosciKazdySznurekZ = Firany.ileDlugosciKazdaSztukaGesta(dlugoscZazdrostki);
        Double jakaDlugoscCalegoSznurkaZ = Firany.ileDlugosciCalosc(ileSztukSznurkaZ, ileDlugosciKazdySznurekZ);
        Double cenaZaSznurek = Firany.cenaSznurka(jakaDlugoscCalegoSznurkaZ);
        Double cenaZUwZarobku = Firany.cenaCalosc(dlugoscZazdrostki, ileSztukSznurkaZ, jakaDlugoscCalegoSznurkaZ);

        Double ostatecznoscCenyZ = zaokraglanieCeny(cenaZUwZarobku);

        String doPierwszej = Double.toString(Firany.roundTo2DecimalPlace(ileSztukSznurkaZ));
        String doDrugiej = Double.toString(Firany.roundTo2DecimalPlace(ileDlugosciKazdySznurekZ));
        String doTrzeciej = Double.toString(Firany.roundTo2DecimalPlace(jakaDlugoscCalegoSznurkaZ));
        String doCzwartej = Double.toString(Firany.roundTo2DecimalPlace(cenaZaSznurek));
        String doPiatej = Double.toString(Firany.roundTo2DecimalPlace(cenaZUwZarobku));

        pierwszaLinijkaZ.setText(doPierwszej);
        drugaLinijkaZ.setText(doDrugiej);
        trzeciaLinijkaZ.setText(doTrzeciej);
        czwartaLinijkaZ.setText(doCzwartej);
        piataLinijkaZ.setText(doPiatej);


        messageZ = ("Witam :) Cena za tę zazdrostkę wynosi " + ostatecznoscCenyZ + " zł. Zapraszam do składania zamówień :)");

    }

    public void onClickSendMessageZ(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, messageZ);

        String chooserTitle = getString(R.string.chooser);
        Intent choosenIntent = Intent.createChooser(intent, chooserTitle);

        startActivity(choosenIntent);
    }
}
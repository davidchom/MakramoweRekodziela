package com.Chomiak.rekodzielamakramowe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Firany extends AppCompatActivity {

    String messageF = "Something gones wrong :(";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firany);
    }

    public static double ileSznurka(double a){
        return (a/4.5) * 2.0;
    }

    public static double ileDlugosciKazdaSztukaGesta (double a){
        return ((a + 5.0) * 4.0)/100.00;
    }

    public static double ileDlugosciKazdaSztukaRzadka (double a) { return ((a + 5.0) * 2.0)/100.00; }

    public static double ileDlugosciCalosc (double a, double b){
        return a * b;
    }

    public static double cenaSznurka (double a){
        return a * 0.2;
    }

    public static double cenaCalosc(double a, double c, double b){
        return ((a/100.00)*4.00 *0.9 * c + (b *0.2));
    }

    public static double cenaCaloscRzadka (double a, double c, double b){
        return ((a/100.00)*2.00 *0.30 * c + (b *0.20));
    }

    public static double roundTo2DecimalPlace (double a){
        return Math.round(a*100.0)/100.0;
    }



    public void zatwierdzenieFirany(View view){
        EditText pierwszeDaneF =  findViewById(R.id.editTextNumber);
        EditText drugieDaneF = findViewById((R.id.editTextNumber2));
        EditText trzecieDaneF = findViewById(R.id.editTextNumber3);
        TextView pierwszaLinijkaF = findViewById(R.id.pierwszaOdpowiedzFirany);
        TextView drugaLinijkaF = findViewById(R.id.drugaOdpowiedzFirany);
        TextView trzeciaLinijkaF = findViewById(R.id.trzeciaOdpowiedzFirany);
        TextView czwartaLinijkaF = findViewById(R.id.czwartaOdpowiedzFirany);
        TextView piataLinijkaF = findViewById(R.id.piataOdpowiedzFirany);
        TextView szostaLinijkaF = findViewById(R.id.szostaOdpowiedzFirany);
        TextView ostatniaLinijkaF = findViewById(R.id.caloscOdpowiedzKasa);

        TextView cenaLinijkaPierwsza = findViewById(R.id.pierwszaOdpowiedzCena);
        TextView cenaLinijkaDruga = findViewById(R.id.drugaOdpowiedzCena);

        double szerokoscFirany = Double.parseDouble(pierwszeDaneF.getText().toString());
        double rzadkaFirana = Double.parseDouble(drugieDaneF.getText().toString());
        double gestaFirana = Double.parseDouble(trzecieDaneF.getText().toString());

        //Tutaj jest liczone dla RZADKIEJ czesci firany!!!
        Double ileSztukSznurkaF = ileSznurka(szerokoscFirany);
        Double poIleDlugosciRzadkaF = ileDlugosciKazdaSztukaRzadka(rzadkaFirana);
        Double ileWynosiCaloscRzadkaF = ileDlugosciCalosc(ileSztukSznurkaF,poIleDlugosciRzadkaF);
        Double cenaZaSznurekRzadkaF = cenaSznurka(ileWynosiCaloscRzadkaF);
        Double kosztZUwZarobkiemRzadkaF = cenaCaloscRzadka(rzadkaFirana, ileSztukSznurkaF, ileWynosiCaloscRzadkaF);

        //Tutaj jest liczone dla GESTEJ czesci firany!!!
        Double poIleDlugosciGestaF = ileDlugosciKazdaSztukaGesta(gestaFirana);
        Double ileWynosiCaloscGestaF = ileDlugosciCalosc(ileSztukSznurkaF, poIleDlugosciGestaF);
        Double cenaZaSznurekGestaF = cenaSznurka(ileWynosiCaloscGestaF);
        Double kosztZUwZarobkiemGestaF = cenaCalosc(gestaFirana, ileSztukSznurkaF, ileWynosiCaloscGestaF);

        Double cenaZaSznurekCalosc = cenaZaSznurekGestaF + cenaZaSznurekRzadkaF;
        Double kosztCalosci = kosztZUwZarobkiemGestaF + kosztZUwZarobkiemRzadkaF;

        Double calkowitaDlugoscF = ileWynosiCaloscGestaF + ileWynosiCaloscRzadkaF;

        Double ostatecznoscCenyF = Zazdrostki.zaokraglanieCeny(kosztCalosci);



        String doPierwszego = Double.toString(roundTo2DecimalPlace(ileSztukSznurkaF));
        String doDrugiego = Double.toString(roundTo2DecimalPlace(poIleDlugosciRzadkaF));
        String doTrzeciego = Double.toString(roundTo2DecimalPlace(ileWynosiCaloscRzadkaF));

        String doCzwartego = Double.toString(roundTo2DecimalPlace(ileSztukSznurkaF));
        String doPiatego = Double.toString(roundTo2DecimalPlace(poIleDlugosciGestaF));
        String doSzostego = Double.toString(roundTo2DecimalPlace(ileWynosiCaloscGestaF));

        String doSiodmego = Double.toString(roundTo2DecimalPlace(calkowitaDlugoscF));
        String doOsmego = Double.toString(roundTo2DecimalPlace(cenaZaSznurekCalosc));
        String doDziewiatego = Double.toString(roundTo2DecimalPlace(kosztCalosci));




        pierwszaLinijkaF.setText(doPierwszego);
        drugaLinijkaF.setText(doDrugiego);
        trzeciaLinijkaF.setText(doTrzeciego);
        czwartaLinijkaF.setText(doCzwartego);
        piataLinijkaF.setText(doPiatego);
        szostaLinijkaF.setText(doSzostego);
        ostatniaLinijkaF.setText(doSiodmego);

        cenaLinijkaPierwsza.setText(doOsmego);
        cenaLinijkaDruga.setText(doDziewiatego);

        messageF =("Witam :) Cena za tę firanę wynosi " + ostatecznoscCenyF + " zł. Zapraszam do składania zamówień :)" );
    }

    public void onClickSendMessageF(View view){


        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, messageF);

        String chooserTitle = getString(R.string.chooser);
        Intent chosenIntent = Intent.createChooser(intent, chooserTitle);

        startActivity(chosenIntent);
    }
}
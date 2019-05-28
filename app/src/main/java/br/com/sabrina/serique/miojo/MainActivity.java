package br.com.sabrina.serique.miojo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText edTempo, edAmp1, edAmp2;
    private TextView tvTempMin;
    private Button btCalc;
    public int tempo, ampl1, ampl2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recuperar os componentes do layout .xml
        edTempo = findViewById(R.id.ed_miojo);
        edAmp1 = findViewById(R.id.ed_ampl1);
        edAmp2 = findViewById(R.id.ed_ampl2);
        tvTempMin = findViewById(R.id.tv_tempo_min);
        btCalc = findViewById(R.id.bt_calc);

        btCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tempo = Integer.parseInt(edTempo.getText().toString());
                ampl1 = Integer.parseInt(edAmp1.getText().toString());
                ampl2 = Integer.parseInt(edAmp2.getText().toString());

                if(mdc(ampl1,ampl2) != 1 || ampl1 <= tempo || ampl2 <= tempo){

                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext())
                            .setTitle("Alerta")
                            .setMessage("Não é possivel calcular com essas ampulhetas!")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });

                    AlertDialog alert = builder.create();
                    alert.show();
                }else{
                    tvTempMin.setText(tempoMimino(tempo, ampl1, ampl2));
                }

            }
        });
    }

    private int mdc(int a, int b){

        int c;
        while (a%b != 0){

            c = a%b;
            a = b;
            b = c;
        }

        return a;
    }

    private int tempoMimino(int tempo, int ampl1, int ampl2){

        return calculoTempoMinimo(tempo, ampl1, ampl2, ampl1, ampl2);

    }

    private int calculoTempoMinimo(int t, int a, int b, int aOrig, int bOrig){
        int c, cOrig, dif, maxt;

        if(a > b){
            c = b;
            a = b;
            b = c;

            cOrig = bOrig;
            aOrig = bOrig;
            bOrig = cOrig;

        }

        dif = b - a;
        maxt = b;

        if(dif == t){
            return maxt;
        }else{
            return calculoTempoMinimo(t, a + aOrig,b, aOrig, bOrig);
        }
    }

}

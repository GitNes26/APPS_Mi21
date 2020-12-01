package com.nestor.mi21;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ClearCacheRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView rvCartas;
    private RequestQueue cartero;
    private VolleyS mVolleyS;
    public int num=0;
    public int puntos=0;
//    EditText nombre = findViewById(R.id.nombre);
    ImageView imgCarta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        imgCarta = findViewById(R.id.imgCarta);

        rvCartas = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rvCartas.setLayoutManager(layoutManager);

        //Este es de prueba a manera local
        List<Numero> ListaNumeros = new ArrayList<>();
        ListaNumeros.add(new Numero( String.valueOf("1")));
        ListaNumeros.add(new Numero( String.valueOf("7")));
        ListaNumeros.add(new Numero( String.valueOf("11")));
//        for (int i=1; i <=11; i++){
//          ListaNumeros.add(new Numero( String.valueOf(i)));
//        }
//
        AdaptadorNumero Numeros = new AdaptadorNumero(ListaNumeros);
        rvCartas.setAdapter(Numeros);
        
        mVolleyS = VolleyS.getInstance(this.getApplicationContext());
        cartero = mVolleyS.getRequestQueue();

        findViewById(R.id.btnSolicitar).setOnClickListener(this);
        findViewById(R.id.btnEnviar).setOnClickListener(this);
        findViewById(R.id.btnResultados).setOnClickListener(this);
        findViewById(R.id.btnReinicar).setOnClickListener(this);
    }

    @Override
    public void onClick(View btn) {
        TextView puntaje = findViewById(R.id.puntos);
        switch (btn.getId()){
            case R.id.btnSolicitar:
                String url = "https://www.ramiro174.com/api/numero";

                JsonObjectRequest numeroJson = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (puntos < 21) {
                                num = response.getInt("numero");

                                puntos += num;

                                TextView puntaje = findViewById(R.id.puntos);
                                puntaje.setText(String.valueOf(puntos));

//                                ImageView imageView = new ImageView(MainActivity.this);
//                                MostrarCarta(num);
//                                imageView.setImageResource(R.drawable.beerbottle);
//                                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.RelativeLayout01);
//                                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
//                                        RelativeLayout.LayoutParams.WRAP_CONTENT,
//                                        RelativeLayout.LayoutParams.WRAP_CONTENT
//                                );
//                                layoutParams.addRule(RelativeLayout.BELOW, R.id.ButtonRecalculate);
//                                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//                                relativeLayout.addView(imageView, layoutParams);

                                List<Numero> ListaNumeros = new ArrayList<>();
                                ListaNumeros.add(new Numero( String.valueOf(num), R.drawable.espada1));
                                AdaptadorNumero Numeros = new AdaptadorNumero(ListaNumeros);
                                rvCartas.setAdapter(Numeros);

//                                Button btnResultado= findViewById(R.id.btnResultados);
//                                btnResultado.setEnabled(true);
//                                Gson gson = new Gson();
//                                final Type CartaType = new TypeToken<List<Numero>>(){}.getType();
//                                List<Numero> ListaCartas = gson.fromJson(String.valueOf(num), CartaType);
//                                AdaptadorNumero Cartas = new AdaptadorNumero(ListaCartas);
//                                rvCartas.setAdapter(Cartas);
                            }
                            else{
//                                Button btnResultado= findViewById(R.id.btnResultados);
//                                btnResultado.setEnabled(false);
                                Toast.makeText(MainActivity.this, "Has perdido, el puntaje maximo de puntos deben ser 21. Reinicia el juego", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
                cartero.add(numeroJson);
                break;

            case R.id.btnEnviar:
                String urlr = "https://www.ramiro174.com/api/enviar/numero";
//                nombre.getText();

                JSONObject datos = new JSONObject();
                try {
                    datos.put("nombre", "Nestor Puentes");
                    datos.put("numero", puntos);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest puntajeJson = new JsonObjectRequest(Request.Method.PUT, urlr, (JSONObject) datos, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
                cartero.add(puntajeJson);
                puntos = 0;
                num = 0;
                puntaje.setText("0");
                break;

            case R.id.btnResultados:
                startActivity(new Intent(getApplicationContext(), ResultadosActivity.class));
                break;

            case R.id.btnReinicar:
                puntos = 0;
                num = 0;
                puntaje.setText("0");
//                Button btnResultado= findViewById(R.id.btnResultados);
//                btnResultado.setEnabled(true);
                //limpiar recycler, aun no se como
                break;

        }
    }

    public void MostrarCarta(int num){
//        ImageView imageView = new ImageView(MainActivity.this);
//        MostrarCarta(num);
//        imageView.setImageResource(R.drawable.beerbottle);
//        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.RelativeLayout01);
//        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT
//        );
//        layoutParams.addRule(RelativeLayout.BELOW, R.id.ButtonRecalculate);
//        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//        relativeLayout.addView(imageView, layoutParams);
        switch (num) {
            case 1:
                imgCarta.setImageResource(R.drawable.espada1);
                break;
            case 2:
                imgCarta.setImageResource(R.drawable.espada2);
                break;
            case 3:
                imgCarta.setImageResource(R.drawable.espada3);
                break;
            case 4:
                imgCarta.setImageResource(R.drawable.espada4);
                break;
            case 5:
                imgCarta.setImageResource(R.drawable.espada5);
                break;
            case 6:
                imgCarta.setImageResource(R.drawable.espada6);
                break;
            case 7:
                imgCarta.setImageResource(R.drawable.espada7);
                break;
            case 8:
                imgCarta.setImageResource(R.drawable.espada8);
                break;
            case 9:
                imgCarta.setImageResource(R.drawable.espada9);
                break;
            case 10:
                imgCarta.setImageResource(R.drawable.espada10);
                break;
            case 11:
                imgCarta.setImageResource(R.drawable.espada11);
                break;
        }
    }
}
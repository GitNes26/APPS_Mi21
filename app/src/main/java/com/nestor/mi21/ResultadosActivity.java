package com.nestor.mi21;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ResultadosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private VolleyS mVolleyS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        mVolleyS = VolleyS.getInstance(this.getApplicationContext());
        requestQueue = mVolleyS.getRequestQueue();


        // primera parte...
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


//        List<Jugador> ListaJugadores = new ArrayList<>();
//        ListaJugadores.add(new Jugador(R.drawable.gamer,"1", "Nestorin", "16", "123","14"));
//        ListaJugadores.add(new Jugador(R.drawable.gamer,"2 ","Josue ","2 ","15656 ", "11515"));
//
//        AdaptadorJugador Jugadores = new AdaptadorJugador(ListaJugadores);
//        recyclerView.setAdapter(Jugadores);

        String url = "https://www.ramiro174.com/api/obtener/numero";

        JsonObjectRequest objetoJSon = new JsonObjectRequest(Request.Method.GET, url, null ,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arreglo = response.getJSONArray("resultados");

                    Gson gson = new Gson();
                    final Type JugadorType = new TypeToken<List<Jugador>>(){}.getType();
                    List<Jugador> ListaJugadores = gson.fromJson(arreglo.toString(), JugadorType);

                    AdaptadorJugador Jugadores = new AdaptadorJugador(ListaJugadores);
                    recyclerView.setAdapter(Jugadores);
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

        requestQueue.add(objetoJSon);
    }

}
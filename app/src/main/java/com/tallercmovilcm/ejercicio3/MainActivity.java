package com.tallercmovilcm.ejercicio3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.tallercmovilcm.ejercicio3.model.Pokemon;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject>
{
    ListView lv;
    ArrayList<Pokemon> pokemons = new ArrayList<>();

    ProgressBar pbConexion;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.lv);
        pbConexion = findViewById(R.id.pbConexion);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = getResources().getString(R.string.urlBase);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.setRetryPolicy(new DefaultRetryPolicy(
                10,
                3,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(MainActivity.this, Valida.class);

                Bundle bundle = new Bundle();
                bundle.putString(getString(R.string.url),pokemons.get(position).getUrl());

                intent.putExtras(bundle);
                startActivity(intent) ;

            }
        });
    }

    @Override
    public void onErrorResponse(VolleyError error)
    {
        pbConexion.setVisibility(View.GONE);
    }

    @Override
    public void onResponse(JSONObject response)
    {
        pbConexion.setVisibility(View.GONE);
        try {
            for (int i = 0; i<response.getJSONArray(getString(R.string.results)).length(); i++)
            {

                pokemons.add(new Pokemon(i,
                        response.getJSONArray(getString(R.string.results)).getJSONObject(i).getString(getString(R.string.name)),
                        response.getJSONArray(getString(R.string.results)).getJSONObject(i).getString(getString(R.string.url))));
                    
            }
        }

        catch (JSONException e)
        {
            e.printStackTrace();
        }

        Adaptador adaptador = new Adaptador( this, pokemons);

        lv.setAdapter(adaptador);

    }
}
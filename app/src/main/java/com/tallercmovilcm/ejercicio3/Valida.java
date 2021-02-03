package com.tallercmovilcm.ejercicio3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.tallercmovilcm.ejercicio3.model.Pokemon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Valida extends AppCompatActivity implements Response.ErrorListener, Response.Listener<JSONObject>
{

    ProgressBar pbConexion;
    ImageView ivPokemon, ivTipo1, ivTipo2;
    TextView tvPokemon, tvExperiencia, tvAltura, tvPeso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valida);

        ivPokemon = findViewById(R.id.ivPokemon);
        ivTipo1 = findViewById(R.id.ivTipo1);
        ivTipo2 = findViewById(R.id.ivTipo2);

        tvPokemon = findViewById(R.id.tvPokemon);
        tvExperiencia = findViewById(R.id.tvExperiencia);
        tvAltura = findViewById(R.id.tvAltura);
        tvPeso = findViewById(R.id.tvPeso);
        pbConexion = findViewById(R.id.pbConexion);

        String url_pokemon;

        Bundle bundle = getIntent().getExtras();

        if (bundle != null)
        {
            url_pokemon = (String) bundle.getSerializable(getString(R.string.url));

            RequestQueue queue = Volley.newRequestQueue(this);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url_pokemon, null, this, this);
            request.setRetryPolicy(new DefaultRetryPolicy(
                    5000,
                    3,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(request);

        }
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

        try
        {

            String urlImagen = response.getJSONObject(getString(R.string.sprites)).
                    getJSONObject(getString(R.string.other)).
                    getJSONObject(getString(R.string.official_artwork)).
                    getString(getString(R.string.front_default));

            // Se carga la imagen en el Image View
            Picasso.with(this)
                    .load(urlImagen)
                    .into(ivPokemon);

            tvPokemon.setText(response.getString(getString(R.string.name)));
            tvExperiencia.setText(String.valueOf(response.getInt(getString(R.string.base_experience))));
            tvAltura.setText(String.valueOf(response.getInt(getString(R.string.height))));
            tvPeso.setText(String.valueOf(response.getInt(getString(R.string.weight))));

            JSONArray tipos = response.getJSONArray(getString(R.string.types));

            if(tipos.length() == 2)
            {
                String tipo1 = tipos.getJSONObject(0).getJSONObject(getString(R.string.type)).getString(getString(R.string.name));
                String tipo2 = tipos.getJSONObject(1).getJSONObject(getString(R.string.type)).getString(getString(R.string.name));

                ivTipo1.setImageResource(colocarTipo(tipo1));
                ivTipo2.setImageResource(colocarTipo(tipo2));
            }

            else
            {
                String tipo1 = tipos.getJSONObject(0).getJSONObject(getString(R.string.type)).getString(getString(R.string.name));

                ivTipo1.setImageResource(colocarTipo(tipo1));
            }

        }

        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }


    private int colocarTipo(String tipo0)
    {
        switch (tipo0)
        {
            case "bug":
                return R.drawable.bug;

            case "dark":
                return R.drawable.dark;

            case "grass":
                return R.drawable.grass;

            case "dragon":
                return R.drawable.dragon;

            case "electric":
                return R.drawable.electric;

            case "fairy":
                return R.drawable.fairy;

            case "fighting":
                return R.drawable.fighting;

            case "fire":
                return R.drawable.fire;

            case "flying":
                return R.drawable.flying;

            case "ghost":
                return R.drawable.ghost;

            case "ground":
                return R.drawable.ground;

            case "ice":
                return R.drawable.ice;

            case "normal":
                return R.drawable.normal;

            case "poison":
                return R.drawable.poison;

            case "psychic":
                return R.drawable.psychic;

            case "rock":
                return R.drawable.rock;

            case "steel":
                return R.drawable.steel;

            case "water":
                return R.drawable.water;

            default:
                return 0;
        }
    }

}
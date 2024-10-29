package com.example.mydailyoutfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Login extends AppCompatActivity {
    Button btniniciar;
    TextView tvregistrarse;
    private EditText usuario, clave;
    public String user, pass, url, resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        tvregistrarse = findViewById(R.id.tvRegistrarse);
        tvregistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ventanaCrearCuenta = new Intent(Login.this, CrearCuenta.class);
                startActivity(ventanaCrearCuenta);
            }
        });

        usuario = findViewById(R.id.etUsuario);
        clave = findViewById(R.id.etClave);
    }

    public void IniciarSesion(View view) {

        user = usuario.getText().toString();
        pass = clave.getText().toString();

        url="https://metcodmdo.site/WebPHPService/validacion.php";

        RequestParams parametros = new RequestParams();
        parametros.put("usu",user);
        parametros.put("pas",pass);

        AsyncHttpClient cliente = new AsyncHttpClient();
        cliente.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                if(statusCode==200){

                    String respuesta = new String(responseBody);

                    try {
                        JSONObject MiJson = new JSONObject(respuesta);
                        if(MiJson.names().get(0).equals("exito")){

                            resultado=MiJson.getString("usuario");
                            Toast.makeText(Login.this, "Bienvenido "+resultado, Toast.LENGTH_SHORT).show();

                            Intent ventana2 = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(ventana2);

                            finish();

                        }


                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }


                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });


    }
}
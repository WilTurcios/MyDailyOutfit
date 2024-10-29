package com.example.mydailyoutfit;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;


public class CrearCuenta extends AppCompatActivity {

    private EditText nombre, apellido, sexo, correo, clave;
    public String name, lastname, sex, email, pass, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear_cuenta);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nombre = findViewById(R.id.etNombre);
        apellido=findViewById(R.id.etApellido);
        sexo = findViewById(R.id.etSexo);
        correo = findViewById(R.id.etCorreo);
        clave=findViewById(R.id.etClave);


    }

    public void RegistrarDatos(View view) {

        name = nombre.getText().toString();
        lastname = apellido.getText().toString();
        sex = sexo.getText().toString();
        email = correo.getText().toString();
        pass = clave.getText().toString();

        url ="https://metcodmdo.site/WebPHPService/usuarios.php";

        RequestParams parametros = new RequestParams();
        parametros.put("name",name);
        parametros.put("apellido",lastname);
        parametros.put("sexo",sex);
        parametros.put("correo",email);
        parametros.put("clave",pass);

        AsyncHttpClient client = new AsyncHttpClient();

        client.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200){

                    Toast.makeText(CrearCuenta.this, "Dato insertado con exito!!", Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                Toast.makeText(CrearCuenta.this, "Error al insertar el dato", Toast.LENGTH_SHORT).show();

            }
        });

        nombre.setText("");
        apellido.setText("");
        sexo.setText("");
        correo.setText("");
        clave.setText("");


    }
}
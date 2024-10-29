package com.example.mydailyoutfit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mydailyoutfit.adaptador.GaleriaCamisasAdapter;

public class ImagenCompletaCami extends AppCompatActivity {
    //DECLARACION DE LA VARIABLE
    ImageView imageCompletaCami;
    GaleriaCamisasAdapter galeriaAdapterCami;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_imagen_completa_cami);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imageCompletaCami=findViewById(R.id.ivCompletaCami);

        //ACTIONBAR PARA INDICAR EN QUE PARTE DE LA APP SE ENCUENTRA
        //ActionBar actionBar = getSupportActionBar();
        // actionBar.setTitle("Foto Completa");

        Intent intent = getIntent();
        //REFERENCIA A LA LLAVE QUE SE CREO EN GALERIA
        int position = intent.getExtras().getInt("misCamisas");
        galeriaAdapterCami = new GaleriaCamisasAdapter(this);
        imageCompletaCami.setImageResource(galeriaAdapterCami.imageArrayCami[position]);
    }
}
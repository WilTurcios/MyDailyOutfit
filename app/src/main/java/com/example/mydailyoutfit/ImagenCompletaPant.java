package com.example.mydailyoutfit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mydailyoutfit.adaptador.GaleriaPantalonesAdapter;

public class ImagenCompletaPant extends AppCompatActivity {
    //DECLARACION DE LA VARIABLE
    ImageView imageCompletaPant;
    GaleriaPantalonesAdapter galeriaAdapterPant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_imagen_completa_pant);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imageCompletaPant=findViewById(R.id.ivCompletaPant);

        //ACTIONBAR PARA INDICAR EN QUE PARTE DE LA APP SE ENCUENTRA
        //ActionBar actionBar = getSupportActionBar();
        // actionBar.setTitle("Foto Completa");

        Intent intent = getIntent();
        //REFERENCIA A LA LLAVE QUE SE CREO EN GALERIA
        int position = intent.getExtras().getInt("misPantalones");
        galeriaAdapterPant = new GaleriaPantalonesAdapter(this);
        imageCompletaPant.setImageResource(galeriaAdapterPant.imageArrayPant[position]);
    }
}
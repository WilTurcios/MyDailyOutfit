package com.example.mydailyoutfit;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView menubuttonprin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        menubuttonprin=findViewById(R.id.menubuttonprincipal);

        // Establecer el elemento seleccionado en el BottomNavigationView
        menubuttonprin.setSelectedItemId(R.id.inicio);

        // Cargar el fragmento de inicio al iniciar la aplicación
        if (savedInstanceState == null) {
            loadFragment(new InicioFragment());
        }

        menubuttonprin.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null; // Variable para almacenar el fragmento seleccionado

                if (item.getItemId() == R.id.inicio) {
                    selectedFragment = new InicioFragment(); // Cargar el fragmento de inicio
                } else if (item.getItemId() == R.id.avatar) {
                    selectedFragment = new AvatarFragment(); // Cargar el fragmento de avatar
                } else if (item.getItemId() == R.id.newoutfit) {
                    selectedFragment = new NuevoOutfitFragment(); // Cargar el fragmento de nuevo outfit
                } else if (item.getItemId() == R.id.categorias) {
                    selectedFragment = new CategoriasFragment(); // Cargar el fragmento de categorías
                }

                return loadFragment(selectedFragment); // Cargar el fragmento seleccionado
            }
        });
    }

    // Método para cargar el fragmento
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true; // Carga exitosa
        }
        return false; // No se cargó ningún fragmento
    }
}
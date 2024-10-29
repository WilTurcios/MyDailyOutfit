package com.example.mydailyoutfit;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NuevoOutfitFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NuevoOutfitFragment extends Fragment {
    //METODO PARA GUARDAR LA IMAGEN
    FloatingActionButton guardarimagen;
    //DECLARACION DE LA VARIABLE PARA OCUPARLA EN LA INVOCACION DE LA PANTALLA VESTIDO
    FloatingActionButton submenuvestido;
    //DECLARACION DE LA VARIABLE PARA OCUPARLA EN LA INVOCACION DE LA PANTALLA FALDA
    FloatingActionButton submenufalda;
    //DECLARACION DE LA VARIABLE PARA OCUPARLA EN LA INVOCACION DE LA PANTALLA CAMISA
    FloatingActionButton submenucamisa;
    //DECLARACION DE LA VARIABLE PARA OCUPARLA EN LA INVOCACION DE LA PANTALLA PANTALON
    FloatingActionButton submenupantalon;
    //DECLARACION DE LA VARIABLE PARA OCUPARLA EN LA INVOCACION DE LA PANTALLA PANTALON
    FloatingActionButton submenushort;
    //DECLARACION DE LA VARIABLE PARA DEL FLOATINGACTIONMENU
    FloatingActionMenu actionMenu;
    //DECLARACION DE VARIABLE PARA EL BOTON DE CAMARA
    FloatingActionButton btncamara;
    //DECLARACION DE VARIABLE PARA EL IMAGEVIEW EN DONDE SE MUESTRA LA IMAGEN
    ImageView imgViewCamara;
    // DECLARACION DEL ACTIVITYRESULTLAUNCHER del ActivityResultLauncher
    private ActivityResultLauncher<Intent> cameraLauncher;
    // Para almacenar la URI de la imagen
    private Uri imageUri;
    private static final int REQUEST_CAMERA_PERMISSION = 100;

    public String img, url;

    private ActivityResultLauncher<String> requestCameraPermissionLauncher;




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NuevoOutfitFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NuevoOutfitFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NuevoOutfitFragment newInstance(String param1, String param2) {
        NuevoOutfitFragment fragment = new NuevoOutfitFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //REFERENCIAMOS
        FloatingActionMenu actionMenu;
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        // INICIALIZAMOS EL ActivityResultLauncher para manejar permisos
        requestCameraPermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) {
                        solicitarPermisoCamara();  // Si el permiso es concedido, abrimos la cámara
                    } else {
                        Toast.makeText(getActivity(), "Permiso de cámara denegado", Toast.LENGTH_SHORT).show();
                    }
                }
        );


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nuevo_outfit, container, false);

        //PROGRAMACION PARA HACER QUE EL MENÚ DESAPAREZCA AL HACER CLIC FUERA DE EL
        //INICIALIZANDO
        actionMenu = view.findViewById(R.id.menuPrincipal);
        //INDICANDO QUE SE CIERRE EL MENÚ AL HACER CLIC FUERA
        actionMenu.setClosedOnTouchOutside(true);



        //EVENTO ONCLICK PARA GUARDAR IMAGEN
        guardarimagen= view.findViewById(R.id.guardar);
        guardarimagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {

                    url = "https://metcodmdo.site/WebPHPService/imagen.php";
                    RequestParams parametros = new RequestParams();
                    parametros.put("imagen", imageUri);

                    AsyncHttpClient cliente = new AsyncHttpClient();
                    cliente.post(url, parametros, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            if (statusCode == 200) {

                                Toast.makeText(getActivity(), "Dato insertado con exito!!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                            Toast.makeText(getActivity(), "No se  pudo insertar", Toast.LENGTH_SHORT).show();

                        }
                    });


                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }



            }
        });


        //EVENTO ONCLICK PARA ABRIR EL SUBMENÚ VESTIDO
        submenuvestido = view.findViewById(R.id.subMenuVestido);
        submenuvestido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GaleriaVestidosFragment galeriaVestidosFragment = new GaleriaVestidosFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, galeriaVestidosFragment);
                transaction.addToBackStack(null);  // REGRESAR AL FRAGMENTO ANTERIOR
                transaction.commit();
            }
        });

        //EVENTO ONCLICK PARA ABRIR EL SUBMENÚ FALDA
        submenufalda = view.findViewById(R.id.subMenuFalda);
        submenufalda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GaleriaFaldasFragment galeriaFaldasFragment = new GaleriaFaldasFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, galeriaFaldasFragment);
                transaction.addToBackStack(null);  // REGRESAR AL FRAGMENTO ANTERIOR
                transaction.commit();
            }
        });

        //EVENTO ONCLICK PARA ABRIR EL SUBMENÚ CAMISA
        submenucamisa = view.findViewById(R.id.subMenuCamisa);
        submenucamisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GaleriaCamisasFragment galeriaCamisasFragment = new GaleriaCamisasFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, galeriaCamisasFragment);
                transaction.addToBackStack(null);  // REGRESAR AL FRAGMENTO ANTERIOR
                transaction.commit();
            }
        });

        //EVENTO ONCLICK PARA ABRIR EL SUBMENÚ PANTALON
        submenupantalon = view.findViewById(R.id.subMenuPantalon);
        submenupantalon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GaleriaPantalonesFragment galeriaPantalonesFragment = new GaleriaPantalonesFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, galeriaPantalonesFragment);
                transaction.addToBackStack(null);  // REGRESAR AL FRAGMENTO ANTERIOR
                transaction.commit();

            }
        });

        //EVENTO ONCLICK PARA ABRIR EL SUBMENÚ SHORT
        submenushort = view.findViewById(R.id.subMenuShort);
        submenushort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GaleriaShortsFragment galeriaShortsFragment = new GaleriaShortsFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, galeriaShortsFragment);
                transaction.addToBackStack(null);  // REGRESAR AL FRAGMENTO ANTERIOR
                transaction.commit();
            }
        });

        //PROGRAMACION PARA ACCEDER A LA CAMARA
        btncamara=view.findViewById(R.id.camara);
        imgViewCamara = view.findViewById(R.id.imgcamara);

        //EVENTO PARA EL CLIC DEL BOTON PARA ACCEDER A LA CAMARA
        btncamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solicitarPermisoCamara();
            }

        });

        // INICIALIZAR EL LAUNCHER
        cameraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == AppCompatActivity.RESULT_OK) {
                // La imagen ya está guardada en el URI, solo la cargamos
                imgViewCamara.setImageURI(imageUri);
            }
        });

        return view;
    }

    //METODO PARA ABRIR LA CAMARA
    private void solicitarPermisoCamara() {
        // Verificar permisos de cámara
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Si no se ha otorgado el permiso, solicítalo
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        } else {
            // Si el permiso ya fue concedido, continúa con la apertura de la cámara
            abrircamara();

        }
    }

    private void abrircamara() {
        //CONTENTVALUE SE UTILIZA PARA ESPESIFICAR LOS DETALLES DE LA IMAGEN VOMO EL TITULO Y LA DESCRIPCION
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Nueva Imagen");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Imagen capturada desde la cámara");
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");

        // Crea un archivo en el almacenamiento externo
        imageUri = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //INFORMA A LA CAMARA EN DONDE GUARDAR LA IMAGEN
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri); // Usa el URI creado
        cameraLauncher.launch(intent);
    }


  /*  public void guardarImagen(View view) {

        try {

            url = "https://metcodmdo.site/WebPHPService/imagen.php";
            RequestParams parametros = new RequestParams();
            parametros.put("imagen", imageUri);

            AsyncHttpClient cliente = new AsyncHttpClient();
            cliente.post(url, parametros, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    if (statusCode == 200) {

                        Toast.makeText(getActivity(), "Dato insertado con exito!!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    Toast.makeText(getActivity(), "No se  pudo insertar", Toast.LENGTH_SHORT).show();

                }
            });


        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
        }
    }
*/
}
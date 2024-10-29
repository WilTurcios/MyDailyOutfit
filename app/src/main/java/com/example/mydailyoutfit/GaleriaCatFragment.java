package com.example.mydailyoutfit;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.mydailyoutfit.adaptador.CategoriaGaleriaAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GaleriaCatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GaleriaCatFragment extends Fragment {
    //VARIABLE PARA EL GRIDVIEW DE LA GALERIA
    GridView gridGaleria;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GaleriaCatFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GaleriaCatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GaleriaCatFragment newInstance(String param1, String param2) {
        GaleriaCatFragment fragment = new GaleriaCatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_galeria_cat, container, false);

        gridGaleria = view.findViewById(R.id.gridviewimages);
        gridGaleria.setAdapter(new CategoriaGaleriaAdapter(getContext()));

        gridGaleria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //ENVIANDO LA IMAGEN SELECICONADA A OTRA PANTALLA
                Intent VentanaImgCompleta = new Intent(getActivity(), ImagenCompleta.class);
                //CREACION DE LLAVE PARA EL ENVIO LLAMADA "misImagenes"
                VentanaImgCompleta.putExtra("misImagenes", position);
                //INICIANDO LA ACTIVIDAD
                startActivity(VentanaImgCompleta);
            }
        });
        return view;
    }
}
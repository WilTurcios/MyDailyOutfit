package com.example.mydailyoutfit;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.mydailyoutfit.adaptador.GaleriaFaldasAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GaleriaFaldasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GaleriaFaldasFragment extends Fragment {
    //VARIABLE PARA EL GRIDVIEW DE LA GALERIA
    GridView gridGaleriaFaldas;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GaleriaFaldasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GaleriaFaldasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GaleriaFaldasFragment newInstance(String param1, String param2) {
        GaleriaFaldasFragment fragment = new GaleriaFaldasFragment();
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
        View view = inflater.inflate(R.layout.fragment_galeria_faldas, container, false);

        gridGaleriaFaldas=view.findViewById(R.id.gridviewimagesFaldas);
        gridGaleriaFaldas.setAdapter(new GaleriaFaldasAdapter(requireContext()));

        gridGaleriaFaldas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //ENVIANDO LA IMAGEN SELECICONADA A OTRA PANTALLA
                Intent VentanaImgCompletaFal = new Intent(getActivity(), ImagenCompletaFal.class);
                //CREACION DE LLAVE PARA EL ENVIO LLAMADA "misFaldas"
                VentanaImgCompletaFal.putExtra("misFaldas", position);
                //INICIANDO LA ACTIVIDAD
                startActivity(VentanaImgCompletaFal);
            }
        });
        return view;
    }
}
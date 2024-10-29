package com.example.mydailyoutfit;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.mydailyoutfit.adaptador.GaleriaVestidosAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GaleriaVestidosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GaleriaVestidosFragment extends Fragment {
    //VARIABLE PARA EL GRIDVIEW DE LA GALERIA
    GridView gridGaleriaVestidos;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GaleriaVestidosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GaleriaVestidosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GaleriaVestidosFragment newInstance(String param1, String param2) {
        GaleriaVestidosFragment fragment = new GaleriaVestidosFragment();
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
        View view = inflater.inflate(R.layout.fragment_galeria_vestidos, container, false);
        gridGaleriaVestidos=view.findViewById(R.id.gridviewimagesVestidos);
        gridGaleriaVestidos.setAdapter(new GaleriaVestidosAdapter(getContext()));

        gridGaleriaVestidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //ENVIANDO LA IMAGEN SELECICONADA A OTRA PANTALLA
                Intent VentanaImgCompletaVes = new Intent(getActivity(), ImagenCompletaVes.class);
                //CREACION DE LLAVE PARA EL ENVIO LLAMADA "misImagenes"
                VentanaImgCompletaVes.putExtra("misVestidos", position);
                //INICIANDO LA ACTIVIDAD
                startActivity(VentanaImgCompletaVes);
            }
        });
        return view;
    }
}
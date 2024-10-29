package com.example.mydailyoutfit.adaptador;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.mydailyoutfit.R;

public class GaleriaVestidosAdapter extends BaseAdapter {
    private Context mContextVes;

    //ARRAY DE LAS IMAGENES
    public int[] imageArrayVes = {
            R.drawable.img7,
            R.drawable.img8,
            R.drawable.img9,
            R.drawable.img10
    };

    //CONSTRUCTOR
    public GaleriaVestidosAdapter(Context mContextVes) {
        this.mContextVes = mContextVes;
    }

    //METODOS CREADOS AUTOMATICAMENTE DESDE EL FOCO ROJO EN LA CLASE
    //SE MODIFICA EL GETCOUNT AGREGANDO (return imageArray.length;) PARA QUE RETORNE LO QUE HAY EN LA LISTA
    @Override
    public int getCount() {
        return imageArrayVes.length;
    }

    //EL GETITEM SIRVE PARA RETORNAR LA POSICION
    @Override
    public Object getItem(int position) {
        return imageArrayVes[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //EL GETVIEW ES EL QUE MUESTRA LAS IMAGENES
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageViewVes = new ImageView(mContextVes);
        imageViewVes.setImageResource(imageArrayVes[position]);
        imageViewVes.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageViewVes.setLayoutParams(new AbsListView.LayoutParams(340, 350));

        return imageViewVes;    }
}

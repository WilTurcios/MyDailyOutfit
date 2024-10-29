package com.example.mydailyoutfit.adaptador;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.mydailyoutfit.R;

public class GaleriaCamisasAdapter extends BaseAdapter {
    private Context mContextCami;

    //ARRAY DE LAS IMAGENES
    public int[] imageArrayCami = {
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6
    };

    //CONSTRUCTOR
    public GaleriaCamisasAdapter(Context mContextCami) {
        this.mContextCami = mContextCami;
    }

    //METODOS CREADOS AUTOMATICAMENTE DESDE EL FOCO ROJO EN LA CLASE
    //SE MODIFICA EL GETCOUNT AGREGANDO (return imageArray.length;) PARA QUE RETORNE LO QUE HAY EN LA LISTA
    @Override
    public int getCount() {
        return imageArrayCami.length;
    }

    //EL GETITEM SIRVE PARA RETORNAR LA POSICION
    @Override
    public Object getItem(int position) {
        return imageArrayCami[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //EL GETVIEW ES EL QUE MUESTRA LAS IMAGENES
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageViewVes = new ImageView(mContextCami);
        imageViewVes.setImageResource(imageArrayCami[position]);
        imageViewVes.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageViewVes.setLayoutParams(new AbsListView.LayoutParams(340, 350));

        return imageViewVes;
    }
}

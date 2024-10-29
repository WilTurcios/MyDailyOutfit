package com.example.mydailyoutfit.adaptador;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.mydailyoutfit.R;

import java.util.Base64;

public class GaleriaShortsAdapter extends BaseAdapter {
    private Context mContextSho;

    //ARRAY DE LAS IMAGENES
    public int[] imageArraySho = {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6,
            R.drawable.img7,
            R.drawable.img8,
            R.drawable.img9,
            R.drawable.img10,
            R.drawable.img6,
            R.drawable.img7,
            R.drawable.img7,
            R.drawable.img7,
            R.drawable.img7
    };

    //CONSTRUCTOR
    public GaleriaShortsAdapter(Context mContextSho) {
        this.mContextSho = mContextSho;
    }

    //METODOS CREADOS AUTOMATICAMENTE DESDE EL FOCO ROJO EN LA CLASE
    //SE MODIFICA EL GETCOUNT AGREGANDO (return imageArray.length;) PARA QUE RETORNE LO QUE HAY EN LA LISTA
    @Override
    public int getCount() {
        return imageArraySho.length;
    }

    //EL GETITEM SIRVE PARA RETORNAR LA POSICION
    @Override
    public Object getItem(int position) {
        return imageArraySho[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //EL GETVIEW ES EL QUE MUESTRA LAS IMAGENES
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageViewVes = new ImageView(mContextSho);
        imageViewVes.setImageResource(imageArraySho[position]);
        imageViewVes.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageViewVes.setLayoutParams(new AbsListView.LayoutParams(340, 350));

        return imageViewVes;    }
}

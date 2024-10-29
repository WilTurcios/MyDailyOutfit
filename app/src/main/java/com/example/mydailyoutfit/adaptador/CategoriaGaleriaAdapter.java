package com.example.mydailyoutfit.adaptador;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.mydailyoutfit.R;

public class CategoriaGaleriaAdapter extends BaseAdapter {
    private Context mContext;

    //ARRAY DE LAS IMAGENES
    public int[] imageArray = {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4
    };

    //CONSTRUCTOR
    public CategoriaGaleriaAdapter(Context mContext) {
        this.mContext = mContext;
    }

    //METODOS CREADOS AUTOMATICAMENTE
    //SE MODIFICA EL GETCOUNT AGREGANDO (return imageArray.length;) PARA QUE RETORNE LO QUE HAY EN LA LISTA
    @Override
    public int getCount() {
        return imageArray.length;
    }

    //EL GETITEM SIRVE PARA RETORNAR LA POSICION
    @Override
    public Object getItem(int position) {
        return imageArray[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //EL GETVIEW ES EL QUE MUESTRA LAS IMAGENES
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(imageArray[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new AbsListView.LayoutParams(340, 350));

        return imageView;    }
}

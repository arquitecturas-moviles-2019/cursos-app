package com.arquitecturasmoviles.asado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.arquitecturasmoviles.asado.model.Curso;

import java.util.ArrayList;

public class AdaptCurseListActivity extends BaseAdapter {
    Context context;
    ArrayList<Curso>listadoCursosDelEvento;
    String lugar;

    public AdaptCurseListActivity(ArrayList<Curso>listadoCursosDelEvento, Context context, String lugar){
        this.listadoCursosDelEvento = listadoCursosDelEvento;
        this.context = context;
        this.lugar = lugar;
    }


    @Override
    public int getCount() {
        return listadoCursosDelEvento.size();
    }

    @Override
    public Object getItem(int position) {
        return listadoCursosDelEvento.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapt_curse_list_activity, parent, false);
        }

        Curso currentCurse = (Curso) getItem(position);

        ImageView iv_calendar = convertView.findViewById(R.id.iv_calendar);
        ImageView iv_location = convertView.findViewById(R.id.iv_location);


        TextView tv_nombreCurso = convertView.findViewById(R.id.tv_tituloAdaptadorCurso);
        TextView tv_fecha = convertView.findViewById(R.id.tv_AdaptadorCursoFechaHora);
        TextView tv_lugar = convertView.findViewById(R.id.tv_adaptadorCursoLugar);

        tv_nombreCurso.setText(currentCurse.getNombre());
        tv_fecha.setText(currentCurse.getDiaHora());
        tv_lugar.setText(this.lugar);

        return convertView;
    }
}

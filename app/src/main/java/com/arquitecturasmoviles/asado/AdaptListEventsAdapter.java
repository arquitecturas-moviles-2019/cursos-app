package com.arquitecturasmoviles.asado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.arquitecturasmoviles.asado.model.Curso;
import com.arquitecturasmoviles.asado.model.Evento;

import java.util.ArrayList;

public class AdaptListEventsAdapter extends BaseAdapter {

    Context context;
    ArrayList<Evento> eventList;

    public AdaptListEventsAdapter(ArrayList<Evento> eventList, Context context){
        this.eventList = eventList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return eventList.size();
    }

    @Override
    public Object getItem(int position) {
        return eventList.get(position);
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

        Evento currentEvent = (Evento) getItem(position);

        ImageView iv_calendar = convertView.findViewById(R.id.iv_calendar);
        ImageView iv_location = convertView.findViewById(R.id.iv_location);


        TextView tv_nombreEvento = convertView.findViewById(R.id.tv_tituloAdaptadorCurso);
        TextView tv_fecha = convertView.findViewById(R.id.tv_AdaptadorCursoFechaHora);
        TextView tv_lugar = convertView.findViewById(R.id.tv_adaptadorCursoLugar);

        tv_nombreEvento.setText(currentEvent.getNombre());
        //tv_fecha.setText(currentEvent.getDiaHora());
        tv_lugar.setText(currentEvent.getLugar());

        return convertView;
    }
}

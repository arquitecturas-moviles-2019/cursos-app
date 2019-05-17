package com.arquitecturasmoviles.asado;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.arquitecturasmoviles.asado.model.Curso;
import com.arquitecturasmoviles.asado.model.CursosResponse;
import com.arquitecturasmoviles.asado.model.Evento;
import com.arquitecturasmoviles.asado.model.EventosResponse;
import com.arquitecturasmoviles.asado.network.RemoteApi;
import com.arquitecturasmoviles.asado.network.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {

    private RemoteApi remoteApi;

    public EventsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);

        remoteApi = RetrofitClientInstance.getRetrofitInstance().create(RemoteApi.class);
        Call<EventosResponse> allEventsCall = remoteApi.getAllEvents();
        allEventsCall.enqueue(new Callback<EventosResponse>() {
            @Override
            public void onResponse(Call<EventosResponse> call, Response<EventosResponse> response) {
                Snackbar.make(getActivity().findViewById(R.id.myCoursesListView), "OK", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                final ArrayList<Evento> listadoDeEventos;
                listadoDeEventos = response.body().getEventos();

                AdaptListEventsAdapter adaptador = new AdaptListEventsAdapter(listadoDeEventos, getActivity().getApplicationContext());
                final ListView eventsListView = getActivity().findViewById(R.id.eventsListView);
                eventsListView.setAdapter(adaptador);
                eventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent goToCourses = new Intent(getActivity().getApplicationContext(), CursosActivity.class);

                        Evento selectedEvent = listadoDeEventos.get(position);

                        goToCourses.putExtra(selectedEvent.KEY_ID, selectedEvent.getId());

                        startActivity(goToCourses);
                    }

                });
            }

            @Override
            public void onFailure(Call<EventosResponse> call, Throwable t) {
                Snackbar.make(getActivity().findViewById(R.id.myCoursesListView), "ERROR", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        return view;
    }

}

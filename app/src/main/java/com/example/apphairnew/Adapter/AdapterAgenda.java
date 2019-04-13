package com.example.apphairnew.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apphairnew.R;
import com.example.apphairnew.model.HorarioModel;

import java.util.Collections;
import java.util.List;

public class AdapterAgenda extends RecyclerView.Adapter<AdapterAgenda.ViewHolder> {
    List<HorarioModel> horarioModels = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;


    public AdapterAgenda(List<HorarioModel>horarioModel, Context context){
        this.horarioModels = horarioModel;
        this.inflater = LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public AdapterAgenda.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_lista_agenda, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);//
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAgenda.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {

        }
    }
}

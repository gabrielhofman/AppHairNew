package com.example.apphairnew.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apphairnew.R;
import com.example.apphairnew.response.GetHorarioResponse;

import java.util.List;

public class AdapterAgenda extends RecyclerView.Adapter<AdapterAgenda.ViewHolder>{
    itemClicadoListener itemClicado;
    List<GetHorarioResponse> horarioModels;
    private LayoutInflater inflater;
    private Context context;

    public AdapterAgenda(List<GetHorarioResponse> horarioModels, Context context) {
        this.horarioModels = horarioModels;
        this.inflater = inflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterAgenda.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_lista_agenda, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAgenda.ViewHolder holder, int position) {

        GetHorarioResponse horarioModel = horarioModels.get(position);
        holder.horaInicio.setText(String.valueOf(horarioModel.getHoraInicio()));
        holder.horaFim.setText(String.valueOf(horarioModel.getHoraFim()));
   //    holder.nomeContato.setText(String.valueOf(horarioModel.getContato()));
 //       holder.nomeServico.setText(String.valueOf(horarioModel.getServico()));
    }

    @Override
    public int getItemCount() {
        return horarioModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView horaInicio;
        TextView horaFim;
        TextView nomeContato;
        TextView nomeServico;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            horaInicio = itemView.findViewById(R.id.labelHorarioInicio);
            horaFim = itemView.findViewById(R.id.labelHorarioFim);
            nomeContato = itemView.findViewById(R.id.labelNomeContato);
            nomeServico = itemView.findViewById(R.id.labelNomeServico);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemClicado != null){
                itemClicado.noItemClicado(view, getAdapterPosition());
            }

        }
    }

    public void setItemClicado(itemClicadoListener itemClicado) {
        this.itemClicado = itemClicado;
    }

    public interface itemClicadoListener{
        void noItemClicado(View view, int position);
    }

    public GetHorarioResponse getItem(int position){
        return horarioModels.get(position);
    }
}

package com.example.apphairnew.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.model.GetAgendaDetalhe;
import com.example.apphairnew.response.GetDetalheAgendaResponse;
import com.example.apphairnew.response.GetHorarioResponse;
import com.example.apphairnew.web.ApiControler;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterAgenda extends RecyclerView.Adapter<AdapterAgenda.ViewHolder>{
    itemClicadoListener itemClicado;
    List<GetHorarioResponse> horarioModels;
    private LayoutInflater inflater;
    private Context context;
    private ApiService service = ApiControler.CreateController();
    private GetAgendaDetalhe modelDetalhe;

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
    public void onBindViewHolder(@NonNull final AdapterAgenda.ViewHolder holder, int position) {






        final GetHorarioResponse horarioModel = horarioModels.get(position);


        this.modelDetalhe = new GetAgendaDetalhe();
        // this.modelDetalhe.contato = detalhe.getContato();

        this.modelDetalhe.contato = horarioModel.getContato();
        this.modelDetalhe.servico = horarioModel.getServico();




        service.GetDetalhe(modelDetalhe).enqueue(new Callback<GetDetalheAgendaResponse>() {
            @Override
            public void onResponse(Call<GetDetalheAgendaResponse> call, Response<GetDetalheAgendaResponse> response) {




                holder.nomeContato.setText(response.body().contato);


            }

            @Override
            public void onFailure(Call<GetDetalheAgendaResponse> call, Throwable t) {

            }
        });



        holder.dataAgenda.setText(String.valueOf(horarioModel.getDataAgenda()));
        holder.horaInicio.setText(String.valueOf(horarioModel.getHoraInicio()));
        holder.horaFim.setText(String.valueOf(horarioModel.getHoraFim()));
     //   holder.nomeContato.setText(String.valueOf(horarioModel.getContato()));

        String lixo = "aehoooo";
        //holder.nomeContato.setText(lixo);
      //  holder.nomeServico.setText(servico);






        if(horarioModel.getContato() == 1) {
            holder.dataAgenda.setTextColor(Color.GREEN);
            holder.horaInicio.setTextColor(Color.GREEN);
            holder.horaFim.setTextColor(Color.GREEN);
            holder.nomeContato.setTextColor(Color.GREEN);

        }else
        {
            holder.dataAgenda.setTextColor(Color.RED);
            holder.horaInicio.setTextColor(Color.RED);
            holder.horaFim.setTextColor(Color.RED);
            holder.nomeContato.setTextColor(Color.RED);

        }
    }


    @Override
    public int getItemCount() {
        return horarioModels.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView dataAgenda;
        TextView horaInicio;
        TextView horaFim;
        TextView nomeContato;
        TextView nomeServico;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dataAgenda = itemView.findViewById(R.id.labelDataAgenda);
            horaInicio = itemView.findViewById(R.id.labelHorarioInicio);
            horaFim = itemView.findViewById(R.id.labelHorarioFim);
            nomeContato = itemView.findViewById(R.id.labelNomeContato);


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

package com.example.apphairnew.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.response.GetLogResponse;
import com.example.apphairnew.web.ApiControler;

import java.util.List;

public class AdapterLog extends  RecyclerView.Adapter<AdapterLog.ViewHolder> {

    AdapterSerico.itemClicadoListener itemClicado;
    List<GetLogResponse> logResponses;
    private LayoutInflater inflater;
    private Context context;



    public AdapterLog(List<GetLogResponse> logResponses, Context context) {
        this.logResponses = logResponses;
        this.inflater = LayoutInflater.from(context);
        this.context = context;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_lista_log, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetLogResponse  logResponse = logResponses.get(position);
        String nome = logResponse.getNomeCliente() + " " + logResponse.getSobrenomeCliente();
        holder.nomeLog.setText(nome);
        holder.dataLog.setText(logResponse.getDataLog());
        holder.dataAgenda.setText(logResponse.getDataAgenda());
        holder.horaInicio.setText(logResponse.getHoraInicioAgenda());
        holder.horaFim.setText(logResponse.getHoraFimAgenda());
        holder.telefLog.setText(logResponse.getClienteTelef());

        String tipoLog = "indefinido";
        if(logResponse.getTipoLog().equals("A"))
        {
            tipoLog = "Agendamento";
            holder.linearLayout.setBackgroundColor(Color.parseColor("#e6ffcc"));
        }
        if(logResponse.getTipoLog().equals("C"))
        {
            tipoLog = "Cancelamento";
            holder.linearLayout.setBackgroundColor(Color.parseColor("#ffcccc"));
        }

        holder.tipoLog.setText(tipoLog);

    }

    @Override
    public int getItemCount() {
        return logResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
            TextView nomeLog;
            TextView dataLog;
             TextView dataAgenda;
            TextView telefLog;
            TextView horaInicio;
            TextView horaFim;
            TextView tipoLog;
            LinearLayout linearLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.linearLayout);
            nomeLog = itemView.findViewById(R.id.nome_log);
            dataLog = itemView.findViewById(R.id.data_log);
            dataAgenda = itemView.findViewById(R.id.data_alteracao);
            telefLog = itemView.findViewById(R.id.telef_log);
            horaInicio = itemView.findViewById(R.id.hora_inicio);
            horaFim = itemView.findViewById(R.id.hora_fim);
            tipoLog = itemView.findViewById(R.id.tipo_log);
        }

        @Override
        public void onClick(View v) {

        }
    }



}

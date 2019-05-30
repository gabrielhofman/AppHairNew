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
import com.example.apphairnew.response.GetFluxoCaixaResponse;

import java.util.List;




public class AdapterFluxoCaixa extends RecyclerView.Adapter<AdapterFluxoCaixa.ViewHolder> {

    itemClicadoListener itemClicado;
    List<GetFluxoCaixaResponse> fluxoCaixas;
    private LayoutInflater inflater;
    private Context context;


    public AdapterFluxoCaixa(List<GetFluxoCaixaResponse> fluxoCaixa, Context context) {
        this.fluxoCaixas = fluxoCaixa;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_fluxo_caixa, parent, false);
        AdapterFluxoCaixa.ViewHolder viewHolder = new AdapterFluxoCaixa.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        GetFluxoCaixaResponse fluxoCaixa = fluxoCaixas.get(position);
        holder.fluxoValor.setText(String.valueOf(fluxoCaixa.getValorFluxo()));
        holder.fluxoData.setText(fluxoCaixa.getDataFluxo());
        if(fluxoCaixa.getValorFluxo() < 0)
        {
            // holder.nomeContato.setTextColor(Color.RED);
            holder.fluxoValor.setTextColor(Color.RED);
            holder.fluxoData.setTextColor(Color.RED);

        }else
        {
            holder.fluxoValor.setTextColor(Color.GREEN);
            holder.fluxoData.setTextColor(Color.GREEN);
        }


    }

    @Override
    public int getItemCount() {
        return  fluxoCaixas.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView fluxoValor;
        TextView fluxoData;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fluxoValor = itemView.findViewById(R.id.labelValorFluxo);
            fluxoData = itemView.findViewById(R.id.labelDataFluxo);
            itemView.setOnClickListener(this);




        }

        @Override
        public void onClick(View view) {
            if(itemClicado != null){
                itemClicado.noItemClicado(view, getAdapterPosition());
            }
        }
    }

    public void setItemClicado(AdapterFluxoCaixa.itemClicadoListener itemClicado) {
        this.itemClicado = itemClicado;
    }

    public interface itemClicadoListener{
        void noItemClicado(View view, int position);

    }


    public GetFluxoCaixaResponse getItem(int position)
    {
        return fluxoCaixas.get(position);
    }

}

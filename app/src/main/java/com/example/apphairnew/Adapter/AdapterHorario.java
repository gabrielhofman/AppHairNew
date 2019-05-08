package com.example.apphairnew.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apphairnew.R;
import com.example.apphairnew.response.GetHorarioResponse;


import java.util.List;

public class AdapterHorario extends RecyclerView.Adapter<AdapterHorario.ViewHolder> {


    itemClicadoListener itemClicado;
    List<GetHorarioResponse> horarioModels;
    private LayoutInflater inflater;
    private Context context;


    public AdapterHorario(List<GetHorarioResponse> horarioResponses, Context context) {
        this.horarioModels = horarioResponses;
        this.inflater = LayoutInflater.from(context);
        this.context = context;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // View view = inflater.inflate(R.layout.row_lista_produto, parent, false);
        //        ViewHolder viewHolder = new ViewHolder(view);
        //        return viewHolder;

        View view = inflater.inflate(R.layout.row_lista_horario, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);//
        return viewHolder;


    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //DataSetProduto dataSetProduto = dataSetProdutos.get(position);
        //        try{
        //            Picasso.with(context).load(dataSetProduto.getImagemProduto().getUrl()).into(holder.imagemProduto);
        //
        //        }catch(Exception e){
        //            holder.imagemProduto.setImageDrawable(null);
        //        }
        //
        //        holder.textoProduto.setText(dataSetProduto.getDescricaoProduto());
        //        holder.textoValor.setText(String.valueOf(dataSetProduto.getPrecoProduto()));
        //        holder.titulo.setText(dataSetProduto.getNomeProduto());


       // GetHorarioResponse horarioModel = horarioModel.get(position);
        GetHorarioResponse horarioModel = horarioModels.get(position);
        holder.dataHorario.setText(horarioModel.horaInicio);

    }
    @Override

    public int getItemCount() {
        return horarioModels.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView dataHorario;


        public ViewHolder(View itemView) {
            super(itemView);

            dataHorario = itemView.findViewById(R.id.dataHorario);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (itemClicado != null) {
                itemClicado.noItemClicado(view, getAdapterPosition());
            }


        }
    }

    public void setItemClicado(itemClicadoListener itemClicado) {
        this.itemClicado = itemClicado;
    }


    public interface itemClicadoListener {
            void noItemClicado(View view, int position);

        }

        public GetHorarioResponse getItem(int position) {
            return horarioModels.get(position);
        }


    }



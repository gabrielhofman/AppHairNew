package com.example.apphairnew.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apphairnew.R;
import com.example.apphairnew.response.GetCtsReceberResponse;

import java.util.List;

public class AdapterCtsReceber extends RecyclerView.Adapter<AdapterCtsReceber.ViewHolder> {

    itemClicadoListener itemClicado;
    List<GetCtsReceberResponse> ctsReceberModels;
    private LayoutInflater inflater;
    private Context context;

    public AdapterCtsReceber(List<GetCtsReceberResponse> ctsReceberModel, Context context) {
        this.ctsReceberModels = ctsReceberModel;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_lista_cts_receber, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        GetCtsReceberResponse ctsReceberModel = ctsReceberModels.get(position);
        holder.recebValor.setText(String.valueOf(ctsReceberModel.getRecebValor()));
        holder.recebVencimento.setText(String.valueOf(ctsReceberModel.getRecebVencimento()));
    }

    @Override
    public int getItemCount() {
        return ctsReceberModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView recebValor;
        TextView recebVencimento;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recebValor=itemView.findViewById(R.id.labelValorRecebLst);
            recebVencimento=itemView.findViewById(R.id.labelDataRecebLst);
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

    public GetCtsReceberResponse getItem(int position ) {
        return ctsReceberModels.get(position);
    }
}

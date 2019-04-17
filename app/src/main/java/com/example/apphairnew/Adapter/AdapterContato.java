package com.example.apphairnew.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apphairnew.R;
import com.example.apphairnew.response.GetContatoResponse;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterContato extends RecyclerView.Adapter<AdapterContato.ViewHolder> {

    itemClicadoListener itemClicado;
    List<GetContatoResponse> contatoModels;
    private LayoutInflater inflater;
    private Context context;

    public AdapterContato(List<GetContatoResponse>contatoModel, Context context) {
        this.contatoModels = contatoModel;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_lista_contato, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        GetContatoResponse contatoModel = contatoModels.get(position);
        holder.nomeContato.setText(String.valueOf(contatoModel.getNomeContato()));

    }

    @Override
    public int getItemCount() {
        return contatoModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView nomeContato;
        TextView telContado;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeContato = itemView.findViewById(R.id.campoNomeContato);
            telContado = itemView.findViewById(R.id.campoTelefoneContato);




        }

        @Override
        public void onClick(View view) {
            if(itemClicado != null)
            {
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

    public GetContatoResponse getItem(int position){
        return contatoModels.get(position);
    }

}


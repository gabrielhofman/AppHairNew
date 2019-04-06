package com.example.apphairnew.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apphairnew.R;
import com.example.apphairnew.model.ContatoModel;

import java.util.Collections;
import java.util.List;

public class ContatoAdapter extends RecyclerView.Adapter {
    //iemCli
    List<ContatoModel> contatoModels = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public ContatoAdapter(List<ContatoModel>contatoModel, Context context){
        this.contatoModels = contatoModel;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return contatoModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView nomeContato;
        TextView telContato;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeContato = itemView.findViewById(R.id.labelNomeContato);
            telContato = itemView.findViewById(R.id.labelTelefoneContato);
            itemView.setOnClickListener(this);
            //botaoAdicionar.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
          //  if (itemClicado !=null) {
           //     itemClicado.noItemClicado(view, getAdapterPosition());
          //  }

        }


    }
}

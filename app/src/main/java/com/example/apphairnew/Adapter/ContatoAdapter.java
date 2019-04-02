package com.example.apphairnew.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apphairnew.R;
import com.example.apphairnew.model.ContatoModel;

import java.util.List;

public class ContatoAdapter extends RecyclerView.Adapter<ContatoAdapter.ViewHolderContato> {

    private List<ContatoModel> listaContato;

    public ContatoAdapter(List<ContatoModel> listaContato){
        this.listaContato = listaContato;
    }

    @NonNull
    @Override
    public ContatoAdapter.ViewHolderContato onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ContatoAdapter.ViewHolderContato viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return listaContato.size();
    }

    public class ViewHolderContato extends RecyclerView.ViewHolder{
        public TextView nomeCliente;
        public TextView telCliente;



        public ViewHolderContato(@NonNull View itemView) {
            super(itemView);

            nomeCliente = (TextView) itemView.findViewById(R.id.labelNomeContato);
            telCliente = (TextView) itemView.findViewById(R.id.labelTelefoneContato);

        }
    }
}

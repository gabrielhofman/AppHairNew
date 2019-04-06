package com.example.apphairnew.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.apphairnew.R;
import com.example.apphairnew.model.ServicoModel;

import java.util.Collections;
import java.util.List;

public class AdapterSerico extends RecyclerView.Adapter<AdapterSerico.ViewHolder> {
    List<ServicoModel> servicoModels = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;


    public AdapterSerico(List<ServicoModel>servicoModel, Context context){
        this.servicoModels = servicoModel;
        this.inflater = LayoutInflater.from(context);
        this.context =context;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // View view = inflater.inflate(R.layout.row_lista_produto, parent, false);
        //        ViewHolder viewHolder = new ViewHolder(view);
        //        return viewHolder;

        View view = inflater.inflate(R.layout.row_lista_servico, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);//
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {


    }



    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {

        }
    }
}

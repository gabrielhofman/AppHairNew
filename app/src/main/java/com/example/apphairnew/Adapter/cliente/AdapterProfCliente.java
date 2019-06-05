package com.example.apphairnew.Adapter.cliente;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.response.GetProfResponse;
import com.example.apphairnew.web.ApiControler;

import java.util.ArrayList;
import java.util.List;

public class AdapterProfCliente extends RecyclerView.Adapter<AdapterProfCliente.ViewHolder> {

    itemClicadoListener itemClicado;
    List<GetProfResponse> profModels;
    private LayoutInflater inflater;
    private Context context;
    private ApiService service = ApiControler.CreateController();

    public AdapterProfCliente(List<GetProfResponse> profModel, Context context) {
        this.profModels = profModel;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterProfCliente.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_lista_prof_cliente, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterProfCliente.ViewHolder holder, int position) {
        final GetProfResponse profModel = profModels.get(position);

        byte[] decodedString = Base64.decode(profModel.getBmFotoProfissional(), Base64.DEFAULT);
        // System.out.println(contatoModel.getFotoContato());
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);


       holder.fotoProfCliente.setImageBitmap(decodedByte);
       holder.nomeProfCliente.setText(String.valueOf(profModel.getNomeEstab()));
       holder.cidadeCliente.setText(String.valueOf(profModel.getCidade()));
    }

    @Override
    public int getItemCount() {
        return profModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView fotoProfCliente;
        TextView nomeProfCliente;
        TextView cidadeCliente;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fotoProfCliente = itemView.findViewById(R.id.fotoProfCliente);
            nomeProfCliente = itemView.findViewById(R.id.labelNomeProfCliente);
            cidadeCliente = itemView.findViewById(R.id.labelCidadeProfCliente);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            {
                itemClicado.noItemClicado(view, getAdapterPosition());
                // Toast.makeText(context.getApplicationContext(), contatoModel.getFotoContato() , Toast.LENGTH_SHORT).show();

            }
        }
    }

    public void setItemClicado(itemClicadoListener itemClicado) {
        this.itemClicado = itemClicado;
    }

    public interface itemClicadoListener{
        void noItemClicado(View view, int position);
    }

    public GetProfResponse getItem(int position){
        return profModels.get(position);
    }

    public void updateList(List<GetProfResponse> newList){
        profModels = new ArrayList<>();
        profModels.addAll(newList);
        notifyDataSetChanged();
}}

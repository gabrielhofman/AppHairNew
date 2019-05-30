package com.example.apphairnew.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.model.ContatoModel;
import com.example.apphairnew.response.GetContatoResponse;
import com.example.apphairnew.ui.ContatoLista;
import com.example.apphairnew.web.ApiControler;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AdapterContato extends RecyclerView.Adapter<AdapterContato.ViewHolder> {

    itemClicadoListener itemClicado;
    List<GetContatoResponse> contatoModels;
    private LayoutInflater inflater;
    private Context context;
    private ApiService service = ApiControler.CreateController();
    //ContatoModel contatoModel = new ContatoModel();

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
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final GetContatoResponse contatoModel = contatoModels.get(position);

        byte[] decodedString = Base64.decode(contatoModel.getFotoContato(), Base64.DEFAULT);
        // System.out.println(contatoModel.getFotoContato());
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);


        holder.fotoContato.setImageBitmap(decodedByte);
        holder.nomeContato.setText(String.valueOf(contatoModel.getNomeContato()));
        holder.telContado.setText(String.valueOf(contatoModel.getTelContato()));
        holder.opcoesMenuContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.opcoesMenuContato);
                popupMenu.inflate(R.menu.op_menu_contato);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()){
                            case R.id.menu_item_apagar_contato:
                                ContatoLista recarrega = new ContatoLista();
                                //recarrega.ExcluirItem(contatoModel.getId());

                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return contatoModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView fotoContato;
        TextView nomeContato;
        TextView telContado;
        TextView opcoesMenuContato;

//f
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fotoContato = itemView.findViewById(R.id.fotoContato);
            nomeContato = itemView.findViewById(R.id.labelNomeContato);
            telContado = itemView.findViewById(R.id.labelTelefoneContato);
            opcoesMenuContato = itemView.findViewById(R.id.txtOpcaoContato);
            itemView.setOnClickListener(this);


//novo

        }

        @Override
        public void onClick(View view) {
            if(itemClicado != null)
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

    public GetContatoResponse getItem(int position){
        return contatoModels.get(position);
    }

    public void updateList(List<GetContatoResponse> newList){
        contatoModels = new ArrayList<>();
        contatoModels.addAll(newList);
        notifyDataSetChanged();
    }

}


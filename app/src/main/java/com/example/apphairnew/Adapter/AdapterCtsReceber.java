package com.example.apphairnew.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.model.CtsReceberModel;
import com.example.apphairnew.response.GetCtsReceberResponse;
import com.example.apphairnew.ui.CtsReceberLista;
import com.example.apphairnew.web.ApiControler;

import java.util.ArrayList;
import java.util.List;

public class AdapterCtsReceber extends RecyclerView.Adapter<AdapterCtsReceber.ViewHolder> {
    itemClicadoListener itemClicado;
    List<GetCtsReceberResponse> ctsReceberModels;
    private LayoutInflater inflater;
    private Context context;
    private ApiService service = ApiControler.CreateController();

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

//asd

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final GetCtsReceberResponse ctsReceberModel = ctsReceberModels.get(position);
        holder.recebValor.setText(String.valueOf(ctsReceberModel.getRecebValor()));
        holder.recebVencimento.setText(String.valueOf(ctsReceberModel.getRecebVencimento()));

        holder.opcoesMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.opcoesMenu);
                popupMenu.inflate(R.menu.op_menu_cts_receber);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menu_item_apagar:
                                CtsReceberLista recarrega = new CtsReceberLista();
                                recarrega.ExcluirItem(ctsReceberModel.getIdCr());
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
        return ctsReceberModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView recebValor;
        TextView recebVencimento;
        TextView opcoesMenu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recebValor=itemView.findViewById(R.id.labelValorRecebLst);
            recebVencimento=itemView.findViewById(R.id.labelDataRecebLst);
            opcoesMenu = itemView.findViewById(R.id.txtOpcaoCtsReceber);
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

    public void updateList(List<GetCtsReceberResponse> newList){
        ctsReceberModels = new ArrayList<>();
        ctsReceberModels.addAll(newList);
        notifyDataSetChanged();
    }

}

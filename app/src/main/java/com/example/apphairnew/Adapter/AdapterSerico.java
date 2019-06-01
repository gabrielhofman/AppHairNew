package com.example.apphairnew.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.response.GetServicoResponse2;
import com.example.apphairnew.ui.ServicoLista;
import com.example.apphairnew.web.ApiControler;

import java.util.ArrayList;
import java.util.List;

public class AdapterSerico extends RecyclerView.Adapter<AdapterSerico.ViewHolder> {

    itemClicadoListener itemClicado;
    List<GetServicoResponse2> servicoModels;
    private LayoutInflater inflater;
    private Context context;
    private ApiService service = ApiControler.CreateController();




    public AdapterSerico(List<GetServicoResponse2>servicoModel, Context context){
        this.servicoModels = servicoModel;
        this.inflater = LayoutInflater.from(context);
        this.context =context;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // View view = inflater.inflate(R.layout.row_lista_produto, parent, false);
        //        ViewHolder viewHolder = new ViewHolder(view);
        //        return viewHolder;

        View view = inflater.inflate(R.layout.row_lista_servico, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);//
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

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


        final GetServicoResponse2 servicoModel = servicoModels.get(   position);
        holder.nomeServico.setText(String.valueOf(servicoModel.getNomeServico()));
        holder.opcoesMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.opcoesMenu);
                popupMenu.inflate(R.menu.op_menu_servico);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menu_item_apagar_servico:
                                ServicoLista recarrega = new ServicoLista();
                               // recarrega.ExcluirItem(servicoModel.getIdServico());
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
        return servicoModels.size();

    }





    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView nomeServico;
        TextView opcoesMenu;


        public ViewHolder(View itemView) {
            super(itemView);

            nomeServico =itemView.findViewById(R.id.nome_servico);
            opcoesMenu = itemView.findViewById(R.id.txtOpcaoServico);

            itemView.setOnClickListener(this);

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

    public GetServicoResponse2 getItem(int position ) {
        return servicoModels.get(position);
    }

    public void updateList(List<GetServicoResponse2> newList){
        servicoModels = new ArrayList<>();
        servicoModels.addAll(newList);
        notifyDataSetChanged();
    }
}

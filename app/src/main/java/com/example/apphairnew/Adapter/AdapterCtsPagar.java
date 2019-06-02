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
import com.example.apphairnew.response.GetCtsPagarResponse;
import com.example.apphairnew.ui.CtsPagarLista;
import com.example.apphairnew.web.ApiControler;

import java.text.NumberFormat;
import java.util.List;

public class AdapterCtsPagar extends RecyclerView.Adapter<AdapterCtsPagar.ViewHolder> {
    itemClicadoListener itemClicado;
    List<GetCtsPagarResponse> ctsPagarModels;
    private LayoutInflater inflater;
    private Context context;
    private ApiService service = ApiControler.CreateController();

    public AdapterCtsPagar(List<GetCtsPagarResponse> ctsPagarModel, Context context) {
        this.ctsPagarModels = ctsPagarModel;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }
    //
    @NonNull
    @Override ///
    public AdapterCtsPagar.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_lista_cts_pagar, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterCtsPagar.ViewHolder holder, final int position) {

        final GetCtsPagarResponse ctsPagarModel = ctsPagarModels.get(position);
        NumberFormat formatado = NumberFormat.getInstance();
        formatado.setMinimumFractionDigits(2);
        holder.pagarValor.setText(String.valueOf(formatado.format(ctsPagarModel.getPagarValor())));
        holder.pagarVencimento.setText(String.valueOf(ctsPagarModel.getPagarVencimento()));



    }



    @Override
    public int getItemCount() {
        return ctsPagarModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView pagarValor;
        TextView pagarVencimento;
        TextView opcoesMenu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            pagarValor = itemView.findViewById(R.id.labelValorPagarLst);
            pagarVencimento = itemView.findViewById(R.id.labelDataPagarLst);

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

    public GetCtsPagarResponse getItem(int position ) {
        return ctsPagarModels.get(position);
    }
}//maoe

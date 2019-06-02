package com.example.apphairnew.Adapter;

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

import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.response.GetGaleriaProfResponse;
import com.example.apphairnew.response.GetProfResponse;
import com.example.apphairnew.ui.GaleriaProf;
import com.example.apphairnew.web.ApiControler;

import java.util.List;

public class AdapterGaleria extends RecyclerView.Adapter<AdapterGaleria.ViewHolder> {
    List<GetGaleriaProfResponse> galeriaModels;
    private LayoutInflater inflater;
    private Context context;
    private ApiService service = ApiControler.CreateController();

    public AdapterGaleria(List<GetGaleriaProfResponse> galeriaModel, Context context) {
        this.galeriaModels = galeriaModel;
        this.inflater = LayoutInflater.from(context);
        this.context = context;

    }



    @NonNull
    @Override
    public AdapterGaleria.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_lista_fotos_prof, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterGaleria.ViewHolder holder, final int position) {
    final GetGaleriaProfResponse galeriaModel = galeriaModels.get(position);

        byte[] decodedString = Base64.decode(galeriaModel.getBmFotoGaleria(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

    holder.bmFotoProfissional.setImageBitmap(decodedByte);
    }

    @Override
    public int getItemCount() {
        return galeriaModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView bmFotoProfissional;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bmFotoProfissional = itemView.findViewById(R.id.fotoProfGaleria);
        }
    }
}

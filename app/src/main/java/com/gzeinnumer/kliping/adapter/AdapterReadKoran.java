package com.gzeinnumer.kliping.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gzeinnumer.kliping.R;
import com.gzeinnumer.kliping.modelpojo.ResultItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterReadKoran extends RecyclerView.Adapter<AdapterReadKoran.MyHolder> {
    private Context context;
    private ArrayList<ResultItem> list;

    public AdapterReadKoran(Context context, ArrayList<ResultItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_koran, viewGroup, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.itemNama.setText(list.get(i).getKoranNama());
        myHolder.itemTanggal.setText(list.get(i).getKoranTanggal());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.itemNama)
        TextView itemNama;
        @BindView(R.id.itemTanggal)
        TextView itemTanggal;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}


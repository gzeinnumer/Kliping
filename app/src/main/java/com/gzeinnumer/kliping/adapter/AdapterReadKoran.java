package com.gzeinnumer.kliping.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gzeinnumer.kliping.R;
import com.gzeinnumer.kliping.activity.ReadKoran;
import com.gzeinnumer.kliping.modelpojo.ResultItemKoran;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterReadKoran extends RecyclerView.Adapter<AdapterReadKoran.MyHolder> {
    private Context context;
    private ArrayList<ResultItemKoran> list;

    public AdapterReadKoran(Context context, ArrayList<ResultItemKoran> list) {
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
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        myHolder.itemNama.setText(list.get(i).getKoranNama());
        myHolder.itemTanggal.setText(list.get(i).getKoranTanggal());
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReadKoran.class);
                intent.putExtra(ReadKoran.ID_KORAN, list.get(i).getKoranId());
                context.startActivity(intent);
            }
        });
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


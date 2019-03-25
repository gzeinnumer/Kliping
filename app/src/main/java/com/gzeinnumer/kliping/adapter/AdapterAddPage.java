package com.gzeinnumer.kliping.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gzeinnumer.kliping.R;
import com.gzeinnumer.kliping.data.DataKoran;
import com.gzeinnumer.kliping.model.ItemAddPage;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterAddPage extends RecyclerView.Adapter<AdapterAddPage.MyHolder> {
    private Context context;
    private ArrayList<ItemAddPage> list;
    private int hal;
    View view;
    private onItemClick click;
    private Uri filePath;
    private String path;
    private Bitmap bitmap = null;
    private ImageView image;
    private int currentPosition;

    public AdapterAddPage(Context context, ArrayList<ItemAddPage> list, int hal) {
        this.context = context;
        this.list = list;
        this.hal = hal;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(context).inflate(R.layout.item_add_page, viewGroup, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, final int i) {
        myHolder.pageAddPage.setText("Halaman "+String.valueOf(list.get(i).getPageNo()));

        myHolder.imageAddPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onItemClick(i);
                image=myHolder.imageAddPage;
                currentPosition=i;
            }

        });
    }

    private void saveToArray(int i) {
        DataKoran.listKoran.add(new ItemAddPage(i,this.path));
        Toast.makeText(context, DataKoran.listKoran.get(i).getPageNo() +"     "+DataKoran.listKoran.get(i).getPathFoto(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setBitmapToImage() {
        image.setImageBitmap(bitmap);

        saveToArray(currentPosition);
    }


    public class MyHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.page_add_page)
        TextView pageAddPage;
        @BindView(R.id.image_add_page)
        ImageView imageAddPage;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, view);
        }
    }


    public interface onItemClick {
        void onItemClick(int position);
    }


    public void setOnClickListener2(onItemClick onClick) {
        click = onClick;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

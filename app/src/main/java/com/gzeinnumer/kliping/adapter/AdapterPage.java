package com.gzeinnumer.kliping.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gzeinnumer.kliping.R;
import com.gzeinnumer.kliping.modelpojo.ResultItemPage;
import com.squareup.picasso.PicassoProvider;

import java.util.List;

public class AdapterPage extends PagerAdapter {
    private Context context;
    List<ResultItemPage> models;
    LayoutInflater layoutInflater;

    public AdapterPage(Context context, List<ResultItemPage> models) {
        this.context = context;
        this.models = models;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_read_page,container,false);

        ImageView imagePage;
        TextView page;

        imagePage = view.findViewById(R.id.image_page);
        page = view.findViewById(R.id.no_page);

        Glide.with(context)
                .load("http://192.168.95.245/kliping/uploads/"+models.get(position).getNamaPage())
                .into(imagePage);
        page.setText(models.get(position).getHalPage());

        container.addView(view,0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


}

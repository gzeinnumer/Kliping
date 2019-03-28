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
import com.gzeinnumer.kliping.model.ItemHome;
import com.gzeinnumer.kliping.modelpojo.ResultItemPage;

import java.util.List;

public class AdapterHomePage extends PagerAdapter {
    private Context context;
    List<ItemHome> models;
    LayoutInflater layoutInflater;
    View view;

    public AdapterHomePage(Context context, List<ItemHome> models) {
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
        view = layoutInflater.inflate(R.layout.item_home,container,false);

        ImageView imagePage;
        TextView page;

        imagePage = view.findViewById(R.id.image_home);
        page = view.findViewById(R.id.nama_home);

        imagePage.setImageResource(models.get(position).getImage());
        page.setText(models.get(position).getNama());

        container.addView(view,0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


}

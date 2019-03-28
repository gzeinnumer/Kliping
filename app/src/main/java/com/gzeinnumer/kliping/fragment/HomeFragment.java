package com.gzeinnumer.kliping.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gzeinnumer.kliping.R;
import com.gzeinnumer.kliping.adapter.AdapterHomePage;
import com.gzeinnumer.kliping.adapter.AdapterPage;
import com.gzeinnumer.kliping.model.ItemHome;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    List<ItemHome> list = new ArrayList<>();
    ViewPager viewPager;
    View view;
    Context context;
    AdapterHomePage adapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);
        context = view.getContext();

        initData();

        return view;
    }

    private void initData() {
        list.add(new ItemHome(R.drawable.img_1, "take line 1"));
        list.add(new ItemHome(R.drawable.img_2, "take line 2"));
        list.add(new ItemHome(R.drawable.img_3, "take line 3"));
        list.add(new ItemHome(R.drawable.img_4, "take line 4"));
        list.add(new ItemHome(R.drawable.img_5, "take line 5"));
        list.add(new ItemHome(R.drawable.img_6, "take line 6"));
//        list.add(new ItemHome(R.drawable.img_7, "take line 7"));
        list.add(new ItemHome(R.drawable.img_8, "take line 8"));
        initDataToPager();
    }

    private void initDataToPager() {
        adapter = new AdapterHomePage(context, list);
        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(0, 0, 0, 0);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

}

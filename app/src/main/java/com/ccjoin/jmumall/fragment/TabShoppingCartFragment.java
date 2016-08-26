package com.ccjoin.jmumall.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ccjoin.jmumall.R;

/**
 * Shopping cart tab
 */
public class TabShoppingCartFragment extends Fragment {


    public TabShoppingCartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_shopping_cart, container, false);
    }

}

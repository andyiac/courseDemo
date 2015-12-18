package com.andyiac.arch.ui.base;

import android.support.v4.app.Fragment;


public class BaseFragment extends Fragment {


    public void setStatusColor(int color) {
        ((BaseActivity) getActivity()).setStatusColor(color);
    }
}

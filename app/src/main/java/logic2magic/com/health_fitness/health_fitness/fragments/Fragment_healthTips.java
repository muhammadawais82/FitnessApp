package logic2magic.com.health_fitness.health_fitness.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import logic2magic.com.health_fitness.R;

/**
 * Created by qasim on 19-Feb-16.
 */
public class Fragment_healthTips extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_healthtipslayout,container,false);
        return view;
    }
}

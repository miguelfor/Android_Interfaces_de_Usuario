package com.example.miguelforero.interfacesdeusuario;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Miguel Forero on 23/05/2017.
 */

public class Inicio extends Fragment {


    public Inicio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_content, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ImageView imageView = (ImageView) view.findViewById(R.id.img);
        imageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_home));
        TextView textView = (TextView) view.findViewById(R.id.txt);
        textView.setText(R.string.inicio);
    }
}

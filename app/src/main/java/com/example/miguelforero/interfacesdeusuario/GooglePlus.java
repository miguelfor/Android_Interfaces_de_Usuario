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

public class GooglePlus extends Fragment {
    public GooglePlus() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imageView = (ImageView) view.findViewById(R.id.img);
        TextView textView = (TextView) view.findViewById(R.id.txt);

        switch (getArguments().getInt("position")){
            case 0:{
                imageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_apps));
                textView.setText(R.string.colecciones);
                break;
            }
            case 1:{
                imageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_communities));
                textView.setText(R.string.comunidades);
                break;
            }
            case 2:{
                imageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_bell));
                textView.setText(R.string.notificaciones);
                break;
            }
        }
    }

    public static GooglePlus getInstance(int position){
        GooglePlus fragment = new GooglePlus();

        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);

        return fragment;
    }
}


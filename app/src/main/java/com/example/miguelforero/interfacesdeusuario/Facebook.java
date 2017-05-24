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

public class Facebook extends Fragment {

    public Facebook() {
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
                imageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_news));
                textView.setText(R.string.noticias);
                break;
            }
            case 1:{
                imageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_friends));
                textView.setText(R.string.solicitudes);
                break;
            }
            case 2:{
                imageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_notifications));
                textView.setText(R.string.notificaciones);
                break;
            }
        }
    }

    public static Facebook getInstance(int position){
        Facebook fragment = new Facebook();

        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);

        return fragment;
    }
}

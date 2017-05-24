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

public class Instagram extends Fragment {


    public Instagram() {
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
        TextView textView = (TextView) view.findViewById(R.id.txt);

        switch (getArguments().getInt("position")){
            case 0:{
                imageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_search));
                textView.setText(R.string.explorar);
                break;
            }
            case 1:{
                imageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_camera));
                textView.setText(R.string.publicar);
                break;
            }
            case 2:{
                imageView.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_heart));
                textView.setText(R.string.notificaciones);
                break;
            }
        }
    }

    public static Instagram getInstance(int position){
        Instagram fragment = new Instagram();

        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);

        return fragment;
    }
}


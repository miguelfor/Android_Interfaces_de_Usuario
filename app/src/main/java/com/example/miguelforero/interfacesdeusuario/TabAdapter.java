package com.example.miguelforero.interfacesdeusuario;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Miguel Forero on 23/05/2017.
 */

public class TabAdapter extends FragmentStatePagerAdapter {

    private String titles[];
    private int fragmento;
    public TabAdapter(FragmentManager fm, int fragmento) {
        super(fm);
        this.fragmento = fragmento;
        switch (fragmento){
            case 0:{
                this.titles = new String[] {"Inicio"};
                break;
            }
            case 1:{
                this.titles = new String[] {"Noticias", "Solicitudes", "Notificaciones"};
                break;
            }
            case 2:{
                this.titles = new String[] {"Explorar", "Publicar", "Notificaciones"};
                break;
            }
            case 3:{
                this.titles = new String[] {"Notificaciones", "Mensajes", "Buscar"};
                break;
            }
            case 4:{
                this.titles = new String[] {"Colecciones", "Comunidades", "Notificaciones"};
                break;
            }
        }
    }

    @Override
    public Fragment getItem(int position) {
        switch(this.fragmento){
            case 0: {
                return new Inicio();
            }
            case 1: {
                return Facebook.getInstance(position);
            }
            case 2: {
                return Instagram.getInstance(position);
            }
            case 3: {
                return Twitter.getInstance(position);
            }
            case 4: {
                return  GooglePlus.getInstance(position);
            }
            default:{
                return new Inicio();
            }
        }
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}

package com.example.miguelforero.interfacesdeusuario;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    Fragment fragment;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        fragment = new Inicio();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        viewPager = (ViewPager) findViewById(R.id.facebook_view_pager);
        tabLayout = (TabLayout) findViewById(R.id.facebook_tabs);
        if (tabLayout != null) {
            setupTabs(0, R.drawable.ic_home, 0, 0);
            tabLayout.setVisibility(View.GONE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        String subtitle = "";
        int colorPrimary = R.color.colorPrimary, colorPrimaryDark = R.color.colorPrimaryDark;
        switch (item.getItemId()) {
            case R.id.nav_home: {
                subtitle = getResources().getString(R.string.inicio);
                setupTabs(0, R.drawable.ic_home, 0, 0);
                tabLayout.setVisibility(View.GONE);
                break;
            }
            case R.id.nav_facebook: {
                subtitle = getResources().getString(R.string.facebook);
                setupTabs(1, R.drawable.ic_news, R.drawable.ic_friends, R.drawable.ic_notifications);
                colorPrimary = R.color.colorPrimaryFacebook;
                colorPrimaryDark = R.color.colorPrimaryDarkFacebook;
                break;
            }
            case R.id.nav_instagram: {
                subtitle = getResources().getString(R.string.instagram);
                setupTabs(2, R.drawable.ic_search, R.drawable.ic_camera, R.drawable.ic_heart);
                colorPrimary = R.color.colorPrimaryInstagram;
                colorPrimaryDark = R.color.colorPrimaryDarkInstagram;
                break;
            }
            case R.id.nav_twitter: {
                subtitle = getResources().getString(R.string.twitter);
                setupTabs(3, R.drawable.ic_bell, R.drawable.ic_message, R.drawable.ic_search);
                colorPrimary = R.color.colorPrimaryTwitter;
                colorPrimaryDark = R.color.colorPrimaryDarkTwitter;
                break;
            }
            case R.id.nav_google: {
                subtitle = getResources().getString(R.string.google_plus);
                setupTabs(4, R.drawable.ic_apps, R.drawable.ic_communities, R.drawable.ic_bell);
                colorPrimary = R.color.colorPrimaryGooglePlus;
                colorPrimaryDark = R.color.colorPrimaryDarkGooglePlus;
                break;
            }
        }

        getWindow().setStatusBarColor(ContextCompat.getColor(getBaseContext(), colorPrimaryDark));
        toolbar.setBackgroundColor(ContextCompat.getColor(getBaseContext(), colorPrimary));
        tabLayout.setBackgroundColor(ContextCompat.getColor(this, colorPrimary));
        toolbar.setSubtitle(subtitle);

        View header = findViewById(R.id.nav_header);
        if (header != null) {
            header.setBackgroundColor(ContextCompat.getColor(getBaseContext(), colorPrimary));
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        SubMenu sm = menu.addSubMenu(R.string.versi_n_web).setIcon(R.drawable.ic_web);
        sm.add(0, 1, 1, R.string.facebook).setIcon(R.drawable.ic_facebook);
        sm.add(0, 2, 2, R.string.instagram).setIcon(R.drawable.ic_instagram);
        sm.add(0, 3, 3, R.string.twitter).setIcon(R.drawable.ic_twitter);
        sm.add(0, 4, 4, R.string.google_plus).setIcon(R.drawable.ic_google);

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.opcion_settings: {
                Intent intent = new Intent(this, Configuracion.class);
                startActivity(intent);
                break;
            }
            case R.id.opcion_share: {
                newShareDialog().show();
                break;
            }
            case 1: {
                newCustomTab(1, R.color.colorPrimaryFacebook);
                break;
            }
            case 2: {
                newCustomTab(2, R.color.colorPrimaryInstagram);
                break;
            }
            case 3: {
                newCustomTab(3, R.color.colorPrimaryTwitter);
                break;
            }
            case 4: {
                newCustomTab(4, R.color.colorPrimaryGooglePlus);
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public AlertDialog newShareDialog(){
        final String[] items = {"Facebook", "Twitter", "Instagram", "Google Plus", "Whatsapp", "Messenger", "SMS"};
        final boolean[] checkedItems = {false, false, false, false, false, false, false};

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogStyle);
        builder.setTitle("Selecciona dónde quieres compartir esta aplicación:");

        builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

            }
        });

        builder.setPositiveButton("Compartir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String seleccion = "Compartiste esta aplicación a través de: ";
                for (int i = 0; i < checkedItems.length; i++){
                    if(checkedItems[i]){
                        seleccion += "\n" + items[i];
                    }
                }
                dialog.cancel();
                newConfirmationDialog(seleccion).show();
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        return builder.create();
    }

    public AlertDialog newConfirmationDialog(final String seleccion){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogStyle);
        builder.setTitle("Confirmación");
        builder.setMessage("¿Compartir esta aplicación a través de los medios seleccionados?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Toast.makeText(getApplicationContext(), seleccion, Toast.LENGTH_LONG).show();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        return builder.create();
    }

    public void newCustomTab(int option, int color) {

        String url = "https://";
        switch (option) {
            case 1: {
                url += "facebook.com";
                break;
            }
            case 2: {
                url += "instagram.com";
                break;
            }
            case 3: {
                url += "twitter.com";
                break;
            }
            case 4: {
                url += "plus.google.com";
                break;
            }
        }

        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(ContextCompat.getColor(this, color));
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setupTabs(int fragmento, int icon1, int icon2, int icon3) {
        TabLayout.Tab tab;
        Drawable icon = null;
        viewPager.setAdapter(new TabAdapter(getSupportFragmentManager(), fragmento));
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            tab = tabLayout.getTabAt(i);
            switch (i) {
                case 0: {
                    icon = ContextCompat.getDrawable(this, icon1);
                    break;
                }
                case 1: {
                    icon = ContextCompat.getDrawable(this, icon2);
                    break;
                }
                case 2: {
                    icon = ContextCompat.getDrawable(this, icon3);
                    break;
                }
            }
            if (tab != null) {
                icon.setTint(ContextCompat.getColor(this, android.R.color.white));
                tab.setIcon(icon);
                tab.setText("");
            }
        }
        tabLayout.setVisibility(View.VISIBLE);
    }
}

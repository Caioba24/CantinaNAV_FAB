package com.cantinatoshio.app;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.cantinatoshio.app.api.Api;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity
{

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    PedidosFragment pedidosFragment = new PedidosFragment();
    PerfilFragment perfilFragment = new PerfilFragment();
    FloatingActionButton btnCart;
    //Drawer Layout
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCart = findViewById(R.id.btnCart);

        //ID NavDrawer
        toolbar = findViewById(R.id.idToolBar);
        drawerLayout = findViewById(R.id.idNavDrawer);
        navigationView = findViewById(R.id.idNavigationView);



        //clique para abrir o NavDrawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //parte do bottom navigation
        bottomNavigationView = findViewById(R.id.btnNav);
        callFragment(homeFragment); //iniciar na home
        //toolbar = findViewById(R.id.mainToolbar);
       // setSupportActionBar(toolbar);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.ic_Home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer, homeFragment).commit();
                        return true;
                    case R.id.ic_pedidos:
                        getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer, pedidosFragment).commit();
                        return true;
                    //case R.id.ic_perfil:
                       // getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer, perfilFragment).commit();
                        //return true;
                }

                return false;
            }
        });

        btnCart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), CarrinhoActivity.class);
                startActivity(intent);
            }
        });

    }

    public void callFragment(Fragment fragment)
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer, fragment).commit();
    }
}
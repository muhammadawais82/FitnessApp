package logic2magic.com.health_fitness;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import logic2magic.com.health_fitness.health_fitness.fragments.Fragment_healthTips;
import logic2magic.com.health_fitness.health_fitness.fragments.Fragment_usefulHeatlhLinks;
import logic2magic.com.health_fitness.health_fitness.fragments.Fragment_Main;
import logic2magic.com.health_fitness.health_fitness.fragments.Fragment_reduceWeight;

public class MainPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.container_Frame, new Fragment_Main()).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            //Toast.makeText(MainPage.this, "  Supervised by Miss Wajeeha Azmat\nDeveloped by Muhammad Qasim Riaz", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentManager fm = getFragmentManager();
        int id = item.getItemId();

        if (id == R.id.nav_mainPage)
        {
            fm.beginTransaction().replace(R.id.container_Frame, new Fragment_Main()).commit();
            // Handle the camera action
        }
        else if (id == R.id.nav_reduceWeight)
        {
            fm.beginTransaction().replace(R.id.container_Frame, new Fragment_reduceWeight()).commit();
        }
        else if (id == R.id.nav_healthTips)
        {
            fm.beginTransaction().replace(R.id.container_Frame, new Fragment_healthTips()).commit();
        }
        else if (id == R.id.nav_healthSites)
        {
            fm.beginTransaction().replace(R.id.container_Frame, new Fragment_usefulHeatlhLinks()).commit();
        }
        else if (id == R.id.nav_stemp_counter)
        {
            startActivity(new Intent(MainPage.this,STEP_COUNTER.class));
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

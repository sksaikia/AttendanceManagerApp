package com.example.attendancemanager.ui.main;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;

import com.example.attendancemanager.R;
import com.example.attendancemanager.ui.main.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector, NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;

    private static final String TAG = "MainActivity2";
    
   @Inject
   DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

   
   @Inject
   MainViewModel viewModel;

   FragmentManager fragmentManager;

   @Inject
   HomeFragment homeFragment;

   
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AndroidInjection.inject(this);




        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //   AndroidInjection.inject(this);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        fragmentManager = getSupportFragmentManager();

        initFrag(homeFragment);


//        Log.d(TAG, "onCreate: " +  viewModel.getAuthToken());

    }

    private void initFrag(Fragment fragment) {

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.frame_layout, fragment);
        ft.commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

       /* if (id == R.id.nav_home)
        {
            // Handle the camera action
        } else if (id == R.id.nav_anuwar_shop || id==R.id.nav_blood_group || id==R.id.nav_any_item_order || id==R.id.nav_fix_your_car || id==R.id.nav_saloon_booking )
        {

            Intent i=new Intent(MainActivity.this, FunctionsActivity.class);
            i.putExtra(Constants.INTENT_MAIN_TO_FUNCTION,id);
            startActivity(i);

        }*/
//        if (id == R.id.nav_my_orders)
//        {
//            checkoutFragment=new CheckoutFragment();
//            initFrag(checkoutFragment);
//
//        } else if (id == R.id.nav_my_cart)
//        {
//            cartFragment=new CartFragment();
//            initFrag(cartFragment);
//
//        } else if (id == R.id.nav_my_account)
//        {
//            accountFragment=new AccountFragment();
//            initFrag(accountFragment);
//
//        } else if (id == R.id.nav_my_booking)
//        {
//            bookingsFragment=new BookingsFragment();
//            initFrag(bookingsFragment);
//
//        }
//        else if(id == R.id.nav_my_notification)
//        {
//            notificationsFragment=new NotificationsFragment();
//            initFrag(notificationsFragment);
//        }
//        else if(id==R.id.nav_contact_us)
//        {
//
//
//        } else if(id==R.id.nav_privacy_policy)
//        {
//
//        } else if(id==R.id.nav_terms_conditions)
//        {
//
//        }
//        else if(id==R.id.nav_log_out)
//        {
//
//        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }



    private void initializeFragments(Fragment frag) {
        String backStateName = frag.getClass().toString();
        //Log.d(TAG, "onBtnOtpLoginClicked: " + backStateName);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        transaction.replace(R.id.frame_layout, frag);
        transaction.addToBackStack(backStateName);
        transaction.commit();
    }


}

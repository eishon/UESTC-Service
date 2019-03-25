package com.eishon.uestc_service;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public int PIC_WIDTH;

    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    //for double backpressed exit
    private boolean doubleBackToExitPressedOnce;
    private Handler mHandler=new Handler();

    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    };

    //floating action button
    FloatingActionButton fab_menu,fab_facebook,fab_email,fab_exit;
    Boolean isOpen=false;

    //animation
    Animation anim_fab_open,anim_fab_close,anim_rotate_clockwise,anim_rotate_anticlockwise;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,new MenuAboutUESTCFragment()).commit();

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (isOpen)
                {
                    fab_menu_close();
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!isOpen)
                {
                    fab_menu_open();
                }
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //drawer.openDrawer(GravityCompat.START);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fab_menu= (FloatingActionButton) findViewById(R.id.fab_menu);
        fab_facebook= (FloatingActionButton) findViewById(R.id.fab_facebook);
        fab_email= (FloatingActionButton) findViewById(R.id.fab_email);
        fab_exit= (FloatingActionButton) findViewById(R.id.fab_exit);

        anim_fab_open= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        anim_fab_close= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        anim_rotate_clockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
        anim_rotate_anticlockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlockwise);

        fab_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpen)
                {
                    fab_menu_close();
                }
                else
                {
                    fab_menu_open();
                }
            }
        });

        fab_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebook = openFacebookIntent(HomeActivity.this);
                startActivity(facebook);
            }
        });

        fab_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email = openGmailIntent(HomeActivity.this);
                startActivity(email);
            }
        });

        fab_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            mHandler.postDelayed(mRunnable, 2000);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            FragmentTransaction softFragmentTransaction = mFragmentManager.beginTransaction();
            softFragmentTransaction.replace(R.id.containerView,new MenuAboutSoftFragment()).commit();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_food_service) {
            FragmentTransaction foodFragmentTransaction = mFragmentManager.beginTransaction();
            foodFragmentTransaction.replace(R.id.containerView,new MenuFoodServiceTabFragment()).commit();
            fab_menu_close();
        } else if (id == R.id.nav_transport_service) {
            FragmentTransaction transportFragmentTransaction = mFragmentManager.beginTransaction();
            transportFragmentTransaction.replace(R.id.containerView,new MenuTransportServiceTabFragment()).commit();
            fab_menu_close();
        } else if (id == R.id.nav_visa_psb) {
            FragmentTransaction visaFragmentTransaction = mFragmentManager.beginTransaction();
            visaFragmentTransaction.replace(R.id.containerView,new MenuVisaPSBTabFragment()).commit();
            fab_menu_close();
        } else if (id == R.id.nav_health_service) {
            FragmentTransaction healthFragmentTransaction = mFragmentManager.beginTransaction();
            healthFragmentTransaction.replace(R.id.containerView,new MenuHealthServiceTabFragment()).commit();
            fab_menu_close();
        } else if (id == R.id.nav_education) {
            FragmentTransaction educationFragmentTransaction = mFragmentManager.beginTransaction();
            educationFragmentTransaction.replace(R.id.containerView,new MenuEducationTabFragment()).commit();
            fab_menu_close();
        } else if (id == R.id.nav_holidays) {
            FragmentTransaction holidaysFragmentTransaction = mFragmentManager.beginTransaction();
            holidaysFragmentTransaction.replace(R.id.containerView,new MenuHolidaysTabFragment()).commit();
            fab_menu_close();
        } else if (id == R.id.nav_job_info) {
            FragmentTransaction jobFragmentTransaction = mFragmentManager.beginTransaction();
            jobFragmentTransaction.replace(R.id.containerView,new MenuJobInfoTabFragment()).commit();
            fab_menu_close();
        } else if (id == R.id.nav_group_activities) {
            FragmentTransaction groupActivitiesFragmentTransaction = mFragmentManager.beginTransaction();
            groupActivitiesFragmentTransaction.replace(R.id.containerView,new MenuGroupActivitiesTabFragment()).commit();
            fab_menu_close();
        } else if (id == R.id.nav_banks_finance) {
            FragmentTransaction bankActivitiesFragmentTransaction = mFragmentManager.beginTransaction();
            bankActivitiesFragmentTransaction.replace(R.id.containerView,new MenuBanksFinanceTabFragment()).commit();
            fab_menu_close();
        } else if (id == R.id.nav_tours_travels) {
            FragmentTransaction tourActivitiesFragmentTransaction = mFragmentManager.beginTransaction();
            tourActivitiesFragmentTransaction.replace(R.id.containerView,new MenuToursTravelsTabFragment()).commit();
            fab_menu_close();
        } else if (id == R.id.nav_language) {
            FragmentTransaction languageActivitiesFragmentTransaction = mFragmentManager.beginTransaction();
            languageActivitiesFragmentTransaction.replace(R.id.containerView,new MenuLanguageTabFragment()).commit();
            fab_menu_close();
        } else if (id == R.id.nav_shopping) {
            FragmentTransaction shoppingActivitiesFragmentTransaction = mFragmentManager.beginTransaction();
            shoppingActivitiesFragmentTransaction.replace(R.id.containerView,new MenuShoppingTabFragment()).commit();
            fab_menu_close();
        } else if (id == R.id.nav_about_uestc) {
            FragmentTransaction uestcFragmentTransaction = mFragmentManager.beginTransaction();
            uestcFragmentTransaction.replace(R.id.containerView,new MenuAboutUESTCFragment()).commit();
            fab_menu_close();
        }
        else if (id == R.id.nav_about_soft) {
            FragmentTransaction softFragmentTransaction = mFragmentManager.beginTransaction();
            softFragmentTransaction.replace(R.id.containerView,new MenuAboutSoftFragment()).commit();
            fab_menu_close();
        }

        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null)
        {
            mHandler.removeCallbacks(mRunnable);
        }
    }

    private void fab_menu_open(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.openDrawer(GravityCompat.START);

        fab_facebook.startAnimation(anim_fab_open);
        fab_email.startAnimation(anim_fab_open);
        fab_exit.startAnimation(anim_fab_open);
        fab_menu.startAnimation(anim_rotate_clockwise);

        fab_facebook.setClickable(true);
        fab_email.setClickable(true);
        fab_exit.setClickable(true);

        isOpen=true;
    }

    private void fab_menu_close(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        fab_facebook.startAnimation(anim_fab_close);
        fab_email.startAnimation(anim_fab_close);
        fab_exit.startAnimation(anim_fab_close);
        fab_menu.startAnimation(anim_rotate_anticlockwise);

        fab_facebook.setClickable(false);
        fab_email.setClickable(false);
        fab_exit.setClickable(false);

        isOpen=false;
    }

    public static Intent openFacebookIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0); //Checks if FB is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/UESTCIntlstd")); //Trys to make intent with FB's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/UESTCIntlstd")); //catches and opens a url to the desired page
        }
    }

    public static Intent openGmailIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.google.android.gm", 0); //Checks if FB is even installed.
            return new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "admission@uestc.edu.cn", null)); //Trys to make intent with FB's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "admission@uestc.edu.cn", null)); //catches and opens a url to the desired page
        }
    }

    private int getScale(){
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        int width = display.getWidth();
        Double val = new Double(width)/new Double(PIC_WIDTH);
        val = val*100;
        return val.intValue();
    }

}

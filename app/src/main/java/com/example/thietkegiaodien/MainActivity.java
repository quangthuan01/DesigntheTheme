package com.example.thietkegiaodien;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.thietkegiaodien.fragment.EmailFragment;
import com.example.thietkegiaodien.fragment.HomeFragment;
import com.example.thietkegiaodien.fragment.ListFragment;
import com.example.thietkegiaodien.fragment.MessagesFragment;
import com.example.thietkegiaodien.fragment.MusicFragment;
import com.example.thietkegiaodien.login.Login;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    Fragment fragment;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Mặc đinh mới vào là Fragment Email
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,
                    new EmailFragment()).commit();
        }

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.home:
                        fragment = new HomeFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.search:
                        fragment = new ListFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.code:
                        fragment = new MusicFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.messager:
                        fragment = new MessagesFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.email:
                        fragment = new EmailFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.support:
                        break;
                    case R.id.contact:
                        break;
                    case R.id.logout:
                        Dialog dialog = new Dialog(MainActivity.this);
                        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                        dialog.setContentView(R.layout.dialog_logout);
                        Button signout = dialog.findViewById(R.id.sign_out);
                        Button exitapp = dialog.findViewById(R.id.exit_app);
                        signout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                FirebaseAuth.getInstance().signOut();
                                dialog.dismiss();
                                startActivity(new Intent(getApplicationContext(), Login.class));
                                finish();
                            }
                        });
                        exitapp.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent out= new Intent(Intent.ACTION_MAIN);
                                out.addCategory(Intent.CATEGORY_HOME);
                                startActivity(out);
                                finish();
                            }
                        });
                        dialog.show();
                        break;
                    default:
                        return true;

                }
                return true;
            }
        });


    }

    private void loadFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentTransaction.addToBackStack(null);
    }

}
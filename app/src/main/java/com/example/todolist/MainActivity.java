package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todolist.adapter.ViewpagerAdapter;
import com.example.todolist.database.AppDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private ViewPager viewPager;
    private FloatingActionButton fab;
    private TextView tvusername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView=findViewById(R.id.bottom_navigation);
        viewPager =findViewById(R.id.viewpager);
        fab=findViewById(R.id.fab);
        Intent intent =getIntent();

        String name =intent.getStringExtra("username");
        tvusername=findViewById(R.id.texviewUser);
        tvusername.setText(name);
//        Toast.makeText(this, "main", Toast.LENGTH_SHORT).show();
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this,AddActivity.class);
                intent1.putExtra("username1",name);
                startActivity(intent1);
            }
        });


        ViewpagerAdapter adapter = new ViewpagerAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0: navigationView.getMenu().findItem(R.id.menu_home).setChecked(true);
                        break;
                    case 1: navigationView.getMenu().findItem(R.id.menu_notdoneyet).setChecked(true);
                        break;
                    case 2: navigationView.getMenu().findItem(R.id.menu_done).setChecked(true);
                        break;
                    case 3: navigationView.getMenu().findItem(R.id.menu_profile).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_home:viewPager.setCurrentItem(0);
                        break;
                    case R.id.menu_notdoneyet:viewPager.setCurrentItem(1);
                        break;
                    case R.id.menu_done:viewPager.setCurrentItem(2);
                        break;
                    case R.id.menu_profile:viewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });

    }
}
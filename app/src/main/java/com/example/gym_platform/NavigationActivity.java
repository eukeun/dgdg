package com.example.gym_platform;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    List<Drawable> temp;
    Button button;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        final View nav_header_view = navigationView.getHeaderView(0);
        final TextView nav_header_point_text = (TextView) nav_header_view.findViewById(R.id.textView_Point);
        final TextView nav_header_id_text = (TextView) nav_header_view.findViewById(R.id.TextView_User);

        if (currentUser != null) {
            DocumentReference docRef = db.collection("User").document(currentUser.getUid());
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            nav_header_point_text.setText(document.get("userPoint").toString() + " 근");
                            nav_header_id_text.setText(document.get("userName").toString());

                        } else {
                        }
                    } else {
                    }
                }
            });
        }


        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        /////////////////////////////////////////////////////////////
        ////로그아웃 버튼////////////////////////////////////////////
        ImageView nav_header_logout_button = (ImageView) nav_header_view.findViewById(R.id.ImageButton_logout);
        nav_header_logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(NavigationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        /////////////////////////////////////////////////////////////
        ////포인트 버튼////////////////////////////////////////////
        ImageView point_button = (ImageView) nav_header_view.findViewById(R.id.ImageButton_point);
        point_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationActivity.this, PayActivity.class);
                startActivity(intent);
            }
        });
        /////////////////////////////////////////////////////////////
        ////예약내역 버튼////////////////////////////////////////////
        ImageView nav_header_reservationlist_button = (ImageView) nav_header_view.findViewById(R.id.imageButton_reservationlist);
        nav_header_reservationlist_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationActivity.this, ReservationListActivity.class);
                startActivity(intent);
            }
        });
        /////////////////////////////////////////////////////////////


        /////


        TextView tvg = (TextView) findViewById(R.id.gym);

        tvg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationActivity.this, GymActivity.class);
                startActivity(intent);
            }
        });
        TextView tvt = (TextView) findViewById(R.id.trainer);
        tvt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationActivity.this, TrainerActivity.class);
                startActivity(intent);
            }
        });

        temp = new ArrayList<>();
        temp.add(ContextCompat.getDrawable(this, R.drawable.default_dot));
        temp.add(ContextCompat.getDrawable(this, R.drawable.selected_dot));
        temp.add(ContextCompat.getDrawable(this, R.drawable.tab_selector));

        Adapter a = new Adapter(temp, this);

        ViewPager pager = findViewById(R.id.viewpager1);
        pager.setAdapter(a);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(pager, true);


        int dpValue = 16;
        float d = getResources().getDisplayMetrics().density;
        int margin = (int) (dpValue * d);

        ArrayList<Integer> listImage1 = new ArrayList<>();
        listImage1.add(R.drawable.ad1);
        listImage1.add(R.drawable.ad2);
        listImage1.add(R.drawable.ad3);

        ViewPager viewPager1 = findViewById(R.id.viewpager1);
        FragmentAdapter fragmentAdapter1 = new FragmentAdapter(getSupportFragmentManager());

        viewPager1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationActivity.this, GymActivity.class);
                startActivity(intent);
            }
        });
        viewPager1.setAdapter(fragmentAdapter1);


        for (int i = 0; i < listImage1.size(); i++) {
            ImageFragment1 imageFragment = new ImageFragment1();
            Bundle bundle = new Bundle();
            bundle.putInt("imgRes", listImage1.get(i));
            imageFragment.setArguments(bundle);
            fragmentAdapter1.addItem(imageFragment);
        }
        fragmentAdapter1.notifyDataSetChanged();


        ArrayList<Integer> listImage2 = new ArrayList<>();
        listImage2.add(R.drawable.gym1);
        listImage2.add(R.drawable.gym2);
        listImage2.add(R.drawable.gym3);

        ViewPager viewPager2 = findViewById(R.id.viewpager2);
        FragmentAdapter fragmentAdapter2 = new FragmentAdapter(getSupportFragmentManager());

        viewPager2.setAdapter(fragmentAdapter2);

        viewPager2.setClipToPadding(false);
        viewPager2.setPadding(margin, 0, margin, 0);
        viewPager2.setPageMargin(margin / 2);


        for (int i = 0; i < listImage2.size(); i++) {
            ImageFragment2 imageFragment = new ImageFragment2();
            Bundle bundle = new Bundle();
            bundle.putInt("imgRes", listImage2.get(i));
            //bundle.putInt("imgPosition", i);
            imageFragment.setArguments(bundle);
            fragmentAdapter2.addItem(imageFragment);
        }
        fragmentAdapter2.notifyDataSetChanged();


        ArrayList<Integer> listImage3 = new ArrayList<>();
        listImage3.add(R.drawable.trainer1);
        listImage3.add(R.drawable.trainer2);
        listImage3.add(R.drawable.trainer3);

        ViewPager viewPager3 = findViewById(R.id.viewpager3);
        FragmentAdapter fragmentAdapter3 = new FragmentAdapter(getSupportFragmentManager());

        viewPager3.setAdapter(fragmentAdapter3);

        viewPager3.setClipToPadding(false);
        viewPager3.setPadding(margin, 0, margin, 0);
        viewPager3.setPageMargin(margin / 2);


        for (int i = 0; i < listImage3.size(); i++) {
            ImageFragment3 imageFragment = new ImageFragment3();
            Bundle bundle = new Bundle();
            bundle.putInt("imgRes", listImage3.get(i));
            imageFragment.setArguments(bundle);
            fragmentAdapter3.addItem(imageFragment);
        }
        fragmentAdapter3.notifyDataSetChanged();

       /*
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        */
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onStart() {
        if (currentUser != null) {
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            final View nav_header_view = navigationView.getHeaderView(0);
            final TextView nav_header_point_text = (TextView) nav_header_view.findViewById(R.id.textView_Point);
            final TextView nav_header_id_text = (TextView) nav_header_view.findViewById(R.id.TextView_User);

            super.onStart();
            ////ReadPoint////
            DocumentReference docRef = db.collection("User").document(currentUser.getUid());
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            nav_header_point_text.setText(document.get("userPoint").toString() + " 근");
                            nav_header_id_text.setText(document.get("userName").toString()+"님");
                        } else {
                        }
                    } else {
                    }
                }
            });
        }
        /////////////////
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_notice) {
            // Handle the camera action
        } else if (id == R.id.nav_ad) {

        } else if (id == R.id.nav_cs) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class FragmentAdapter extends FragmentPagerAdapter {

        // ViewPager에 들어갈 Fragment들을 담을 리스트
        private ArrayList<Fragment> fragments = new ArrayList<>();

        // 필수 생성자
        FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        // List에 Fragment를 담을 함수
        void addItem(Fragment fragment) {
            fragments.add(fragment);
        }
    }


    class Adapter extends PagerAdapter {

        Context context;
        List<Drawable> obj;

        Adapter(List<Drawable> res, Context context) {
            obj = res;
            this.context = context;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {


            View view = null;
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.pager_adapter, container, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            imageView.setImageDrawable(obj.get(position));
            container.addView(view);

            return view;

        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return false;
        }


        public int getCount() {
            return obj.size();
        }

    }

}




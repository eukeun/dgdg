package com.example.gym_platform;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GymActivity extends AppCompatActivity {


    List<Drawable> temp;
    RecyclerView mRecyclerView1, mRecyclerView2;
    RecyclerView.LayoutManager mLayoutManager1, mLayoutManager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym);

        temp = new ArrayList<>();
        temp.add(ContextCompat.getDrawable(this, R.drawable.default_dot));
        temp.add(ContextCompat.getDrawable(this, R.drawable.default_dot));
        temp.add(ContextCompat.getDrawable(this, R.drawable.default_dot));

        Adapter a = new Adapter(temp, this);

        ViewPager pager = findViewById(R.id.viewpager1);
        pager.setAdapter(a);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(pager, true);


        ArrayList<Integer> listImage1 = new ArrayList<>();
        listImage1.add(R.drawable.gym11);
        listImage1.add(R.drawable.gym22);
        listImage1.add(R.drawable.gym33);
        listImage1.add(R.drawable.gym44);

        ViewPager viewPager1 = findViewById(R.id.viewpager1);
        FragmentAdapter fragmentAdapter1 = new FragmentAdapter(getSupportFragmentManager());

        viewPager1.setAdapter(fragmentAdapter1);
        for (int i = 0; i < listImage1.size(); i++) {
            ImageFragment1 imageFragment = new ImageFragment1();
            Bundle bundle = new Bundle();
            bundle.putInt("imgRes", listImage1.get(i));
            imageFragment.setArguments(bundle);
            fragmentAdapter1.addItem(imageFragment);
        }
        fragmentAdapter1.notifyDataSetChanged();



        //

        LinearLayout mon_1 = (LinearLayout) findViewById(R.id.month_1);
        LinearLayout mon_3 = (LinearLayout) findViewById(R.id.month_3);
        LinearLayout mon_6 = (LinearLayout) findViewById(R.id.month_6);
        LinearLayout mon_12 = (LinearLayout) findViewById(R.id.month_12);

        mon_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GymActivity.this, HealthpayActivity1.class);
                startActivity(intent);
            }
        });
        mon_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GymActivity.this, HealthpayActivity3.class);
                startActivity(intent);
            }
        });
        mon_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GymActivity.this, HealthpayActivity6.class);
                startActivity(intent);
            }
        });
        mon_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GymActivity.this, HealthpayActivity12.class);
                startActivity(intent);
            }
        });

        Button used_gym = (Button) findViewById(R.id.btnCall);
        used_gym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GymActivity.this, HealthpayActivity.class);
                startActivity(intent);
            }
        });

        Button review = (Button)findViewById(R.id.writereview);
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GymActivity.this, ReviewActivity.class);
                startActivity(intent);
            }
        });

        TabHost tab_host = (TabHost) findViewById(R.id.tabhost);
        tab_host.setup();

        TabHost.TabSpec ts1 = tab_host.newTabSpec("tab1");
        ts1.setIndicator("상품");
        ts1.setContent(R.id.tab1);
        tab_host.addTab(ts1);

        TabHost.TabSpec ts2 = tab_host.newTabSpec("tab2");
        ts2.setIndicator("정보");
        ts2.setContent(R.id.tab2);
        tab_host.addTab(ts2);

        TabHost.TabSpec ts3 = tab_host.newTabSpec("tab3");
        ts3.setIndicator("리뷰");
        ts3.setContent(R.id.tab3);
        tab_host.addTab(ts3);

        tab_host.setCurrentTab(0);


        mRecyclerView1 = findViewById(R.id.review_recycler_view);
        mRecyclerView1.setHasFixedSize(true);
        mLayoutManager1 = new LinearLayoutManager(this);
        //((LinearLayoutManager) mLayoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);//가로로 보여주는거
        mRecyclerView1.setLayoutManager(mLayoutManager1);

        ArrayList<reviewItem> reviewItemArrayList = new ArrayList<>();
        reviewItemArrayList.add(new reviewItem(R.drawable.user_1, "강상우", "좋네요", "2019/05/12"));
        reviewItemArrayList.add(new reviewItem(R.drawable.user_1, "노의건", "좋네요", "2019/04/22"));
        reviewItemArrayList.add(new reviewItem(R.drawable.user_1, "최낙범", "좋네요", "2019/04/12"));
        reviewItemArrayList.add(new reviewItem(R.drawable.user_1, "라지수 ", "좋네요", "2019/04/02"));

        reviewAdapter reviewAdapter = new reviewAdapter(reviewItemArrayList);


        mRecyclerView1.setAdapter(reviewAdapter);
/*

        mRecyclerView2 = findViewById(R.id.membership_recycler_view);
        mRecyclerView2.setHasFixedSize(true);
        mLayoutManager2 = new LinearLayoutManager(this);
        ((LinearLayoutManager) mLayoutManager2).setOrientation(LinearLayoutManager.HORIZONTAL);//가로로 보여주는거
        mRecyclerView2.setLayoutManager(mLayoutManager2);

        ArrayList<membershipItem> membershipItemArrayList = new ArrayList<>();
        membershipItemArrayList.add(new membershipItem("1개월 - 7 pass", "1개월 / 7 pass", "34,300"));
        membershipItemArrayList.add(new membershipItem("3개월 - 20 pass", "3개월 / 20 pass", "67,300"));
        membershipItemArrayList.add(new membershipItem("6개월 - 40 pass", "6개월 / 40 pass", "95,300"));
        membershipItemArrayList.add(new membershipItem("9개월 - 60 pass", "9개월 / 60 pass", "120,000"));
        membershipItemArrayList.add(new membershipItem("12개월 - 80 pass", "12개월 / 80 pass", "150,000"));

        membershipAdapter membershipAdapter = new membershipAdapter (membershipItemArrayList);

        mRecyclerView2.setAdapter(membershipAdapter);
        */
        //////////CHAT//////////////////////////////
        Button chat_button = (Button) findViewById(R.id.btnMessage);
        chat_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GymActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });
        ///////////////////////////////////////////
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

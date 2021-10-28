package com.talkies.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.talkies.R;
import com.talkies.adapters.CustomTabFragmentAdapter;
import com.talkies.adapters.PurchaseViewPagerAdapter;

import org.json.JSONObject;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

public class MyPurchaseFragment extends Fragment implements  View.OnClickListener{

    PurchaseViewPagerAdapter customTabFragmentAdapter;
    ArrayList<Fragment> fragments;
    ViewPager viewPager;
    TabLayout tabLayout;
 @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_purchase, container, false);
    }

    public MyPurchaseFragment() {
        setRetainInstance(true);  //Hear put this line

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = (ViewPager) view.findViewById(R.id.viewpagerMyPurchase);
       tabLayout =  view.findViewById(R.id.tabLayoutMyPurchase);
        fragments = new ArrayList<>();
        fragments.clear();
        fragments.add(new MySubscriptionFragment());
        fragments.add(new MyRentalFragment());
        fragments.add(new MyExpiredFragment());


        customTabFragmentAdapter = new PurchaseViewPagerAdapter(getActivity().getSupportFragmentManager(), getApplicationContext(), fragments);
        viewPager.setAdapter(customTabFragmentAdapter);
        customTabFragmentAdapter.notifyDataSetChanged();
        tabLayout.setupWithViewPager(viewPager);

            tabLayout.getTabAt(0).setText("Subscription");
        tabLayout.getTabAt(1).setText("Rental");
        tabLayout.getTabAt(2).setText("Expired");
            tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.app_theme_yellow_color));
        viewPager.setCurrentItem(0);
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//
//            @Override
//            public void onPageSelected(int position) {
//                viewPager.setCurrentItem(position,false);
//                tabLayout.getTabAt(position).select();
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//            }
//        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager.removeView(tab.getCustomView());

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setUIVisibility() {


    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
            //flagForVisibility= args.getString(DATA_FLAG);
            setUIVisibility();
        }


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.iv_tell_your_friends_next:
                //visibilityForAllViewGone();
                //layoutAboutUsDetail.setVisibility(View.VISIBLE);
                break;
        }
    }




}

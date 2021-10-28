package com.talkies.fragments;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.os.Bundle;
import android.util.Log;
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
import com.talkies.callbacks.MainActivityCallBack;
import com.talkies.managers.MainActivityManager;
import com.talkies.utils.GeneralMethods;
import com.talkies.utils.MyPreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment implements MainActivityCallBack {

    TabLayout tbLayout;
    MainActivityCallBack mHomeCallBack;
    GeneralMethods mGeneralMethods;
    ViewPager viewPager;
    CustomTabFragmentAdapter customTabFragmentAdapter;
    ArrayList<Fragment> fragments;
    MyPreferenceManager prefManager;

    public HomeFragment() {
        setRetainInstance(true);  //Hear put this line
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tbLayout = view.findViewById(R.id.tabLayout);
        mGeneralMethods = new GeneralMethods(this.getContext(), getActivity());
        viewPager = view.findViewById(R.id.viewpager);
        prefManager = new MyPreferenceManager(getContext());


        mHomeCallBack = this;

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("onResume: ", "home");
        new MainActivityManager(getActivity(), mHomeCallBack, prefManager).getTabsCategories(mGeneralMethods);


    }


    @Override
    public void onSuccessSearchListResult(JSONArray result) {

    }

    @Override
    public void onSuccessSearchListResult(String result) {

    }

    @Override
    public void onFailureSearchResult() {

    }

    @Override
    public void onSuccessSearchResult(JSONArray result) {

    }

    @Override
    public void onSuccessSearchResult(String result) {


    }

    @Override
    public void onSuccessTabCategoryList(JSONArray result) {

    }

    @Override
    public void onSuccessTabCategoryList(String result) {
        fragments = new ArrayList<>();
        fragments.clear();
        try {
            JSONArray categoryArray = new JSONArray(result);
            for (int i = 0; i < categoryArray.length(); i++) {
                JSONObject category = categoryArray.getJSONObject(i);
                String mainTitle = category.getString("title");
                String slug = category.getString("slug");
                fragments.add(new TabHomeFragment(mainTitle, slug));
            }
            customTabFragmentAdapter = new CustomTabFragmentAdapter(requireActivity().getSupportFragmentManager(), getApplicationContext(), fragments);
            viewPager.setAdapter(customTabFragmentAdapter);
            tbLayout.setupWithViewPager(viewPager);
            for (int i = 0; i < categoryArray.length(); i++) {
                JSONObject category = categoryArray.getJSONObject(i);
                String mainTitle = category.getString("title");
                String slug = category.getString("slug");
                Objects.requireNonNull(tbLayout.getTabAt(i)).setText(mainTitle);
                Objects.requireNonNull(tbLayout.getTabAt(i)).setTag(slug);
                tbLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.app_theme_yellow_color));
            }
            viewPager.setCurrentItem(0);
            viewPager.setSaveFromParentEnabled(false);

            tbLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onFailureTabCategoryResult() {

    }

    @Override
    public void onSuccessSearchIemDetailResult(JSONObject result) {

    }

    @Override
    public void onSuccessSearchIemDetailResult(String result) {

    }

    @Override
    public void onFailureSearchIemDetailResult() {

    }

    @Override
    public void onSuccessAnnouncementsResult(JSONArray result) {

    }

    @Override
    public void onSuccessAnnouncementsResult(String result) {

    }

    @Override
    public void onFailureAnnouncementsResult() {

    }

    @Override
    public void onSuccessMoreClicked(JSONObject result) {

    }

    @Override
    public void onSuccessMoreClicked(String result) {

    }

    @Override
    public void onFailureMoreClicked() {

    }


    @Override
    public void onSuccessNotificationResult(JSONObject result) {

    }

    @Override
    public void onSuccessNotificationResult(JSONArray result) {

    }

    @Override
    public void onSuccessNotificationResult(String result) {

    }

    @Override
    public void onFailureNotificationResult() {

    }


}

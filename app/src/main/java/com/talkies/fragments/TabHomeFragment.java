package com.talkies.fragments;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.talkies.R;
import com.talkies.activities.VideoPlayerActivity;
import com.talkies.adapters.CategoryChildAdapter;
import com.talkies.adapters.CategoryVideoAdapter;
import com.talkies.adapters.ContinueWatchingAdapter;
import com.talkies.callbacks.HomeCallBack;
import com.talkies.managers.TabHomeManager;
import com.talkies.model.AlertNotification;
import com.talkies.model.continuewatching.ContinueWatching;
import com.talkies.model.recyclercategorytab.Datum;
import com.talkies.model.recyclercategorytab.RecyclerCategoryList;
import com.talkies.model.topbannermasthead.TopBannerList;
import com.talkies.utils.GeneralMethods;
import com.talkies.utils.MyPreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

public class TabHomeFragment extends Fragment implements View.OnClickListener, HomeCallBack, CategoryChildAdapter.OnCategoryItemClickListener, CategoryVideoAdapter.onMoreItemClickListener {
    Context mContext;
    HomeCallBack mTabHomeCallBack;
    GeneralMethods mGeneralMethods;
    ViewFlipper viewFlipper;
    RecyclerView mContinueWatchingList, mCategoryList;
    TextView tvHeader;
    ContinueWatchingAdapter mAdapter;
    CategoryVideoAdapter mCategoryAdapter;
    String mainTitle;
    View view;
    String tabSlug;
    CategoryChildAdapter.OnCategoryItemClickListener listener;
    CategoryVideoAdapter.onMoreItemClickListener moreListener;
    Gson gson;
    LinearLayout linearLayoutTopImageBanner;
    ImageView imageView_Badet_Logo;
    TextView tv_title_notification, tv_subtitle_notification;
    ImageView ivCloseNotification;
    LinearLayout llNotificationLayout;
    Button buttonSubscribeNow;
    MyPreferenceManager prefManager;
    public TabHomeFragment(String mainTitle, String tabSlug) {
        this.mainTitle = mainTitle;
        this.tabSlug = tabSlug;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGeneralMethods = new GeneralMethods(this.getContext(), getActivity());
        prefManager = new MyPreferenceManager(getContext());
        linearLayoutTopImageBanner = view.findViewById(R.id.rootContainer);
        listener = this;
        moreListener = this;
        mTabHomeCallBack = this;
        gson = new Gson();
        this.view = view;
        mContext = getApplicationContext();
        viewFlipper = new ViewFlipper(getContext());
        ivCloseNotification = view.findViewById(R.id.imageView_notification_close);
        mContinueWatchingList = view.findViewById(R.id.continueWatching_videoList);
        mCategoryList = view.findViewById(R.id.category_videoList);
        llNotificationLayout = view.findViewById(R.id.notification_badge);
        tv_title_notification = view.findViewById(R.id.tv_title_notification);
        imageView_Badet_Logo = view.findViewById(R.id.imageView_Badet_Logo);
        tv_subtitle_notification = view.findViewById(R.id.tv_subtitle_notification);
        buttonSubscribeNow = view.findViewById(R.id.buttonSubscribeNow);
        tvHeader = view.findViewById(R.id.tv_catHeader);
        buttonSubscribeNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("onResume: ", "Tabhome");
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, 0);
        viewFlipper = new ViewFlipper(getContext());

        viewFlipper.setInAnimation(getContext(), R.anim.slide_in_right);
        viewFlipper.setOutAnimation(getContext(), R.anim.slide_out_left);
        viewFlipper.setLayoutParams(layoutParams);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);
        viewFlipper.setFocusable(true);
        viewFlipper.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);

        viewFlipper.setInAnimation(getContext(), R.anim.slide_in_right);
        viewFlipper.setOutAnimation(getContext(), R.anim.slide_out_left);

        ivCloseNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llNotificationLayout.setVisibility(View.GONE);
            }
        });

        new TabHomeManager(getActivity(), mTabHomeCallBack, prefManager).getNotificationAlterResult(mGeneralMethods);

    }

    @Override
    public void onSuccessMasterTopImageListHome(JSONArray result) {

    }

    @Override
    public void onSuccessMasterTopImageListHome(String result) {
        Type collectionType = new TypeToken<Collection<TopBannerList>>() {
        }.getType();
        ArrayList<TopBannerList> enums = gson.fromJson(result, collectionType);
        Log.e("onSuccessMasterTop: ", enums.toString());

        try {
            JSONArray jsonArray = new JSONArray(result);
            URL url = null;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = null;

                jsonObject = jsonArray.getJSONObject(i);

                String image = jsonObject.getString("banner");

                setImageInFlipper(enums.get(i).getBanner());

            }
            viewFlipper.startFlipping();

            if (linearLayoutTopImageBanner.getParent() != null) {
                ((ViewGroup) linearLayoutTopImageBanner.getParent()).removeView(viewFlipper); // <- fix
            }
            linearLayoutTopImageBanner.addView(viewFlipper);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        new TabHomeManager(getActivity(), mTabHomeCallBack, prefManager).getContinueWatchingList(mGeneralMethods, tabSlug);
    }

    private void setImageInFlipper(String imgUrl) {
        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setFocusableInTouchMode(false);
        imageView.setFocusable(false);
        Picasso.get().load(imgUrl).into(imageView);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 300);
        params.gravity = Gravity.TOP;
        params.setMargins(5, 0, 10, 5);
        params.gravity = Gravity.TOP;
        imageView.setLayoutParams(params);
        viewFlipper.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        viewFlipper.setFocusable(true);
        viewFlipper.setFocusableInTouchMode(false);
        viewFlipper.addView(imageView);
    }

    @Override
    public void onFailureMasterTopImageListHome() {

    }

    @Override
    public void onSuccessContinueWatchingListHome(JSONArray result) {

    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onSuccessContinueWatchingListHome(String result) {

        Log.d("zzz", "onSuccessContinueWatchingListHome:   " + result);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        Type collectionType = new TypeToken<Collection<ContinueWatching>>() { }.getType();
        ArrayList<ContinueWatching> continueWatchingArrayList = gson.fromJson(result, collectionType);
        mAdapter = new ContinueWatchingAdapter(getContext(), continueWatchingArrayList);
        mContinueWatchingList.setHasFixedSize(false);
        mContinueWatchingList.setLayoutManager(layoutManager);
        mContinueWatchingList.setNestedScrollingEnabled(false);
        mContinueWatchingList.setHasFixedSize(false);
        mContinueWatchingList.setAdapter(mAdapter);
        tvHeader.setVisibility(View.VISIBLE);
        mAdapter.notifyDataSetChanged();
        new TabHomeManager(getActivity(), mTabHomeCallBack, prefManager).getCategoryList(mGeneralMethods, tabSlug);
    }

    @Override
    public void onFailureContinueWatchingHome() {
        new TabHomeManager(getActivity(), mTabHomeCallBack, prefManager).getCategoryList(mGeneralMethods, tabSlug);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

    }

    @Override
    public void onPause() {
        super.onPause();
        linearLayoutTopImageBanner.removeAllViews();
    }

    @Override
    public void onSuccessCategoryListHome(String result) {
        Type collectionType = new TypeToken<Collection<RecyclerCategoryList>>() {
        }.getType();
        ArrayList<RecyclerCategoryList> recyclerCategoryList = gson.fromJson(result, collectionType);
        Log.e("onSuccessMasterTop: ", recyclerCategoryList.toString());
        mCategoryAdapter = new CategoryVideoAdapter(getContext(), recyclerCategoryList, listener, moreListener);
        mCategoryList.setHasFixedSize(false);
        mCategoryList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCategoryList.setNestedScrollingEnabled(false);
        mCategoryList.setHasFixedSize(false);
        mCategoryList.setAdapter(mCategoryAdapter);
        mCategoryAdapter.notifyDataSetChanged();

    }

    @Override
    public void onFailureCategoryListHome() {

    }

    @Override
    public void onSuccessAlertResult(String result) {

        Type collectionType = new TypeToken<Collection<AlertNotification>>() {
        }.getType();
        ArrayList<AlertNotification> alertNotification = gson.fromJson(result, collectionType);

        if (prefManager.getNotificationEnable()) {
            if (alertNotification.size() != 0) {
                tv_title_notification.setText(alertNotification.get(0).getTitle());
                tv_subtitle_notification.setText(alertNotification.get(0).getDescription());
                buttonSubscribeNow.setText(alertNotification.get(0).getActionLabel());
                Picasso.get().load(alertNotification.get(0).getIcon()).into(imageView_Badet_Logo);
                llNotificationLayout.setVisibility(View.VISIBLE);
            } else {
                llNotificationLayout.setVisibility(View.GONE);
            }
        }


//
        //Toast.makeText(getContext(),"slug"+tabSlug,Toast.LENGTH_SHORT).show();


        new TabHomeManager(getActivity(), mTabHomeCallBack, prefManager).getTopBannerImages(mGeneralMethods, tabSlug);

    }

    @Override
    public void onFailure() {
        mGeneralMethods.launchHomeScreen();
        // new TabHomeManager(getActivity(), mTabHomeCallBack).getTopBannerImages(mGeneralMethods,slug);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.imageView_notification_close:
//
//                break;
        }
    }


    public void fragmentReplacer(int containerId, Fragment fragment, String tag) {
        final FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerId, fragment, tag);
        fragmentTransaction.commit();
    }

    public void fragmentAdder(int containerId, Fragment fragment, String tag) {
        final FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerId, fragment, tag);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragmentTransaction.addToBackStack("MovieItemDetailFragment");
        fragmentTransaction.commit();
    }

    @Override
    public void OnCategoryItemClickListener(Datum item) {

        Intent vd = new Intent(getActivity(), VideoPlayerActivity.class);
        vd.putExtra("slug", item.getSlug());
        startActivity(vd);
    }


    @Override
    public void onMoreItemClickListener(String slug) {
        MoreClickedFragment moreClickedFragment = new MoreClickedFragment(tabSlug, slug);
        fragmentAdder(R.id.main_FL, moreClickedFragment, "MoreClickedFragment");
    }
}
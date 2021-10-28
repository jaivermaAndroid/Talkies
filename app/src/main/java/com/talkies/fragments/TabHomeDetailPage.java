package com.talkies.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.talkies.R;
import com.talkies.adapters.CategoryVideoAdapter;
import com.talkies.adapters.ContinueWatchingAdapter;
import com.talkies.callbacks.HomeCallBack;
import com.talkies.managers.TabHomeManager;
import com.talkies.model.continuewatching.ContinueWatching;
import com.talkies.model.recyclercategorytab.RecyclerCategoryList;
import com.talkies.model.topbannermasthead.TopBannerList;
import com.talkies.utils.GeneralMethods;
import com.squareup.picasso.Picasso;
import com.talkies.utils.MyPreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class TabHomeDetailPage extends Fragment implements View.OnClickListener, HomeCallBack {

    Context mContext;
    private boolean pictureTaken = false;
    ProgressDialog mProgressDialog;
    boolean updateActivity=false;

    HomeCallBack mTabHomeCallBack;
    GeneralMethods mGeneralMethods;
    ViewFlipper viewFlipper;

    ImageView ivCloseNotification;
    LinearLayout llNotificationLayout;
    RecyclerView mContinueWatchingList,mCategoryList;

    ContinueWatchingAdapter mAdapter;
    CategoryVideoAdapter mCategoryAdapter;

    String mainTitle;
    String slug;
    View view;
    String flagDetail;

    Gson gson;
    LinearLayout linearLayoutTopImageBanner;
    MyPreferenceManager prefManager;

    public TabHomeDetailPage(String mainTitle, String slug) {
        this.mainTitle=mainTitle;
        this.slug=slug;
    }

    public TabHomeDetailPage() {
        setRetainInstance(true);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_home_detail_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGeneralMethods = new GeneralMethods(this.getContext(), getActivity());
        linearLayoutTopImageBanner= view.findViewById(R.id.rootContainer);
        mTabHomeCallBack = this;
        gson = new Gson();
        this.view= view;
        viewFlipper = new ViewFlipper(getContext());
        ivCloseNotification=view.findViewById(R.id.imageView_notification_close);
        mContinueWatchingList = view.findViewById(R.id.continueWatching_videoList);
        mCategoryList= view.findViewById(R.id.category_videoList);
        llNotificationLayout=view.findViewById(R.id.notification_badge);
        prefManager = new MyPreferenceManager(getContext());


    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e( "onResume: ", "Tabhome");
        Log.d( "zzz: ", "TabhomeDetailsPage");
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 350);
        layoutParams.setMargins(10, 10, 10, 10);
        viewFlipper.setLayoutParams(layoutParams);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(getContext(), R.anim.slide_in_right);
        viewFlipper.setOutAnimation(getContext(),  R.anim.slide_out_left);

        ivCloseNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llNotificationLayout.setVisibility(View.GONE);
            }
        });
        new TabHomeManager(getApplicationContext(), mTabHomeCallBack,prefManager).getTopBannerImages(mGeneralMethods,slug);


    }

    @Override
    public void onSuccessMasterTopImageListHome(JSONArray result) {

    }

    @Override
    public void onSuccessMasterTopImageListHome(String result) {


        // TOP Banner View Flipper


        Type collectionType = new TypeToken<Collection<TopBannerList>>(){}.getType();
        ArrayList<TopBannerList> enums = gson.fromJson(result, collectionType);
        Log.e("onSuccessMasterTop: ",enums.toString() );

        try {
            JSONArray jsonArray = new JSONArray(result);
            URL url = null;

            ArrayList<Bitmap> imageList= new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = null;

                jsonObject = jsonArray.getJSONObject(i);

                String image = jsonObject.getString("banner");

                setImageInFlipr(enums.get(i).getBanner());

            }
            viewFlipper.startFlipping();


            if (linearLayoutTopImageBanner != null) {
                linearLayoutTopImageBanner.addView(viewFlipper);
            }




        } catch (JSONException e) {
            e.printStackTrace();
        }

        new TabHomeManager(getApplicationContext(), mTabHomeCallBack,prefManager).getContinueWatchingList(mGeneralMethods,slug);

    }

    private void setImageInFlipr(String imgUrl) {

        ImageView image = new ImageView(getApplicationContext());
        Picasso.get().load(imgUrl).into(image);
        FrameLayout.LayoutParams params =
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 350);
        params.setMargins(10, 10, 10, 10);
        params.gravity = Gravity.CENTER;
        image.setLayoutParams(params);
        viewFlipper.addView(image);
    }

    @Override
    public void onFailureMasterTopImageListHome() {

    }

    @Override
    public void onSuccessContinueWatchingListHome(JSONArray result) {

    }

    @Override
    public void onSuccessContinueWatchingListHome(String result) {

        Type collectionType = new TypeToken<Collection<ContinueWatching>>(){}.getType();
        ArrayList<ContinueWatching> continueWatchingArrayList = gson.fromJson(result, collectionType);
        mAdapter = new ContinueWatchingAdapter(getContext(), continueWatchingArrayList);
        mContinueWatchingList.setHasFixedSize(false);
        mContinueWatchingList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mContinueWatchingList.setNestedScrollingEnabled(false);
        mContinueWatchingList.setAdapter(mAdapter);



        new TabHomeManager(getApplicationContext(), mTabHomeCallBack,prefManager).getCategoryList(mGeneralMethods,slug);
    }

    @Override
    public void onFailureContinueWatchingHome() {
        new TabHomeManager(getApplicationContext(), mTabHomeCallBack,prefManager).getCategoryList(mGeneralMethods,slug);
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
        try {
            JSONArray jsonArray = new JSONArray(result);
            Log.e("resultCategory: ",result );
            Type collectionType = new TypeToken<Collection<RecyclerCategoryList>>(){}.getType();
            ArrayList<RecyclerCategoryList> recyclerCategoryList = gson.fromJson(result, collectionType);
            Log.e("onSuccessMasterTop: ",recyclerCategoryList.toString() );




//            mCategoryAdapter = new CategoryVideoAdapter(getContext(), recyclerCategoryList,listener);
//            mCategoryList.setHasFixedSize(false);
//            mCategoryList.setLayoutManager(new LinearLayoutManager(getApplicationContext(),));
//            mCategoryList.setNestedScrollingEnabled(false);
//            mCategoryList.setAdapter(mCategoryAdapter);
//            mCategoryAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFailureCategoryListHome() {

    }

    @Override
    public void onSuccessAlertResult(String result) {

    }

    @Override
    public void onFailure() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.imageView_notification_close:
//
//                break;
        }
    }
}
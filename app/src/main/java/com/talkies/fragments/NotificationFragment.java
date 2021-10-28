package com.talkies.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.talkies.R;
import com.talkies.adapters.AnnouncementsVideoAdapter;
import com.talkies.adapters.NotificationListAdapter;
import com.talkies.callbacks.MainActivityCallBack;
import com.talkies.callbacks.SwipeToDeleteCallback;
import com.talkies.managers.MainActivityManager;
import com.talkies.model.announcements.Anouncement;
import com.talkies.model.notifications.NotificationData;
import com.talkies.model.notifications.Result;
import com.talkies.utils.GeneralMethods;
import com.talkies.utils.MyPreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class NotificationFragment extends Fragment implements MainActivityCallBack, NotificationListAdapter.NotificationOnItemClickListener {


    RecyclerView mAnnouncementList;
    RecyclerView mNotificationList;
    MainActivityCallBack mMainCallBack;
    GeneralMethods mGeneralMethods;
    Gson gson;
    AnnouncementsVideoAdapter mCategoryAdapter;
    Button mReleaseDates;
    ReleaseDateDetailsFragment mReleaseDateDetailsFragment;
    NotificationListAdapter mNotificationAdapter;
    NotificationListAdapter.NotificationOnItemClickListener notifcationListener;
    TextView mClearAllNotifications;
    Result resultModel;
    ArrayList<Result> notificationList;
    int positionDelete;
    Boolean isprogrammatic = false;
    MyPreferenceManager prefManager;

    public NotificationFragment() {
        setRetainInstance(true);  //Hear put this line

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gson = new Gson();
        this.notifcationListener = this;
        mReleaseDateDetailsFragment = new ReleaseDateDetailsFragment();
        mAnnouncementList = view.findViewById(R.id.announcementsList);
        mNotificationList = view.findViewById(R.id.notificationList);
        mReleaseDates = view.findViewById(R.id.buttonReleaseDates);
        mClearAllNotifications = view.findViewById(R.id.clearAllNotifications);
        mMainCallBack = this;
        mGeneralMethods = new GeneralMethods(this.getContext(), getActivity());
        prefManager = new MyPreferenceManager(getContext());
        new MainActivityManager(getActivity(), mMainCallBack, prefManager).getAnnouncementsList(mGeneralMethods);

        mReleaseDates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isprogrammatic = true;
                if (isprogrammatic) {

                    fragmentAdder(R.id.main_FL, mReleaseDateDetailsFragment, "mReleaseDateDetailsFragment");

                    isprogrammatic = false;
                }
            }
        });

        mClearAllNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MainActivityManager(getActivity(), mMainCallBack, prefManager).getClearAllNotificationList(mGeneralMethods, 1584370309);
            }
        });
        enableSwipeToDeleteAndUndo();

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

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onSuccessAnnouncementsResult(String result) {
        try {
            JSONArray jsonArray = new JSONArray(result);
            Log.e("Anouncement: ", result);
            Type collectionType = new TypeToken<Collection<Anouncement>>() {
            }.getType();
            ArrayList<Anouncement> anouncementListArray = gson.fromJson(result, collectionType);
            if (anouncementListArray.size() > 1) {
                anouncementListArray.remove(1);}
            mCategoryAdapter = new AnnouncementsVideoAdapter(getContext(), anouncementListArray);
            mAnnouncementList.setHasFixedSize(false);
            mAnnouncementList.setLayoutManager(new LinearLayoutManager(getActivity()));
            mAnnouncementList.setNestedScrollingEnabled(false);
            mAnnouncementList.setAdapter(mCategoryAdapter);
            mCategoryAdapter.notifyDataSetChanged();

            new MainActivityManager(getActivity(), mMainCallBack, prefManager).getNotificationList(mGeneralMethods);

        } catch (JSONException e) {
            e.printStackTrace();
        }
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
        if (result.optString("detail").equalsIgnoreCase("Success")) {
            if (notificationList.size() != 0) {
                notificationList.remove(positionDelete);

            }
        } else {
            Type collectionType = new TypeToken<NotificationData>() {
            }.getType();

            NotificationData rental = gson.fromJson(result.toString(), collectionType);
            Log.e("rental: ", rental.toString());
            notificationList = new ArrayList<>();
            for (int item = 0; item < rental.getResults().size(); item++) {
                notificationList.add(rental.getResults().get(item));

            }
            Log.e("notificationList: ", notificationList.toString());


            mNotificationAdapter = new NotificationListAdapter(getContext(), notificationList, notifcationListener);
            mNotificationList.setHasFixedSize(false);
            mNotificationList.setLayoutManager(new LinearLayoutManager(getActivity()));
            mNotificationList.setNestedScrollingEnabled(true);
            mNotificationList.setAdapter(mNotificationAdapter);
        }


        mNotificationAdapter.notifyDataSetChanged();

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


    public void fragmentAdder(int containerId, Fragment fragment, String tag) {
        final FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerId, fragment, tag);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragmentTransaction.addToBackStack("mNotificationDetailsFragment");
        fragmentTransaction.commit();
    }

    @Override
    public void onItemClick(Result item) {


    }


    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(getContext()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                positionDelete = viewHolder.getAdapterPosition();


                String idDeleted = notificationList.get(viewHolder.getAdapterPosition()).getId();

                new MainActivityManager(getActivity(), mMainCallBack, prefManager).getClearSelectedNotificationList(mGeneralMethods, idDeleted);


            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(mNotificationList);


    }

}
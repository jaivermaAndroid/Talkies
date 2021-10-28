package com.talkies.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.talkies.R;
import com.talkies.adapters.MoreResultVideoAdapter;
import com.talkies.adapters.SearchListAdapter;
import com.talkies.adapters.SearchResultVideoAdapter;
import com.talkies.callbacks.MainActivityCallBack;
import com.talkies.managers.MainActivityManager;
import com.talkies.model.SearchViewList;
import com.talkies.model.moredetails.MoreResult;
import com.talkies.model.moredetails.Result;
import com.talkies.utils.GeneralMethods;
import com.talkies.utils.MyPreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MoreClickedFragment extends Fragment implements MainActivityCallBack {


    SearchListAdapter adapter;
    MoreResultVideoAdapter moreResultVideoAdapter;

    RecyclerView mMoreResult;
    MainActivityCallBack mainActivityCallBack;
    GeneralMethods mGeneralMethods;
    ArrayList<SearchViewList> arraylist = new ArrayList<SearchViewList>();
    String slug;
    String tabSlug;
    SearchResultVideoAdapter.OnItemClickListener mSearchListItemListener;
    private Gson gson;
    MyPreferenceManager prefManager;

    public MoreClickedFragment(String tabSlug, String slug) {
        this.slug = slug;
        this.tabSlug = tabSlug;
    }

    public MoreClickedFragment() {
        setRetainInstance(true);  //Hear put this line
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_more, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("zzz","MoreClicked Fragment");
        mGeneralMethods = new GeneralMethods(this.getContext(), getActivity());
        mainActivityCallBack = this;
        gson = new Gson();


        mMoreResult = view.findViewById(R.id.recycler_view_more);
        prefManager = new MyPreferenceManager(getContext());


        new MainActivityManager(getActivity(), mainActivityCallBack, prefManager).getMoreDetails(mGeneralMethods, slug, tabSlug);


    }


    @Override
    public void onSuccessSearchResult(JSONArray result) {


    }

    @Override
    public void onSuccessSearchResult(String result) {

        Log.e("onSuccessSearchResult: ", result);


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

    @Override
    public void onSuccessAnnouncementsResult(String result) {

    }

    @Override
    public void onFailureAnnouncementsResult() {

    }

    @Override
    public void onSuccessMoreClicked(JSONObject result) {

        Type collectionType = new TypeToken<MoreResult>() {
        }.getType();

        MoreResult rental = gson.fromJson(result.toString(), collectionType);
        ArrayList<Result> rentalList = new ArrayList<>();
        for (int item = 0; item < rental.getResults().size(); item++) {
            rentalList.add(rental.getResults().get(item));

        }
        moreResultVideoAdapter = new MoreResultVideoAdapter(getContext(), rentalList);
        mMoreResult.setHasFixedSize(false);
        mMoreResult.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMoreResult.setNestedScrollingEnabled(false);
        mMoreResult.setAdapter(moreResultVideoAdapter);
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


    @Override
    public void onSuccessSearchListResult(JSONArray result) {


    }

    @Override
    public void onSuccessSearchListResult(String result) {
        try {
            JSONArray resultArray = new JSONArray(result);

            for (int i = 0; i < resultArray.length(); i++) {
                JSONObject searchObject = resultArray.getJSONObject(i);

                SearchViewList animalNames = new SearchViewList(searchObject.getString("title"));
                // Binds all strings into an array
                arraylist.add(animalNames);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter = new SearchListAdapter(getActivity(), arraylist);

        // Binds the Adapter to the ListView
    }

    @Override
    public void onFailureSearchResult() {


    }


    public void fragmentAdder(int containerId, Fragment fragment, String tag) {
        final FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerId, fragment, tag);
        Bundle args = new Bundle();
        fragmentTransaction.addToBackStack("SearchDetailFragment");
        fragmentTransaction.commit();
    }
}
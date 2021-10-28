package com.talkies.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.talkies.R;
import com.talkies.adapters.SearchListAdapter;
import com.talkies.adapters.SearchResultVideoAdapter;
import com.talkies.callbacks.MainActivityCallBack;
import com.talkies.managers.MainActivityManager;
import com.talkies.model.SearchViewList;
import com.talkies.model.searchResult.SearchResultResponseList;
import com.talkies.utils.GeneralMethods;
import com.talkies.utils.MyPreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class SearchFragment extends Fragment implements MainActivityCallBack, SearchResultVideoAdapter.OnItemClickListener {


    ListView list;
    SearchListAdapter adapter;
    SearchResultVideoAdapter searchResultVideoAdapter;

    RecyclerView mSearchResult;
    // SearchView search;
    EditText search;
    String[] animalNameList;
    MainActivityCallBack mainActivityCallBack;
    GeneralMethods mGeneralMethods;
    ArrayList<SearchViewList> arraylist = new ArrayList<SearchViewList>();
    ImageView ivClose, ivSearch;

    SearchResultVideoAdapter.OnItemClickListener mSearchListItemListener;
    MyPreferenceManager prefManager;
    private Gson gson;

    public SearchFragment() {
        setRetainInstance(true);  //Hear put this line

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGeneralMethods = new GeneralMethods(this.getContext(), getActivity());
        mainActivityCallBack = this;
        gson = new Gson();
        mSearchListItemListener = this;
        prefManager = new MyPreferenceManager(getContext());
        new MainActivityManager(getActivity(), mainActivityCallBack, prefManager).getSearchListResult(mGeneralMethods);


        // Locate the ListView in listview_main.xml
        list = (ListView) view.findViewById(R.id.search_list_view);

        mSearchResult = view.findViewById(R.id.recycler_view_search_result);
        search = view.findViewById(R.id.searchView);
        ivClose = view.findViewById(R.id.search_close);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search.setText("");
                ivClose.setVisibility(View.GONE);
            }
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (adapter != null) {

                    adapter.filter(charSequence.toString());
                }
                if (charSequence.length() > 0) {
                    ivClose.setVisibility(View.VISIBLE);
                } else {
                    ivClose.setVisibility(View.GONE);
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.e("onTextChanged: ", editable.toString());

            }
        });


        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Log.e("onTextChanged: ", v.getText().toString());
                    hideKeyboard(v);

                    new MainActivityManager(getActivity(), mainActivityCallBack, prefManager).getSearchQueryResult(mGeneralMethods, v.getText().toString());
                    ivClose.setVisibility(View.GONE);
                    return true;
                }
                return false;
            }
        });


    }


    public void hideKeyboard(View view) {
        try {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception ignored) {
        }
    }

    @Override
    public void onSuccessSearchResult(JSONArray result) {


    }

    @Override
    public void onSuccessSearchResult(String result) {

        Log.e("onSuccessSearchResult: ", result);

        //JSONObject response = new JSONObject(result);


        // String jsonFileString = response.toString();
        // SearchResultResponseList uiConfig = gson.fromJson(jsonFileString, type);
        //Log.e("onSuccessSearchResult: ", jsonFileString);
//            SearchResultResponseList emp = gson.fromJson(result, SearchResultResponseList.class);
//            Log.e("onSuccessSearchResult: ", emp.toString());
//            ArrayList<SearchResultResponseList> list = gson.fromJson(result, SearchResultResponseList.class);
//            list.add(emp);
        Log.e("onSuccessSearchResult: ", list.toString());

        Type collectionType = new TypeToken<Collection<SearchResultResponseList>>() {
        }.getType();
        ArrayList<SearchResultResponseList> list = gson.fromJson(result, collectionType);

        searchResultVideoAdapter = new SearchResultVideoAdapter(getContext(), list, this);
        mSearchResult.setHasFixedSize(false);
        mSearchResult.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSearchResult.setNestedScrollingEnabled(false);
        mSearchResult.setAdapter(searchResultVideoAdapter);


    }

    @Override
    public void onSuccessTabCategoryList(JSONArray result) {

    }

    @Override
    public void onSuccessTabCategoryList(String result) {

    }

    @Override
    public void onFailureTabCategoryResult() {
        mGeneralMethods.launchHomeScreen();
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
        list.setAdapter(adapter);
    }

    @Override
    public void onFailureSearchResult() {

        mGeneralMethods.launchHomeScreen();

    }

    @Override
    public void onItemClick(SearchResultResponseList item) {


        SearchItemDetailFragment searchDetailFragment = new SearchItemDetailFragment(item);
        fragmentAdder(R.id.main_FL, searchDetailFragment, "SearchDetailFragment");

    }


    public void fragmentAdder(int containerId, Fragment fragment, String tag) {
        final FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerId, fragment, tag);
        Bundle args = new Bundle();
        fragmentTransaction.addToBackStack("SearchDetailFragment");
        fragmentTransaction.commit();
    }
}
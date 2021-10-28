package com.talkies.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.talkies.R;
import com.talkies.adapters.ReleaseListAdapter;
import com.talkies.callbacks.MainActivityCallBack;
import com.talkies.managers.MainActivityManager;
import com.talkies.utils.GeneralMethods;

import org.json.JSONArray;
import org.json.JSONObject;

public class ReleaseDateDetailsFragment extends Fragment  {

    final static String DATA_FLAG = "DATA_FLAG";
    MainActivityCallBack mainActivityCallBack;
    GeneralMethods mGeneralMethods;
    String rentalType, isExpired;
    RecyclerView mRecyclerUpcoming;
    WebView webView;
    Gson gson;
 @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_release_dates, container, false);
    }
    public ReleaseDateDetailsFragment() {
        setRetainInstance(true);  //Hear put this line

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        gson = new Gson();
        mGeneralMethods = new GeneralMethods(getContext(),getActivity());
        webView = view.findViewById(R.id.webviewReleaseDate);
        webView.loadUrl("https://talkiesapp.com/release-dates");

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
    public void onResume() {
        super.onResume();
        Log.e( "onResume: ", "Rental");


    }









}

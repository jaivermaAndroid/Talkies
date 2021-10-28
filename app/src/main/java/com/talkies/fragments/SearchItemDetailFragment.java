package com.talkies.fragments;

import static com.talkies.utils.Constants.STORAGE_USER_DETAILS_RESPONSE;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.talkies.R;
import com.talkies.adapters.RecyclerCrewMemberAdapter;
import com.talkies.adapters.RecyclerStaringAdapter;
import com.talkies.callbacks.MainActivityCallBack;
import com.talkies.managers.MainActivityManager;
import com.talkies.model.UserDetails;
import com.talkies.model.searchResult.SearchResultResponseList;
import com.talkies.model.searchitem.SearchDeatils;
import com.talkies.utils.CustomDialogClass;
import com.talkies.utils.GeneralMethods;
import com.talkies.utils.MyPreferenceManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;

import io.paperdb.Paper;

public class SearchItemDetailFragment extends Fragment implements MainActivityCallBack {

    private final boolean pictureTaken = false;
    Context mContext;
    ProgressDialog mProgressDialog;
    boolean updateActivity = false;
    Gson gson;
    SearchResultResponseList mSearchItem;
    ImageView mImageBanner;
    TextView mItemTitle, mItemViewCount, mShortDescription, mSearchResultYear, mItemCategoryType1, mItemCategoryType2, mDetailCategoryDurationSearch, searchResultTypeIconSearch;
    Button mButtonStartWatching, mButtonConnectTv, mButtonWatchingTrailer;
    RecyclerView mStarCastList, mCreMembers;
    RecyclerCrewMemberAdapter recyclerCrewMemberAdapter;
    RecyclerStaringAdapter recyclerStaringAdapter;
    MainActivityCallBack mainActivityCallBack;
    GeneralMethods mGeneralMethods;
    View viewMoreDetails, viewCrew;
    TextView mCastCrewButton, mMoreDetailsButton;
    LinearLayout mCrewLayout, mMoreLayout;
    String trailerLink;
    UserDetails storageUserDetails;
    String mediaURL, title;
    PlayerView player_view;
    MyPreferenceManager prefManager;
    private SimpleExoPlayer exoPlayer;

    public SearchItemDetailFragment(SearchResultResponseList mSearchItem) {
        this.mSearchItem = mSearchItem;
    }

    public SearchItemDetailFragment() {
        setRetainInstance(true);  //Hear put this line

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("zzz", "SearchItemDetailsFragment");

        mGeneralMethods = new GeneralMethods(this.getContext(), getActivity());
        mainActivityCallBack = this;
        prefManager = new MyPreferenceManager(getContext());
        gson = new Gson();
        mImageBanner = view.findViewById(R.id.imageViewDetailPageSearch);
        mItemTitle = view.findViewById(R.id.titleSearch);
        mItemViewCount = view.findViewById(R.id.viewCount);
        mStarCastList = view.findViewById(R.id.starCastList);
        mCreMembers = view.findViewById(R.id.crewMemberList);

        viewMoreDetails = view.findViewById(R.id.moreDetailsView);
        viewCrew = view.findViewById(R.id.castAndCrewView);

        mCastCrewButton = view.findViewById(R.id.castAndCrewButton);
        mMoreDetailsButton = view.findViewById(R.id.viewMoreButton);

        mMoreLayout = view.findViewById(R.id.more_item_details);
        mCrewLayout = view.findViewById(R.id.cast_crew);

        mShortDescription = view.findViewById(R.id.short_description_detailSearch);
        mSearchResultYear = view.findViewById(R.id.searchResultYearSearch);
        mItemCategoryType1 = view.findViewById(R.id.detailCategoryType1Search);
        mItemCategoryType2 = view.findViewById(R.id.detailCategoryType2Search);
        mDetailCategoryDurationSearch = view.findViewById(R.id.detailCategoryDurationSearch);
        searchResultTypeIconSearch = view.findViewById(R.id.searchResultType_iconSearch);
        mButtonStartWatching = view.findViewById(R.id.buttonStartWatchingSearch);
        mButtonConnectTv = view.findViewById(R.id.buttonConnectTVSearch);
        mButtonWatchingTrailer = view.findViewById(R.id.buttonWatchTrailerSearch);

        Paper.book().read(STORAGE_USER_DETAILS_RESPONSE);
        storageUserDetails = Paper.book().read(STORAGE_USER_DETAILS_RESPONSE);
        setMenuVisibilityCrewView();
        new MainActivityManager(getActivity(), mainActivityCallBack, prefManager).getSearchDetails(mGeneralMethods, mSearchItem.getSlug());

        mMoreDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMenuVisibilityMoreView();
            }
        });

        mCastCrewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMenuVisibilityCrewView();
            }
        });

        mButtonWatchingTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trailerLink != null) {
                    showTrailer(trailerLink);

                } else {
                    Toast.makeText(getContext(), "Sorry Link is not available", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mButtonStartWatching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (storageUserDetails.getActiveSubscriptions() == null) {
                    CustomDialogClass cdd = new CustomDialogClass(getActivity());
                    cdd.show();

                } else {
                    String url = "";
                    String title = "";
                    url = mediaURL;
                    Uri uriOfContentUrl = Uri.parse(url);
                    initializePlayer(uriOfContentUrl, title);
                    player_view.setVisibility(View.VISIBLE);
                }
            }
        });

        mButtonConnectTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentConnectTv fragmentConnectTv = new FragmentConnectTv();
                fragmentAdder(R.id.main_FL, fragmentConnectTv, "fragmentConnectTv");
            }
        });
    }

    public void fragmentAdder(int containerId, Fragment fragment, String tag) {
        final FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerId, fragment, tag);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragmentTransaction.addToBackStack("fragmentConnectTv");
        fragmentTransaction.commit();
    }

    private void showTrailer(String trailerLink) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(trailerLink));
        intent.setPackage("com.google.android.youtube");
        startActivity(intent);
    }

    private void setMenuVisibilityCrewView() {
        viewMoreDetails.setVisibility(View.GONE);
        viewCrew.setVisibility(View.VISIBLE);
        mMoreLayout.setVisibility(View.GONE);
        mCrewLayout.setVisibility(View.VISIBLE);

    }

    private void setMenuVisibilityMoreView() {
        viewMoreDetails.setVisibility(View.VISIBLE);
        viewCrew.setVisibility(View.GONE);
        mCrewLayout.setVisibility(View.GONE);
        mMoreLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();

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
        Type collectionType = new TypeToken<SearchDeatils>() {
        }.getType();

        SearchDeatils searchItem = gson.fromJson(result.toString(), collectionType);
        Log.e("rental: ", searchItem.toString());
        mItemTitle.setText(searchItem.getTitle());
        mItemViewCount.setText(searchItem.getViewsCount().toString());
        mShortDescription.setText(searchItem.getShortDescription());
        mDetailCategoryDurationSearch.setText(searchItem.getRunTime().toString());
        mSearchResultYear.setText(searchItem.getReleaseYear().toString());
        if (searchItem.getLanguages().size() > 1) {
            mItemCategoryType1.setText(searchItem.getLanguages().get(0).getName());
            mItemCategoryType2.setText(searchItem.getLanguages().get(1).getName());
        }
        trailerLink = searchItem.getTrailerLink();
        mediaURL = searchItem.getMediaUrl();
        title = searchItem.getMediaUrl();
        searchResultTypeIconSearch.setText(searchItem.getCertificates().get(0).getTitle());
        Picasso.
                get()
                .load(searchItem.getBanner()).
                into(mImageBanner);
        trailerLink = searchItem.getTrailerLink();
        recyclerCrewMemberAdapter = new RecyclerCrewMemberAdapter(getContext(), searchItem.getOtherCrewMembers());
        RecyclerView.LayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        mCreMembers.setLayoutManager(manager);
        mCreMembers.setHasFixedSize(false);
        mCreMembers.setNestedScrollingEnabled(true);
        mCreMembers.setAdapter(recyclerCrewMemberAdapter);


        recyclerStaringAdapter = new RecyclerStaringAdapter(mContext, searchItem.getCast());
        RecyclerView.LayoutManager managerStar = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        mStarCastList.setLayoutManager(managerStar);
        mStarCastList.setHasFixedSize(false);
        mStarCastList.setNestedScrollingEnabled(true);
        mStarCastList.setAdapter(recyclerStaringAdapter);
        recyclerStaringAdapter.notifyDataSetChanged();
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

    private void initializePlayer(Uri uriOfContentUrl, String title) {
        prepareMediaForPlaying(uriOfContentUrl);
    }

    private void prepareMediaForPlaying(Uri uri) {
        // Build the appropriate MediaSource
        MediaSource mediaSource;
        DataSource.Factory dataSourceFactory;

        // Parse the URI to try to determine the type.
        // NOTE: inferContentType returns C.TYPE_OTHER if no matching signatures found
        int mediaSourceType = Util.inferContentType(uri);
        switch (mediaSourceType) {
            case C.TYPE_DASH:
                // Create a data source factory.
                dataSourceFactory = new DefaultHttpDataSourceFactory();
                // Create a DASH media source pointing to a DASH manifest uri.
                mediaSource =
                        new DashMediaSource.Factory(dataSourceFactory)
                                .createMediaSource(MediaItem.fromUri(uri));
                // Create a player instance.
                exoPlayer = new SimpleExoPlayer.Builder(getContext()).build();
                // Set the media source to be played.
                exoPlayer.setMediaSource(mediaSource);
                // Prepare the player.
                exoPlayer.prepare();
                break;

            case C.TYPE_SS:
                // Create a data source factory.
                dataSourceFactory = new DefaultHttpDataSourceFactory();
                // Create a SmoothStreaming media source pointing to a manifest uri.
                mediaSource =
                        new SsMediaSource.Factory(dataSourceFactory)
                                .createMediaSource(MediaItem.fromUri(uri));
                // Create a player instance.
                exoPlayer = new SimpleExoPlayer.Builder(getContext()).build();
                // Set the media source to be played.
                exoPlayer.setMediaSource(mediaSource);
                // Prepare the player.
                exoPlayer.prepare();
                break;

            case C.TYPE_HLS:
                // Create a data source factory.
                dataSourceFactory = new DefaultHttpDataSourceFactory();
                // Create a HLS media source pointing to a playlist uri.
                HlsMediaSource hlsMediaSource =
                        new HlsMediaSource.Factory(dataSourceFactory)
                                .createMediaSource(MediaItem.fromUri(uri));
                // Create a player instance.
                exoPlayer = new SimpleExoPlayer.Builder(getContext()).build();
                // Set the media source to be played.
                exoPlayer.setMediaSource(hlsMediaSource);
                // Prepare the player.
                exoPlayer.prepare();
                break;

            case C.TYPE_OTHER:
            default:
                // Create a data source factory.
                dataSourceFactory = new DefaultHttpDataSourceFactory();
                // Create a progressive media source pointing to a stream uri.
                mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(MediaItem.fromUri(uri));
                // Create a player instance.
                exoPlayer = new SimpleExoPlayer.Builder(getContext()).build();
                // Set the media source to be played.
                exoPlayer.setMediaSource(mediaSource);
                // Prepare the player.
                exoPlayer.prepare();
        }
        player_view.setPlayer(exoPlayer);

        exoPlayer.addListener(new Player.EventListener() {
            @Override
            public void onPlayerError(ExoPlaybackException error) {
            }
        });
        startPlayer();
    }

    private void releasePlayer() {
        if (exoPlayer != null) {
            exoPlayer.release();
            exoPlayer = null;
        }
    }

    private void pausePlayer() {
        if (exoPlayer != null) {
            exoPlayer.setPlayWhenReady(false);
            exoPlayer.getPlaybackState();
        }
    }

    private void startPlayer() {
        if (exoPlayer != null) {
            exoPlayer.setPlayWhenReady(true);
            exoPlayer.getPlaybackState();
        }
    }

}
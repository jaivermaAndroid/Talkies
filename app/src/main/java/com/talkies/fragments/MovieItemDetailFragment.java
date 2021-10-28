package com.talkies.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlaybackException;
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
import com.talkies.activities.LoginActivity;
import com.talkies.activities.MainActivity;
import com.talkies.activities.MySubscriptionDialogActivity;
import com.talkies.activities.OTPVerificationActivity;
import com.talkies.activities.VideoPlayerActivity;
import com.talkies.adapters.RecyclerCrewMemberAdapter;
import com.talkies.adapters.RecyclerStaringAdapter;
import com.talkies.callbacks.MainActivityCallBack;
import com.talkies.managers.MainActivityManager;
import com.talkies.model.UserDetails;
import com.talkies.model.recyclercategorytab.Datum;
import com.talkies.model.searchResult.SearchResultResponseList;
import com.talkies.model.searchitem.SearchDeatils;
import com.talkies.utils.CustomDialogClass;
import com.talkies.utils.GeneralMethods;
import com.talkies.utils.MyPreferenceManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;

import io.paperdb.Paper;

import static com.talkies.utils.Constants.STORAGE_USER_DETAILS_RESPONSE;

public class MovieItemDetailFragment extends Fragment implements MainActivityCallBack {

    Context mContext;
    private boolean pictureTaken = false;
    ProgressDialog mProgressDialog;
    boolean updateActivity = false;


    Gson gson;
    Datum mSearchItem;
    ImageView mImageBanner;

    TextView mItemTitle, mItemViewCount, mShortDescription, mSearchResultYear, mItemCategoryType1, mItemCategoryType2, mDetailCategoryDurationSearch, searchResultTypeIconSearch;
    AppCompatButton mButtonStartWatching, mButtonWatchingTrailer;
    AppCompatButton mButtonConnectTv;

    RecyclerView mStarCastList, mCreMembers;

    RecyclerCrewMemberAdapter recyclerCrewMemberAdapter;
    RecyclerStaringAdapter recyclerStaringAdapter;

    // SearchView search;
    MainActivityCallBack mainActivityCallBack;
    GeneralMethods mGeneralMethods;
    View viewMoreDetails, viewCrew;
    TextView mCastCrewButton, mMoreDetailsButton;
    LinearLayout mCrewLayout, mMoreLayout,subscribeLayout;

    MySubscriptionFragment mySubscriptionFragment;
    String trailerLink;
    String mediaURL,title;
    UserDetails storageUserDetails;
    private SimpleExoPlayer exoPlayer;

    private VideoView mVideoView;
    private TextView mBufferingTextView;
    ImageView screenImage;

    // Current playback position (in milliseconds).
    private int mCurrentPosition = 0;

    // Tag for the instance state bundle.
    private static final String PLAYBACK_TIME = "play_time";
    public MovieItemDetailFragment(Datum mSearchItem) {
        this.mSearchItem = mSearchItem;
    }
    MediaController controller;
    MyPreferenceManager prefManager;
    public MovieItemDetailFragment() {
        setRetainInstance(true);  //Hear put this line

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_details, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("zzz","movieItemDetailsFragment");

        mGeneralMethods = new GeneralMethods(this.getContext(), getActivity());
        mainActivityCallBack = this;
        gson = new Gson();
        prefManager= new MyPreferenceManager(getContext());
        mImageBanner = view.findViewById(R.id.imageViewDetailPageSearch);
        mItemTitle = view.findViewById(R.id.titleSearch);
        mItemViewCount = view.findViewById(R.id.viewCount);
        mStarCastList = view.findViewById(R.id.starCastList);
        mCreMembers = view.findViewById(R.id.crewMemberList);
        mVideoView = view.findViewById(R.id.videoview);
        mBufferingTextView = view.findViewById(R.id.buffering_textview);

        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(PLAYBACK_TIME);
        }

        subscribeLayout=view.findViewById(R.id.subscriptionLayout);
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
        new MainActivityManager(getActivity(), mainActivityCallBack,prefManager).getSearchDetails(mGeneralMethods, mSearchItem.getSlug());

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
//
//                if (storageUserDetails.getActive_subscriptions() == null) {
//                    CustomDialogClass cdd = new CustomDialogClass(getActivity());
//                    cdd.show();
//
////
//
//                } else {
//
//                    String url = "";
//                    String title = "";
//                    url =   mediaURL ;
////                    Uri uriOfContentUrl = Uri.parse(url);
////                    initializePlayer(uriOfContentUrl,title);
//                    if(mediaURL!=null)
//                    {
//                        player_view.setVideoPath(mediaURL);
//
//                        player_view.start();
//                        player_view.setVisibility(View.VISIBLE);
//                        subscribeLayout.setVisibility(View.GONE);
//                    }
//
//                }
                if(mediaURL!=null)
                {
                    mVideoView.setVideoPath(mediaURL);
                    mVideoView.start();
                    mVideoView.setVisibility(View.VISIBLE);
                    //subscribeLayout.setVisibility(View.GONE);
                    mImageBanner.setVisibility(View.GONE);
                }

//                Intent vd = new Intent(getActivity(), VideoPlayerActivity.class);
//                vd.putExtra("url",mediaURL);
//                startActivity(vd);


            }
        });

        mImageBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (storageUserDetails.getActive_subscriptions() == null) {
//                    CustomDialogClass cdd = new CustomDialogClass(getActivity());
//                    cdd.show();
//
////
//
//                } else {
//
//                    String url = "";
//                    String title = "";
//                    url =   mediaURL ;
////                    Uri uriOfContentUrl = Uri.parse(url);
////                    initializePlayer(uriOfContentUrl,title);
//                    if(mediaURL!=null)
//                    {
//                        mVideoView.setVideoPath(mediaURL);
//
//                        mVideoView.start();
//                        mVideoView.setVisibility(View.VISIBLE);
//                        //subscribeLayout.setVisibility(View.GONE);
//                        mImageBanner.setVisibility(View.GONE);
//                    }
//
//                }

                if(mediaURL!=null)
                {
                    mVideoView.setVideoPath(mediaURL);

                    mVideoView.start();
                    mVideoView.setVisibility(View.VISIBLE);
                    //subscribeLayout.setVisibility(View.GONE);
                    mImageBanner.setVisibility(View.GONE);
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
        initializePlayer();
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onSuccessSearchIemDetailResult(JSONObject result) {
        Type collectionType = new TypeToken<SearchDeatils>() {
        }.getType();

        SearchDeatils searchItem = gson.fromJson(result.toString(), collectionType);
        Log.e("rental: ", searchItem.toString());
        mItemTitle.setText(searchItem.getTitle());
        String count=searchItem.getViewsCount()+" Views";
        mItemViewCount.setText(count);
        mShortDescription.setText(searchItem.getShortDescription());
        mDetailCategoryDurationSearch.setText(searchItem.getRunTime().toString());
        mSearchResultYear.setText(searchItem.getReleaseYear().toString());
        if (searchItem.getLanguages().size() > 1) {
            mItemCategoryType1.setText(searchItem.getLanguages().get(0).getName());
            mItemCategoryType2.setText(searchItem.getLanguages().get(1).getName());
        }

        searchResultTypeIconSearch.setText(searchItem.getCertificates().get(0).getTitle());
        Picasso
                .get()
                .load(searchItem.getBanner())
                .into(mImageBanner);

        trailerLink = searchItem.getTrailerLink();
        mediaURL= searchItem.getMediaUrl();
        title=searchItem.getMediaUrl();
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



    @Override
    public void onStart() {
        super.onStart();

        // Load the media each time onStart() is called.



        // Set up the media controller widget and attach it to the video view.
        controller = new MediaController(getContext());
        controller.setMediaPlayer(mVideoView);
        mVideoView.setMediaController(controller);

    }

    @Override
    public void onPause() {
        super.onPause();

        // In Android versions less than N (7.0, API 24), onPause() is the
        // end of the visual lifecycle of the app.  Pausing the video here
        // prevents the sound from continuing to play even after the app
        // disappears.
        //
        // This is not a problem for more recent versions of Android because
        // onStop() is now the end of the visual lifecycle, and that is where
        // most of the app teardown should take place.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            mVideoView.pause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        // Media playback takes a lot of resources, so everything should be
        // stopped and released at this time.
        releasePlayer();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current playback position (in milliseconds) to the
        // instance state bundle.
        outState.putInt(PLAYBACK_TIME, mVideoView.getCurrentPosition());
    }

    private void initializePlayer() {
        // Show the "Buffering..." message while the video loads.
        mBufferingTextView.setVisibility(VideoView.VISIBLE);

        // Buffer and decode the video sample.
        Uri videoUri = getMedia(mediaURL);
        mVideoView.setVideoURI(videoUri);

        // Listener for onPrepared() event (runs after the media is prepared).
        mVideoView.setOnPreparedListener(
                new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {

                        // Hide buffering message.
                        mBufferingTextView.setVisibility(VideoView.INVISIBLE);

                        // Restore saved position, if available.
                        if (mCurrentPosition > 0) {
                            mVideoView.seekTo(mCurrentPosition);
                        } else {
                            // Skipping to 1 shows the first frame of the video.
                            mVideoView.seekTo(1);
                        }

                        // Start playing!
                        mVideoView.start();
                    }
                });

        // Listener for onCompletion() event (runs after media has finished
        // playing).
        mVideoView.setOnCompletionListener(
                new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {


                        // Return the video position to the start.
                        mVideoView.seekTo(0);
                    }
                });
    }


    // Release all media-related resources. In a more complicated app this
    // might involve unregistering listeners or releasing audio focus.
    private void releasePlayer() {
        mVideoView.stopPlayback();
    }

    // Get a Uri for the media sample regardless of whether that sample is
    // embedded in the app resources or available on the internet.
    private Uri getMedia(String mediaName) {
        if (URLUtil.isValidUrl(mediaName)) {
            // Media name is an external URL.
            return Uri.parse(mediaName);
        } else {
            // Media name is a raw resource embedded in the app.
            return Uri.parse("android.resource://" + getContext().getPackageName() +
                    "/raw/" + mediaName);
        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mVideoView.setMediaController(controller);
            mVideoView.setVisibility(View.VISIBLE);
            subscribeLayout.setVisibility(View.GONE);

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){

            mVideoView.setMediaController(controller);
            mVideoView.setVisibility(View.VISIBLE);
            subscribeLayout.setVisibility(View.VISIBLE);
        }
    }
}
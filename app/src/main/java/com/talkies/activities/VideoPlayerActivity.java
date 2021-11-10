package com.talkies.activities;

import static android.media.session.PlaybackState.STATE_BUFFERING;
import static com.google.android.exoplayer2.Player.STATE_READY;
import static com.google.android.exoplayer2.util.Util.getUserAgent;
import static com.talkies.utils.Constants.STORAGE_USER_DETAILS_RESPONSE;

import android.app.MediaRouteActionProvider;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.mediarouter.app.MediaRouteButton;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.ext.cast.CastPlayer;
import com.google.android.exoplayer2.ext.cast.SessionAvailabilityListener;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.hls.DefaultHlsDataSourceFactory;
import com.google.android.exoplayer2.source.hls.HlsDataSourceFactory;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.CastMediaControlIntent;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.cast.framework.CastButtonFactory;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.talkies.R;
import com.talkies.adapters.NewSeriesAdapter;
import com.talkies.adapters.RecyclerCrewMemberAdapter;
import com.talkies.adapters.RecyclerStaringAdapter;
import com.talkies.adapters.SeasonAdapter;
import com.talkies.callbacks.MainActivityCallBack;
import com.talkies.managers.MainActivityManager;
import com.talkies.model.Header;
import com.talkies.model.SeriesModel;
import com.talkies.model.ShowModel;
import com.talkies.model.UserDetails;
import com.talkies.model.searchitem.SearchDeatils;
import com.talkies.utils.AppURLs;
import com.talkies.utils.CustomDialogClass;
import com.talkies.utils.GeneralMethods;
import com.talkies.utils.MyPreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.CookieHandler;
import java.net.CookiePolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.paperdb.Paper;
import kotlin.jvm.internal.Intrinsics;

public class VideoPlayerActivity extends AppCompatActivity implements MainActivityCallBack, ExoPlayer.EventListener {

    private MediaRouter mMediaRouter;
    private MediaRouteSelector mMediaRouteSelector;
    private MediaRouter.Callback mMediaRouterCallback;
    private CastDevice mSelectedDevice;
    private GoogleApiClient mApiClient;
    private RemoteMediaPlayer mRemoteMediaPlayer;
    private Cast.Listener mCastClientListener;
    private boolean mWaitingForReconnect = false;
    private boolean mApplicationStarted = false;
    private boolean mVideoIsLoaded;
    private boolean mIsPlaying;
    private CastPlayer castPlayer;
    String castTitle, type, slug, pid, type_;

    ArrayList<ShowModel> itemCategoryArrayList = new ArrayList<>();



    private ExtractorsFactory extractorsFactory;
    private DefaultTrackSelector defaultTrackSelector;


    private static final String PLAYBACK_TIME = "play_time";
    public static Toolbar toolAppBar;
    public static TextView toolBarText;
    private final String m3 = "https://multiplatform-f.akamaihd.net/i/multi/april11/sintel/sintel-hd_,512x288_450_b,640x360_700_b,768x432_1000_b,1024x576_1400_m,.mp4.csmil/master.m3u8";
    private final String VIDEO_SAMPLE = "https://developers.google.com/training/images/tacoma_narrows.mp4";
    Context mContext;
    MediaController controller;
    Gson gson;
    String searchItemSlug;
    ImageView mImageBanner;
    TextView mItemTitle, mItemViewCount, mShortDescription, mSearchResultYear, mItemCategoryType1, mItemCategoryType2, mDetailCategoryDurationSearch, searchResultTypeIconSearch, textHeader;
    Button mButtonStartWatching, mButtonWatchingTrailer;
    MediaRouteButton mButtonConnectTv;
    RecyclerView mStarCastList, mCreMembers, recycler_season;
    RecyclerCrewMemberAdapter recyclerCrewMemberAdapter;
    RecyclerStaringAdapter recyclerStaringAdapter;
    SeasonAdapter seasonAdapter;
    NewSeriesAdapter seriesAdapter;
    MainActivityCallBack mainActivityCallBack;
    GeneralMethods mGeneralMethods;
    View viewMoreDetails, viewCrew;
    TextView mCastCrewButton, mMoreDetailsButton;
    LinearLayout mCrewLayout, mMoreLayout, subscribeLayout;
    String trailerLink;
    String mediaURL, title, castUrl;
    UserDetails storageUserDetails;
    MyPreferenceManager prefManager;
    SimpleExoPlayer player;
    PlayerView playerView;
    ImageView fullscreenButton;
    boolean fullscreen = false;
    ProgressBar progressBar;
    DataSource.Factory dataSourceFactory;
    MyPreferenceManager myPreferenceManager;
    private String cookies;
    //    private VideoView mVideoView;
    private TextView mBufferingTextView;
    private int mCurrentPosition = 0;
    private HlsMediaSource hlsMediaSource;
    private ExtractorMediaSource extractorMediaSource;
    java.net.CookieManager cookieManager;
    private List seasonList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search_details);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        CastContext.getSharedInstance(this);
        toolAppBar = findViewById(R.id.topAppBar);
        toolBarText = findViewById(R.id.tv_toolbar_title);
        setSupportActionBar(toolAppBar);
        toolBarText.setText("Talkies");
        myPreferenceManager = new MyPreferenceManager(this);
//        mVideoView = findViewById(R.id.videoview);
        mBufferingTextView = findViewById(R.id.buffering_textview);
        playerView = findViewById(R.id.player_view);
        fullscreenButton = findViewById(R.id.exo_fullscreen_icon);
        progressBar = findViewById(R.id.progress_player);
        cookies = myPreferenceManager.getCookies();
        cookieManager = new java.net.CookieManager();
        type = getIntent().getStringExtra("type");
        slug = getIntent().getStringExtra("slug");


        Log.d("zzz", "type:  " + type);


        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);


        if (CookieHandler.getDefault() != cookieManager) {
            CookieHandler.setDefault(cookieManager);
        }

        Log.d("zzz", "cookies:    " + cookies);


        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(PLAYBACK_TIME);
        }
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.searchItemSlug = extras.getString("slug");
        }
//        controller = new MediaController(this);
//        controller.setAnchorView(mVideoView);

//        controller.setMediaPlayer(mVideoView);
        prefManager = new MyPreferenceManager(this);
        mGeneralMethods = new GeneralMethods(this, this);
        mainActivityCallBack = this;
        gson = new Gson();
        mImageBanner = findViewById(R.id.imageViewDetailPageSearch);
        mItemTitle = findViewById(R.id.titleSearch);
        mItemViewCount = findViewById(R.id.viewCount);
        mStarCastList = findViewById(R.id.starCastList);
        mCreMembers = findViewById(R.id.crewMemberList);
        recycler_season = findViewById(R.id.recycler_season);
        textHeader = findViewById(R.id.textHeader);
//        mVideoView = findViewById(R.id.videoview);
        mBufferingTextView = findViewById(R.id.buffering_textview);
        mBufferingTextView.setVisibility(View.GONE);
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(PLAYBACK_TIME);
        }
        subscribeLayout = findViewById(R.id.subscriptionLayout);
//        viewMoreDetails = findViewById(R.id.moreDetailsView);
//        viewCrew = findViewById(R.id.castAndCrewView);
        mCastCrewButton = findViewById(R.id.castAndCrewButton);
        mMoreDetailsButton = findViewById(R.id.viewMoreButton);
        mMoreLayout = findViewById(R.id.more_item_details);
        mCrewLayout = findViewById(R.id.cast_crew);
        mShortDescription = findViewById(R.id.short_description_detailSearch);
        mSearchResultYear = findViewById(R.id.searchResultYearSearch);
        mItemCategoryType1 = findViewById(R.id.detailCategoryType1Search);
        mItemCategoryType2 = findViewById(R.id.detailCategoryType2Search);
        mDetailCategoryDurationSearch = findViewById(R.id.detailCategoryDurationSearch);
        searchResultTypeIconSearch = findViewById(R.id.searchResultType_iconSearch);
        mButtonStartWatching = findViewById(R.id.buttonStartWatchingSearch);
        mButtonConnectTv = findViewById(R.id.buttonConnectTVSearch);
        mButtonWatchingTrailer = findViewById(R.id.buttonWatchTrailerSearch);
        Paper.book().read(STORAGE_USER_DETAILS_RESPONSE);
        storageUserDetails = Paper.book().read(STORAGE_USER_DETAILS_RESPONSE);
        setMenuVisibilityCrewView();
        new MainActivityManager(this, mainActivityCallBack, prefManager).getSearchDetails(mGeneralMethods, searchItemSlug);
        mMoreDetailsButton.setOnClickListener(v -> setMenuVisibilityMoreView());
        mCastCrewButton.setOnClickListener(v -> setMenuVisibilityCrewView());
        mButtonWatchingTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trailerLink != null) {
                    showTrailer(trailerLink);

                } else {
                    Toast.makeText(getApplicationContext(), "Sorry Link is not available", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mButtonStartWatching.setOnClickListener(v -> {
            if (mediaURL != null) {
                Log.d("videourl", "videolink " + mediaURL);
                ExoPlayer(mediaURL);
                mImageBanner.setVisibility(View.GONE);
            } else {
                CustomDialogClass cdd = new CustomDialogClass(VideoPlayerActivity.this);
                cdd.show();
            }

        });

//        initMediaRouter();
        mButtonConnectTv.setOnClickListener(this::onClick);
        CastButtonFactory.setUpMediaRouteButton(this, mButtonConnectTv);


    }

    private void initPlayer(String mediaURL) {


        castPlayer = new CastPlayer(CastContext.getSharedInstance(this));

        castPlayer.setSessionAvailabilityListener(new SessionAvailabilityListener() {
            @Override
            public void onCastSessionAvailable() {


                castPlayer.loadItem(buildMediaQueueItem(VideoPlayerActivity.this.mediaURL), 0);
            }

            @Override
            public void onCastSessionUnavailable() {

            }

        });


//        Uri uri = Uri.parse(mediaURL);

//        DefaultHttpDataSourceFactory httpDataSourceFactory = new DefaultHttpDataSourceFactory(Util.getUserAgent((Context) this, "exoplayer"));
//        DefaultHlsDataSourceFactory hlsDataSourceFactory = new DefaultHlsDataSourceFactory((DataSource.Factory) httpDataSourceFactory);
//        com.google.android.exoplayer2.source.hls.HlsMediaSource.Factory hlsMediaSourceFactory = new com.google.android.exoplayer2.source.hls.HlsMediaSource.Factory((HlsDataSourceFactory) hlsDataSourceFactory);
//        HlsMediaSource hlsMediaSource = hlsMediaSourceFactory.createMediaSource(uri);
//        SimpleExoPlayer var16 = this.player;
//        if (var16 != null) {
//            var16.prepare((MediaSource) hlsMediaSource);
//        }
////
//        var16 = this.player;
//        if (var16 != null) {
//            var16.setPlayWhenReady(true);
//        }
//
//        var16 = this.player;
//        if (var16 != null) {
//            var16.seekTo(0L);
//        }
    }

    private final DefaultTrackSelector.Parameters disableClosedCaptionParams() {
        return (new DefaultTrackSelector.ParametersBuilder()).setRendererDisabled(2, true).clearSelectionOverrides().build();
    }

    private final MediaQueueItem buildMediaQueueItem(String video) {

        Map headers = new HashMap();
        headers.put("Cookie", cookies);
        MediaMetadata movieMetadata = new MediaMetadata(1);
        movieMetadata.putString("com.google.android.gms.cast.metadata.TITLE", castTitle);
        MediaInfo mediaInfo = (new MediaInfo.Builder(Uri.parse(video).toString(), cookies)).setStreamType(1).setContentType("application/x-mpegURL").setMetadata(movieMetadata).build();

        MediaQueueItem mediaQueueItem = (new com.google.android.gms.cast.MediaQueueItem.Builder(mediaInfo)).build();

        Intrinsics.checkExpressionValueIsNotNull(mediaQueueItem, "MediaQueueItem.Builder(mediaInfo).build()");
        return mediaQueueItem;
    }


    @Override
    protected void onResume() {
        super.onResume();
//        if ((Util.SDK_INT <= 23 || player == null)) {
//            initPlayer();
//        }
        // Start media router discovery
//        mMediaRouter.addCallback( mMediaRouteSelector, mMediaRouterCallback, MediaRouter.CALLBACK_FLAG_PERFORM_ACTIVE_SCAN );
    }


    private void ExoPlayer(String mediaURL) {
        player = ExoPlayerFactory.newSimpleInstance(getApplicationContext());
        playerView.setVisibility(View.VISIBLE);
        fullscreenButton.setVisibility(View.VISIBLE);
        fullscreenButton.setOnClickListener(view -> {
            if (fullscreen) {
                fullscreenButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_fullscreen_open));
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                if (getSupportActionBar() != null) {
                    getSupportActionBar().show();
                }
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) playerView.getLayoutParams();
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                params.height = (int) (300 * getApplicationContext().getResources().getDisplayMetrics().density);
                playerView.setLayoutParams(params);
                fullscreen = false;
            } else {
                fullscreenButton.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_fullscreen_close));
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

                if (getSupportActionBar() != null) {
                    getSupportActionBar().hide();
                }
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) playerView.getLayoutParams();
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                params.height = ViewGroup.LayoutParams.MATCH_PARENT;
                playerView.setLayoutParams(params);
                fullscreen = true;
            }
        });
        playerView.setPlayer(player);
        playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIXED_HEIGHT);
        dataSourceFactory = new DefaultHttpDataSourceFactory();
        ((DefaultHttpDataSourceFactory) dataSourceFactory).setDefaultRequestProperty("Cookie", cookies);


//        Log.d("videourl", "videolink " + mediaURL);
        if (mediaURL.contains(".m3u8")) {
            Log.d("zzz", "videolink 111 " + mediaURL);

            hlsMediaSource = new HlsMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(mediaURL));
            player.prepare(hlsMediaSource);
            player.setPlayWhenReady(true);
        } else {
            Log.d("zzz", "videolink  " + mediaURL);

            extractorMediaSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(mediaURL));
            player.prepare(extractorMediaSource);
            player.setPlayWhenReady(true);
//        } else {
//            hlsMediaSource = new HlsMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(mediaURL));
//            player.prepare(hlsMediaSource);
//            player.setPlayWhenReady(true);
        }

//       HlsMediaSource hlsMediaSource =
//                new HlsMediaSource.Factory(dataSourceFactory)
//                        .createMediaSource(MediaItem.fromUri(mediaURL));
////                        .createMediaSource(MediaItem.fromUri("https://talkiesapp.akamaized.net/prod/videos/2020/movies/garva/hls/720p.m3u8?hdnts=st=1634481021~exp=1634481521~acl=/prod/videos/2020/movies/garva/hls/*~id=eccirjsbzp4lzg019vnh6przapq1us3b~hmac=40186745c4dbc0344515279d5b3f3e86e1db4eee9e186a5c54ba940c541b0f08"));
//        player.prepare(hlsMediaSource);
//        player.setPlayWhenReady(true);
        player.addListener(new Player.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, int reason) {

            }

            @Override
            public void onTimelineChanged(Timeline timeline, @Nullable Object manifest, int reason) {

            }

            @Override
            public void onMediaItemTransition(@Nullable MediaItem mediaItem, int reason) {

            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

            }

            @Override
            public void onIsLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

            }

            @Override
            public void onPlaybackStateChanged(int state) {

            }

            @Override
            public void onPlayWhenReadyChanged(boolean playWhenReady, int reason) {
                switch (reason) {
                    case Player.STATE_ENDED:

                        progressBar.setVisibility(View.VISIBLE);

                    case STATE_BUFFERING:
                        progressBar.setVisibility(View.VISIBLE);
                        break;

                    case STATE_READY:
                        progressBar.setVisibility(View.GONE);
                        break;

                    case Player.STATE_IDLE:

                        break;
                }
            }

            @Override
            public void onPlaybackSuppressionReasonChanged(int playbackSuppressionReason) {

            }

            @Override
            public void onIsPlayingChanged(boolean isPlaying) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {

            }

            @Override
            public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {
                Toast.makeText(getApplicationContext(), "" + error, Toast.LENGTH_SHORT).show();
                Log.d("error", "errorshow " + error);
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onPositionDiscontinuity(int reason) {

            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

            }

            @Override
            public void onSeekProcessed() {

            }

            @Override
            public void onExperimentalOffloadSchedulingEnabledChanged(boolean offloadSchedulingEnabled) {

            }
        });
    }

    private void showTrailer(String trailerLink) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(trailerLink));
        intent.setPackage("com.google.android.youtube");
        startActivity(intent);
    }

    private void setMenuVisibilityCrewView() {
//        viewMoreDetails.setVisibility(View.GONE);
//        viewCrew.setVisibility(View.VISIBLE);
        mMoreLayout.setVisibility(View.GONE);
        mCrewLayout.setVisibility(View.VISIBLE);
    }

    private void setMenuVisibilityMoreView() {
//        viewMoreDetails.setVisibility(View.VISIBLE);
//        viewCrew.setVisibility(View.GONE);
        mCrewLayout.setVisibility(View.GONE);
        mMoreLayout.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        initializePlayer();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        player.setPlayWhenReady(false);

        if (isFinishing()) {
            // End media router discovery
//                mMediaRouter.removeCallback(mMediaRouterCallback);
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
//            mVideoView.pause();
        }
    }

    private void onClick(View v) {
        Log.d("zzz", "button clicked");
    }


    @Override
    public void onDestroy() {
        if (player != null) {
            player.release();
        }
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();

//        releasePlayer();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

//        outState.putInt(PLAYBACK_TIME, mVideoView.getCurrentPosition());
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
        castTitle = searchItem.getTitle();
        castUrl = (String) searchItem.getChromecastMediaUrl();


        if (type.equals("series")) {
            textHeader.setVisibility(View.VISIBLE);
            mStarCastList.setVisibility(View.GONE);
            mCreMembers.setVisibility(View.GONE);
        } else {
            textHeader.setVisibility(View.GONE);
            recycler_season.setVisibility(View.GONE);
            mStarCastList.setVisibility(View.VISIBLE);
            mCreMembers.setVisibility(View.VISIBLE);
        }

        Log.d("zzz", "Cast Url:  " + castUrl);

        mItemViewCount.setText(searchItem.getViewsCount().toString());
//        mShortDescription.setText(searchItem.getShortDescription());
        if (searchItem.getRunTime() != null) {
            mDetailCategoryDurationSearch.setText(searchItem.getRunTime().toString());

        }
        mSearchResultYear.setText(searchItem.getReleaseYear().toString());
        if (searchItem.getLanguages().size() != 0) {
            if (searchItem.getLanguages().size() > 1) {
                mItemCategoryType1.setText(searchItem.getLanguages().get(0).getName());
                mItemCategoryType2.setText(searchItem.getLanguages().get(1).getName());
            } else {
                mItemCategoryType1.setText("NA");
                mItemCategoryType2.setText("NA");
            }
        }

        if (searchItem.getCertificates().size() != 0) {
            searchResultTypeIconSearch.setText(searchItem.getCertificates().get(0).getTitle());

        } else {
            searchResultTypeIconSearch.setText("NA");
        }
        Picasso.get()
                .load(searchItem.getBanner())
                .into(mImageBanner);

        trailerLink = searchItem.getTrailerLink();
        mediaURL = searchItem.getMediaUrl();
        castUrl = (String) searchItem.getChromecastMediaUrl();


        Log.d("zzz", "cast url" + castUrl);

//        ExoPlayer(mediaURL);


        title = searchItem.getMediaUrl();
        recyclerCrewMemberAdapter = new RecyclerCrewMemberAdapter(this, searchItem.getOtherCrewMembers());
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

        seriesAdapter = new NewSeriesAdapter(getApplicationContext(), itemCategoryArrayList);
        RecyclerView.LayoutManager seasonManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recycler_season.setLayoutManager(seasonManager);
        recycler_season.setHasFixedSize(false);
        recycler_season.setNestedScrollingEnabled(true);
//        recycler_season.setAdapter(seriesAdapter);
//        recycler_season.setItemAnimator(new DefaultItemAnimator());
        seriesAdapter.notifyDataSetChanged();


        load_Data(slug);

        if ((Util.SDK_INT <= 23 || player == null)) {
            initPlayer(mediaURL);
        }

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
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            mVideoView.setMediaController(controller);
//            mVideoView.setVisibility(View.VISIBLE);
//            subscribeLayout.setVisibility(View.GONE);

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {

//            mVideoView.setMediaController(controller);
//            mVideoView.setVisibility(View.VISIBLE);
//            subscribeLayout.setVisibility(View.VISIBLE);
        }
    }


    void load_Data(String slug) {
        StringRequest request = new StringRequest(Request.Method.GET, AppURLs.BASE_URL + "media/" + slug + "/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("zzz", "VideoPlayerActivity:   " + response);
                if (!response.equals(null)) {
//                    ArrayList<SeriesModel> itemCategoryArrayList = new ArrayList<>();
//                    seasonAdapter = new SeasonAdapter(getApplicationContext(), itemCategoryArrayList);

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        SeriesModel seriesModel = new SeriesModel();
                        seriesModel.setTitle(jsonObject.getString("title"));

                        JSONArray jsonArray_sub_media_list = jsonObject.getJSONArray("sub_media_list");
                        if (jsonArray_sub_media_list.length() >= 0) {

                            for (int i = 0; i < jsonArray_sub_media_list.length(); i++) {
                                JSONObject jsonObject_subMediaList = jsonArray_sub_media_list.getJSONObject(i);
                                Log.d("zzz", "title:   " + jsonObject.getString("title"));
                                Log.d("bb", "banner    :   " + jsonObject_subMediaList.getString("banner"));
                                pid = jsonObject.getString("pid");
                                Log.d("fff", "Pid:    " + jsonObject.getString("pid"));
                                JSONObject jsonObjectvideo_section_type = jsonObject.getJSONObject("video_section_type");
                                type_ = jsonObjectvideo_section_type.getString("slug");
//                                itemCategoryArrayList.add(seriesModel);
                            }
//                            recycler_season.setItemAnimator(new DefaultItemAnimator());
//                            recycler_season.setAdapter(seasonAdapter);
//                            seasonAdapter.notifyDataSetChanged();
                            load_episode(type,pid);


                        } else {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Nothing to show",
                                    Toast.LENGTH_SHORT
                            ).show();
//                            HomeActivity.progressBar.visibility = View.GONE
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    Log.e("Your Array Response", "Data Null");
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {

            //This is for Headers If You Needed
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("Cookie", cookies.toString());
                return headers;
            }

            //Pass Your Parameters here
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("User", UserName);
//                params.put("Pass", PassWord);
//                return params;
//            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

    void load_episode(String type,String pid) {
        StringRequest request = new StringRequest(Request.Method.GET, AppURLs.BASE_URL + "media/list/?video_section_type="+type+"&pid="+pid+"&page=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("zzz","VideoPlayerActivity: Episodes   "+ response);
                    try {
                        JSONObject jsonObject0 = new JSONObject(response);
                        JSONArray jsonArraysResult = jsonObject0.getJSONArray("results");
                        for (int i = 0; i < jsonArraysResult.length(); i++) {
                            JSONObject jsonObject = jsonArraysResult.getJSONObject(i);
                            ShowModel showModel = new ShowModel();
                            showModel.setTitle(jsonObject.getString("title"));


                            if (jsonObject.has("genres")) {
                                JSONArray jsonArrayGen = jsonObject.getJSONArray("genres");
                                for (int j = 0; j < 1; j++) {


                                }
                            }
                            JSONArray jsonArray_certificates = jsonObject.getJSONArray("certificates");
                            for (int k=0; k<jsonArray_certificates.length();k++) {
                                JSONObject jsonObjectjsonArrayGen_certificates = jsonArray_certificates.getJSONObject(k);
//                                HomeActivity.gridtvUa.setText(jsonObjectjsonArrayGen_certificates.getString("title"))
                            }
                            JSONArray jsonArray_sub_media_list = jsonObject.getJSONArray("sub_media_list");
                            if (jsonArray_sub_media_list.length()>0) {
                                for (int l = 0; l < jsonArray_sub_media_list.length(); l++) {

                                    Log.d("ccc","loop Called");
                                    JSONObject jsonObject_subMediaList = jsonArray_sub_media_list.getJSONObject(l);


                                    showModel.setType("");
                                    Log.d("zzz", "title: 111   " + jsonObject.getString("title"));

                                    showModel.setSubmideaTitle(jsonObject_subMediaList.getString("title"));
                                    showModel.setSubMediaBanner(jsonObject_subMediaList.getString("banner"));
                                    showModel.setSubMideabigbanner(
                                            jsonObject_subMediaList.getString("big_banner"));
                                    showModel.setShort_Midea_description(
                                            jsonObject_subMediaList.getString("short_description"));
                                    showModel.setSubMideaslug( jsonObject_subMediaList.getString("slug"));
                                    showModel.setSubmideaTitle(
                                            jsonObject_subMediaList.getString("season_title"));
                                    showModel.setRun_time("N/A");
//                                    showModel.subMideaslug = jsonObject.getString("slug")
//                                    HomeActivity.gridtvTime.setText("\u2022"+" "+jsonObject.getString("run_time") + "min")
//                                    HomeActivity.gridtvYear.setText(jsonObject.getString("release_year"))
//                        HomeActivity.gridtvUa.text="\u2022"+" "
                                    showModel.setRelease_year(jsonObject.getString("release_year"));
                                    Log.d("fff", "Pid:    " + jsonObject.getString("pid"));
                                    showModel.setEpisode_title(jsonObject_subMediaList.getString("episode_title"));



                                }
                                itemCategoryArrayList.add(showModel);

                            }
                            recycler_season.setAdapter(seriesAdapter);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {

            //This is for Headers If You Needed
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("Cookie", cookies.toString());
                return headers;
            }

            //Pass Your Parameters here
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("User", UserName);
//                params.put("Pass", PassWord);
//                return params;
//            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
}


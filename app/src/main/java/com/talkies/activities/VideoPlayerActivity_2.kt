package com.talkies.activities

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ext.cast.CastPlayer
import com.google.android.exoplayer2.source.hls.DefaultHlsDataSourceFactory
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.MimeTypes
import com.google.android.exoplayer2.util.Util
import com.google.android.gms.cast.MediaInfo
import com.google.android.gms.cast.MediaMetadata
import com.google.android.gms.cast.MediaQueueItem
import com.google.android.gms.cast.framework.CastContext
import com.talkies.R
import com.talkies.model.ShowModel
import com.talkies.utils.AppURLs
import com.talkies.utils.MyPreferenceManager
import org.json.JSONException
import org.json.JSONObject

class VideoPlayerActivity_2 : AppCompatActivity() {
    var myPreferenceManager: MyPreferenceManager? = null
    var slug:String=""
    var pid:String=""
    var type:String=""
    private var cookies: String? = null
    private lateinit var castPlayer: CastPlayer
    private var player: SimpleExoPlayer? = null
    val videoString = "http://cbsnewshd-lh.akamaihd.net/i/CBSNHD_7@199302/index_700_av-p.m3u8"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player_2)

//        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
//        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        invalidateOptionsMenu()
        CastContext.getSharedInstance(this)
        myPreferenceManager = MyPreferenceManager(this)
        cookies = myPreferenceManager!!.cookies
        slug= intent.getStringExtra("slug")!!
        Log.d("zzz","videoPlayerActivity2:  "+slug)

        loadData1(slug)
    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            initPlayer()
        }
    }

    override fun onResume() {
        super.onResume()
//        hideSystemUi()
        if ((Util.SDK_INT <= 23 || player == null)) {
            initPlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }

    private fun releasePlayer() {
        if (player != null) {
            player?.release()
            player = null
        }
    }

//    private fun hideSystemUi() {
//        player.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
//                or View.SYSTEM_UI_FLAG_FULLSCREEN
//                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
//    }
    private fun initPlayer() {
        player = ExoPlayerFactory.newSimpleInstance(
                requireNotNull(this),
                DefaultTrackSelector().apply {
                    parameters = disableClosedCaptionParams()
                })

        player = player
        castPlayer = CastPlayer(CastContext.getSharedInstance(this))

//        castPlayer.setSessionAvailabilityListener(object : CastPlayer.SessionAvailabilityListener {
//            override fun onCastSessionAvailable() {
//                castPlayer.loadItem(buildMediaQueueItem(videoString),0)
//            }
//            override fun onCastSessionUnavailable(){
//                //  = viewModel.onCastingStateChanged(false, castPlayer.currentPosition)
//
//            }
//        })

        val uri = Uri.parse(videoString)
        val httpDataSourceFactory = DefaultHttpDataSourceFactory(Util.getUserAgent(this, "exoplayer"))
        val hlsDataSourceFactory = DefaultHlsDataSourceFactory(httpDataSourceFactory)
        val hlsMediaSourceFactory = HlsMediaSource.Factory(hlsDataSourceFactory)
        val hlsMediaSource = hlsMediaSourceFactory.createMediaSource(uri)


        player?.prepare(hlsMediaSource)
        player?.playWhenReady = true
        player?.seekTo(0)

    }
    private fun buildMediaQueueItem(video: String): MediaQueueItem {
        val movieMetadata = MediaMetadata(MediaMetadata.MEDIA_TYPE_MOVIE)
        movieMetadata.putString(MediaMetadata.KEY_TITLE, "CBSN News")
        val mediaInfo = MediaInfo.Builder(Uri.parse(video).toString())
                .setStreamType(MediaInfo.STREAM_TYPE_BUFFERED).setContentType(MimeTypes.APPLICATION_M3U8)
                .setMetadata(movieMetadata).build()
        return MediaQueueItem.Builder(mediaInfo).build()
    }
    private fun disableClosedCaptionParams() = DefaultTrackSelector.ParametersBuilder()
            .setRendererDisabled(TRACK_TEXT, true)
            .clearSelectionOverrides()
            .build()

    companion object {
        // TODO Determine why 2. Reference: https://stackoverflow.com/questions/42432371/how-to-turn-on-off-closed-captions-in-hls-streaming-url-in-exoplayer
        private const val TRACK_TEXT = 2
    }

    fun loadData1(slug: String) {
        Log.d("zzz","VideoPlayerActivity_2:   Method Called")
        try {
//            HomeActivity.details_layout.visibility=View.GONE
            val stringRequest = object : StringRequest(
                    Method.GET, AppURLs.BASE_URL + "media/" + slug + "/",
                    Response.Listener { response ->
                        Log.e("VideoPlayerActivity2", "$response" + "slug " + slug)
                        Log.e("TAG Show Fragment", "slug " + slug)
                        val jsonObject = JSONObject(response)
                        Log.d("bbb", "category  " + response)
                        val jsonArray_sub_media_list = jsonObject.getJSONArray("sub_media_list")
                        if (jsonArray_sub_media_list.length() >= 0) {

                            for (i in 0 until jsonArray_sub_media_list.length()) {
//                        row++
                                val jsonObject_subMediaList = jsonArray_sub_media_list.getJSONObject(i)
//                            cardPresenter_Header = HeaderItem(jsonObject.getString("title"))
                                Log.d("zzz", "title:   " + jsonObject.getString("title"))
                                Log.d("bb", "banner    :   " + jsonObject_subMediaList.getString("banner"))
                                 pid = jsonObject.getString("pid")
                                Log.d("fff", "Pid:    " + jsonObject.getString("pid"))
                                val jsonObjectvideo_section_type = jsonObject.getJSONObject("video_section_type")
                                type = jsonObjectvideo_section_type.getString("slug")
                            }
                            loadEpisodes(type,pid)


                        } else {
//                        Toast.makeText(
//                                activity,
//                                "Nothing to show",
//                                Toast.LENGTH_SHORT
//                        ).show()
//                            HomeActivity.progressBar.visibility = View.GONE
                        }
                        Log.d("fff", "method called")

                    }, Response.ErrorListener {
                Toast.makeText(
                        applicationContext,
                        "Something went wrong.",
                        Toast.LENGTH_SHORT
                ).show()
//                HomeActivity.progressBar.visibility = View.GONE

            }) {
                override fun getHeaders(): Map<String, String> {
                    val headers = HashMap<String, String>()
                    headers["Accept"] = "application/json"
                    headers["Cookie"] = cookies.toString()
                    return headers
                }
            }
            val requestQueue = Volley.newRequestQueue(applicationContext)
            requestQueue.add(stringRequest)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }


    fun loadEpisodes(type: String,pid:String) {
        try {

            val stringRequest = object : StringRequest(
                    Method.GET, AppURLs.BASE_URL + "media/list/?video_section_type="+type+"&pid="+pid+"&page=1",
                    Response.Listener { response ->
                        Log.e("bbn", "EpisodeData +tyep:  "+type+"  pid+  "+pid)
                        val jsonObject0 = JSONObject(response)
                        Log.d("bbb", "EpisodeResponse  " + response)
                        val jsonArraysResult=jsonObject0.getJSONArray("results")
                        for (i in 0 until jsonArraysResult.length()) {
                            val jsonObject=jsonArraysResult.getJSONObject(i)
                            if (jsonObject.has("genres")) {
                                val jsonArrayGen = jsonObject.getJSONArray("genres")
                                for (i in 0 until 1) {
                                    val jsonObjectGen = jsonArrayGen.getJSONObject(i)
//                                    HomeActivity.gridtvGenre.text = "\u2022" + jsonObjectGen.getString("title")
                                }
                            }
                            val jsonArray_certificates = jsonObject.getJSONArray("certificates")
                            for (i in 0 until jsonArray_certificates.length()) {
                                val jsonObjectjsonArrayGen_certificates = jsonArray_certificates.getJSONObject(i)
//                                HomeActivity.gridtvUa.setText(jsonObjectjsonArrayGen_certificates.getString("title"))
                            }
                            val jsonArray_sub_media_list = jsonObject.getJSONArray("sub_media_list")
//                            cardRow_Adapter = ArrayObjectAdapter(cardPresenter)

                            if (jsonArray_sub_media_list.length() >= 0) {
                                for (k in 0 until jsonArray_sub_media_list.length()) {
//                                    row++
                                    val jsonObject_subMediaList = jsonArray_sub_media_list.getJSONObject(k)
//                                    cardPresenter_Header = HeaderItem(jsonObject.getString("title"))
                                    Log.d("zzz", "title:   " + jsonObject.getString("title"))
                                    val showModel = ShowModel()
//                                    showModel.row = k
                                    showModel.type = ""
                                    showModel.submideaTitle = jsonObject_subMediaList.getString("title")
                                    showModel.subMediaBanner = jsonObject_subMediaList.getString("banner")
                                    Log.d("bb", "banner    :   " + jsonObject_subMediaList.getString("banner"))
                                    showModel.subMideabigbanner =
                                            jsonObject_subMediaList.getString("big_banner")
                                    showModel.short_Midea_description =
                                            jsonObject_subMediaList.getString("short_description")
                                    showModel.subMideaslug = jsonObject_subMediaList.getString("slug")
                                    showModel.subMidea_season_title =
                                            jsonObject_subMediaList.getString("season_title")
                                    showModel.run_time = "N/A"
//                                    showModel.subMideaslug = jsonObject.getString("slug")
//                                    HomeActivity.gridtvTime.setText("\u2022"+" "+jsonObject.getString("run_time") + "min")
//                                    HomeActivity.gridtvYear.setText(jsonObject.getString("release_year"))
//                        HomeActivity.gridtvUa.text="\u2022"+" "
                                    showModel.release_year = jsonObject.getString("release_year")
                                    Log.d("fff", "Pid:    " + jsonObject.getString("pid"))
                                    showModel.subMideaepisode_title = jsonObject_subMediaList.getString("episode_title")
//                                    cardRow_Adapter!!.add(showModel)

                                }
//                                mCategory_RowAdapter.add(ListRow(cardPresenter_Header, cardRow_Adapter))

                            }
                            else {
                                Toast.makeText(
                                        applicationContext,
                                        "Nothing to show",
                                        Toast.LENGTH_SHORT
                                ).show()
//                                HomeActivity.progressBar.visibility = View.GONE
                            }
//                            adapter = mCategory_RowAdapter

                        }
//                        adapter = mCategory_RowAdapter



                    }, Response.ErrorListener {
                Toast.makeText(
                        applicationContext,
                        "Something went wrong.",
                        Toast.LENGTH_SHORT
                ).show()
            }) {
                override fun getHeaders(): Map<String, String> {
                    val headers = HashMap<String, String>()
                    headers["Accept"] = "application/json"
                    headers["Cookie"] = cookies.toString()
                    return headers
                }
            }
            val requestQueue = Volley.newRequestQueue(applicationContext)
            requestQueue.add(stringRequest)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }


}
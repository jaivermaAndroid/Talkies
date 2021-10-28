package com.talkies.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toolbar
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

class VideoPlayerActivity_2 : AppCompatActivity() {
    private lateinit var castPlayer: CastPlayer
    private var player: SimpleExoPlayer? = null
    val videoString = "http://cbsnewshd-lh.akamaihd.net/i/CBSNHD_7@199302/index_700_av-p.m3u8"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player_2)
        player=findViewById(R.id.epvVideo)
//        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
//        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        invalidateOptionsMenu()
        CastContext.getSharedInstance(this)
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
        val httpDataSourceFactory = DefaultHttpDataSourceFactory(Util.getUserAgent(this,"exoplayer"))
        val hlsDataSourceFactory = DefaultHlsDataSourceFactory(httpDataSourceFactory)
        val hlsMediaSourceFactory = HlsMediaSource.Factory(hlsDataSourceFactory)
        val hlsMediaSource = hlsMediaSourceFactory.createMediaSource(uri)


        player?.prepare(hlsMediaSource)
        player?.playWhenReady = true
        player?.seekTo(0)

    }
    private fun buildMediaQueueItem(video :String): MediaQueueItem {
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

}
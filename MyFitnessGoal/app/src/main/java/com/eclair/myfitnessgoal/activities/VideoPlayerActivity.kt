package com.eclair.myfitnessgoal.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eclair.myfitnessgoal.R
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import kotlinx.android.synthetic.main.activity_video_player.*

class VideoPlayerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        var link: String? = null
        if (intent != null && intent.extras != null) {
            link = intent.getStringExtra("link").toString()
        }

        val simpleExoPlayer = SimpleExoPlayer.Builder(this).build()
        vidPlayer.player = simpleExoPlayer
        val mediaItem = MediaItem.fromUri(link!!)
        simpleExoPlayer.addMediaItem(mediaItem)
        simpleExoPlayer.prepare()
        simpleExoPlayer.play()
    }
}
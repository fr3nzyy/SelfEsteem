package com.example.selfesteem

import android.annotation.TargetApi
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

@TargetApi(Build.VERSION_CODES.M)
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var mediaPlayer = MediaPlayer.create(this, R.raw.rad)
        mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(.7f))
        mediaPlayer.setOnPreparedListener {
            println("ready")
        }
        mediaPlayer.isLooping = true
        mediaPlayer.start()
        textView5.text = mediaPlayer.playbackParams.speed.toString()
        button.setOnClickListener {
            Thread(Runnable {

                val nextInt = Random.nextInt(20, 30)
                for (i in 0..nextInt) {
                    progressBar.progress = Random.nextInt(1, 50)
                    if (0 <= i && i < 2 * nextInt / 3)
                        Thread.sleep(100)
                    if (2 * nextInt / 3 <= i && i < nextInt - nextInt / 4)
                        Thread.sleep(200)
                    if (nextInt - nextInt / 4 <= i && i < nextInt)
                        Thread.sleep(300)
                }
                runOnUiThread {
                    textView2.text = "Ваша самооценка: ${progressBar.progress}"
                    val speed = mediaPlayer.getPlaybackParams().setSpeed(2.0f)
                    mediaPlayer.setPlaybackParams(speed);
                    textView5.text = mediaPlayer.playbackParams.speed.toString()
                }
            }).start()

        }
    }
}

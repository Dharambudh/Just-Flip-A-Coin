package com.example.justflipacoin

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    private var coin: ImageView? = null
    private var btn: Button? = null
    private var tv: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        coin = findViewById<View>(R.id.coin) as ImageView
        btn = findViewById<View>(R.id.btn) as Button
        tv = findViewById<View>(R.id.name) as TextView
        btn!!.setOnClickListener {
            flipCoin()
            val ring = MediaPlayer.create(this@MainActivity, R.raw.coinflip)
            ring.start()
        }
    }

    private fun flipCoin() {
        val fadeOut: Animation = AlphaAnimation(1f, 0f)
        fadeOut.interpolator = AccelerateInterpolator()
        fadeOut.duration = 1000
        fadeOut.fillAfter = true
        fadeOut.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                if (Math.random() < 0.5) {
                    coin!!.setImageResource(R.drawable.tails)
                    tv!!.text = "tails"
                } else {
                    coin!!.setImageResource(R.drawable.heads)
                    tv!!.text = "heads"
                }
                val fadeIn: Animation = AlphaAnimation(0f, 1f)
                fadeIn.interpolator = DecelerateInterpolator()
                fadeIn.duration = 3000
                fadeIn.fillAfter = true
                coin!!.startAnimation(fadeIn)
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        coin!!.startAnimation(fadeOut)
    }

    companion object {
        val RANDOM = Random()
    }
}
package com.archive23.archive_sign_up_test

import android.content.Intent
import android.os.Build.VERSION_CODES.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.archive23.archive_sign_up_test.databinding.ActivitySplashBinding
import com.archive23.archive_sign_up_test.ui.login.LoginActivity

private const val SPLASH_SCREEN = 2800

class SplashActivity : AppCompatActivity() {

    // Variables
    private lateinit var topAnim: Animation
    private lateinit var bottomAnim: Animation
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val logoFluorescentStone = binding.logoFluorescentStone
        val logoGoosukkiColored = binding.logoGoosukkiColored

        // Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        logoFluorescentStone.animation = topAnim
        logoGoosukkiColored.animation = bottomAnim

        Handler().postDelayed({
            val intent : Intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_SCREEN.toLong())
    }
}
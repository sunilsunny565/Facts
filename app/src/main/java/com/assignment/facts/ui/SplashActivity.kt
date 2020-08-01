package com.assignment.facts.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import com.assignment.facts.R
import com.assignment.facts.extensions.getTargetIntent
import com.assignment.facts.ui.main.MainActivity
import kotlinx.android.synthetic.main.splash_activity.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        //Listener for detecting the end of animation
        motionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
            }

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                navigateToNextActivity()
            }

        })

    }

    // Method for navigating to the next activity
    private fun navigateToNextActivity() {
        val targetIntent = getTargetIntent(MainActivity::class.java)
        startActivity(targetIntent)
        finish()
    }
}
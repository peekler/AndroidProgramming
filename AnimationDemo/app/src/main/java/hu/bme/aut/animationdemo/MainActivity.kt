package hu.bme.aut.animationdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val demoAnim = AnimationUtils.loadAnimation(this, R.anim.demo_anim)

        demoAnim.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationRepeat(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                Toast.makeText(this@MainActivity,
                        "Animation ended", Toast.LENGTH_LONG).show()
            }

            override fun onAnimationStart(p0: Animation?) {
            }

        })

        btnAnim.setOnClickListener {

            btnAnim.startAnimation(demoAnim)

        }
    }
}
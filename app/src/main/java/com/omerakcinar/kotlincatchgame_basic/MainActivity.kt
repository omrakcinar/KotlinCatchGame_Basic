package com.omerakcinar.kotlincatchgame_basic

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.omerakcinar.kotlincatchgame_basic.databinding.ActivityMainBinding
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var score = 0
    private var imageContainer: ArrayList<ImageView> = arrayListOf()
    private var handler = Handler()
    private var runnable = Runnable {}
    private var gameTime = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        imagesToArray()
        hideEachImage()
        timeCounter()
    }

    // Contain images in a list called imageContainer
    fun imagesToArray() {
        imageContainer.add(binding.imageView)
        imageContainer.add(binding.imageView2)
        imageContainer.add(binding.imageView3)
        imageContainer.add(binding.imageView4)
        imageContainer.add(binding.imageView5)
        imageContainer.add(binding.imageView6)
        imageContainer.add(binding.imageView7)
        imageContainer.add(binding.imageView8)
        imageContainer.add(binding.imageView9)
        imageContainer.add(binding.imageView10)
        imageContainer.add(binding.imageView11)
        imageContainer.add(binding.imageView12)
        imageContainer.add(binding.imageView13)
        imageContainer.add(binding.imageView14)
        imageContainer.add(binding.imageView15)
        imageContainer.add(binding.imageView16)
    }

    fun hideEachImage() {
        runnable = object : Runnable {
            override fun run() {
                for (eachImage in imageContainer) {
                    eachImage.visibility = View.INVISIBLE
                }
                val randomIndex = Random.nextInt(16)
                imageContainer[randomIndex].visibility = View.VISIBLE
                handler.postDelayed(runnable, 300)
            }
        }
        handler.post(runnable)
    }

    fun scoreUp(view: View) {
        score++
        binding.scoreView.text = "Score : ${score}"
    }

    fun timeCounter() {
        binding.timeView.text = "Time : ${gameTime}"
        var gameTimeLong = gameTime.toLong() * 1000
        object : CountDownTimer(gameTimeLong, 1000) {
            override fun onTick(p0: Long) {
                binding.timeView.text = "Time : ${p0 / 1000}"
            }

            override fun onFinish() {
                binding.timeView.text = "Time's UP!!"
                handler.removeCallbacks(runnable)
                for (eachImage in imageContainer) {
                    eachImage.visibility = View.INVISIBLE
                }
                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game over")
                alert.setMessage("Do you want to play again? Score : ${score}.")
                alert.setPositiveButton(
                    "Yes",
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        val restartIntent = intent
                        finish()
                        startActivity(restartIntent)
                    })
                alert.setNegativeButton(
                    "No",
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        Toast.makeText(this@MainActivity, "Game over.", Toast.LENGTH_LONG).show()
                    })
                alert.show()
            }
        }.start()
    }

}
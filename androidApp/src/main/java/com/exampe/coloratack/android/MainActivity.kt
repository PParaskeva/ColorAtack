package com.exampe.coloratack.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.exampe.coloratack.Greeting
import androidx.fragment.app.Fragment
import com.exampe.coloratack.android.gameFragment.GameFragment

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentTransaction(GameFragment.newInstance())
    }

    fun fragmentTransaction(fragment: Fragment, addToBackStack: Boolean = false) {
        if (addToBackStack) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame, fragment)
                .addToBackStack(null)
                .commit()
        } else {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame, fragment)
                .commit()
        }
    }

}

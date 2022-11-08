package pl.edu.uwr.pum.l_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // replaceFragment(FragmentList())
    }
/*
    private fun replaceFragment(fragmentList: FragmentList) {
        println("Aaaaaaaaaaaaaaa")
        val fragmentMenager = supportFragmentManager
        val fragmentTransaction = fragmentMenager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayoutMain, fragmentList)
        fragmentTransaction.commit()
    }

 */
}
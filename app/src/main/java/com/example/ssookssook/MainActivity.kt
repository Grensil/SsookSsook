package com.example.ssookssook

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.ssookssook.Fragment.*
import com.example.ssookssook.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //Log.i("currentUser:",FirebaseAuth.getInstance().currentUser?.uid.toString())
        Toast.makeText(this@MainActivity, FirebaseAuth.getInstance().currentUser?.displayName + "님 환영합니다.", Toast.LENGTH_SHORT).show()


        binding.bottomNavigation.setOnItemSelectedListener(onNavigationSelectedListener(applicationContext))
        binding.bottomNavigation.selectedItemId = R.id.action_home//시작부터 action_home으로 설정함
        ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1) //저장소 접근 권한

    }


    // 호출지점잉 ㅓㅇ디?


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        Log.i("data",data.toString())
//    }

    inner class onNavigationSelectedListener(private val context: Context) : NavigationBarView.OnItemSelectedListener{
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            //setToobalDeafult()
            when (item.itemId) {
                R.id.action_home -> { //home
                    val fragment = HomeFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, fragment).commit()
                    true

                }
                R.id.action_community -> {
                    val fragment = ChannelFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, fragment).commit()
                    true
                }
                R.id.action_add -> {
//                    if(ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {
//                        startActivity(Intent(context, AddPhotoActivity::class.java))
//                    }
//                    true
                    val fragment = AddVideoFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, fragment).commit()
                    true
                }
                R.id.action_video -> {
                    val fragment = VideoFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, fragment).commit()
                    true
                }
                R.id.action_allvideos -> {
                    val fragment = AllVideoListFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, fragment).commit()
                    true

                }
                else -> false
            }
            //}
            return false

        }
//        fun setToobalDeafult() {
//            binding.toolbalUsername.visibility = View.GONE
//            binding.toolbalBtnBack.visibility = View.GONE
//            binding.toolbalTitleImage.visibility = View.VISIBLE
//
//        }


    }
}
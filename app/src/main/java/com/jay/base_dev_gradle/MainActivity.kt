package com.jay.base_dev_gradle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jay.base_dev_gradle.ui.main.MainFragment
import com.jay.router_annotations.Destination

//使用注解
@Destination(url = "router:page_a", description = "首页")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}
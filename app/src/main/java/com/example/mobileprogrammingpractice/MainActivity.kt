package com.example.mobileprogrammingpractice

import CardViewAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlinx.android.synthetic.main.activity_main.*

data class Item(val name: String, val activityClass: Class<*>, val imageResId: Int) // Item 데이터 클래스 정의(카드 이름, 해당 액티비티, 이미지경로)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemList = arrayListOf(
            Item("About Me", AboutMeActivity::class.java, R.drawable.kangmin), //각각 아이템들의 정보
            Item("Experience", ExperienceActivity::class.java,R.drawable.kangmin),
            Item("Project", ProjectActivity::class.java,R.drawable.kangmin),
            Item("Prize", PrizeActivity::class.java,R.drawable.kangmin)
        )
        var manager1 = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        var adapter1 = CardViewAdapter(itemList)

        var RecyclerView = recyclerHorizon.apply {
            adapter = adapter1
            layoutManager = manager1
        }

        adapter1.setOnItemClickListener { position -> //아이템들에 대해서 클릭 리스너 적용
            val intent = Intent(this, itemList[position].activityClass)
            startActivity(intent)
        }








    }
}

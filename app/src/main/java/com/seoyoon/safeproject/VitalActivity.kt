package com.seoyoon.safeproject

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class VitalActivity : AppCompatActivity() {

    private lateinit var heartRateTextView: TextView
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vital)

        // XML 레이아웃에서 TextView 참조
        heartRateTextView = findViewById(R.id.heartrate)

        // 매 초마다 심박수 업데이트
        updateHeartRate()
    }

    private fun updateHeartRate() {
        handler.postDelayed({
            // 모의 심박수 데이터 생성 (실제로는 센서 또는 외부 기기에서 가져와야 함)
            val heartRate = Random.nextInt(70, 80)

            // TextView에 심박수 표시
            heartRateTextView.text = "심박수: $heartRate bpm"

            // 다음 업데이트 예약
            updateHeartRate()
        }, 3000) // 3초마다 업데이트
    }
}
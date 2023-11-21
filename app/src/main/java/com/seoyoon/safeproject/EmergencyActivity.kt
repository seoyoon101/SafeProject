import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.seoyoon.safeproject.R

class EmergencyActivity : AppCompatActivity() {

    private lateinit var emergencyButton: Button
    private lateinit var statusTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emergency)

        emergencyButton = findViewById(R.id.emergencyButton)
        statusTextView = findViewById(R.id.statusTextView)

        emergencyButton.setOnClickListener {
            sendEmergencySMS()
        }
    }

    private fun sendEmergencySMS() {
        val phoneNumber = "123456789" // 여기에 실제로 보낼 전화번호를 입력하세요
        val message = "긴급 상황입니다. 도움이 필요합니다!"

        // SMS 전송 권한 확인
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.SEND_SMS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // SMS 전송
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)

            // 상태 업데이트
            statusTextView.text = "긴급 문자 전송 완료"
        } else {
            // 권한이 없는 경우 권한 요청
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.SEND_SMS),
                MY_PERMISSIONS_REQUEST_SEND_SMS
            )
        }
    }

    companion object {
        private const val MY_PERMISSIONS_REQUEST_SEND_SMS = 1
    }
}
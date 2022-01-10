//package com.example.assign2
//
//import android.app.Activity
//import android.content.Intent
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.google.zxing.integration.android.IntentIntegrator
//import com.google.zxing.integration.android.IntentResult
//
//class QrcodeScanActivity: AppCompatActivity() {
//
//    private fun initQRcodeScanner() {
//        val integator = IntentIntegrator(this as Activity)
//        integator.setBeepEnabled(false) // 소리 유무
//        integator.setOrientationLocked(true) // 세로 가로 모드 고정
//        integator.setPrompt("바코드를 인증해주세요") // 원하는 문구 넣어주기
//        integator.initiateScan()
//   }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        val result: IntentResult? = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
//        var toast = Toast(this)
//        if(result != null) {
//            if (result.contents == null) {
//                toast.drawCustomToast("QR코드 인증이 취소되었습니다.")
//                toast.show("")
//                finish()
//            } else {
//                toast.initQrcodeToas(result.contents)
//            }
//        }else{
//            upser.onActivityResult(requestCode,resultCode,data)
//        }
//    }
//}
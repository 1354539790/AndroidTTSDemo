package com.manager.demo.androidttsdemo

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener, TextToSpeech.OnInitListener {
    override fun onInit(status: Int) {
        if(status==TextToSpeech.SUCCESS){
            val language = tts.setLanguage(Locale.CHINA)
            if(language==TextToSpeech.LANG_MISSING_DATA||language==TextToSpeech.LANG_NOT_SUPPORTED){
                Log.d("文字转语音异常", "文字转语音异常")
            }
        }
    }

    private val TAG:String = "TextToSpeechDemo"
    private val LAST_SPOKEN:String = "lastSpoken"
    private lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hookButtons()
        init()
    }

    private fun init() {
        deactivateUi()
        tts = TextToSpeech(this,this)
    }

    private fun deactivateUi() {

    }

    private fun activateUi(){

    }

    private fun speak(str: String){
        if(!tts.isSpeaking){
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                tts.speak(str,TextToSpeech.QUEUE_FLUSH,null,null)
            }else{
                tts.speak(str,TextToSpeech.QUEUE_FLUSH,null)
            }
        }
    }

    private fun hookButtons() {
        speakBt.setOnClickListener(this)
        stopSpeakBt.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.speakBt->{
                speak("我是赵仁杰")
            }
            R.id.stopSpeakBt->{

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        tts.stop()
        tts.shutdown()
    }

}

package com.example.antlabs

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    val antLabsHttpService = AntLabsHttpService()
    lateinit var apiClass: IApiClass
    lateinit var btnAutoConnect: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        apiClass = antLabsHttpService.GetApi()

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mapControls()
    }

    private fun mapControls() {
        btnAutoConnect = findViewById(R.id.btnAutoConnect);
        btnAutoConnect.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch{

               apiClass.getData("1d45e900e64e4afd576abc9b7aa16706").enqueue((object :
                   Callback<ApiResponse>{
                   override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                       Toast.makeText(this@MainActivity, response.body()?.error, Toast.LENGTH_SHORT ).show()
                   }

                   override fun onFailure(p0: Call<ApiResponse>, p1: Throwable) {
                       Toast.makeText(this@MainActivity, "Test" , Toast.LENGTH_SHORT ).show()
                   }

               }))

            }


        }
    }
}
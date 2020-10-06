package hu.bme.aut.weatherdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import hu.bme.aut.weatherdemo.data.WeatherResult
import hu.bme.aut.weatherdemo.network.WeatherAPI
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// HOST:  http://api.openweathermap.org
// PATH: /data/2.5/weather
// QUERY/URL params: ?q=Budapest&units=metric&appid=f3d694bc3e1d44c1ed5a97bd1120e8fe

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var weatherAPI = retrofit.create(WeatherAPI::class.java)



        btnGet.setOnClickListener {
            val call = weatherAPI.getWeatherDetails(etCity.text.toString(),
                "metric",
                "f3d694bc3e1d44c1ed5a97bd1120e8fe")

            call.enqueue(object: Callback<WeatherResult>{
                override fun onFailure(call: Call<WeatherResult>, t: Throwable) {
                    tvResult.text = t.message
                }

                override fun onResponse(
                    call: Call<WeatherResult>,
                    response: Response<WeatherResult>
                ) {
                    var weatherResult = response.body()
                    tvResult.text = """
                        ${weatherResult!!.weather!!.get(0).main}
                        ${weatherResult!!.weather!!.get(0).description}
                        ${weatherResult!!.main!!.temp}
                    """.trimIndent()

                    var iconUrl = "https://openweathermap.org/img/w/${weatherResult!!.weather!!.get(0).icon}.png"

                    Glide.with(this@MainActivity).load(iconUrl).into(ivWeather)
                }

            })
        }

    }
}
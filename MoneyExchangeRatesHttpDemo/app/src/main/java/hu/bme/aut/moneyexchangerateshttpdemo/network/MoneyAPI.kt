package hu.bme.aut.moneyexchangerateshttpdemo.network

import hu.bme.aut.moneyexchangerateshttpdemo.data.MoneyResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// URL: https://api.exchangeratesapi.io/latest?base=USD

// HOST: https://api.exchangeratesapi.io
//
// PATH: /latest
//
// QUERY arguments: ?   base=EUR

interface MoneyAPI {

    @GET("/latest")
    fun getMoney(@Query("base") base: String) : Call<MoneyResult>
}
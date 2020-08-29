package fergaral.tidesapp

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("/ports.json")
    suspend fun getPorts(): PortsResult

    companion object {
        private const val BASE_URL = "https://tidesapp.netlify.app"

        val instance by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            retrofit.create(ApiService::class.java)
        }
    }
}
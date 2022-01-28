package fergaral.tidesapp.service

import retrofit2.http.GET

interface ApiService {

    @GET("/ports.json")
    suspend fun getPorts(): PortsResult

    companion object {
        const val BASE_URL = "https://tidesapp.netlify.app"
    }
}
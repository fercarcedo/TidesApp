package fergaral.tidesapp.repository

import fergaral.tidesapp.model.Port
import fergaral.tidesapp.service.ApiService
import javax.inject.Inject

class TidesRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getPorts(): List<Port> {
        return apiService.getPorts().ports
    }
}
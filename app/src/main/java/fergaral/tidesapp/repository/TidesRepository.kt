package fergaral.tidesapp.repository

import fergaral.tidesapp.model.Port
import fergaral.tidesapp.service.ApiService

class TidesRepository {
    suspend fun getPorts(): List<Port> {
        return ApiService.instance.getPorts().ports
    }
}
package fergaral.tidesapp

class TidesRepository {
    suspend fun getPorts(): List<Port> {
        return ApiService.instance.getPorts().ports
    }
}
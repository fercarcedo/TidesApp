package fergaral.tidesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import fergaral.tidesapp.repository.TidesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PortsViewModel : ViewModel() {
    private val tidesRepository = TidesRepository()

    val ports = liveData {
        val ports = withContext(Dispatchers.IO) {
            tidesRepository.getPorts()
        }
        emit(ports)
    }
}
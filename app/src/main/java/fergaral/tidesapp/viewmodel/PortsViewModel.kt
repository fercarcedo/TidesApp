package fergaral.tidesapp.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.assisted.Assisted
import fergaral.tidesapp.repository.TidesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PortsViewModel @ViewModelInject constructor(
    private val tidesRepository: TidesRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val ports = liveData {
        val ports = withContext(Dispatchers.IO) {
            tidesRepository.getPorts()
        }
        emit(ports)
    }
}
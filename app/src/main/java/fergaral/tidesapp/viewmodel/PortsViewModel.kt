package fergaral.tidesapp.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import fergaral.tidesapp.repository.TidesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PortsViewModel @Inject constructor(
    private val tidesRepository: TidesRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val ports = liveData {
        val ports = withContext(Dispatchers.IO) {
            tidesRepository.getPorts()
        }
        emit(ports)
    }
}
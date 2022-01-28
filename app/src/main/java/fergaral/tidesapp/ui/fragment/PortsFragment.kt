package fergaral.tidesapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import fergaral.tidesapp.databinding.FragmentPortsBinding
import fergaral.tidesapp.repository.TidesRepository
import fergaral.tidesapp.ui.adapter.PortsAdapter
import fergaral.tidesapp.viewmodel.PortsViewModel
import javax.inject.Inject

@AndroidEntryPoint
class PortsFragment : Fragment() {

    private val portsViewModel: PortsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPortsBinding.inflate(inflater, container, false)
        val adapter = PortsAdapter()
        val layoutManager = GridLayoutManager(activity, 2)
        binding.portsRv.adapter = adapter
        binding.portsRv.layoutManager = layoutManager
        //portsViewModel.ports.observe(viewLifecycleOwner) { ports ->
        //    adapter.setPorts(ports)
        //}
        return binding.root
    }
}
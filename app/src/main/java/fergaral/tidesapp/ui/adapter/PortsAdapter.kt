package fergaral.tidesapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import fergaral.tidesapp.R
import fergaral.tidesapp.databinding.PortItemBinding
import fergaral.tidesapp.model.Port

class PortsAdapter : RecyclerView.Adapter<PortsAdapter.PortsViewHolder>() {

    private var ports = mutableListOf<Port>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = PortItemBinding.inflate(inflater, parent, false)
        return PortsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PortsViewHolder, position: Int) = holder.bindPort(ports[position])

    override fun getItemCount() = ports.size

    fun setPorts(ports: List<Port>) {
        this.ports.clear()
        this.ports.addAll(ports)
        notifyDataSetChanged()
    }

    class PortsViewHolder(val itemBinding: PortItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bindPort(port: Port) {
            itemBinding.portIv.load(port.photo) {
                crossfade(true)
                placeholder(R.drawable.ic_baseline_image_24)
            }
            itemBinding.portTv.text = port.name
        }
    }
}
package pl.edu.uwr.pum.lista3

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import pl.edu.uwr.pum.lista3.databinding.DetailsItemViewBinding
import pl.edu.uwr.pum.lista3.databinding.FragmentDetailsEditBinding


class AdapterFragmentDetails(private val list: List<ListDetail>, private val context: FragmentListDetails):
    RecyclerView.Adapter<AdapterFragmentDetails.ListDetailsViewHolder>() {


    inner class ListDetailsViewHolder(private val itemBinding: DetailsItemViewBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: ListDetail){
            itemBinding.itemTitle.text = item.points.toString()
            itemBinding.itemDetails.text = item.info


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDetailsViewHolder {
        val itemBinding = DetailsItemViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ListDetailsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ListDetailsViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)

        holder.itemView.setOnClickListener {
            holder.itemView.findNavController().navigate(
                FragmentListDetailsDirections.toFragmentDetailsEdit(
                    moduleId = item.id
                )
            )
        }
    }

    override fun getItemCount(): Int = list.size
}

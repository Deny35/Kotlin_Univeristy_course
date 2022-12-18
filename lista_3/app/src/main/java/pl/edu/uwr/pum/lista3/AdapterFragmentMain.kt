package pl.edu.uwr.pum.lista3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class AdapterFragmentMain: RecyclerView.Adapter<AdapterFragmentMain.ListMainViewHolder>() {

    private val listLists = ObjectListOfLists.listTables

    inner class ListMainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val listNameText: TextView = itemView.findViewById(R.id.listName)
        fun bind(item: ListOfLists) {
            listNameText.text = item.listName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListMainViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.main_item_view, parent, false)

        return ListMainViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListMainViewHolder, position: Int) {
        val currentItem = listLists[position]
        holder.bind(currentItem)
         holder.itemView.setOnClickListener {
             holder.itemView.findNavController().navigate(
                FragmentMainDirections.toFragmentListDetails(
                    moduleTab = currentItem.whereList
                )
            )
        }

    }

    override fun getItemCount(): Int {
        return listLists.size
    }
}

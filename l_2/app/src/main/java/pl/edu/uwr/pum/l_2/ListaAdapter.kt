package pl.edu.uwr.pum.l_2

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.edu.uwr.pum.l_2.CrimeInfoObject
import androidx.navigation.findNavController


class ListaAdapter: RecyclerView.Adapter<ListaAdapter.ListViewHolder>() {
/*
    private lateinit var click: onItemClickListener

    interface onItemClickListener{

        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        click = listener
    }
 */

    private val crimeList = CrimeInfoObject.crime

    inner class ListViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val caseId: TextView = itemView.findViewById(R.id.case_id_list)
        val caseData: TextView = itemView.findViewById(R.id.date_list)
        val image: ImageView = itemView.findViewById(R.id.solve_image)
        fun bind(item: CrimeInfo) {
            caseId.text = "#" + item.Id.toString()
            caseData.text = item.crimeDate
            if (item.caseProcess == true){
                image.setImageResource(R.drawable.yes)

            }else{
                image.setImageResource(R.drawable.no)

            }
        }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_info, parent, false)

        return  ListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentItem = crimeList[position]
        holder.bind(currentItem)
        holder.itemView.setOnClickListener {
            holder.itemView.findNavController().navigate(
                FragmentListDirections.toFragmentDetal(
                    moduleId = currentItem.Id
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return crimeList.size
    }
}
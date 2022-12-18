package pl.edu.uwr.pum.lista3

import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import pl.edu.uwr.pum.lista3.databinding.FragmentAddPhotoBinding
import pl.edu.uwr.pum.lista3.databinding.FragmentPhotosSwipingBinding
import pl.edu.uwr.pum.lista3.databinding.PhotoViewBinding
import pl.edu.uwr.pum.lista3.DBHandler

class AdapterPhotoSwiping(private val dbHandler: DBHandler, private val a: Int) : RecyclerView.Adapter<AdapterPhotoSwiping.ViewHolder>(){
    val idd = dbHandler

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val button: Button = view.findViewById(R.id.button_delete_photo)
        val picture: ImageView = view.findViewById(R.id.rcImageView)

        fun bind (item: ListDetail){
            if(item.info == "none"){
              picture.setImageResource(R.drawable.ic_baseline_photo)
            }else {
                picture.setImageURI(Uri.parse(item.info))
            }
           // itemBinding.buttonDeletePhoto.setOnClickListener {
           // //Nie mogę wenątrz tej clasy użyć dbHolder. Dlaczego?
           // }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.photo_view, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dbHandler.getAllPhotos(a)[position])
        holder.button.setOnClickListener {
            dbHandler.deleteRec(dbHandler.getAllPhotos(a)[position].id)
            notifyDataSetChanged()
        }

    }

    override fun getItemCount(): Int  {
        return dbHandler.getAllPhotos(a).size
    }

}

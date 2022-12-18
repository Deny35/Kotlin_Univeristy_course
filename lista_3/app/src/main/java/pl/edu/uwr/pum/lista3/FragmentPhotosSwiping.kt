package pl.edu.uwr.pum.lista3

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import pl.edu.uwr.pum.lista3.databinding.FragmentListDetailsBinding
import pl.edu.uwr.pum.lista3.databinding.FragmentPhotosSwipingBinding

class FragmentPhotosSwiping : Fragment() {
    private val dbHandler by lazy { DBHandler(requireContext()) }
    private lateinit var binding: FragmentPhotosSwipingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_photos_swiping, container, false)
        binding = FragmentPhotosSwipingBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val a = requireArguments().getInt("moduleTab") + 10

        binding.viewPager.apply {
            adapter = AdapterPhotoSwiping(dbHandler,a)
        }

        binding.buttonAddPhoto.setOnClickListener {
            view.findNavController().navigate(
                FragmentPhotosSwipingDirections.toFragmentAddPhoto(
                    moduleTab = (a)
                )
            )
        }

        binding.buttonTasks.setOnClickListener {
            view.findNavController().navigate(
                FragmentPhotosSwipingDirections.toFragmentListDetailsFromPhotos(
                    moduleTab = (a-10)
                )
            )
        }

        binding.viewPager.adapter?.notifyItemInserted(dbHandler.getAllPhotos(a).size)

    }

}

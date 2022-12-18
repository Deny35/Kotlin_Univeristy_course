package pl.edu.uwr.pum.lista3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.edu.uwr.pum.lista3.databinding.FragmentListDetailsBinding


class FragmentListDetails : Fragment() {
    private lateinit var adapter: AdapterFragmentDetails
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentListDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_list_details, container, false)
        binding = FragmentListDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dbHandler = DBHandler(requireContext())
        val a = requireArguments().getInt("moduleTab")
        println(a)
        binding.letterRecyclerViewDetails.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = AdapterFragmentDetails(dbHandler.getAllItems(a), this@FragmentListDetails)
        }
        binding.buttonAdd.setOnClickListener {
            view.findNavController().navigate(
                FragmentListDetailsDirections.toFragmentAdd(
                    moduleTab = a
                )
            )
        }
        binding.buttonPhotos.setOnClickListener {
            view.findNavController().navigate(
                FragmentListDetailsDirections.toFragmentPhotosSwiping(
                    moduleTab = a
                )
            )
        }


        binding.letterRecyclerViewDetails.adapter?.notifyItemInserted(dbHandler.getAllItems(a).size)
    }
}
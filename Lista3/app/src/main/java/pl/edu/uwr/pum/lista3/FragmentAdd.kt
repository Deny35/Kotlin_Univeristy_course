package pl.edu.uwr.pum.lista3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import pl.edu.uwr.pum.lista3.databinding.FragmentAddBinding
import pl.edu.uwr.pum.lista3.databinding.FragmentDetailsEditBinding

class FragmentAdd : Fragment() {
    private lateinit var binding: FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dbHandler = DBHandler(requireContext())
        val a = requireArguments().getInt("moduleTab")
        val bin = FragmentDetailsEditBinding.inflate(LayoutInflater.from(context))
        bin.apply {

            binding.buttonSave.setOnClickListener{

                val updatePoint = binding.editTextPoints.text.toString()
                val updateInfo = binding.editTextInfo.text.toString()
                if (updatePoint.isNotEmpty() && updateInfo.isNotEmpty()) {
                    dbHandler.addRec(updatePoint.toInt(), updateInfo, a)
                }
                back(view, a)
            }

        }

    }
    fun back(view: View, pos:Int){
        view.findNavController().navigate(
            FragmentAddDirections.toFragmentListDetailsFromAdd(
                moduleTab = pos
            )
        )
    }
}
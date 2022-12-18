package pl.edu.uwr.pum.lista3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import pl.edu.uwr.pum.lista3.databinding.FragmentDetailsEditBinding
import pl.edu.uwr.pum.lista3.databinding.FragmentListDetailsBinding

class FragmentDetailsEdit : Fragment() {
    private lateinit var binding: FragmentDetailsEditBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsEditBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dbHandler = DBHandler(requireContext())
        val a = requireArguments().getInt("moduleId")
        val bin = FragmentDetailsEditBinding.inflate(LayoutInflater.from(context))
        bin.apply {
            val b = dbHandler.getEdit(a)
            println("AAaaaAA")
            binding.editTextPoints.setText(b[0])
            binding.editTextInfo.setText(b[1])

            binding.buttonSave.setOnClickListener{

                val updatePoint = binding.editTextPoints.text.toString()
                val updateInfo = binding.editTextInfo.text.toString()
                if (updatePoint.isNotEmpty() && updateInfo.isNotEmpty()) {
                    dbHandler.updateInfo(a, updatePoint.toInt(), updateInfo)
                }
                back(view, b[2])
            }

            binding.buttonDelete.setOnClickListener{

                dbHandler.deleteRec(a)
                back(view, b[2])
            }


       }

    }
    fun back(view: View, pos:String){
        view.findNavController().navigate(
            FragmentDetailsEditDirections.toFragmentListDetailsFromEdit(
                moduleTab = pos.toInt()
            )
        )
    }


}

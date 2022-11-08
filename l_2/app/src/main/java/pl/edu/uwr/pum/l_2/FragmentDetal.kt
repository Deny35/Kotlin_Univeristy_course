package pl.edu.uwr.pum.l_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentDetal : Fragment() {

    private val module by lazy {
        val moduleId = arguments?.getInt("moduleId")
            ?: throw IllegalArgumentException("moduleId doesn't exist")
        CrimeInfoObject.crime[moduleId - 1]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detal, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        itemView.findViewById<TextView>(R.id.case_id).text = "#" + module.Id.toString()
        itemView.findViewById<TextView>(R.id.student_name).text = module.name
        itemView.findViewById<TextView>(R.id.case_info).text = module.detal
        val casePro: String
        if (module.caseProcess == true){
            casePro = "Solved"
        }
        else{
            casePro = " In progress"
        }
        itemView.findViewById<TextView>(R.id.case_isSolve).text = casePro
        itemView.findViewById<TextView>(R.id.case_date).text = module.crimeDate

    }

}
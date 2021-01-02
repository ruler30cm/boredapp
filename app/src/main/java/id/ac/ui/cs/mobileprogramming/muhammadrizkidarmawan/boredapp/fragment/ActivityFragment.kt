package id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.fragment

import android.app.Service
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.R
import id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.database.ActivityApplication
import id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.viewmodel.ActivityViewModel
import id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.viewmodel.ActivityViewModelFactory

/**
 * A fragment representing a list of Items.
 */
class ActivityFragment : Fragment() {
//
//    private var columnCount = 1
    val viewModel: ActivityViewModel by viewModels {
        ActivityViewModelFactory((requireActivity().application as ActivityApplication).repository)
}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        arguments?.let {
//            columnCount = it.getInt(ARG_COLUMN_COUNT)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_activity_list, container, false) as RecyclerView

        // Set the adapter
        val adapter = ActivityRecyclerViewAdapter()
        view.adapter = adapter
        view.layoutManager = LinearLayoutManager(context)

        viewModel.allActivity.observe(viewLifecycleOwner, Observer {
            adapter.setActivities(it)
        })
        return view
    }

//    companion object {
//
//        // TODO: Customize parameter argument names
//        const val ARG_COLUMN_COUNT = "column-count"
//
//        // TODO: Customize parameter initialization
//        @JvmStatic
//        fun newInstance(columnCount: Int) =
//            ActivityFragment().apply {
//                arguments = Bundle().apply {
//                    putInt(ARG_COLUMN_COUNT, columnCount)
//                }
//            }
//    }
}
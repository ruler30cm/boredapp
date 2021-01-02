package id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.R
import id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.databinding.FragmentFindBinding
import id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.viewmodel.FindViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [FindFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FindFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var findViewModel: FindViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewModel = ViewModelProvider(this).get(FindViewModel::class.java)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding : FragmentFindBinding = FragmentFindBinding.inflate(inflater, container, false)
        if (checkInternetConnection()) {
            binding.progressBar.visibility = View.VISIBLE
            findViewModel.getActivity()!!.observe(viewLifecycleOwner, Observer {
                binding.progressBar.visibility = View.INVISIBLE
                binding.activityText.text = it.activity
                binding.typeText.text =  "Type: " + it.type
            })
        } else {
            binding.activityText.text = "Please connect to the Internet"
            binding.typeText.text = "Waiting you..."
        }


        binding.lifecycleOwner = this
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.randomButton).setOnClickListener {
            view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE

            if (checkInternetConnection()) {
                findViewModel.getActivity()!!.observe(viewLifecycleOwner, Observer {
                    view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.INVISIBLE
                    view.findViewById<TextView>(R.id.activityText).text = it.activity
                    view.findViewById<TextView>(R.id.typeText).text = "Type: " + it.type
                })
            } else {
                view.findViewById<TextView>(R.id.activityText).text = "Please connect to the Internet"
                view.findViewById<TextView>(R.id.typeText).text = "Waiting you..."
            }
        }

        view.findViewById<Button>(R.id.listButton).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_findFragment2_to_activityFragment2)
        }

        view.findViewById<Button>(R.id.about_me_button).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_findFragment2_to_aboutMeFragment)
        }

        view.findViewById<Button>(R.id.timer_button).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_findFragment2_to_timerFragment)
        }
    }

    @SuppressLint("SetTextI18n")
    fun checkInternetConnection(): Boolean {
        val cm : ConnectivityManager = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return (cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnectedOrConnecting)
    }

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment FindFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            FindFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}
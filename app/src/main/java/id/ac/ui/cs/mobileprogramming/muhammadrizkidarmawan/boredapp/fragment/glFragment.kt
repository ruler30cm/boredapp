package id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.fragment

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.R
import r21nomi.com.glrippleview.GLRippleView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [glFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class glFragment : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_gl, container, false)
        val glView = view.findViewById<GLRippleView>(R.id.glView)
        glView.run {
            addBackgroundImages(listOf(
                BitmapFactory.decodeResource(resources, R.drawable.water),
                BitmapFactory.decodeResource(resources, R.drawable.water2)
            ))
            setRippleOffset(0.01f)
            setFadeInterval(2000)
            startCrossFadeAnimation()
        }
        return view
    }

}
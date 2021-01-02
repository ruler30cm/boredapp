package id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.fragment

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.R
import id.ac.ui.cs.mobileprogramming.muhammadrizkidarmawan.boredapp.database.BoredResponse

/**
 * [RecyclerView.Adapter] that can display a [BoredResponse].
 * TODO: Replace the implementation with code for your data type.
 */
class ActivityRecyclerViewAdapter() : RecyclerView.Adapter<ActivityRecyclerViewAdapter.ActivityViewHolder>() {
    private var activities : List<BoredResponse>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_activity, parent, false)
        return ActivityViewHolder(view)
    }

    override fun onBindViewHolder(holderActivity: ActivityViewHolder, position: Int) {
        val activity : BoredResponse = activities!!.get(position)
        holderActivity.contentView.text = activity.activity
    }

    override fun getItemCount(): Int = activities!!.size

    fun setActivities(activity : List<BoredResponse>) {
        activities = activity
        notifyDataSetChanged()
    }

    inner class ActivityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contentView: TextView = view.findViewById(R.id.content)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}
package com.example.ojtTest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class CourseAdapter(private val mList: List<CourseModel>, private val courseDataListner: CourseDataListner) : RecyclerView.Adapter<CourseAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.course_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = mList[position]
        holder.courseHeader.text = itemsViewModel.title
        holder.courseLogo.setImageResource(itemsViewModel.imgResource)
        holder.courseRating.rating = itemsViewModel.stars
        holder.courseLayout.setOnClickListener {
            courseDataListner.showCourseData(DescriptionFragment.newInstance(Gson().toJson(itemsViewModel)))
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val courseLayout: LinearLayout = itemView.findViewById(R.id.course_layout)
        val courseHeader: TextView = itemView.findViewById(R.id.course_header)
        val courseLogo: ImageView = itemView.findViewById(R.id.course_logo)
        val courseRating : RatingBar = itemView.findViewById(R.id.rating)
    }
}
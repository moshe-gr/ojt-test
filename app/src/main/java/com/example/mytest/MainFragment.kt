package com.example.mytest

import android.content.Context
import android.content.res.AssetManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class MainFragment : Fragment(), CourseData {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        val nameView = view.findViewById<TextView>(R.id.action_bar_name)

        nameView.text = (activity as AppCompatActivity).getSharedPreferences(
            "sharedPrefs",
            Context.MODE_PRIVATE
        )
            .getString("userName", "")

        val images = arrayOf(
            R.drawable.android_logo,
            R.drawable.web_logo,
            R.drawable.django_logo,
            R.drawable.node_logo,
            R.drawable.flutter_logo
        )
        val courses = ArrayList<CourseModel>()

        courses.addAll(
            Gson().fromJson(
                activity?.assets?.readAssetsFile("udemy courses.json"),
                Array<CourseModel>::class.java
            )
        )
        for (course in courses) {
            course.imgResource = images[courses.indexOf(course)]
        }

        val adapter = CourseAdapter(courses, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        return view
    }

    private fun AssetManager.readAssetsFile(fileName: String): String =
        open(fileName).bufferedReader().use { it.readText() }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    override fun showCourseData(fragment: Fragment) {
        parentFragmentManager.beginTransaction().apply {
            addToBackStack("desc")
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
    }
}
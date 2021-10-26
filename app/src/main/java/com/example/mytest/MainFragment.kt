package com.example.mytest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        val data = ArrayList<CourseModel>()
        data.add(CourseModel(R.drawable.android_logo, R.string.android_header, 4f))
        data.add(CourseModel(R.drawable.web_logo, R.string.web_header, 3f))
        data.add(CourseModel(R.drawable.django_logo, R.string.django_header, 4f))
        data.add(CourseModel(R.drawable.node_logo, R.string.node_header, 3f))
        data.add(CourseModel(R.drawable.flutter_logo, R.string.flutter_header, 5f))
        val adapter = CourseAdapter(data)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}
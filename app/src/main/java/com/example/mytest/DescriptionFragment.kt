package com.example.mytest

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson

private const val COURSE_DATA = "courseData"

class DescriptionFragment : Fragment() {
    private var courseData: CourseModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            courseData = Gson().fromJson(it.getString(COURSE_DATA), CourseModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_description, container, false)
        courseData?.let {
            view.findViewById<ImageView>(R.id.course_logo).setImageResource(it.imgResource)
        }
        view.findViewById<TextView>(R.id.action_bar_title).text = courseData?.title
        view.findViewById<TextView>(R.id.sub_title).text = courseData?.Subtitle
        view.findViewById<TextView>(R.id.description).text = courseData?.Description
        view.findViewById<ImageView>(R.id.back_arrow).setOnClickListener {
            parentFragmentManager.popBackStackImmediate()
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(courseData: String) =
            DescriptionFragment().apply {
                arguments = Bundle().apply {
                    putString(COURSE_DATA, courseData)
                }
            }
    }
}
package com.example.mytest

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val userNameView = view.findViewById<EditText>(R.id.user_name)
        val passwordView = view.findViewById<EditText>(R.id.password)
        val loginButton = view.findViewById<TextView>(R.id.login_button)
        val progressBarLayout = view.findViewById<LinearLayout>(R.id.progress_bar_layout)
        val determinateBarView = view.findViewById<ProgressBar>(R.id.determinateBar)

        progressBarLayout.visibility = View.GONE

        loginButton.setOnClickListener {
            if(userNameView.text.filterNot { it.isWhitespace() }.isNotEmpty() &&
                passwordView.text.filterNot { it.isWhitespace() }.isNotEmpty()) {
                progressBarLayout.visibility = View.VISIBLE
                increaseProgress(determinateBarView)
                (activity as AppCompatActivity).getSharedPreferences("sharedPrefs", MODE_PRIVATE)
                    .edit().putString("userName", userNameView.text.toString()).apply()
            }
        }
        return view
    }

    private fun increaseProgress(view: ProgressBar, progress: Int = 34){
        Handler(Looper.myLooper()!!).postDelayed({
            view.progress = progress
            if(progress <= 100){
                increaseProgress(view, progress+33)
            }
            else {
                parentFragmentManager.beginTransaction().apply {
                    replace(R.id.fl_wrapper, CoursesFragment.newInstance())
                    commit()
                }
            }
        }, 1000)
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}
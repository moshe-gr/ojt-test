package com.example.mytest

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
import com.google.android.material.appbar.AppBarLayout

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
                (activity as AppCompatActivity).findViewById<TextView>(R.id.action_bar_name).text = userNameView.text
                increaseProgress(determinateBarView)
            }
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).findViewById<AppBarLayout>(R.id.my_toolbar).visibility = View.GONE
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).findViewById<AppBarLayout>(R.id.my_toolbar).visibility = View.VISIBLE
    }

    private fun increaseProgress(view: ProgressBar, progress: Int = 34){
        Handler(Looper.myLooper()!!).postDelayed({
            view.progress = progress
            if(progress <= 100){
                increaseProgress(view, progress+33)
            }
            else {
                parentFragmentManager.beginTransaction().apply {
                    replace(R.id.fl_wrapper, MainFragment.newInstance())
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
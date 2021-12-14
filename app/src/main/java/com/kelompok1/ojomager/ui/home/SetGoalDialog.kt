package com.kelompok1.ojomager.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.kelompok1.ojomager.R

class SetGoalDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewRoot: View = inflater.inflate(R.layout.dialog_fragment_set_goal, container, false)

        val weekly = resources.getStringArray(R.array.weekly)
        val weeklyAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_weekly_item, weekly)
//        inflater.dropdown_weekly

        return viewRoot
    }
}
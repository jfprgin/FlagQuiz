package com.example.flagquiz.view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.flagquiz.R
import com.example.flagquiz.databinding.FragmentResultBinding
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry


class FragmentResult : Fragment() {

    lateinit var fragmentResultBinding: FragmentResultBinding

    var correctNumber = 0F
    var emptyNumber = 0F
    var wrongNumber = 0F

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentResultBinding = FragmentResultBinding.inflate(inflater, container, false)

        var args = arguments?.let {
            FragmentResultArgs.fromBundle(it)
        }

        args?.let {
            correctNumber = it.correct.toFloat()
            emptyNumber = it.empty.toFloat()
            wrongNumber = it.wrong.toFloat()
        }

        val barEntriesArrayListCorrect = ArrayList<BarEntry>()
        val barEntriesArrayListEmpty = ArrayList<BarEntry>()
        val barEntriesArrayListWrong = ArrayList<BarEntry>()

        barEntriesArrayListCorrect.add(BarEntry(0F, correctNumber))
        barEntriesArrayListEmpty.add(BarEntry(1F, emptyNumber))
        barEntriesArrayListWrong.add(BarEntry(2F, wrongNumber))

        var barDataSetCorrect = BarDataSet(barEntriesArrayListCorrect, "Correct Number").apply {
            color = Color.GREEN
            valueTextSize = 24F
            setValueTextColors(arrayListOf(Color.BLACK))
        }

        var barDataSetEmpty = BarDataSet(barEntriesArrayListEmpty, "Empty Number").apply {
            color = Color.BLUE
            valueTextSize = 24F
            setValueTextColors(arrayListOf(Color.BLACK))
        }

        var barDataSetWrong = BarDataSet(barEntriesArrayListWrong, "Wrong Number").apply {
            color = Color.RED
            valueTextSize = 24F
            setValueTextColors(arrayListOf(Color.BLACK))
        }

        var barData = BarData(barDataSetCorrect, barDataSetEmpty, barDataSetWrong)

        fragmentResultBinding.resultChart.data = barData

        fragmentResultBinding.buttonNewQuiz.setOnClickListener {
            this.findNavController().popBackStack(R.id.fragmentHome, inclusive = false)
        }

        fragmentResultBinding.buttonExit.setOnClickListener {
            requireActivity().finish()
        }

        // Inflate the layout for this fragment
        return fragmentResultBinding.root
    }
}
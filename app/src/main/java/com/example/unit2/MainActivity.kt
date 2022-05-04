package com.example.unit2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.unit2.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {
        val textInField = binding.plainTextInput.text.toString()
        val cost = textInField.toDoubleOrNull()
        if (cost == null) {
            binding.tipResult.text = ""
            return
        }

        val tipPercentage = when(binding.radioGroup.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var tip = tipPercentage * cost

        if (binding.roundUpSwitch.isChecked) tip = Math.ceil(tip)
        displayTip(tip)
    }

    private fun displayTip(tip : Double) {
        val formatedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formatedTip)
    }
}
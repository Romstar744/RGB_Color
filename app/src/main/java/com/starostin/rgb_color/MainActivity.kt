package com.starostin.rgb_color

import android.app.AlertDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.SeekBar
import com.starostin.rgb_color.databinding.ActivityMainBinding
import com.starostin.rgb_color.databinding.ColorpickBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bindings: ColorpickBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.colorbutton.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val dialogView = layoutInflater.inflate(R.layout.colorpick, null)
        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("Choose Color")

        val dialog = dialogBuilder.show()

        bindings = ColorpickBinding.bind(dialogView)

        bindings.ok.setOnClickListener {
            val red = bindings.RR.text.toString().toIntOrNull() ?: 0
            val green = bindings.G.text.toString().toIntOrNull() ?: 0
            val blue = bindings.B.text.toString().toIntOrNull() ?: 0
            setColor(red, green, blue)
            dialog.dismiss()
        }

        bindings.cancel.setOnClickListener {
            dialog.dismiss()
        }

        bindings.RED.setOnSeekBarChangeListener(createSeekBarChangeListener(bindings.RR))
        bindings.GREEBN.setOnSeekBarChangeListener(createSeekBarChangeListener(bindings.G))
        bindings.BLUE.setOnSeekBarChangeListener(createSeekBarChangeListener(bindings.B))
    }

    private fun createSeekBarChangeListener(editText: EditText): SeekBar.OnSeekBarChangeListener {
        return object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                editText.setText(progress.toString())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        }
    }

    private fun setColor(red: Int, green: Int, blue: Int) {
        val color = Color.rgb(red, green, blue)
        binding.colorBg.setBackgroundColor(color)
    }
}
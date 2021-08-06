package com.example.dieunnph15766_ass.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import com.example.dieunnph15766_ass.R
import android.widget.EditText
import android.content.DialogInterface
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.*
import kotlinx.android.synthetic.main.fragment_income_type.*
import java.lang.ClassCastException

class CustomDialogEditIncomeType : DialogFragment() {


    private lateinit var onInputSelected: OnInputSelected

    interface OnInputSelected {
        fun setText(text: String)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val mView = LayoutInflater.from(requireParentFragment().requireContext())
            .inflate(R.layout.custom_dialog_new_income_type, null)

        builder.setView(mView)
            .setPositiveButton("OK") { dialog: DialogInterface?, which: Int ->
                onInputSelected.setText("Lmao")
            }
            .setNegativeButton(resources.getString(R.string.cancel)) { dialog: DialogInterface?, which: Int ->
                dialog?.dismiss()
            }
        return builder.create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            onInputSelected = targetFragment as OnInputSelected
        }catch (e:ClassCastException) {
        }
    }
}
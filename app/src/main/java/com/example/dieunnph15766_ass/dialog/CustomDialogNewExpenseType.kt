package com.example.dieunnph15766_ass.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.dieunnph15766_ass.R
import java.lang.ClassCastException

class CustomDialogNewExpenseType: DialogFragment() {

    private lateinit var textResult: TextView
    lateinit var onInputSelected: OnInputSelected

    interface OnInputSelected {
        fun setText(text: String)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val mView = LayoutInflater.from(requireParentFragment().requireContext())
            .inflate(R.layout.custom_dialog_new_expense_type, null)

        textResult = mView.findViewById<EditText>(R.id.edittext_add_new_expense)
        builder.setView(mView)
            .setPositiveButton("OK") { dialog: DialogInterface?, which: Int ->
                onInputSelected.setText(textResult.text.toString())
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
        }catch (e: ClassCastException) {

        }
    }
}
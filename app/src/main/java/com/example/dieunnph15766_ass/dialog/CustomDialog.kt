package com.example.dieunnph15766_ass.dialog

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatDialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import com.example.dieunnph15766_ass.R
import android.widget.EditText
import android.content.DialogInterface
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import androidx.lifecycle.LifecycleOwner
import com.example.dieunnph15766_ass.fragment.IncomeTypeFragment
import kotlinx.android.synthetic.main.fragment_income_type.*
import java.io.FileDescriptor
import java.io.PrintWriter
import java.lang.ClassCastException

class CustomDialog : DialogFragment() {


    private lateinit var textResult: TextView
    lateinit var onInputSelected: OnInputSelected

    interface OnInputSelected {
        fun setText(text: String)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val mView = LayoutInflater.from(requireParentFragment().requireContext())
            .inflate(R.layout.new_income_type_dialog, null)

        textResult = mView.findViewById<EditText>(R.id.edittext_add_new_income)
        builder.setView(mView)
            .setPositiveButton("OK") { dialog: DialogInterface?, which: Int ->
                onInputSelected.setText(textResult.text.toString())
            }
            .setNegativeButton("Huy") { dialog: DialogInterface?, which: Int ->
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
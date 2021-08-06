package com.example.dieunnph15766_ass.adapter

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import com.example.dieunnph15766_ass.R
import com.example.dieunnph15766_ass.database.Database
import com.example.dieunnph15766_ass.database.income.IncomeTypeDB
import com.example.dieunnph15766_ass.dialog.CustomDialogEditIncomeType
import com.example.dieunnph15766_ass.fragment.income.IncomeTypeFragment
import com.example.dieunnph15766_ass.model.income.IncomeType

class IncomeTypeArrayAdapter(
    private val mContext: Context,
    private val mResource: Int,
    private var mList: ArrayList<IncomeType>
) : ArrayAdapter<IncomeType>(mContext, 0, mList) {

    lateinit var database: Database
    lateinit var incomeTypeDB: IncomeTypeDB
    lateinit var editButton: ImageButton

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(mContext)?.inflate(R.layout.income_type_row, null)
        val incomeTypeName = view!!.findViewById<TextView>(R.id.textview_income_type_name_row)
        editButton = view.findViewById<ImageButton>(R.id.imagebtn_edit_income_type)
        val deleteButton = view.findViewById<ImageButton>(R.id.imagebtn_delete_income_type)
        database = Database(mContext)
        incomeTypeDB = IncomeTypeDB(database)

        incomeTypeName.text = mList[position].incomeTypeName

        deleteButton.setOnClickListener {
            AlertDialog.Builder(mContext)
                .setTitle("Xác nhận xoá")
                .setMessage("Bạn muốn xoá ${mList[position].incomeTypeName} ?")
                .setNegativeButton("Huỷ") { dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveButton("Xoá") { _, _ ->
                    if (incomeTypeDB.removeIncomeType(mList[position])) {
                        Toast.makeText(mContext, "Xoá thành công", Toast.LENGTH_SHORT).show()

                        // Refresh adapter
                        this.apply {
                            clear()
                            addAll(incomeTypeDB.getAllIncomeType())
                        }
                    } else {
                        Toast.makeText(mContext, "Xoá thất bại", Toast.LENGTH_SHORT).show()
                    }
                }
                .create()
                .show()
        }

        editButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("bundle", mList[position].incomeTypeName)

            val builder = AlertDialog.Builder(mContext)
            val input = EditText(mContext)
            input.setText(bundle.getString("bundle"))
            input.inputType = InputType.TYPE_CLASS_TEXT



            builder.apply {
                setTitle("Chỉnh sửa khoản thu")
                setView(input)
                setNegativeButton("Hủy") {dialog, _ ->
                    dialog.dismiss()
                }
                setPositiveButton("Sửa") {_, _ ->
                    if (input.text.toString().isEmpty()) {
                        Toast.makeText(mContext, R.string.cannot_be_empty, Toast.LENGTH_SHORT).show()
                        return@setPositiveButton
                    }

                    if(incomeTypeDB.editIncomeType(bundle.getString("bundle")!!, input.text.toString())) {
                        Toast.makeText(mContext, "Edit successfully", Toast.LENGTH_SHORT).show()
                        this.apply {
                            clear()
                            addAll(incomeTypeDB.getAllIncomeType())
                        }
                    } else {
                        Toast.makeText(mContext, "Edit failed", Toast.LENGTH_SHORT).show()
                    }
                }
                create()
                show()
            }
        }
        return view!!
    }
}
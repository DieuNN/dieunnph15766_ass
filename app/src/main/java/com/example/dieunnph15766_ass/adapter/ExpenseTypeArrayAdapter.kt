package com.example.dieunnph15766_ass.adapter

import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.dieunnph15766_ass.R
import com.example.dieunnph15766_ass.database.Database
import com.example.dieunnph15766_ass.database.expense.ExpenseTypeDB
import com.example.dieunnph15766_ass.model.expense.ExpenseType

class ExpenseTypeArrayAdapter(
    private val mContext: Context,
    private val mResource: Int,
    private val mList: ArrayList<ExpenseType>
) : ArrayAdapter<ExpenseType>(mContext, 0, mList) {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(mContext).inflate(R.layout.expense_type_row, null)
        val expenseTypeName = view.findViewById<TextView>(R.id.textview_expense_type_name_row)
        val editButton = view.findViewById<ImageButton>(R.id.imagebtn_edit_expense_type)
        val deleteButton = view.findViewById<ImageView>(R.id.imagebtn_delete_expense_type)
        val database = Database(mContext)
        val expenseTypeDB = ExpenseTypeDB(database)

        expenseTypeName.text = mList[position].expenseTypeName

        deleteButton.setOnClickListener {
            AlertDialog.Builder(mContext)
                .setTitle("Xác nhận xoá")
                .setMessage("Bạn muốn xoá ${mList[position].expenseTypeName} ?")
                .setNegativeButton("Huỷ") { dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveButton("Xoá") { _, _ ->
                    if (expenseTypeDB.removeExpenseType(mList[position])) {
                        Toast.makeText(mContext, "Xoá thành công", Toast.LENGTH_SHORT).show()
                        this.apply {
                            clear()
                            addAll(expenseTypeDB.getAllExpenseType())
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
            bundle.putString("bundle", mList[position].expenseTypeName)

            val builder = AlertDialog.Builder(mContext)
            val input = EditText(mContext)
            input.setText(bundle.getString("bundle"))
            input.inputType = InputType.TYPE_CLASS_TEXT

            builder.apply {
                setTitle("Chỉnh sửa khoản chi")
                setView(input)
                setNegativeButton("Hủy") { dialog, _ ->
                    dialog.dismiss()
                }
                setPositiveButton("Sửa") { _, _ ->
                    if (input.text.toString().isEmpty()) {
                        Toast.makeText(mContext, R.string.cannot_be_empty, Toast.LENGTH_SHORT)
                            .show()
                        return@setPositiveButton
                    }

                    if (expenseTypeDB.editExpenseType(
                            bundle.getString("bundle")!!,
                            input.text.toString()
                        )
                    ) {
                        Toast.makeText(mContext, "Edit successfully", Toast.LENGTH_SHORT).show()
                        this.apply {
                            clear()
                            addAll(expenseTypeDB.getAllExpenseType())
                        }
                    } else {
                        Toast.makeText(mContext, "Edit failed", Toast.LENGTH_SHORT).show()
                    }
                }
                    .create()
                    .show()
            }
        }

        return view!!
    }
}
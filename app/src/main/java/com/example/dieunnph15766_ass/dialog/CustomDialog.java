package com.example.dieunnph15766_ass.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.dieunnph15766_ass.R;

public class CustomDialog extends AppCompatDialogFragment {
    CustomDialogListener customDialogListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.new_income_type_dialog, null);
        EditText incomeType = view.findViewById(R.id.edittext_add_new_income);

        builder.setView(view)
                .setPositiveButton(getResources().getString(R.string.add_income_type), (dialog, which) -> {
                    String name = incomeType.getText().toString();
                    customDialogListener.applyText(name);
                })
                .setNegativeButton(getResources().getString(R.string.cancel), (dialog, which) -> {
                    dialog.cancel();
                });



        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        customDialogListener = (CustomDialogListener) context;
    }

    public interface CustomDialogListener {
        void applyText(String name);
    }
}

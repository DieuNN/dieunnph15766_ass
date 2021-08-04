package com.example.dieunnph15766_ass.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dieunnph15766_ass.R;
import com.example.dieunnph15766_ass.dialog.CustomDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class IncomeTypeFragment extends Fragment implements CustomDialog.CustomDialogListener {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_income_type, null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.floating_action_button_add_new_income_type);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog dialog = new CustomDialog();
                dialog.show(getParentFragmentManager(), "TAG");

            }
        });
    }

    @Override
    public void applyText(String name) {
        Toast.makeText(getActivity().getBaseContext(), name, Toast.LENGTH_SHORT).show();
    }
}

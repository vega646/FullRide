package com.estela.fullride;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class infopopmap extends AppCompatDialogFragment {


    public infopopmap() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        LayoutInflater cardIn = requireActivity().getLayoutInflater();
        View vr = inflater.inflate(R.layout.info_pop2, null);




        builder.setView(vr)


                .setPositiveButton("OK", (dialog, which) -> {



                });

        return builder.create();

    }
}

package com.github.drraider.mymenu.dialogfragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

public class EulaDialogFragment extends DialogFragment {

    public static EulaDialogFragment newInstance(int title) {
        EulaDialogFragment frag = new EulaDialogFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("EULA");
        builder.setMessage(
                "Novo denique perniciosoque exemplo idem Gallus ausus est inire " +
                "flagitium grave, quod Romae cum ultimo dedecore temptasse aliquando dicitur " +
                "Gallienus, et adhibitis paucis clam ferro succinctis vesperi per tabernas " +
                "palabatur et conpita quaeritando Graeco sermone, cuius erat inpendio gnarus, " +
                "quid de Caesare quisque sentiret. et haec confidenter agebat in urbe ubi " +
                "pernoctantium luminum claritudo dierum solet imitari fulgorem. postremo agnitus " +
                "saepe iamque, si prodisset, conspicuum se fore contemplans, non nisi luce palam " +
                "egrediens ad agenda quae putabat seria cernebatur. et haec quidem medullitus " +
                "multis gementibus agebantur."
        );

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        return builder.create();
    }
}

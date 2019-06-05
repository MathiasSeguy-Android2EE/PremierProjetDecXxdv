/**
 * <ul>
 * <li>WarningDialog</li>
 * <li>com.android2ee.formation.inter.premierprojet.decxxdv</li>
 * <li>11/12/2015</li>
 * <p/>
 * <li>======================================================</li>
 * <p/>
 * <li>Projet : Mathias Seguy Project</li>
 * <li>Produit par MSE.</li>
 * <p/>
 * /**
 * <ul>
 * Android Tutorial, An <strong>Android2EE</strong>'s project.</br>
 * Produced by <strong>Dr. Mathias SEGUY</strong>.</br>
 * Delivered by <strong>http://android2ee.com/</strong></br>
 * Belongs to <strong>Mathias Seguy</strong></br>
 * ***************************************************************************************************************</br>
 * This code is free for any usage but can't be distribute.</br>
 * The distribution is reserved to the site <strong>http://android2ee.com</strong>.</br>
 * The intelectual property belongs to <strong>Mathias Seguy</strong>.</br>
 * <em>http://mathias-seguy.developpez.com/</em></br> </br>
 * <p/>
 * *****************************************************************************************************************</br>
 * Ce code est libre de toute utilisation mais n'est pas distribuable.</br>
 * Sa distribution est reservée au site <strong>http://android2ee.com</strong>.</br>
 * Sa propriété intellectuelle appartient à <strong>Mathias Seguy</strong>.</br>
 * <em>http://mathias-seguy.developpez.com/</em></br> </br>
 * *****************************************************************************************************************</br>
 */

package com.android2ee.formation.inter.premierprojet.decxxdv;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by Mathias Seguy - Android2EE on 11/12/2015.
 */
public class WarningDialog extends DialogFragment {
    MyFragmentModel model;

    public WarningDialog() {
        super();
    }

    public WarningDialog(MyFragmentModel model) {
        this.model = model;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialoBuilder=new AlertDialog.Builder(getContext());
        alertDialoBuilder.setMessage(getString(R.string.alertdialog_genericmessage));
        alertDialoBuilder.setPositiveButton(getString(R.string.alertdialog_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                model.copyItemToEdt();
            }
        });
        alertDialoBuilder.setNegativeButton(getString(R.string.alertdialog_nok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                model.abortCopyItemToEdt();
            }
        });
        return alertDialoBuilder.create();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AlertDialog)getDialog()).setMessage(model.getAlertDialogMessage());
    }
}

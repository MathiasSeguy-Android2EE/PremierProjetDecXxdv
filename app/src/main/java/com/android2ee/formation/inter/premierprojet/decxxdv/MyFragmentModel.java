/**
 * <ul>
 * <li>MainActivityModel</li>
 * <li>com.android2ee.formation.inter.premierprojet.decxxdv</li>
 * <li>10/12/2015</li>
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

import android.annotation.TargetApi;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Mathias Seguy - Android2EE on 10/12/2015.
 */
public class MyFragmentModel {

    /***
     * The position of the selected item
     */
    int currentSelectedItemPosition=-1;

    MyFragment view;

    public MyFragmentModel(MyFragment view) {
        this.view = view;
        messages=new ArrayList<Human>();
    }

    /**
     * Dataset managed by the humanArrayAdapter
     */
    ArrayList<Human> messages;
    /***********************************************************
     *  Business methods
     **********************************************************/
    /**
     * TempVariable
     */
    private String messageTemp;
    /**
     * Add the content of the edittext in the reulst area
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
     void addMessage(){
        //launch animation
        if(view.getResources().getBoolean(R.bool.postHC)){
            view.animator.start();
        }else{
            view.btnAdd.startAnimation(view.bumpAnimation);
        }

        //add message
        messageTemp=view.edtMessage.getText().toString();
        //second way
        messages.add(new Human(messageTemp,messages.size()));
        view.humanArrayAdapter.notifyDataSetChanged();
        view.edtMessage.setText("");
    }

    /**
     * Paste the item clicked in th edittext
     * @param position the position of the clicked item
     */
     void itemClicked(int position){
        //display the AlertDialog
        currentSelectedItemPosition=position;
         //create the DialogFragment
         FragmentManager fm = view.getFragmentManager();
         WarningDialog deleteDialog=(WarningDialog)fm.findFragmentByTag("warningDialog");
         if(deleteDialog==null){
             deleteDialog=new WarningDialog(this);
         }
         deleteDialog.show(view.getFragmentManager(), "warningDialog");
    }

    /**
     * Delete all the items from the list
     */
     void deleteItems(){
         messages.clear();
         view.humanArrayAdapter.notifyDataSetChanged();
    }

    /**
     * Do the copy
     */
     void copyItemToEdt(){
        messageTemp=messages.get(currentSelectedItemPosition).getMessage();
         view.edtMessage.setText(messageTemp);
    }

    /**
     * Aboart the copy
     */
     void abortCopyItemToEdt(){
        Toast.makeText(view.getContext(), view.getString(R.string.toast_action_abort), Toast.LENGTH_LONG).show();
    }

    @NonNull
    String getAlertDialogMessage() {
        return view.getString(R.string.alertdialog_specificmessage,messages.get(currentSelectedItemPosition).getMessage());
    }
}

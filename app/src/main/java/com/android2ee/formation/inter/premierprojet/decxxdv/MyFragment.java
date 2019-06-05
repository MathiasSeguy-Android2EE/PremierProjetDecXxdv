/**
 * <ul>
 * <li>MyFragment</li>
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


import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by Mathias Seguy - Android2EE on 11/12/2015.
 */
public class MyFragment extends Fragment {
    /***********************************************************
     *  Attributes
     **********************************************************/
    MyFragmentModel model;
    //constants
    private static final String RESULTAT = "RESULTAT";
    private static final String EDITTEXT = "EDITTEXT";
    private static final String TAG = "MainActivity";
    /**
     * The editText in which we put the message
     */
    EditText edtMessage;
    /**
     * The button to add the message
     */
    Button btnAdd;
    /**
     * The result
     */
    ListView lsvResult;
    /**
     * The listView arrayadapter
     */
    HumanArrayAdapter humanArrayAdapter;
    /**
     * Gingerbread animation
     */
    Animation bumpAnimation;
    /**
     * Post ICS animation
     */
    Animator animator;

    /***********************************************************
     * Managing lifecycle
     **********************************************************/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //instanciatet the model
        model=new MyFragmentModel(this);
        //has the optionMenu
        setHasOptionsMenu(true);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //inflate the view
        View view=inflater.inflate(R.layout.myfragment,container,false);
        //instanciate the graphical elements
        edtMessage= (EditText) view.findViewById(R.id.edtMessage);
        btnAdd= (Button) view.findViewById(R.id.btnAdd);
        lsvResult= (ListView) view.findViewById(R.id.lsvResult);
        //instanciate animation
        if(getResources().getBoolean(R.bool.postHC)){
            animator= AnimatorInflater.loadAnimator(getContext(), R.animator.rotate_y);
            animator.setTarget(btnAdd);
        }else{
            bumpAnimation= AnimationUtils.loadAnimation(getContext(), R.anim.bump);
        }
        //Instanciate listView pattern
        Log.e(TAG, "hello je suis un log");
        humanArrayAdapter =new HumanArrayAdapter(
                getContext(),//Context
                model.messages//dataset
        );
        lsvResult.setAdapter(humanArrayAdapter);
        lsvResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                model.itemClicked(position);
            }
        });
        //add the listener on the click on the btnAdd
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.addMessage();
            }
        });
        return view;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(RESULTAT, model.messages);
        outState.putString(EDITTEXT,edtMessage.getText().toString());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState!=null) {
            model.messages.clear();
            for (Parcelable mes : savedInstanceState.getParcelableArrayList(RESULTAT)) {
                model.messages.add((Human) mes);
            }
            humanArrayAdapter.notifyDataSetChanged();
            edtMessage.setText(savedInstanceState.getString(EDITTEXT));
        }
    }

    /***********************************************************
     *  Managing Menu
     **********************************************************/
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_delete:
                model.deleteItems();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

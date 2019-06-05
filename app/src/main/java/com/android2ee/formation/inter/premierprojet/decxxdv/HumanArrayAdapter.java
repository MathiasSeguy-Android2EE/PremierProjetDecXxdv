/**
 * <ul>
 * <li>HumanArrayAdapter</li>
 * <li>com.android2ee.formation.inter.premierprojet.decxxdv</li>
 * <li>07/12/2015</li>
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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mathias Seguy - Android2EE on 07/12/2015.
 */
public class HumanArrayAdapter extends ArrayAdapter<Human> {
    /***********************************************************
     *  Attributes
     **********************************************************/
    LayoutInflater inflater;
    Human tHuman;
    View tView;
    ViewHolder tViewHolder;

    /***********************************************************
     *  Constructors
     **********************************************************/
    /**
     * Constructor
     *
     * @param context  The current context.
     * @param dataset the humans
     */
    public HumanArrayAdapter(Context context, ArrayList<Human> dataset) {
        super(context, R.layout.human_even,dataset);
        inflater=LayoutInflater.from(context);
    }

    /***********************************************************
     * GetView
     **********************************************************/

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //find the human
        tHuman=getItem(position);
        //build the view
        tView=convertView;
        if(tView==null){
            if(0==getItemViewType(position)){
                tView=inflater.inflate(R.layout.human_even,parent, false);
            }else{
                tView=inflater.inflate(R.layout.human_odd,parent, false);
            }
            tViewHolder=new ViewHolder(tView);
            tView.setTag(tViewHolder);
        }
        //update the view
        tViewHolder= (ViewHolder) tView.getTag();
        tViewHolder.getImvPicture().setBackgroundResource(tHuman.getPicture());
        tViewHolder.getTxvMessage().setText(tHuman.getMessage());
        tViewHolder.getTxvName().setText(tHuman.getName());
        //return it
        return tView;
    }

    /***********************************************************
     * Managing odd and even lines using 2 pools of convertViews
     **********************************************************/
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position%2;
    }

    /***********************************************************
     *  ViewHolder pattern
     **********************************************************/
    public class ViewHolder{
        TextView txvName;
        TextView txvMessage;
        ImageView imvPicture;
        View rootView;


        public ViewHolder(View rootView) {
            this.rootView = rootView;
            txvMessage= (TextView) rootView.findViewById(R.id.txvMessage);
            txvName= (TextView) rootView.findViewById(R.id.txvName);
            imvPicture= (ImageView) rootView.findViewById(R.id.imvPicture);
        }

        public ImageView getImvPicture() {
            return imvPicture;
        }

        public TextView getTxvMessage() {
            return txvMessage;
        }

        public TextView getTxvName() {
            return txvName;
        }
    }


}

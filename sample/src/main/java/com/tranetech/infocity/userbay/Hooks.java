package com.tranetech.infocity.userbay;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Harshad on 19/07/2016.
 */
public class Hooks extends Fragment
{

    public Hooks()
    {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootview=inflater.inflate(R.layout.parties,container,false);

        return  rootview;
    }
}

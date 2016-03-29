package com.example.almudena.mad_btv1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by clive on 25-May-14.
 * www.101apps.co.za
 */
public class MyAdapter extends FragmentStatePagerAdapter {

    public MyAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    //    returns the number of views available
    @Override
    public int getCount() {
        return Graph.NUMBER_OF_VIEWS;
    }

    // when swiping returns a fragment with the object identified by position
    @Override
    public Fragment getItem(int position) {
        return ArrayListFragment.createNewFragmentToDisplay(position);
    }

    /*gets the title describing specified page
    and passes it to the PagerTitleStrip in
    fragment_pager.xml - displays either top or
    bottom of screen*/
    @Override
    public CharSequence getPageTitle(int position) {
        return "Harry's List " + (position + 1);
    }
}

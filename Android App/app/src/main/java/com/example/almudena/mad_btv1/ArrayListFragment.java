package com.example.almudena.mad_btv1;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by clive on 25-May-14.
 * www.101apps.co.za
 */
public class ArrayListFragment extends ListFragment {
    private int mNum;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        get this fragment's number
        if (getArguments() != null) {
            mNum = getArguments().getInt("num");
        } else {
            mNum = 1;
        }
    }

    /**
     * Create a new instance of our fragment, providing "num"
     * as an argument - it determines which page to display
     */
    static ArrayListFragment createNewFragmentToDisplay(int num) {
        ArrayListFragment displayFragment = new ArrayListFragment();
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        displayFragment.setArguments(args);
        return displayFragment;
    }

    /*called each time the fragment's activity is created - which is
    each time a new page is displayed*/
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, getMyListArray()));
    }

    //    our list arrays to be used - one per page
    private String[] getMyListArray() {
        String[] returnArray = {"no lists"};
        switch (mNum) {
            case 0:
                returnArray = CountriesClass.countries_one;
                break;
            case 1:
                returnArray = CountriesClass.countries_two;
                break;
            case 2:
                returnArray = CountriesClass.countries_three;
                break;
            case 3:
                returnArray = CountriesClass.countries_four;
                break;
            case 4:
                returnArray = CountriesClass.countries_five;
                break;
            case 5:
                returnArray = CountriesClass.countries_six;
                break;
            case 6:
                returnArray = CountriesClass.countries_seven;
                break;
        }
        return returnArray;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(getActivity(), "List " + (mNum + 1) + " selected item: " + (position + 1),Toast.LENGTH_SHORT).show();
    }
}
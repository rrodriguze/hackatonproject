package grupof.opendata.unex.es.hackaton_project.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import grupof.opendata.unex.es.hackaton_project.fragment.FarmaciasFragment;
import grupof.opendata.unex.es.hackaton_project.fragment.RestaurantesFragment;

public class TabsAdapter extends FragmentStatePagerAdapter {

    private int numTabs;

    TabsAdapter(FragmentManager fm, int NoofTabs) {
        super(fm);
        this.numTabs = NoofTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FarmaciasFragment();

            case 1:
                return new RestaurantesFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }

}

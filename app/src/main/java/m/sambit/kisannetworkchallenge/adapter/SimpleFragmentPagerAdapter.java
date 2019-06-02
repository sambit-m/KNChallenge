package m.sambit.kisannetworkchallenge.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import m.sambit.kisannetworkchallenge.R;
import m.sambit.kisannetworkchallenge.fragments.ContactFragment;
import m.sambit.kisannetworkchallenge.fragments.MessageFragment;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public SimpleFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ContactFragment();
        } else {
            return new MessageFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.contacts);
            case 1:
                return mContext.getString(R.string.message);
            default:
                return null;
        }
    }
}
package m.sambit.kisannetworkchallenge.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import m.sambit.kisannetworkchallenge.R;
import m.sambit.kisannetworkchallenge.adapter.SimpleFragmentPagerAdapter;

/**
 * @author Sambit Mallick
 * This is the main screen which contains a viewpager
 * Fragments {@link m.sambit.kisannetworkchallenge.fragments.ContactFragment} and {@link m.sambit.kisannetworkchallenge.fragments.MessageFragment}
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.view_pager);

        // Create an adapter that knows which fragment should be shown on each page
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(this, getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}

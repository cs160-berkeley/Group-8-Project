package com.cs160.team8.ally;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements ProfilesFragment.OnProfileFragmentInteractionListener,
        HomeFragment.OnFragmentInteractionListener,
        RemindersFragment.OnFragmentInteractionListener {

    public List<Profile> profiles;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private int[] tabIcons = {
            R.drawable.ic_person,
            R.drawable.ic_people,
            R.drawable.ic_check_box
    };
    private String[] tabTitles = {
            "My Patient",
            "Visitors",
            "Reminders"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap evanPhoto = BitmapFactory.decodeResource(getResources(), R.drawable.evan);
        Bitmap chloePhoto = BitmapFactory.decodeResource(getResources(), R.drawable.chloe);
        Bitmap jeremyPhoto = BitmapFactory.decodeResource(getResources(), R.drawable.jeremy);
        profiles = new ArrayList<>();
        profiles.add(new Profile("Evan Miller", "Grandson", evanPhoto, 9));
        profiles.add(new Profile("Chloe Stanson", "Caretaker", chloePhoto, 26));
        profiles.add(new Profile("Jeremy Miller", "Son", jeremyPhoto, 42));

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(mViewPager) {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        View view = tab.getCustomView();
                        ImageView icon = (ImageView) view.findViewById(R.id.tab_icon);
                        TextView title = (TextView) view.findViewById(R.id.tab_title);

                        int activeColor = ContextCompat.getColor(MainActivity.this, R.color.tabActive);
                        icon.setColorFilter(activeColor, PorterDuff.Mode.SRC_IN);
                        title.setTextColor(activeColor);
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);
                        View view = tab.getCustomView();
                        TextView title = (TextView) view.findViewById(R.id.tab_title);

                        ImageView icon = (ImageView) view.findViewById(R.id.tab_icon);
                        int inactiveColor = ContextCompat.getColor(MainActivity.this, R.color.tabInactive);
                        icon.setColorFilter(inactiveColor, PorterDuff.Mode.SRC_IN);
                        title.setTextColor(inactiveColor);
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                }
        );
        setupTabIcons();


        /*

        HARD-CODED PROFILE PUSH




         */
    }

    private void setupTabIcons() {
        for (int i = 0; i < tabTitles.length; i += 1) {
            View tab = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
            TextView tabTitle = (TextView) tab.findViewById(R.id.tab_title);
            tabTitle.setText(tabTitles[i]);

            if (i == 0) {
                int activeColor = ContextCompat.getColor(MainActivity.this, R.color.tabActive);
                tabTitle.setTextColor(activeColor);
            }

            ImageView tabIcon = (ImageView) tab.findViewById(R.id.tab_icon);
            tabIcon.setImageResource(tabIcons[i]);
            tabLayout.getTabAt(i).setCustomView(tab);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            switch (position) {
                case 0:
                    return HomeFragment.newInstance();
                case 1:
                    return ProfilesFragment.newInstance(1);
                case 2:
                    return RemindersFragment.newInstance();
            }
            return HomeFragment.newInstance();
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position < tabTitles.length) {
                return tabTitles[position];
            }
            return null;
        }
    }

    public void onProfileFragmentInteraction(Profile profile) {

    }

    public void onFragmentInteraction(Uri uri){

    }
}

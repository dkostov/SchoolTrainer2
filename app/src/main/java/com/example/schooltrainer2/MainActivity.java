package com.example.schooltrainer2;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

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
     * A single exercise description fragment containing a simple view.
     */
    public static class ExerciseDescriptionFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String ARG_EXERCISE_NAME = "exercise_name";
        private static final String ARG_EXERCISE_DESCR = "exercise_descr";

        public ExerciseDescriptionFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static ExerciseDescriptionFragment newInstance(int sectionNumber, String sName, String sDescription) {
            ExerciseDescriptionFragment fragment = new ExerciseDescriptionFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putString(ARG_EXERCISE_NAME, sName);
            args.putString(ARG_EXERCISE_DESCR, sDescription);
            fragment.setArguments(args);
            return fragment;
        }

        @Override // Handle the visualization of the concrete exercise description page
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

            textView = (TextView) rootView.findViewById(R.id.txtExerciseName);
            textView.setText(getArguments().getString(ARG_EXERCISE_NAME));

            textView = (TextView) rootView.findViewById(R.id.txtDescription);
            textView.setText(getArguments().getString(ARG_EXERCISE_DESCR));

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        protected Vector<Exercise> vExercises = new Vector<Exercise>();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);

            vExercises.add(new Exercise("Division", "Practice division of integers."));
            vExercises.add(new Exercise("English to Bulgarian", "Translate English words to Bulgarian, while minding the accurate grammar."));
            vExercises.add(new Exercise("Bulgarian to English", "Train translation to English and the correct spelling."));
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a ExerciseDescriptionFragment (defined as a static inner class below).
            return ExerciseDescriptionFragment.newInstance(position + 1,
                    vExercises.get(position).sName,
                    vExercises.get(position).sDescription);
        }

        @Override
        public int getCount() {
            return vExercises.size();
        }
    }
}

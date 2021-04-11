package com.example.flow;

import android.content.Context;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.fragment.app.Fragment;
import devlight.io.library.ArcProgressStackView;



public class EventsFragment extends Fragment {


    private ArcProgressStackView mDateProgress;
    private TextView mTodayProgress;
    private TextView mWeekProgress;
    private TextView mMonthProgress;
    private TextView mYearProgress;
    void setupDateProgress() {
        String[] fgColors = getResources().getStringArray(R.array.date_progress_fg);
        String[] bgColors = getResources().getStringArray(R.array.date_progress_bg);

        int todayProgress = (int) DateTimeUtils.getTodayProgress();
        int weekProgress = (int) DateTimeUtils.getDayOfWeekProgress(AppPreferences.isMondayTheFirstDay(getContext()));
        int monthProgress = (int) DateTimeUtils.getMonthProgress();
        int yearProgress = (int) DateTimeUtils.getYearProgress();

        ArrayList<ArcProgressStackView.Model> dateModels = new ArrayList<>();
        dateModels.add(new ArcProgressStackView.Model(getString(R.string.day), todayProgress, Color.parseColor(bgColors[0]), Color.parseColor(fgColors[0])));
        dateModels.add(new ArcProgressStackView.Model(getString(R.string.week), weekProgress, Color.parseColor(bgColors[1]), Color.parseColor(fgColors[1])));
        dateModels.add(new ArcProgressStackView.Model(getString(R.string.month), monthProgress, Color.parseColor(bgColors[2]), Color.parseColor(fgColors[2])));
        dateModels.add(new ArcProgressStackView.Model(getString(R.string.year), yearProgress, Color.parseColor(bgColors[3]), Color.parseColor(fgColors[3])));

        mDateProgress = getView().findViewById(R.id.progress_date_arc);
        mDateProgress.setModels(dateModels);

        mTodayProgress = getView().findViewById(R.id.progress_today);
        mWeekProgress = getView().findViewById(R.id.progress_week);
        mMonthProgress = getView().findViewById(R.id.progress_month);
        mYearProgress = getView().findViewById(R.id.progress_year);

        mTodayProgress.setText(getString(R.string.progress_today_summary, DateTimeUtils.getCurrentDateTimeName(Calendar.DAY_OF_WEEK), todayProgress));
        mWeekProgress.setText(getString(R.string.progress_week_summary, weekProgress));
        mMonthProgress.setText(getString(R.string.progress_month_summary, DateTimeUtils.getCurrentDateTimeName(Calendar.MONTH), monthProgress));
        mYearProgress.setText(getString(R.string.progress_year_summary, DateTimeUtils.getCurrentDateTimeName(Calendar.YEAR), yearProgress));
    }

    public static class AppPreferences {
        private static final String PREF_IS_MONDAY_THE_FIRST_DAY = "IS_MONDAY_THE_FIRST_DAY";
        private static final boolean DEF_IS_MONDAY_THE_FIRST_DAY = true;
        public static boolean isMondayTheFirstDay(Context context) {
            return PreferenceManager.getDefaultSharedPreferences(context)
                    .getBoolean(PREF_IS_MONDAY_THE_FIRST_DAY, DEF_IS_MONDAY_THE_FIRST_DAY);
        }
    }
}

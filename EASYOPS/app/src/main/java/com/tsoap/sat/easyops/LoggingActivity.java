package com.tsoap.sat.easyops;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.FrameLayout;

import com.tsoap.sat.businessobject.BaseLogging;
import com.tsoap.sat.fragments.fragmentInteraction.UserLoggingInteraction;
import com.tsoap.sat.fragments.logging.LoggingAccountFragment;
import com.tsoap.sat.fragments.logging.LoggingCategoryFragment;
import com.tsoap.sat.fragments.logging.LoggingEfficiencyFragment;
import com.tsoap.sat.fragments.logging.LoggingExpenseFragment;
import com.tsoap.sat.fragments.logging.LoggingRouteFragment;
import com.tsoap.sat.utils.EasyOpsUtil;

import java.util.List;

public class LoggingActivity extends AppCompatActivity implements UserLoggingInteraction{
    private static final String LOGGING_TYPE = "Logging_type";
    private static final String LOG_TAG = LoggingActivity.class.getName();


    Toolbar appBar;

    List<BaseLogging> mList;

    FrameLayout frameLayout;

    EasyOpsUtil.loggingEnum loggingType;

    Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logging);

        Intent intent = getIntent();
       // = intent.getExtras().getString(LOGGING_TYPE);
        loggingType = (EasyOpsUtil.loggingEnum)getIntent().getSerializableExtra("LOGGING_TYPE");


        appBar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(appBar);

        frameLayout = (FrameLayout)findViewById(R.id.frame_layout);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.primaryColorDark));

        switch (loggingType) {
            case ROUTE:
                ft.add(R.id.frame_layout, new LoggingRouteFragment());
                window.setStatusBarColor(this.getResources().getColor(R.color.route_status_bar_theme));
                appBar.setBackgroundColor(getColor(R.color.route_theme));
                getSupportActionBar().setTitle("New RouteDetails Income");
                break;
            case EXPENSE:
                ft.add(R.id.frame_layout, LoggingExpenseFragment.newInstance());
                window.setStatusBarColor(this.getResources().getColor(R.color.expense_status_bar_theme));
                appBar.setBackgroundColor(getColor(R.color.expense_theme));
                getSupportActionBar().setTitle("New ExpenseCategory");
                break;
            case EFFICIENCY:
                ft.add(R.id.frame_layout, LoggingEfficiencyFragment.newInstance());
                window.setStatusBarColor(this.getResources().getColor(R.color.efficiency_status_bar_theme));
                appBar.setBackgroundColor(getColor(R.color.efficiency_theme));
                getSupportActionBar().setTitle("Efficiency");
                break;
            case ACCOUNT:
                ft.add(R.id.frame_layout, LoggingAccountFragment.newInstance());
                window.setStatusBarColor(this.getResources().getColor(R.color.route_status_bar_theme));
                appBar.setBackgroundColor(getColor(R.color.route_theme));
                getSupportActionBar().setTitle("Add Account");
                break;
            case EXPENSE_CATEGORY:
                ft.add(R.id.frame_layout, LoggingCategoryFragment.newInstance());
                window.setStatusBarColor(this.getResources().getColor(R.color.expense_status_bar_theme));
                appBar.setBackgroundColor(getColor(R.color.expense_theme));
                getSupportActionBar().setTitle("Add ExpenseCategory Category");
                break;


        }

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_logging, menu);
        mMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if(id == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void submit(BaseLogging data) {
        data.getParseObject().saveInBackground();
        finish();
    }

}

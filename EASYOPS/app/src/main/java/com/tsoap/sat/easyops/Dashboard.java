package com.tsoap.sat.easyops;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ScrollView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.tsoap.sat.businessobject.Overview;
import com.tsoap.sat.fragments.dashboard.ExpenseWidgetFragment;
import com.tsoap.sat.fragments.dashboard.ExpensiveRouteWidgets;
import com.tsoap.sat.fragments.dashboard.HotRoutesWidget;
import com.tsoap.sat.fragments.dashboard.NavigationDrawerFragment;
import com.tsoap.sat.fragments.dashboard.OverviewWidgetFragment;
import com.tsoap.sat.fragments.dashboard.VehicleServicingWidget;
import com.tsoap.sat.fragments.fragmentInteraction.NavigationInteraction;
import com.tsoap.sat.utils.EasyOpsUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Dashboard extends AppCompatActivity implements NavigationInteraction{
    private static final String LOGGING_TYPE = "LOGGING_TYPE";

    Toolbar appBar;

    @Bind(R.id.action_Task)
    FloatingActionButton actionTask;

    @Bind(R.id.action_Efficiency)
    FloatingActionButton actionEfficiency;

    @Bind(R.id.action_Expense)
    FloatingActionButton actionExpense;

    @Bind(R.id.action_Route)
    FloatingActionButton actionRoute;

    @Bind(R.id.dashboard_scroll)
    ScrollView mScrollView;


    @Bind(R.id.multiple_actions)
    FloatingActionsMenu multipleActions;


    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    BarChart barChart;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_cp_with_drawer);
        ButterKnife.bind(this);

        appBar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(appBar);

        FloatingActionsMenu menuMultipleActions = (FloatingActionsMenu) findViewById(R.id.multiple_actions);
        menuMultipleActions.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {
                 mScrollView.setAlpha(0.15f);
            }

            @Override
            public void onMenuCollapsed() {
                mScrollView.setAlpha(1);

            }
        });



        barChart = (BarChart)findViewById(R.id.barChart);
        pieChart = (PieChart)findViewById(R.id.pieChart);

        setDataToPieChart();
        setDataToBarChart();

        if ((!drawerLayout.isDrawerOpen(GravityCompat.START)) && Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.primaryColorDark));
        }else if((drawerLayout.isDrawerOpen(GravityCompat.START)) && Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.drawerNavigationFragment);
        drawerFragment.setUp(drawerLayout, appBar, menuMultipleActions);


    }

    private void setDataToPieChart() {


        List<Entry> piedataYs = new ArrayList<Entry>();
        Entry e1 = new Entry(1000,1);
        piedataYs.add(e1);
        Entry e2 = new Entry(4000,2);
        piedataYs.add(e2);
        Entry e3 = new Entry(500,3);
        piedataYs.add(e3);
        Entry e4 = new Entry(8000,4);
        piedataYs.add(e4);
        Entry e5 = new Entry(100,5);
        piedataYs.add(e5);
        Entry e6 = new Entry(10000,6);
        piedataYs.add(e6);

        PieDataSet pieDataSet = new PieDataSet(piedataYs,EasyOpsUtil.loggingEnum.EXPENSE.toString());


        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("e.1");
        xVals.add("e.2");
        xVals.add("e.3");
        xVals.add("e.4");

        xVals.add("e.5");
        xVals.add("e.6");

        PieData data = new PieData(xVals,pieDataSet);
        pieChart.setData(data);
        pieChart.invalidate();
    }

    private void setDataToBarChart() {

        List<BarEntry> incomeList = new ArrayList<BarEntry>();
        List<BarEntry> expenseList = new ArrayList<BarEntry>();
        List<BarEntry> revenueList = new ArrayList<BarEntry>();

        BarEntry e1m1 = new BarEntry(8000,1);
        incomeList.add(e1m1);
        BarEntry e1m2 = new BarEntry(7000,2);
        incomeList.add(e1m2);
        BarEntry e1m3 = new BarEntry(9000,3);
        incomeList.add(e1m3);
        BarEntry e2m1 = new BarEntry(2000,1);
        expenseList.add(e2m1);
        BarEntry e2m2 = new BarEntry(3000,2);
        expenseList.add(e2m2);
        BarEntry e2m3 = new BarEntry(3500,3);
        expenseList.add(e2m3);
        BarEntry e3m1 = new BarEntry(6000,1);
        revenueList.add(e3m1);
        BarEntry e3m2 = new BarEntry(4000,2);
        revenueList.add(e3m2);
        BarEntry e3m3 = new BarEntry(5500,3);
        revenueList.add(e3m3);

        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("1.m");
        xVals.add("2.m");
        xVals.add("3.m");
        BarDataSet incomeDataset = new BarDataSet(incomeList,"i");
        BarDataSet expenseDataset = new BarDataSet(expenseList,"e");
        BarDataSet revenueDataset = new BarDataSet(revenueList,"r");

        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(incomeDataset);
        dataSets.add(expenseDataset);
        dataSets.add(revenueDataset);

        BarData data = new BarData(xVals,dataSets);

        barChart.setData(data);

        barChart.invalidate();




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
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


    @OnClick(R.id.action_Route)
    public void newRoute(){
        Intent intent = new Intent(this,LoggingActivity.class);
        intent.putExtra(LOGGING_TYPE, EasyOpsUtil.loggingEnum.ROUTE);
        startActivity(intent);
    }

    @OnClick(R.id.action_Expense)
    public void newExpense(){
        Intent intent = new Intent(this,LoggingActivity.class);
        intent.putExtra(LOGGING_TYPE, EasyOpsUtil.loggingEnum.EXPENSE);
        startActivity(intent);
    }

    @OnClick(R.id.action_Efficiency)
    public void newEfficiency(){
        Intent intent = new Intent(this,LoggingActivity.class);
        intent.putExtra(LOGGING_TYPE, EasyOpsUtil.loggingEnum.EFFICIENCY);
        startActivity(intent);
    }

    @OnClick(R.id.action_Task)
    public void newTodotask(){
        Intent intent = new Intent(this,LoggingActivity.class);
        intent.putExtra(LOGGING_TYPE, EasyOpsUtil.loggingEnum.ROUTE);
        startActivity(intent);
    }

    @Override
    public void navigate() {

    }

    @Override
    public void menuItemClick(View view, int pos) {

        Intent intent = getNavigationIntent(pos);
        startActivity(intent);

    }

    private Intent getNavigationIntent(int pos) {

        Intent intent = null;
        switch (pos){
            case 0:

                intent = new Intent(this,Categories.class);
                intent.putExtra("CATEGORYTYPE",EasyOpsUtil.loggingEnum.ACCOUNT);
                break;
            case 3:

                intent = new Intent(this,Categories.class);
                intent.putExtra("CATEGORYTYPE",EasyOpsUtil.loggingEnum.EXPENSE_CATEGORY);
                break;
            case 1:

                intent = new Intent(this,DetailScreen.class);
                intent.putExtra("DETAILTYPE",EasyOpsUtil.loggingEnum.ROUTE);
                break;
            case 2:

                intent = new Intent(this,DetailScreen.class);
                intent.putExtra("DETAILTYPE",EasyOpsUtil.loggingEnum.EXPENSE);
                break;

            case 7:

                intent = new Intent(this,OverviewScreen.class);
                //intent.putExtra("DETAILTYPE",EasyOpsUtil.loggingEnum.EXPENSE);
                break;


        }
        return intent;
    }


}

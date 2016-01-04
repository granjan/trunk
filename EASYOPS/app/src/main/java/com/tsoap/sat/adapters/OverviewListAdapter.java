package com.tsoap.sat.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tonicartos.superslim.LayoutManager;
import com.tonicartos.superslim.LinearSLM;
import com.tsoap.sat.businessobject.Account;
import com.tsoap.sat.businessobject.ExpenseCategory;
import com.tsoap.sat.businessobject.Overview;
import com.tsoap.sat.easyops.R;
import com.tsoap.sat.utils.EasyOpsCache;
import com.tsoap.sat.utils.EasyOpsUtil;
import com.tsoap.sat.viewholders.ExpenseCategoryHolder;
import com.tsoap.sat.viewholders.OverviewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by nisheeth on 17/10/15.
 */
public class OverviewListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String LOG_TAG = OverviewListAdapter.class.getName();
    Context mContext;

    EasyOpsUtil.loggingEnum loggingEnum;
    Map<String,Double> mMap = new HashMap<String,Double>();

    List<ExpenseCategory> expenseCategoryList;
    List<Account> accountList;

    List mList = new ArrayList();
    EasyOpsCache instance;

    //Overview mOverview;

    public OverviewListAdapter(Context context){
        mContext = context;
    }

    public OverviewListAdapter(Context context, EasyOpsUtil.loggingEnum loggingEnum,Overview overview){
        mContext = context;
        //mOverview = overview;
        this.loggingEnum = loggingEnum;

        instance = EasyOpsCache.getInstance(mContext);
        switch (loggingEnum){
            case EXPENSE:
                mList = new ArrayList(overview.getExpenseListPerCategory().keySet());
                mMap = overview.getExpenseListPerCategory();
                break;
            case ROUTE:
                mList = new ArrayList(overview.getIncomePerAccount().keySet());
                mMap = overview.getIncomePerAccount();
                break;
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("inside Overview","onCreateViewHolder" );
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.list_item_overview, parent, false);
        RecyclerView.ViewHolder viewHolder = new OverviewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Log.d("inside Overview", "onBindViewHolder" + position);
        OverviewHolder holder = (OverviewHolder)viewHolder;
        View itemView = viewHolder.itemView;

        //holder.colorNotationView.setImageResource(R.color.route_theme);
        LayoutManager.LayoutParams params = (LayoutManager.LayoutParams) itemView.getLayoutParams();
        params.setSlm(LinearSLM.ID);
        holder.getCategory().setText(mList.get(position).toString());
        holder.getValue().setText("INR : " + mMap.get(mList.get(position)).toString());
        switch (loggingEnum) {
            case EXPENSE:
                holder.getValue().setTextColor(mContext.getColor(R.color.red500));
                holder.colorNotationView.setBackground(mContext.getDrawable(instance.getColorCodeMap().get(instance.getExpenseCategories().get(mList.get(position).toString()).getColorcode())));
                break;

            case ROUTE:
                holder.getValue().setTextColor(mContext.getColor(R.color.green700));
                holder.colorNotationView.setBackground(mContext.getDrawable(instance.getColorCodeMap().get(instance.getAccountMap().get(mList.get(position).toString()).getColorCode())));
                break;

        }
        params.setFirstPosition(0);
        itemView.setLayoutParams(params);
    }


    @Override
    public int getItemCount() {
        Log.d(LOG_TAG,"item count");
        return mList.size();
    }

}

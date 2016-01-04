package com.tsoap.sat.fragments.logging;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.tsoap.sat.businessobject.Efficiency;
import com.tsoap.sat.businessobject.UserProfile;
import com.tsoap.sat.easyops.R;
import com.tsoap.sat.fragments.fragmentInteraction.UserLoggingInteraction;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.tsoap.sat.fragments.fragmentInteraction.UserLoggingInteraction} interface
 * to handle interaction events.
 * Use the {@link LoggingEfficiencyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoggingEfficiencyFragment extends Fragment {

    @Bind(R.id.meter_reading)
    EditText meterReading;

    @Bind(R.id.workhours)
    EditText workhours;

    @Bind(R.id.attachment)
    Button attachment;

    @Bind(R.id.attachment_viewer)
    ImageView attachmentViewer;

    @Bind(R.id.scrollView)
    ScrollView scrollView;

    @Bind(R.id.submit)
    Button submit;


    private UserLoggingInteraction mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LoggingEfficiencyFragment.
     */
    public static LoggingEfficiencyFragment newInstance() {
        LoggingEfficiencyFragment fragment = new LoggingEfficiencyFragment();
        return fragment;
    }

    public LoggingEfficiencyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.logging_efficiency_fragment, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @OnClick(R.id.submit)
    public void submit() {
        Efficiency efficiencyData = validateForm();

        if (mListener != null && efficiencyData != null) {
            mListener.submit(efficiencyData);
        }
    }

    private Efficiency validateForm() {
        meterReading.setError(null);
        // mDrivingLicence.setError(null);

        boolean cancel = false;
        View focusView = null;

        String reading = meterReading.getText().toString();
        String hours = workhours.getText().toString();


        //String drivingLicence = mDrivingLicence.getText().toString();

        if (TextUtils.isEmpty(reading)) {
            meterReading.setError(getString(R.string.error_field_required));
            focusView = meterReading;
            cancel = true;
        }



        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            Efficiency data = new Efficiency();
            data.setMeterReading(reading);

            if(hours != null){
                data.setWorkHours(getActivity().getString(R.string.not_logged));
            }
            data.setWorkHours(hours);
            // profile.setDrivingLicence(drivingLicence);

           /* cal.setTime(new Date());
            String strdate = dateformat3.format(cal.getTime());
            data.setCreatedOn(strdate);
*/
            // TODO: camera attachment
            Byte[] b = new Byte[10];
            data.setMeterReadingAttachment(b);
            return data;
        }

        return null;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (UserLoggingInteraction) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

  /*  *//**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     *//*
    public interface OnEfficiencyDataSubmitListener {
        public void submitEfficiencyData(Efficiency efficiency);
    }*/

}

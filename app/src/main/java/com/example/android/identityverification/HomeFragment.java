package com.example.android.identityverification;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private String output;
    private OnFragmentInteractionListener mListener;
    TextView name,yob,gender,city,house,street,loc,pin;
    ProgressDialog progressDialog;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            output = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("This may take a while...");
        progressDialog.setTitle("Verifying your details");
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    progressDialog.dismiss();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        name = (TextView) rootView.findViewById(R.id.name);
        yob = (TextView) rootView.findViewById(R.id.yob);
        gender = (TextView) rootView.findViewById(R.id.gender);
        city = (TextView) rootView.findViewById(R.id.city);
        house = (TextView) rootView.findViewById(R.id.house);
        street = (TextView) rootView.findViewById(R.id.street);
        loc = (TextView) rootView.findViewById(R.id.loc);
        pin = (TextView) rootView.findViewById(R.id.pin);
        name.setText(output.split("=")[2].split(",")[0]);
        yob.setText(output.split("=")[4].split(",")[0]);
        gender.setText(output.split("=")[3].split(",")[0]);
        city.setText(output.split("=")[11].split(",")[0]);
        house.setText(output.split("=")[6].split(",")[0]);
        street.setText(output.split("=")[7].split(",")[0]);
        loc.setText(output.split("=")[8].split(",")[0]);
        pin.setText(output.split("=")[12].split(",")[0]);
//                intent.putExtra(output.split("=")[0],output.split("=")[1].split(",")[0]);
//                intent.putExtra(output.split("=")[1].split(",")[1],output.split("=")[2].split(",")[0]);
//                intent.putExtra(output.split("=")[2].split(",")[1],output.split("=")[3].split(",")[0]);
//                intent.putExtra(output.split("=")[3].split(",")[1],output.split("=")[4].split(",")[0]);
//                intent.putExtra(output.split("=")[4].split(",")[1],output.split("=")[5].split(",")[0]);
//                intent.putExtra(output.split("=")[5].split(",")[1],output.split("=")[6].split(",")[0]);
//                intent.putExtra(output.split("=")[6].split(",")[1],output.split("=")[7].split(",")[0]);
//                intent.putExtra(output.split("=")[7].split(",")[1],output.split("=")[8].split(",")[0]);
//                intent.putExtra(output.split("=")[8].split(",")[1],output.split("=")[9].split(",")[0]);
//                intent.putExtra(output.split("=")[9].split(",")[1],output.split("=")[10].split(",")[0]);
//                intent.putExtra(output.split("=")[10].split(",")[1],output.split("=")[11].split(",")[0]);
//                intent.putExtra(output.split("=")[11].split(",")[1],output.split("=")[12].split(",")[0]);
    return rootView;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}

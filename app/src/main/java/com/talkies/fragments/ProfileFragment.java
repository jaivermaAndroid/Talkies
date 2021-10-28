package com.talkies.fragments;

import static com.facebook.FacebookSdk.getApplicationContext;
import static com.talkies.utils.Constants.STORAGE_USER_DETAILS_RESPONSE;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.talkies.BuildConfig;
import com.talkies.R;
import com.talkies.activities.LoginActivity;
import com.talkies.callbacks.ProfileDetailCallBack;
import com.talkies.managers.ProfileDetailManager;
import com.talkies.model.UserDetails;
import com.talkies.utils.GeneralMethods;
import com.talkies.utils.MyPreferenceManager;

import org.json.JSONArray;
import org.json.JSONObject;

import io.paperdb.Paper;


public class ProfileFragment extends Fragment implements View.OnClickListener, ProfileDetailCallBack {

    ProfileDetailFragment profileDetailFragment;
    FrameLayout main_FL;
    LinearLayout layoutEditName, layoutEditNumber, layoutEditEmail, layoutContactUs, layoutAboutUs, layoutTellYourFriends;
    ImageView imageViewNameTitle, imageViewContactUsTitle, imageViewAboutUsTitle, imageViewTellUs, imageViewPurchaseNext;
    TextView tvName, tvPhone, tvEmail, tvAppVersion, tv_logout;
    UserDetails storageUserDetails;
    ScrollView scrollViewMainProfilePage, scrollViewProfileDetailPage;
    MyPurchaseFragment myPurchaseFragment;
    ProfileDetailCallBack mProfileDetailCallBack;
    GeneralMethods mGeneralMethods;
    SwitchCompat sw_notification;
    MyPreferenceManager prefManager;
    AlertDialog.Builder builder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profileDetailFragment = new ProfileDetailFragment();
        mGeneralMethods = new GeneralMethods(getContext(), getActivity());
        prefManager = new MyPreferenceManager(getContext());
        main_FL = view.findViewById(R.id.main_FL);
        mProfileDetailCallBack = this;
        tvName = view.findViewById(R.id.tv_name_profile);
        tvPhone = view.findViewById(R.id.tv_phone_profile);
        tvEmail = view.findViewById(R.id.tv_email_profile);
        tv_logout = view.findViewById(R.id.tv_logout);
        tvAppVersion = view.findViewById(R.id.tv_about_app_version_talkies_profile);
        scrollViewMainProfilePage = view.findViewById(R.id.profileMainPage);
//        scrollViewProfileDetailPage = view.findViewById(R.id.profileDetailPage);

        imageViewTellUs = view.findViewById(R.id.iv_tell_your_friends_next);
        sw_notification = view.findViewById(R.id.sw_notification);
        imageViewAboutUsTitle = view.findViewById(R.id.iv_about_talkies_next);
        imageViewNameTitle = view.findViewById(R.id.iv_name_next);
        imageViewContactUsTitle = view.findViewById(R.id.iv_contact_us_next);
        imageViewPurchaseNext = view.findViewById(R.id.iv_purchase_next);
        imageViewPurchaseNext.setOnClickListener(this);
        imageViewTellUs.setOnClickListener(this);
        imageViewAboutUsTitle.setOnClickListener(this);
        imageViewNameTitle.setOnClickListener(this);
        imageViewContactUsTitle.setOnClickListener(this);
        builder = new AlertDialog.Builder(requireContext());
        tv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Uncomment the below code to Set the message and title from the strings.xml file
                builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

                //Setting message manually and performing action on button click
                builder.setMessage(R.string.dialog_title)
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                new ProfileDetailManager(getActivity(), mProfileDetailCallBack, prefManager).logoutUser(mGeneralMethods);

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                Toast.makeText(requireContext(),"Thanks For Select No",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Logout");
                alert.show();

        }
        });

        sw_notification.setChecked(true);
        sw_notification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                prefManager.setNotificationEnable(isChecked);
            }
        });
    }

    @Override
    public void onResume() {
        Paper.book().read(STORAGE_USER_DETAILS_RESPONSE);
        storageUserDetails = Paper.book().read(STORAGE_USER_DETAILS_RESPONSE);
        try {
            PackageInfo pInfo = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
            String version = pInfo.versionName;
            tvAppVersion.setText("Version : " + version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (storageUserDetails != null) {

            tvName.setText(storageUserDetails.getFirstName());
            tvPhone.setText(storageUserDetails.getMobileNumber());
            tvEmail.setText(storageUserDetails.getEmail());
        }
        super.onResume();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_tell_your_friends_next:
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Talkies");
                    String shareMessage = "\nLet me recommend you this application\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch (Exception e) {
                    //e.toString();
                }
                break;
            case R.id.iv_about_talkies_next:
                fragmentAdder(R.id.main_FL, profileDetailFragment, "ProfileDetail", "ABout_US");

                break;
            case R.id.iv_name_next:
                fragmentAdder(R.id.main_FL, profileDetailFragment, "ProfileDetail", "Edit_Name");
                break;

            case R.id.iv_purchase_next:
                myPurchaseFragment = new MyPurchaseFragment();
                fragmentAdder(R.id.main_FL, myPurchaseFragment, "myPurchaseFragment");
                break;
            case R.id.iv_contact_us_next:
                fragmentAdder(R.id.main_FL, profileDetailFragment, "ProfileDetail", "Contact_Us");
                break;

            case R.id.tv_logout:
                new ProfileDetailManager(getActivity(), mProfileDetailCallBack, prefManager).logoutUser(mGeneralMethods);
                break;
        }

    }

    public void fragmentAdder(int containerId, Fragment fragment, String tag, String flagFor) {
        final FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerId, fragment, tag);
        Bundle args = new Bundle();
        args.putString(ProfileDetailFragment.DATA_FLAG, flagFor);
        fragment.setArguments(args);
        fragmentTransaction.addToBackStack("ProfileFragment");
        fragmentTransaction.commit();
    }

    public void fragmentAdder(int containerId, Fragment fragment, String tag) {
        final FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerId, fragment, tag);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragmentTransaction.addToBackStack("ProfileFragment");
        fragmentTransaction.commit();
    }


    @Override
    public void onSuccessFAQ(JSONArray result) {

    }

    @Override
    public void onSuccessFAQ(String result) {

    }

    @Override
    public void onFailureFAQ() {

    }

    @Override
    public void onSuccessUpdateName(JSONObject result) {

    }

    @Override
    public void onSuccessUpdateName(JSONArray result) {

    }

    @Override
    public void onSuccessUpdateName(String result) {

    }

    @Override
    public void onFailureUpdateName() {

    }

    @Override
    public void onSuccessIssueList(JSONArray result) {

    }

    @Override
    public void onSuccessIssueList(String result) {

    }

    @Override
    public void onFailureIssueList() {

    }

    @Override
    public void onSuccessRentalDetailsList(JSONArray result) {

    }

    @Override
    public void onSuccessRentalDetailsList(JSONObject result) {

    }

    @Override
    public void onSuccessRentalDetailsList(String result) {

    }

    @Override
    public void onFailureRentalDetailsList() {

    }

    @Override
    public void onSuccessSubmitQuery(JSONObject result) {

    }

    @Override
    public void onFailSubmitQuery() {

    }

    @Override
    public void onSuccessSubscriptionList(JSONArray result) {

    }

    @Override
    public void onSuccessSubscriptionList(JSONObject result) {

    }

    @Override
    public void onSuccessSubscriptionList(String result) {

    }

    @Override
    public void onFailureSubscriptionList() {

    }

    @Override
    public void onLogoutSuccess() {
        Intent mInfo = new Intent(getActivity(), LoginActivity.class);
        startActivity(mInfo);
    }

    @Override
    public void onLogoutFailure() {

    }

    @Override
    public void onSuccessRazorKeyResult(JSONObject result) {

    }


    @Override
    public void onFailureRazorKeyResult() {

    }

    @Override
    public void onSuccessConnectTv(JSONObject result) {

    }

    @Override
    public void onSuccessConnectTv(String result) {

    }

    @Override
    public void onFailure() {

    }
}

package com.talkies.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hbb20.CountryCodePicker;
import com.talkies.R;
import com.talkies.callbacks.ProfileDetailCallBack;
import com.talkies.managers.ProfileDetailManager;
import com.talkies.model.IssueModel;
import com.talkies.model.UserDetails;
import com.talkies.utils.GeneralMethods;
import com.talkies.utils.MyPreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import io.paperdb.Paper;

import static com.talkies.activities.MainActivity.toolAppBar;
import static com.talkies.utils.Constants.STORAGE_USER_DETAILS_RESPONSE;


public class ProfileDetailFragment extends Fragment implements  View.OnClickListener, ProfileDetailCallBack {

    final static String DATA_FLAG = "DATA_FLAG";
    String flagForVisibility;
    LinearLayout layoutEditNameDetail, layoutContactUsDetail ,layoutAboutUsDetail,
            layoutTellYourFriendsDetail,layoutPurchaseDetail;
    FAQFragment faqFragment;
    MyPurchaseFragment myPurchaseFragment;
    ProfileDetailFragment profileDetailFragment;
    ImageView imageViewFAQNext,imageViewPurchaseNext;
    ProfileDetailCallBack mProfileDetailCallBack;
    GeneralMethods mGeneralMethods;
    CountryCodePicker mEdtEmailCountryCode;
    EditText mEdtEmail,mEditPhone, mEditName,mEditLastName;
    EditText mEdtEmailSubmitQuery,mEditPhoneSubmitQuery, mEditNameSubmitQuery,mEditLastNameSubmitQuery ,mIssueDetailSubmitQuery;
    String countryCodeSubmitQuery,  emailSubmitQuery,  mobile_numberSubmitQuery, descriptionSubmitQuery;
    String countryCode,  email,  mobile_number, description, issueTitle ,  issueId;
    String name,  lastName;
    Button mBtnSaveName, mBtnSavePhone, mBtnSaveEmail,mBtnSubmitQuery;
    AppCompatSpinner spinIssueType;
    Gson gson;
    ImageView instagramLink, youTubeLink,facebookLink, twitterLink;
    MyPreferenceManager prefManager;
     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_detail_fragment, container, false);
    }
    public ProfileDetailFragment() {
        setRetainInstance(true);  //Hear put this line

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolAppBar.setNavigationIcon(R.drawable.ic_action_back);

        Log.d("zzz","profileDetailsFragment");

        profileDetailFragment = this;
        gson = new Gson();
        prefManager = new MyPreferenceManager(getContext());
        mProfileDetailCallBack= this;
        mGeneralMethods = new GeneralMethods(getContext(),getActivity());
        layoutAboutUsDetail=view.findViewById(R.id.ll_about_us_detail);
        layoutEditNameDetail=view.findViewById(R.id.ll_edit_name_detail);
        layoutContactUsDetail=view.findViewById(R.id.ll_contact_us_detail);
        layoutPurchaseDetail=view.findViewById(R.id.ll_purchase_detail);
        imageViewFAQNext=view.findViewById(R.id.iv_FAQ_next);
        imageViewFAQNext.setOnClickListener(this);

        mBtnSaveName=view.findViewById(R.id.buttonSaveNameProfile);
        mBtnSaveName.setOnClickListener(this);

        mBtnSubmitQuery=view.findViewById(R.id.buttonSubmitQuery);
        mBtnSubmitQuery.setOnClickListener(this);
        mEdtEmailCountryCode=view.findViewById(R.id.country_code_picker_submit_query);

        mEdtEmailSubmitQuery=view.findViewById(R.id.editTextEmailSubmitQuery);
        mEditPhoneSubmitQuery=view.findViewById(R.id.editTextPhoneLoginSubmitQuery);
        mEditPhoneSubmitQuery=view.findViewById(R.id.editTextPhoneLoginSubmitQuery);
        mIssueDetailSubmitQuery=view.findViewById(R.id.editTextHelpUsMore);
        spinIssueType = view.findViewById(R.id.spinnerIssue);

        mEditName=view.findViewById(R.id.editTextFirstName);


        instagramLink=view.findViewById(R.id.imageView_insta);
        youTubeLink=view.findViewById(R.id.imageView_you_tube);
        twitterLink=view.findViewById(R.id.imageView_twitter);
        facebookLink=view.findViewById(R.id.imageView_facebook);

        instagramLink.setOnClickListener(this);
        youTubeLink.setOnClickListener(this);
        twitterLink.setOnClickListener(this);
        facebookLink.setOnClickListener(this);

        toolAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentRemover(R.id.main_FL, profileDetailFragment, "ProfileDetail");
            }
        });


    }

    private void setUIVisibility() {
         visibilityForAllViewGone();
        if(!flagForVisibility.equalsIgnoreCase(""))
        {
            switch (flagForVisibility) {
                case "ABout_US":

                    layoutAboutUsDetail.setVisibility(View.VISIBLE);
                    break;
                case "Edit_Name":
                    layoutEditNameDetail.setVisibility(View.VISIBLE);
                    break;
                case "Contact_Us":
                    layoutContactUsDetail.setVisibility(View.VISIBLE);

                    new ProfileDetailManager(getActivity(), mProfileDetailCallBack,prefManager).getIssueTypeList(mGeneralMethods,"IssueList");

                    //Creating the ArrayAdapter instance having the country list

                    break;
                case "Purchase":
                   break;


            }
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
            flagForVisibility= args.getString(DATA_FLAG);
            setUIVisibility();
        }


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.iv_tell_your_friends_next:
                visibilityForAllViewGone();
                layoutAboutUsDetail.setVisibility(View.VISIBLE);
                break;

            case R.id.iv_FAQ_next:
                faqFragment = new FAQFragment();
                fragmentAdder(R.id.main_FL, faqFragment, "FAQFragment");
                break;



            case R.id.buttonSubmitQuery:
                //mEdit getText().toString();
               countryCode =mEdtEmailCountryCode.getSelectedCountryCode();
               emailSubmitQuery = mEdtEmailSubmitQuery.getText().toString();
               mobile_numberSubmitQuery = mEditPhoneSubmitQuery.getText().toString();
               descriptionSubmitQuery = mIssueDetailSubmitQuery.getText().toString();
               if(!issueId.equalsIgnoreCase("")){
                   new ProfileDetailManager(getActivity(), mProfileDetailCallBack,prefManager).submitQuery(mGeneralMethods,"submitQuery",countryCode,  emailSubmitQuery,  mobile_numberSubmitQuery, descriptionSubmitQuery,    issueTitle,issueId);

               }


                break;

            case R.id.iv_purchase_next:
                //mEdit getText().toString();
                myPurchaseFragment = new MyPurchaseFragment();
                fragmentAdder(R.id.main_FL, myPurchaseFragment, "myPurchaseFragment");

                break;

            case R.id.buttonSaveNameProfile:
                if(mEditName.getText().toString()!=null ||!mEditName.getText().toString().equalsIgnoreCase("")) {
                    new ProfileDetailManager(getActivity(), mProfileDetailCallBack,prefManager).saveName(mGeneralMethods, "saveName", mEditName.getText().toString(), mEditName.getText().toString());
                }
                break;
            case R.id.imageView_insta:
                String link = "https://www.instagram.com/talkiesapp/";
                showSocialLink(link);
                break;
            case R.id.imageView_facebook:
                String facebookLink = "https://www.facebook.com/talkiesappnow";
                showSocialLink(facebookLink);
                break;
            case R.id.imageView_you_tube:
                String uTube = "https://www.youtube.com/channel/UCscEPrZAybgWqq933rw9cOg";
                showSocialLink(uTube);
                break;
            case R.id.imageView_twitter:
                String twitter = "https://twitter.com/talkiesapp";
                showSocialLink(twitter);
                break;


        }
    }

    public void fragmentAdder(int containerId, Fragment fragment, String tag) {
        final FragmentTransaction fragmentTransaction =  getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerId, fragment, tag);Bundle args = new Bundle();
        fragment .setArguments(args);
        fragmentTransaction.addToBackStack("ProfileDetailFragment");
        fragmentTransaction.commit();
    }

    public void fragmentRemover(int containerId, Fragment fragment, String tag) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove(fragment);
        trans.commit();
        manager.popBackStack();
        toolAppBar.setNavigationIcon(null);
    }

    private void visibilityForAllViewGone() {
        layoutEditNameDetail.setVisibility(View.GONE);
        layoutContactUsDetail.setVisibility(View.GONE);
        layoutAboutUsDetail.setVisibility(View.GONE);
        layoutPurchaseDetail.setVisibility(View.GONE);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            //finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
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

        String jsonFileString = result.toString();
        Type type = new TypeToken<UserDetails>() {
        }.getType();
        UserDetails uiConfig = gson.fromJson(jsonFileString, type);
        Paper.book().write(STORAGE_USER_DETAILS_RESPONSE, uiConfig);
        fragmentRemover(R.id.main_FL, profileDetailFragment, "ProfileDetail");
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
        ArrayList<IssueModel> issueArray = new ArrayList<>();
        ArrayList<String> issueTitleArray = new ArrayList<>();

        issueArray.add(new IssueModel("0","Issues*"));
        issueTitleArray.add("Issues*");
        try {
            JSONArray issueList = new JSONArray(result);
            for(int issue= 0 ; issue<issueList.length();issue++)
            {
                JSONObject issueObject = issueList.getJSONObject(issue);
                issueArray.add(new IssueModel(issueObject.getString("id"),issueObject.getString("title")));
                issueTitleArray.add(issueObject.getString("title"));
            }



        
            //Setting the ArrayAdapter data on the Spinner
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_layout, issueTitleArray);
            spinIssueType.setAdapter(adapter);

            spinIssueType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    issueId = issueArray.get(position).getId();
                    issueTitle = issueArray.get(position).getIssue();
                    Log.e("onItemSelected: ", issueId + issueTitle);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        } catch (JSONException e) {


        }


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

        Toast.makeText(getContext(),"Issue Submited ",Toast.LENGTH_SHORT).show();
        fragmentRemover(R.id.main_FL, profileDetailFragment, "ProfileDetail");
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


    private void showSocialLink(String link)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(intent);
    }
}
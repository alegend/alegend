package com.alegend.www.al.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alegend.www.al.R;
import com.alegend.www.al.base.App;
import com.alegend.www.al.dataModel.DummyObject;
import com.alegend.www.al.dataModel.STestObject;
import com.alegend.www.al.network.ApiRequests;
import com.alegend.www.al.network.GsonGetRequest;
import com.alegend.www.al.network.GsonPostRequest;
import com.android.volley.Response;
import com.android.volley.VolleyError;


/**
 * Demonstrates how to make a JSON Object request
 */
public class JSONObjectActivity extends AppCompatActivity
{
    private static final String sTag = "tagOne";

    private TextView mTitle, mBody;
    private ProgressBar mProgressBar;
    private LinearLayout mContent, mErrorView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_object_request);

       // setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mTitle = (TextView) findViewById(R.id.my_title);
        mBody = (TextView) findViewById(R.id.my_body);
        mBody.setMovementMethod(new ScrollingMovementMethod());
        mErrorView = (LinearLayout) findViewById(R.id.error_view);
        mContent = (LinearLayout) findViewById(R.id.content);

        final GsonGetRequest<DummyObject> gsonGetRequest =
                ApiRequests.getDummyObject
                        (
                                new Response.Listener<DummyObject>() {
                                    @Override
                                    public void onResponse(DummyObject dummyObject) {
                                        // Deal with the DummyObject here
                                        mProgressBar.setVisibility(View.GONE);
                                        mContent.setVisibility(View.VISIBLE);
                                        setData(dummyObject);
                                    }
                                }
                                ,
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        // Deal with the error here
                                        mProgressBar.setVisibility(View.GONE);
                                        mErrorView.setVisibility(View.VISIBLE);
                                    }
                                }
                        );
        final GsonPostRequest<STestObject> stTest = ApiRequests.getTest(
                new Response.Listener<STestObject>() {
                    @Override
                    public void onResponse(STestObject dummyObject) {
                        // Deal with the DummyObject here
                        mProgressBar.setVisibility(View.GONE);
                        mContent.setVisibility(View.VISIBLE);
                        setData1(dummyObject);
                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Deal with the error here
                        mProgressBar.setVisibility(View.GONE);
                        mErrorView.setVisibility(View.VISIBLE);
                    }
                }
        );
        App.addRequest(stTest, sTag);
    }

    @Override
    protected void onStop()
    {
        App.cancelAllRequests(sTag);

        super.onStop();
    }

    /**
     * Sets the data in the UI
     *
     * @param dummyObject is the object to get the data from
     */
    private void setData(@NonNull final DummyObject dummyObject)
    {
        mTitle.setText(dummyObject.getTitle());
        mBody.setText(dummyObject.getBody());
    }

    /**
     * Sets the data in the UI
     *
     * @param dummyObject is the object to get the data from
     */
    private void setData1(@NonNull final STestObject dummyObject)
    {
//        mTitle.setText(dummyObject.getTitle());
//        mBody.setText(dummyObject.getBody());
    }
}

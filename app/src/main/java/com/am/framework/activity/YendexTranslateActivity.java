package com.am.framework.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.am.framework.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.am.framework.utils.CONST.ARABIC;
import static com.am.framework.utils.CONST.ENGLISH;
import static com.am.framework.utils.CONST.YENDEX_API_KEY;
import static com.am.framework.utils.CONST.YENDEX_BASE_URL;

//TODO : Change the Volley Request to Retrofit
public class YendexTranslateActivity extends BaseActivity {

    private static final String TAG = YendexTranslateActivity.class.getSimpleName();

    @BindView(R.id.btn_translate)
    Button mTranslateBtn;
    @BindView(R.id.txt_orginal_text)
    TextView mOriginalTextET;
    @BindView(R.id.txt_translated_text)
    TextView mTranslatedTextTV;
    @BindView(R.id.spinner_original_lang)
    Spinner mOriginalLangSpinner;
    @BindView(R.id.spinner_destination_language)
    Spinner mDestinationLangSpinner;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        showToolbarBackArrow();

        requestQueue = Volley.newRequestQueue(this);

        initListener();
        initSpinners();
    }

    private void initSpinners() {
        List<String> languagesList = new ArrayList<>();
        languagesList.add(ENGLISH);
        languagesList.add(ARABIC);

        ArrayAdapter<String> languagesListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, languagesList);
        languagesListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mOriginalLangSpinner.setAdapter(languagesListAdapter);
        mDestinationLangSpinner.setAdapter(languagesListAdapter);

        mOriginalLangSpinner.setSelection(0);
        mDestinationLangSpinner.setSelection(1);
    }


    private void initListener() {
        mTranslateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String originalText = mOriginalTextET.getText().toString();
                String destinationLangCode = mDestinationLangSpinner.getSelectedItem().toString();
                String originalLangCode = mOriginalLangSpinner.getSelectedItem().toString();
                String langsParm = originalLangCode + "-" + destinationLangCode;
                String finalUrl = YENDEX_BASE_URL + YENDEX_API_KEY + "&text=" + originalText + "&lang=" + langsParm;

                JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, finalUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse:response:" + response);
                        try {
                            String translation = response.getString("text");
                            //Getting the characters between [ & ]
                            translation = translation.substring(translation.indexOf('[') + 1);
                            translation = translation.substring(0, translation.indexOf("]"));
                            //Getting the characters between " & "
                            translation = translation.substring(translation.indexOf("\"") + 1);
                            translation = translation.substring(0, translation.indexOf("\""));
                            mTranslatedTextTV.setText(translation);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "onErrorResponse:error", error);
                    }
                });
                requestQueue.add(jsObjRequest);
            }
        });
    }




}

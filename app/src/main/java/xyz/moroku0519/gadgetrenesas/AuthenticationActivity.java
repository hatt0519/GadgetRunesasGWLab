package xyz.moroku0519.gadgetrenesas;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.mlkcca.client.DataStore;
import com.mlkcca.client.MilkCocoa;

import xyz.moroku0519.gadgetrenesas.milkcocoa.MilkcocoaAdapter;


/**
 * Created by kazuki on 2016/11/16.
 */

public class AuthenticationActivity extends Activity {

    private final static String APP_ID_DOMAIN = ".mlkcca.com";

    private final static String APP_ID = "guitariw0hgcep";
    private final static String DATASTORE = "esp8266";

    private EditText mAppId;
    private EditText mDataStore;
    private BootstrapButton mButton;
    private MilkCocoa mMilkcocoa;
    private DataStore mDataStoreMessanger;

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(isValidate()) {
                mButton.setEnabled(true);
            } else {
                mButton.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentication);

        mAppId = (EditText) findViewById(R.id.app_id);
        mDataStore = (EditText) findViewById(R.id.data_store);
        mButton = (BootstrapButton) findViewById(R.id.send);

        mAppId.setText(APP_ID);
        mDataStore.setText(DATASTORE);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectMilkcocoa();
                accessDataStore();
                MilkcocoaAdapter milkcocoaAdapter = MilkcocoaAdapter.getInstance();
                milkcocoaAdapter.setMilkocoa(mMilkcocoa);
                milkcocoaAdapter.setDataStore(mDataStoreMessanger);
                Toast.makeText(getApplicationContext(), getString(R.string.connect_success), Toast.LENGTH_LONG).show();
                finish();
            }
        });

        mAppId.addTextChangedListener(mTextWatcher);
        mDataStore.addTextChangedListener(mTextWatcher);

        if(!isNetworkconnected()) {
            Toast.makeText(this, getString(R.string.disable_network), Toast.LENGTH_LONG);
            mButton.setEnabled(false);
        }

        mButton.performClick();

    }

    private void connectMilkcocoa() {
        String app_id = mAppId.getText().toString() + APP_ID_DOMAIN;
        mMilkcocoa = new MilkCocoa(app_id);

    }

    private void accessDataStore() {
        if (mMilkcocoa == null) {
            return;
        }
        mDataStoreMessanger = mMilkcocoa.dataStore(mDataStore.getText().toString());
    }

    private boolean isValidate(){
        if(TextUtils.isEmpty(mAppId.getText().toString())){
            return false;
        }
        if(TextUtils.isEmpty(mDataStore.getText().toString())){
            return false;
        }
        return true;
    }

    private boolean isNetworkconnected() {
        // Context 経由でインスタンスを取得する
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info.isConnected();
    }
}

package xyz.moroku0519.gadgetrenesas;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.mlkcca.client.DataStore;
import com.mlkcca.client.MilkCocoa;

import xyz.moroku0519.gadgetrenesas.milkcocoa.MilkcocoaAdapter;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by kazuki on 2016/11/16.
 */

public class AuthenticationActivity extends Activity {

    private final static String APP_ID_DOMAIN = "mlkcca.com";

    private EditText mAppId;
    private EditText mDataStore;
    private BootstrapButton mButton;
    private MilkCocoa mMilkcocoa;
    private DataStore mDataStoreMessanger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentication);

        mAppId = (EditText) findViewById(R.id.app_id);
        mDataStore = (EditText) findViewById(R.id.data_store);
        mButton = (BootstrapButton) findViewById(R.id.send);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectMilkcocoa();
                accessDataStore();
                if(mMilkcocoa != null && mDataStoreMessanger != null) {
                    MilkcocoaAdapter milkcocoaAdapter = MilkcocoaAdapter.getInstance();
                    milkcocoaAdapter.setMilkocoa(mMilkcocoa);
                    milkcocoaAdapter.setDataStore(mDataStoreMessanger);
                    finish();
                    Toast.makeText(getApplicationContext(), getString(R.string.connect_success), LENGTH_SHORT).show();
                }
            }
        });

    }

    private void connectMilkcocoa() {
        if (mAppId.getText() == null) {
            return;
        }
        String app_id = mAppId.getText().toString() + APP_ID_DOMAIN;
        try {
            mMilkcocoa = new MilkCocoa(app_id);
        } catch (Exception e) {
            Toast.makeText(this, getString(R.string.invalid_app_id), LENGTH_SHORT).show();
            return;
        }

    }

    private void accessDataStore() {
        if (mDataStore.getText() == null) {
            return;
        }
        try {
            if(mMilkcocoa != null) {
                mDataStoreMessanger = mMilkcocoa.dataStore(mDataStore.getText().toString());
            }
        } catch (Exception e) {
            Toast.makeText(this, getString(R.string.invalid_data_store), LENGTH_SHORT).show();
            return;
        }
    }
}

package xyz.moroku0519.gadgetrenesas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand;
import com.mlkcca.client.DataElement;
import com.mlkcca.client.DataElementValue;
import com.mlkcca.client.DataStore;
import com.mlkcca.client.DataStoreEventListener;
import com.mlkcca.client.MilkCocoa;

import xyz.moroku0519.gadgetrenesas.entity.Command;
import xyz.moroku0519.gadgetrenesas.entity.Mode;
import xyz.moroku0519.gadgetrenesas.milkcocoa.MilkcocoaAdapter;


public class MainActivity extends Activity implements DataStoreEventListener{

    private BootstrapButton mEcho;
    private BootstrapButton mRadio;
    private BootstrapButton mLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startAuthencationActivity();

        mEcho = (BootstrapButton) findViewById(R.id.echo);
        mRadio = (BootstrapButton) findViewById(R.id.radio);
        mLine = (BootstrapButton) findViewById(R.id.line);

        BootstrapButton logout = (BootstrapButton) findViewById(R.id.logout);

        mEcho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEcho.setBootstrapBrand(DefaultBootstrapBrand.DANGER);
                mRadio.setBootstrapBrand(DefaultBootstrapBrand.PRIMARY);
                mLine.setBootstrapBrand(DefaultBootstrapBrand.PRIMARY);
                setEchoCommand();
            }
        });

        mRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEcho.setBootstrapBrand(DefaultBootstrapBrand.PRIMARY);
                mRadio.setBootstrapBrand(DefaultBootstrapBrand.DANGER);
                mLine.setBootstrapBrand(DefaultBootstrapBrand.PRIMARY);
                Intent intent = new Intent(getApplicationContext(), RadioActivity.class);
                startActivity(intent);
            }
        });

        mLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEcho.setBootstrapBrand(DefaultBootstrapBrand.PRIMARY);
                mRadio.setBootstrapBrand(DefaultBootstrapBrand.PRIMARY);
                mLine.setBootstrapBrand(DefaultBootstrapBrand.DANGER);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
                Toast.makeText(getApplicationContext(), getString(R.string.connect_out), Toast.LENGTH_LONG).show();
                MilkcocoaAdapter milkcocoaAdapter = MilkcocoaAdapter.getInstance();
                startAuthencationActivity();
            }
        });



    }

    private void setEchoCommand() {
        MilkcocoaAdapter milkcocoaAdapter = MilkcocoaAdapter.getInstance();
        DataElementValue echo = new DataElementValue();
        try {
            echo.put("mode", Mode.ECHO.id);
            echo.put("command", Command.STOP.id);
        } catch (Exception e) {

        }
        milkcocoaAdapter.getDataStore().push(echo);
    }

    @Override
    public void onRestart() {
        super.onRestart();
        mEcho.setBootstrapBrand(DefaultBootstrapBrand.PRIMARY);
        mRadio.setBootstrapBrand(DefaultBootstrapBrand.PRIMARY);
        mLine.setBootstrapBrand(DefaultBootstrapBrand.PRIMARY);
    }


    @Override
    public void onPushed(DataElement dataElement) {

    }

    @Override
    public void onSetted(DataElement dataElement) {

    }

    @Override
    public void onSended(DataElement dataElement) {

    }

    @Override
    public void onRemoved(DataElement dataElement) {

    }

    private void startAuthencationActivity() {
        Intent intent = new Intent(getApplicationContext(), AuthenticationActivity.class);
        startActivity(intent);
    }

    private void logout() {
        MilkcocoaAdapter milkcocoaAdapter = MilkcocoaAdapter.getInstance();
        milkcocoaAdapter.getMilkcocoa().logout();
        milkcocoaAdapter.setMilkocoa(null);
        milkcocoaAdapter.setDataStore(null);
    }
}

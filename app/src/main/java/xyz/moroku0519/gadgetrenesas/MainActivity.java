package xyz.moroku0519.gadgetrenesas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.mlkcca.client.DataElement;
import com.mlkcca.client.DataElementValue;
import com.mlkcca.client.DataStoreEventListener;

import xyz.moroku0519.gadgetrenesas.entity.Command;
import xyz.moroku0519.gadgetrenesas.entity.Mode;
import xyz.moroku0519.gadgetrenesas.milkcocoa.MilkcocoaAdapter;


public class MainActivity extends Activity implements DataStoreEventListener{

    private Button mEcho;
    private Button mRadio;
    private boolean mIsEchoOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startAuthencationActivity();

        mEcho = (Button) findViewById(R.id.echo);
        mRadio = (Button) findViewById(R.id.radio);
        mIsEchoOn = false;

        BootstrapButton logout = (BootstrapButton) findViewById(R.id.logout);

        mEcho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEchoCommand();
                if(mIsEchoOn) {
                    mEcho.setBackground(getResources().getDrawable(R.drawable.echo_button_off));
                    mIsEchoOn = false;
                } else {
                    mEcho.setBackground(getResources().getDrawable(R.drawable.echo_button_on));
                    mIsEchoOn = true;
                }
            }
        });

        mRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RadioActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
                Toast.makeText(getApplicationContext(), getString(R.string.connect_out), Toast.LENGTH_LONG).show();
                startAuthencationActivity();
            }
        });



    }

    private void setEchoCommand() {
        MilkcocoaAdapter milkcocoaAdapter = MilkcocoaAdapter.getInstance();
        DataElementValue echo = new DataElementValue();
        try {
            Mode mode = mIsEchoOn ? Mode.OFF : Mode.ECHO;
            echo.put("mode", mode.id);
            echo.put("command", Command.STOP.id);
        } catch (Exception e) {

        }
        milkcocoaAdapter.getDataStore().push(echo);
    }

    @Override
    public void onRestart() {
        super.onRestart();
        mEcho.setBackground(getResources().getDrawable(R.drawable.echo_button_off));
        mRadio.setBackground(getResources().getDrawable(R.drawable.radio_button));
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

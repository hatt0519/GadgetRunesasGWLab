package xyz.moroku0519.gadgetrenesas;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.mlkcca.client.DataElement;
import com.mlkcca.client.DataElementValue;
import com.mlkcca.client.DataStore;
import com.mlkcca.client.DataStoreEventListener;
import com.mlkcca.client.MilkCocoa;

import xyz.moroku0519.gadgetrenesas.milkcocoa.MilkcocoaAdapter;

/**
 * Created by kazuki on 2016/11/15.
 */

public class RadioActivity extends Activity  implements DataStoreEventListener{

    private BootstrapButton mBack;
    private BootstrapButton mUp;
    private BootstrapButton mLeft;
    private BootstrapButton mRight;
    private BootstrapButton mDown;
    private ImageButton mAbutton;
    private ImageButton mBbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radio);

        mBack = (BootstrapButton) findViewById(R.id.back);
        mUp = (BootstrapButton) findViewById(R.id.up);
        mLeft = (BootstrapButton) findViewById(R.id.left);
        mRight = (BootstrapButton) findViewById(R.id.right);
        mDown = (BootstrapButton) findViewById(R.id.down);

        mAbutton = (ImageButton) findViewById(R.id.Abutton);
        mBbutton = (ImageButton) findViewById(R.id.Bbutton);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

        mUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushCommand(Command.UP);
            }
        });

        mDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushCommand(Command.DOWN);
            }
        });

        mRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushCommand(Command.RIGHT);
            }
        });

        mLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushCommand(Command.LEFT);
            }
        });

        mAbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushCommand(Command.STOP);
            }
        });

        mBbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushCommand(Command.STOP);
            }
        });

    }

    private void pushCommand(Command key) {
        MilkcocoaAdapter milkcocoaAdapter = MilkcocoaAdapter.getInstance();
        DataElementValue echo = new DataElementValue();
        try {
            echo.put("mode", 0);
            echo.put("command", key.id);
        } catch (Exception e) {

        }
        milkcocoaAdapter.getDataStore().push(echo);
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

    private enum Command {
        UP(0), DOWN(1), RIGHT(2), LEFT(3), STOP(4);

        public final int id;

        private Command(int key) {
            id = key;
        }
    }

}

package xyz.moroku0519.gadgetrenesas;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.mlkcca.client.DataElement;
import com.mlkcca.client.DataElementValue;
import com.mlkcca.client.DataStoreEventListener;

import xyz.moroku0519.gadgetrenesas.entity.Command;
import xyz.moroku0519.gadgetrenesas.entity.Mode;
import xyz.moroku0519.gadgetrenesas.milkcocoa.MilkcocoaAdapter;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_UP;

/**
 * Created by kazuki on 2016/11/15.
 */

public class RadioActivity extends Activity implements DataStoreEventListener {

    private Button mUp;
    private Button mDown;
    private Button mLeft;
    private Button mRight;

    private SoundPool mSoundPool;
    private int mUpVoice;
    private int mDownVoice;
    private int mRightVoice;
    private int mLeftVoice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radio);

        Button back = (Button) findViewById(R.id.back);
        mUp = (Button) findViewById(R.id.up);
        mLeft = (Button) findViewById(R.id.left);
        mRight = (Button) findViewById(R.id.right);
        mDown = (Button) findViewById(R.id.down);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mUp.setOnTouchListener(new TouchListener(mUp));

        mDown.setOnTouchListener(new TouchListener(mDown));

        mRight.setOnTouchListener(new TouchListener(mRight));

        mLeft.setOnTouchListener(new TouchListener(mLeft));

        mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        mUpVoice = mSoundPool.load(this, R.raw.kei_voice_119, 1);
        mDownVoice = mSoundPool.load(this, R.raw.kei_voice_120, 1);
        mRightVoice = mSoundPool.load(this, R.raw.kei_voice_121, 1);
        mLeftVoice = mSoundPool.load(this, R.raw.kei_voice_122, 1);

    }

    private void pushCommand(Command key) {
        MilkcocoaAdapter milkcocoaAdapter = MilkcocoaAdapter.getInstance();
        DataElementValue echo = new DataElementValue();
        try {
            echo.put("mode", Mode.RADIO.id);
            echo.put("command", key.id);
        } catch (Exception e) {

        }
        milkcocoaAdapter.getDataStore().push(echo);
    }

    private void sound(Command key) {
        switch(key) {
            case UP:
                mSoundPool.play(mUpVoice, 0.5f, 0.5f, 0, 0, 1);
                break;
            case DOWN:
                mSoundPool.play(mDownVoice, 0.5f, 0.5f, 0, 0, 1);
                break;
            case RIGHT:
                mSoundPool.play(mRightVoice, 0.5f, 0.5f, 0, 0, 1);
                break;
            case LEFT:
                mSoundPool.play(mLeftVoice, 0.5f, 0.5f, 0, 0, 1);
                break;
            default:
                break;
        }
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

    private class TouchListener implements View.OnTouchListener{

        private Button mControllerButton;
        private Command mCommandActionDown;
        private Command mCommandActionUp;

        public TouchListener(Button controllerButton) {
            mControllerButton = controllerButton;
        }

        private void setCommand() {
            switch(mControllerButton.getId()) {
                case R.id.up:
                    mCommandActionDown = Command.UP;
                    break;
                case R.id.down:
                    mCommandActionDown = Command.DOWN;
                    break;
                case R.id.right:
                    mCommandActionDown = Command.RIGHT;
                    break;
                case R.id.left:
                    mCommandActionDown = Command.LEFT;
                    break;
                default:
                    break;
            }
            mCommandActionUp = Command.STOP;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            setCommand();
            switch(event.getAction()) {
                case ACTION_DOWN:
                    pushCommand(mCommandActionDown);
                    sound(mCommandActionDown);
                    break;
                case ACTION_UP:
                    pushCommand(mCommandActionUp);
                    break;
            }
            return false;
        }
    }

}

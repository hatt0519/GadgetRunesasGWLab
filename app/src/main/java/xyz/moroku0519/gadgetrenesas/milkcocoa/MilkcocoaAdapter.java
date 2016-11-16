package xyz.moroku0519.gadgetrenesas.milkcocoa;

import com.mlkcca.client.DataStore;
import com.mlkcca.client.MilkCocoa;

/**
 * Created by kazuki on 2016/11/16.
 */
public class MilkcocoaAdapter {
    private static MilkcocoaAdapter ourInstance = new MilkcocoaAdapter();

    public static MilkcocoaAdapter getInstance() {
        return ourInstance;
    }

    private MilkCocoa mMilkcocoa;
    private DataStore mMessagesDataStore;

    private MilkcocoaAdapter() {
    }

    public void setMilkocoa(MilkCocoa milkcocoa) {
        mMilkcocoa = milkcocoa;
    }

    public void setDataStore(DataStore dataStore) {
        mMessagesDataStore = dataStore;
    }

    public MilkCocoa getMilkcocoa() {
        return mMilkcocoa;
    }

    public DataStore getDataStore() {
        return mMessagesDataStore;
    }
}

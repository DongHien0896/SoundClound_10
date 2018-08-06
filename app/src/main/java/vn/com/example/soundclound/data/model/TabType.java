package vn.com.example.soundclound.data.model;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static vn.com.example.soundclound.data.model.TabType.OFFLINE;
import static vn.com.example.soundclound.data.model.TabType.ONLINE;

@IntDef({OFFLINE, ONLINE})
@Retention(RetentionPolicy.SOURCE)
public @interface TabType {
    int OFFLINE = 0;
    int ONLINE = 1;
}

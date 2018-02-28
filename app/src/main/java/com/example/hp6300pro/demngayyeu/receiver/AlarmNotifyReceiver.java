package com.example.hp6300pro.demngayyeu.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.example.hp6300pro.demngayyeu.R;
import com.example.hp6300pro.demngayyeu.activity.MainActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by HP 6300 Pro on 12/16/2017.
 */

public class AlarmNotifyReceiver extends BroadcastReceiver {

    private RemoteViews mContentView;
    private NotificationCompat.Builder mNotify;
    private NotificationCompat.BigTextStyle mBigText = new NotificationCompat.BigTextStyle();
    private NotificationManager mNotifyManager;
    public static final int NOTIFY_ID = 5;

    public static final String ALARM_VALENTINE = "ALARM_VALENTINE";
    public static final String ALARM_83 = "ALARM_83";
    public static final String ALARM_TET = "ALARM_TET";
    public static final String ALARM_100 = "ALARM_100";
    public static final String ALARM_1NAM = "ALARM_1NAM";
    public static final String SANG_7H = "SANG_7H";
    public static String TYPE_OF_ALARM = "";
    public int mTimeStart;

    private Calendar mCa83 = Calendar.getInstance(),
            mCa142 = Calendar.getInstance(),
            mCaTET = Calendar.getInstance(),
            mCa100 = Calendar.getInstance(),
            mCa365 = Calendar.getInstance();
    private Calendar mNgayLe = Calendar.getInstance();

    private String timeToDateString(long mTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(new Date(mTime));
    }

    private Date stringToDate(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = formatter.parse(str);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    long mTime, mTime100, mTime365;

    @Override
    public void onReceive(Context context, Intent intent) {
        mTime = intent.getLongExtra(MainActivity.TIME_START, 0);
        mTime100 = mTime + 8640000000l;
        mTime365 = mTime + 31536000000l;

        mCa100.setTime(stringToDate(timeToDateString(mTime100)));
        mCa365.setTime(stringToDate(timeToDateString(mTime365)));
        mCa83.set(Calendar.getInstance().get(Calendar.YEAR), 2, 8);
        mCa142.set(Calendar.getInstance().get(Calendar.YEAR), 1, 14);
        mCaTET.set(Calendar.getInstance().get(Calendar.YEAR), 0, 1);

        if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == mCa83.get(Calendar.DAY_OF_MONTH)
                && Calendar.getInstance().get(Calendar.MONTH) == mCa83.get(Calendar.MONTH)) {
            TYPE_OF_ALARM = ALARM_83;
        } else if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == mCa142.get(Calendar.DAY_OF_MONTH)
                && Calendar.getInstance().get(Calendar.MONTH) == mCa142.get(Calendar.MONTH)) {
            TYPE_OF_ALARM = ALARM_VALENTINE;
        } else if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == mCaTET.get(Calendar.DAY_OF_MONTH)
                && Calendar.getInstance().get(Calendar.MONTH) == mCaTET.get(Calendar.MONTH)) {
            TYPE_OF_ALARM = ALARM_TET;
        } else if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == mCa100.get(Calendar.DAY_OF_MONTH)
                && Calendar.getInstance().get(Calendar.MONTH) == mCa100.get(Calendar.MONTH)) {
            TYPE_OF_ALARM = ALARM_100;
        } else if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == mCa365.get(Calendar.DAY_OF_MONTH)
                && Calendar.getInstance().get(Calendar.MONTH) == mCa365.get(Calendar.MONTH)
                && Calendar.getInstance().get(Calendar.YEAR) == mCa365.get(Calendar.YEAR)) {
            TYPE_OF_ALARM = ALARM_1NAM;
        } else if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == 7) {
            TYPE_OF_ALARM = SANG_7H;
        }

        switch (TYPE_OF_ALARM) {
            case ALARM_VALENTINE:
                pushNotify("VALENTINE", context, "Em yêu à… Anh hứa sẽ luôn nắm chặt tay em, cùng em đi đến cuối con đường tình yêu của hai chúng mình. Chúc em một Valentine hạnh phúc.");
                break;
            case ALARM_83:
                pushNotify("8/3", context, "Em yêu hôm nay là ngày quốc tế phụ nữ ngày dành riêng để thể hiện lòng biết ơn sự quan tâm đến phụ nữ. Anh muốn gửi tới em trọn vẹn khối óc và trái tim anh, gửi tới em trọn vẹn tấm lòng anh, gửi tới em tất cả những gì cao quý và thiêng liêng nhất của anh.");
                break;
            case ALARM_TET:
                pushNotify("TẾT", context, "Năm mới đến rồi một năm qua thật đặc biệt với anh và cả em nữa. Năm mới đến thế là a lại được nối dài năm tháng được yêu thương chăm sóc em. Anh chúc em tràn đầy hạnh phúc ngọt ngào hãy gìn giữ và nuôi dưỡng tình yêu của chúng mình em nhé.");
                break;
            case ALARM_100:
                pushNotify("100 ngày yêu rồi đấy", context, "Chúng ta đã cùng vượt qua những khoảng thời gian khó khăn, em yêu. Anh chỉ muốn cho tất cả mọi người biết rằng: em là duy nhất của anh... Anh yêu em rất nhiều.");
                break;
            case ALARM_1NAM:
                pushNotify("Kỷ niệm 1 năm yêu nhau", context, "Mình đã trải qua 365 ngày yêu rồi đấy. À mà k phải,có những ngày k yêu,rõ là ghét,là khó chịu với a. Còn có những ngày trôi qua trong im lặng, vừa nhớ vừa chỉ muốn phát điên. Nhưng mà những cái ngày đấy k thể thiếu để có được những ngày vui vẻ và hạnh phúc anh nhỉ.");
                break;
            case SANG_7H:
                pushNotify("Let's continue... ^^~", context, "2 bạn đã yêu nhau được " + ((getCurrentTimeFromCurrentCalendar() - mTime) / 86400000) + " ngày. Hãy tiếp tục tình yêu đó ^^~");
                break;
            default:
                pushNotify("Let's continue... ^^~", context, "2 bạn đã yêu nhau được " + ((getCurrentTimeFromCurrentCalendar() - mTime) / 86400000) + " ngày. Hãy tiếp tục tình yêu đó ^^~");
                break;
        }
    }

    private long getCurrentTimeFromCurrentCalendar() {
        long cal = Calendar.getInstance().getTimeInMillis();
        return cal;
    }

    public static int mNotificationId = 0;

    private void pushNotify(String title, Context context, String msg) {

        NotificationCompat.Builder mBuilder;

        mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.icon_app)
                .setContentTitle(title)
                .setContentText(msg)
                .setDefaults(
                        Notification.DEFAULT_SOUND
                                | Notification.DEFAULT_VIBRATE)
                .setOngoing(false);

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(contentIntent);

        NotificationManager mNotifyMgr = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }

}

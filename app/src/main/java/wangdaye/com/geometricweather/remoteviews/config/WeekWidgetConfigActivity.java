package wangdaye.com.geometricweather.remoteviews.config;

import android.view.View;
import android.widget.RemoteViews;

import wangdaye.com.geometricweather.R;
import wangdaye.com.geometricweather.remoteviews.presenter.WeekWidgetIMP;

/**
 * Week widget config activity.
 * */

public class WeekWidgetConfigActivity extends AbstractWidgetConfigActivity {

    @Override
    public void initView() {
        super.initView();
        viewTypeContainer.setVisibility(View.GONE);
        hideSubtitleContainer.setVisibility(View.GONE);
        subtitleDataContainer.setVisibility(View.GONE);
        clockFontContainer.setVisibility(View.GONE);
    }

    @Override
    public RemoteViews getRemoteViews() {
        return WeekWidgetIMP.getRemoteViews(
                this, getLocationNow(),
                cardStyleValueNow, cardAlpha, textColorValueNow, textSize
        );
    }

    @Override
    public String getSharedPreferencesName() {
        return getString(R.string.sp_widget_week_setting);
    }
}
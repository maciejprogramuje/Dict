package commaciejprogramuje.facebook.dict;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by 5742ZGPC on 2017-09-03.
 */

public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        WidgetDataProvider dataProvider = new WidgetDataProvider(getApplicationContext(), intent);
        return dataProvider;
    }
}

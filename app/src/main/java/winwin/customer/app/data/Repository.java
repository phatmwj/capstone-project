package winwin.customer.app.data;

import winwin.customer.app.data.local.prefs.PreferencesService;
import winwin.customer.app.data.local.room.RoomService;
import winwin.customer.app.data.remote.ApiService;


public interface Repository {

    /**
     * ################################## Preference section ##################################
     */
    String getToken();

    void setToken(String token);

    PreferencesService getSharedPreferences();


    /**
     * ################################## Remote api ##################################
     */
    ApiService getApiService();

    RoomService getRoomService();

}

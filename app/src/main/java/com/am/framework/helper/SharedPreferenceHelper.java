package com.am.framework.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.am.fansrepublic.adapter.recycler.expandable_recyclerview.Child;
import com.am.fansrepublic.model.user_data.UserInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.SortedSet;
import java.util.TreeSet;


public class SharedPreferenceHelper {
    private static SharedPreferenceHelper instance = null;
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    private static String CHAMPS_NAMES_DB = "userInfo";

    private SharedPreferenceHelper() {
    }

    public static SharedPreferenceHelper getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferenceHelper();
            preferences = context.getSharedPreferences(CHAMPS_NAMES_DB, Context.MODE_PRIVATE);
            editor = preferences.edit();
            editor.apply();
        }
        return instance;
    }


    public void saveCompetitionName(int id, String name, String logo) {

        editor.putString(String.valueOf(id), name);
        editor.putString(String.valueOf(id) + "f", logo);
        editor.apply();
    }


    public void saveUserInfo(UserInfo userInfo) {
        editor.putInt("id", userInfo.getId());
        editor.putString("display_name", userInfo.getDisplayName());
        editor.putString("username", userInfo.getUsername());
        editor.putString("avatar", userInfo.getAvatar());
        editor.putString("teamLogo", userInfo.getTeamLogo());
        editor.putString("natTeamLogo", userInfo.getNatTeamLogo());
        editor.putInt("following", userInfo.getFollowing());
        editor.putInt("followers", userInfo.getFollowers());
        editor.apply();
    }

    public UserInfo getUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(Integer.valueOf(preferences.getString("id", "")));
        userInfo.setDisplayName(preferences.getString("display_name", ""));
        userInfo.setUsername(preferences.getString("username", ""));
        userInfo.setAvatar(preferences.getString("avatar", ""));
        userInfo.setTeamLogo(preferences.getString("teamLogo", ""));
        userInfo.setNatTeamLogo(preferences.getString("natTeamLogo", ""));
        userInfo.setFollowing(Integer.valueOf(preferences.getString("following", "")));
        userInfo.setFollowers(Integer.valueOf(preferences.getString("followers", "")));
        return userInfo;
    }


    public String getCompetitionName(int id) {
        return preferences.getString(String.valueOf(id), "Competition Not Found");
    }


    public String getCompetitionLogo(int id) {
        return preferences.getString(String.valueOf(id) + "f", "Competition Not Found");
    }


    public void saveCountryName(int id, String name) {
        editor.putString(String.valueOf(id), name);
        editor.apply();

    }

    public void saveUserToken(String token) {
        editor.putString("token", token);
        editor.apply();
    }

    public void addCompatIdToFav(String id) {
        Log.d("wwww", "addCompatIdToFav() called with: id = [" + id + "]");
        editor.putBoolean(id + "ff", true);
        editor.apply();

    }


    public void SSaddFavId(String id) {
        Log.d("sss", "SSaddFavId() called with: id = [" + id + "]");
        Gson gson = new Gson();
        SortedSet<String> favsCompats = SSgetFavIds();
        if (favsCompats == null) {
            favsCompats = new TreeSet<>();
        }
        favsCompats.add(id);
        String json = gson.toJson(id);
        Log.d("sss", "json: "+json);
        editor.putString("favsList", json);
        editor.apply();
    }

    public SortedSet<String> SSgetFavIds() {
        Log.d("sss", "SSgetFavIds() called");
        Type listType = new TypeToken<SortedSet<String>>() {}.getType();
        preferences.getString("favsList", "no no");
        Gson gson = new Gson();
        String json = preferences.getString("favsList", "");
        Log.d("sss", "favsList: "+json);
        return gson.fromJson(json, listType);
    }



    public void SSremoveFavId(String id) {
        Log.d("sss", "SSremoveFavId() called with: id = [" + id + "]");
        SortedSet<String> favsCompats = SSgetFavIds();
        if (favsCompats != null) {
            if (favsCompats.contains(id)) {
                favsCompats.remove(id);
            }
        }
        editor.apply();
    }

    public boolean SSisFavId(String id) {
        Log.d("sss", "SSisFavId() called with: id = [" + id + "]");
        SortedSet<String> favsCompats = SSgetFavIds();
        if (favsCompats != null) {
            if (favsCompats.contains(id)) {
                return true;
            }
        }
        return false;
    }




    public void removeCompatIdToFav(String id) {
        editor.putBoolean(id + "ff", false);
        editor.apply();
    }

    public boolean isCompatIdFav(String id) {
        boolean bb = preferences.getBoolean(id + "ff", false);
        Log.d("wwww", "isCompatIdFav() called with: id = [" + id + "]" + "result = [" + bb + "]");
        return bb;
    }


    public void addCompetitionToFav(Child child) {
        Gson gson = new Gson();

        SortedSet<Child> children = getChildren();
        if (children == null) {
            children = new TreeSet<>();
        }
        children.add(child);
        String json = gson.toJson(children);
        editor.putString("children", json);
        editor.apply();
    }

    public void cleanCompetitionToFav() {
        Gson gson = new Gson();
        SortedSet<Child> children = new TreeSet<>();
        String json = gson.toJson(children);
        editor.putString("children", json);
        editor.apply();
    }

    public SortedSet<Child> getChildren() {
        Type listType = new TypeToken<SortedSet<Child>>() {
        }.getType();
        preferences.getString("children", "no no");
        Gson gson = new Gson();
        String json = preferences.getString("children", "");
        Log.d("ttt", "getChildren:" + json);
        Log.d("ttt", "getChildren:gson.fromJson(json, listType):" + gson.fromJson(json, listType));

        return gson.fromJson(json, listType);
    }


    public String getUserToken() {
        return preferences.getString("token", "");
    }


    public String getCountryName(int id) {
        return preferences.getString(String.valueOf(id), "Country Not Found");
    }


    public String getUserId() {
        return preferences.getString("UserId", "User Id Not Found");
    }

    public void saveUserId(String userId) {
        editor.putString("UserId", userId);
        editor.apply();
    }

    public void saveUserName(String userName) {
        editor.putString("userName", userName);
        editor.apply();
    }

    public String getUserName() {
        return preferences.getString("userName", "");
    }

}

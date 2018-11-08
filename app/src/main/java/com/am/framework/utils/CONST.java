package com.am.framework.utils;

public class CONST {
    //Youtube API
    public static final String YOUTUBE_API_KEY = "AIzaSyDDHTAP0cLsSt_ZV3CRZo3HYXlI5pxJlzY";
    public final static String BASE_YOUTUBE_VID_URL = "http://www.youtube.com/watch?v=";


    //The Movies Database
    public static final String MDB_BASE_PATH = "https://api.themoviedb.org";
    public static final String MDB_BASE_POSTER_PATH = "https://image.tmdb.org/t/p/w500";
    public static final String MDB_API_KEY = "5113c42b53daf9e85a4239d8b7be45a5";

    //Yandex Translator
    public static final String YENDEX_BASE_URL = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=";
    public static final String YENDEX_API_KEY = "trnsl.1.1.20171216T184548Z.4dbf620b106c8c65.8fb70dae9aac55f4ed24968b2891c490afa029b9";
    public static final String ENGLISH = "en";
    public static final String ARABIC = "ar";


    //Time in Milliseconds
    private static final long MINUTE_MILLISECONDS = 1000 * 60;
    private static final long HOUR_MILLISECONDS = MINUTE_MILLISECONDS * 60;
    private static final long DAY_MILLISECONDS = HOUR_MILLISECONDS * 24;
    long timeNow = System.currentTimeMillis();







}

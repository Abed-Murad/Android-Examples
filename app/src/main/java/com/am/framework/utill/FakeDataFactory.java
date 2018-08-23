package com.am.framework.utill;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.am.framework.data.WaitlistContract;
import com.am.framework.model.Item;

import java.util.ArrayList;
import java.util.List;

// TODO (1) Add the factory pattern to this class
public class FakeDataFactory {


    //Dummy
    public static final String DUMMY_IMG_URL_FOOTBALL = "https://i.imgur.com/5H2WatB.jpg";
    public static final String DUMMY_IMG_URL_STARS_AT_NIGHT = "https://i.imgur.com/UexSTU5.png";
    public static final String DUMMY_IMG_URL_QUEEN = "https://i.imgur.com/50WwTJP.png";
    public static final String DUMMY_IMG_THUNDER_CAT = "https://i.imgur.com/ta6iyNE.png";


    String[] dummyWeatherData = {
            "Today, May 17 - Clear - 17°C / 15°C",
            "Tomorrow - Cloudy - 19°C / 15°C",
            "Thursday - Rainy- 30°C / 11°C",
            "Friday - Thunderstorms - 21°C / 9°C",
            "Saturday - Thunderstorms - 16°C / 7°C",
            "Sunday - Rainy - 16°C / 8°C",
            "Monday - Partly Cloudy - 15°C / 10°C",
            "Tue, May 24 - Meatballs - 16°C / 18°C",
            "Wed, May 25 - Cloudy - 19°C / 15°C",
            "Thu, May 26 - Stormy - 30°C / 11°C",
            "Fri, May 27 - Hurricane - 21°C / 9°C",
            "Sat, May 28 - Meteors - 16°C / 7°C",
            "Sun, May 29 - Apocalypse - 16°C / 8°C",
            "Mon, May 30 - Post Apocalypse - 15°C / 10°C",
    };

    /**
     * @return A list of popular toys
     */
    public static String[] getToyNames(String google) {
        return new String[]{
                "Red Toy Wagon", "Chemistry Set", "Yo-Yo", "Pop-Up Book",
                "Generic Uncopyrighted Mouse", "Finger Paint", "Sock Monkey", "Microscope Set",
                "Beach Ball", "BB Gun", "Green Soldiers", "Bubbles", "Spring Toy",
                "Fortune Telling Ball", "Plastic Connecting Blocks", "Water Balloon",
                "Paint-by-Numbers Kit", "Tuber Head", "Cool Ball with Holes in It",
                "Toy Truck", "Flying Disc", "Two-Handed Pogo Stick", "Toy Hoop",
                "Dysmorphia Doll", "Toy Train", "Fake Vomit", "Toy Telephone", "Barrel of Primates",
                "Remote Control Car", "Square Puzzle Cube", "Football",
                "Intergalactic Electronic Phasers", "Baby Horse Dolls",
                "Machines that turn into other Machines",
                "Teddy Bears", "Shaved Ice Maker", "Glow Stick", "Squirt Guns",
                "Miniature Replica Animals Stuffed with Beads that you swore to your parents would be worth lots of money one day",
                "Creepy Gremlin Doll", "Neodymium-Magnet Toy"
        };
    }


    public static void insertFakeData(SQLiteDatabase db) {
        if (db == null) {
            return;
        }
        //create a list of fake guests
        List<ContentValues> list = new ArrayList<ContentValues>();

        ContentValues cv = new ContentValues();
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME, "John");
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE, 12);
        list.add(cv);

        cv = new ContentValues();
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME, "Tim");
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE, 2);
        list.add(cv);

        cv = new ContentValues();
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME, "Jessica");
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE, 99);
        list.add(cv);

        cv = new ContentValues();
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME, "Larry");
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE, 1);
        list.add(cv);

        cv = new ContentValues();
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME, "Kim");
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE, 45);
        list.add(cv);

        //insert all guests in one transaction
        try {
            db.beginTransaction();
            //clear the table first
            //db.delete (WaitlistContract.WaitlistEntry.TABLE_NAME,null,null);
            //go through the list and addLast one by one
            for (ContentValues c : list) {
                db.insert(WaitlistContract.WaitlistEntry.TABLE_NAME, null, c);
            }
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            //too bad :(
        } finally {
            db.endTransaction();
        }

    }


    public static List<Item> getFakeItemList() {
        List<Item> list = new ArrayList<Item>();

        Item item1 = new Item(1, "John", "Manger");
        list.add(item1);

        Item item2 = new Item(2, "Tim", "Developer");
        list.add(item2);

        Item item3 = new Item(3, "Jessica", "Marketing");
        list.add(item3);

        Item item4 = new Item(4, "Larry", "CEO");
        list.add(item4);

        Item item5 = new Item(5, "Kim", "Designer");
        list.add(item5);

        return list;

    }


}

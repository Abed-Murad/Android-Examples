package com.am.framework.dummy;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.am.framework.R;
import com.am.framework.data.sqlite.WaitListContract;
import com.am.framework.model.BoardingPassInfo;
import com.am.framework.model.Item;
import com.am.framework.model.SliderImage;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

// TODO (1) Add the factory pattern to this class
public class DummyDataFactory {

    //Dummy
    public static final String IMG_URL_FOOTBALL = "https://i.imgur.com/5H2WatB.jpg";
    public static final String IMG_URL_STARS = "https://i.imgur.com/UexSTU5.png";
    public static final String IMG_URL_QUEEN = "https://i.imgur.com/50WwTJP.png";
    public static final String IMG_URL_CAT = "https://i.imgur.com/ta6iyNE.png";

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
            "Mon, May 30 - Post Apocalypse - 15°C / 10°C",};

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
        List<ContentValues> list = new ArrayList<>();

        ContentValues cv = new ContentValues();
        cv.put(WaitListContract.WaitListEntry.COLUMN_GUEST_NAME, "John");
        cv.put(WaitListContract.WaitListEntry.COLUMN_PARTY_SIZE, 12);
        list.add(cv);

        cv = new ContentValues();
        cv.put(WaitListContract.WaitListEntry.COLUMN_GUEST_NAME, "Tim");
        cv.put(WaitListContract.WaitListEntry.COLUMN_PARTY_SIZE, 2);
        list.add(cv);

        cv = new ContentValues();
        cv.put(WaitListContract.WaitListEntry.COLUMN_GUEST_NAME, "Jessica");
        cv.put(WaitListContract.WaitListEntry.COLUMN_PARTY_SIZE, 99);
        list.add(cv);

        cv = new ContentValues();
        cv.put(WaitListContract.WaitListEntry.COLUMN_GUEST_NAME, "Larry");
        cv.put(WaitListContract.WaitListEntry.COLUMN_PARTY_SIZE, 1);
        list.add(cv);

        cv = new ContentValues();
        cv.put(WaitListContract.WaitListEntry.COLUMN_GUEST_NAME, "Kim");
        cv.put(WaitListContract.WaitListEntry.COLUMN_PARTY_SIZE, 45);
        list.add(cv);

        //insert all guests in one transaction
        try {
            db.beginTransaction();
            //clear the table first
            //db.delete (WaitListContract.WaitListEntry.TABLE_NAME,null,null);
            //go through the list and addLast one by one
            for (ContentValues c : list) {
                db.insert(WaitListContract.WaitListEntry.TABLE_NAME, null, c);
            }
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            //too bad :(
        } finally {
            db.endTransaction();
        }
    }

    public static List<Item> getFakeItemList() {
        List<Item> list = new ArrayList<>();
        Item item1 = new Item(1, "John", "Manger");
        Item item2 = new Item(2, "Tim", "Developer");
        Item item3 = new Item(3, "Jessica", "Marketing");
        Item item4 = new Item(4, "Larry", "CEO");
        Item item5 = new Item(5, "Kim", "Designer");
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.add(item5);

        return list;
    }


    public static ArrayList<SliderImage> getFakeSliderImagesList() {
        ArrayList<SliderImage> imagesList = new ArrayList<>();
        imagesList.add(new SliderImage("Football Field", IMG_URL_FOOTBALL, IMG_URL_FOOTBALL, IMG_URL_FOOTBALL, "2018-05-06"));
        imagesList.add(new SliderImage("Stars At Night", IMG_URL_STARS, IMG_URL_STARS, IMG_URL_STARS, "2000-01-09"));
        imagesList.add(new SliderImage("Best Band Ever", IMG_URL_QUEEN, IMG_URL_QUEEN, IMG_URL_QUEEN, "2014-12-04"));
        imagesList.add(new SliderImage("Thunder Cat Meme", IMG_URL_CAT, IMG_URL_CAT, IMG_URL_CAT, "2020-02-20"));
        return imagesList;
    }



    /**
     * Generates fake boarding pass data to be displayed.
     * @return fake boarding pass data
     */
    public static BoardingPassInfo generateFakeBoardingPassInfo() {

        BoardingPassInfo bpi = new BoardingPassInfo();

        bpi.passengerName = "MR. RANDOM PERSON";
        bpi.flightCode = "UD 777";
        bpi.originCode = "JFK";
        bpi.destCode = "DCA";

        long now = System.currentTimeMillis();

        // Anything from 0 minutes up to (but not including) 30 minutes
        long randomMinutesUntilBoarding = (long) (Math.random() * 30);
        // Standard 40 minute boarding time
        long totalBoardingMinutes = 40;
        // Anything from 1 hours up to (but not including) 6 hours
        long randomFlightLengthHours = (long) (Math.random() * 5 + 1);

        long boardingMillis = now + minutesToMillis(randomMinutesUntilBoarding);
        long departure = boardingMillis + minutesToMillis(totalBoardingMinutes);
        long arrival = departure + hoursToMillis(randomFlightLengthHours);

        bpi.boardingTime = new Timestamp(boardingMillis);
        bpi.departureTime = new Timestamp(departure);
        bpi.arrivalTime = new Timestamp(arrival);
        bpi.departureTerminal = "3A";
        bpi.departureGate = "33C";
        bpi.seatNumber = "1A";
        bpi.barCodeImageResource = R.drawable.art_plane;

        return bpi;
    }

    private static long minutesToMillis(long minutes) {
        return TimeUnit.MINUTES.toMillis(minutes);
    }

    private static long hoursToMillis(long hours) {
        return TimeUnit.HOURS.toMillis(hours);
    }
}

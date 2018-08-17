package com.am.framework.utill;

// TODO (1) Add the factory pattern to this class
public class MockDataFactory {



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
        return new String[] {
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


}

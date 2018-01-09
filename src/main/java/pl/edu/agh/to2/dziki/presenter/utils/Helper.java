package pl.edu.agh.to2.dziki.presenter.utils;

public class Helper {
    private static final String TITLE = "USER MANUAL";
    private static final String HEADER = "Useful commands list and how to use them";
    private static final String CONTENT =
                    "    * FORWARD [value] - move forward by value\n" +
                    "    * BACKWARD [value] - move backward by value\n" +
                    "    * LEFT [value] - move left by value\n" +
                    "    * RIGHT [value] - move right by value\n" +
                    "    * TURN [angle] - turn model by angle\n" +
                    "    * CIRCLE [value] - print circle with radius = value\n" +
                    "    * LOOP [value] [TASK] ENDLOOP - repeat TASK \'value\' times\n" +
                    "    * HIDE - hide model, any move interrupt operation\n" +
                    "    * SHOW - show model again\n" +
                    "    * LIFT - lift model, model cannot print anything on canvas\n" +
                    "    * LOWER - lower model, model can print on canvas\n" +
                    "    * RESTART - set model on initial coordinate\n";


    public static String getTITLE() {
        return TITLE;
    }

    public static String getHEADER() {
        return HEADER;
    }

    public static String getCONTENT() {
        return CONTENT;
    }
}

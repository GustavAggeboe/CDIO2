public class Field {
    private String name = "Udefineret feltnavn";
    private int space = 0;
    private int value = 0;
    private boolean grantsBonusTurn = false;
    private String landingDescription = "Udefineret feltbeskrivelse";

    public enum FieldEffects {
        neutral,
        positive,
        negative
    }

    private FieldEffects fieldEffect = FieldEffects.neutral;

    public Field(String name, int space, FieldEffects fieldEffect, int value, boolean grantsBonusTurn,
            String landingDescription) {
        this.name = name;
        this.space = space;
        this.fieldEffect = fieldEffect;
        this.value = value;
        this.grantsBonusTurn = grantsBonusTurn;
        this.landingDescription = landingDescription;
    }

    public String getName() {
        return name;
    }

    public int getSpace() {
        return space;
    }

    public FieldEffects getFieldEffect() {
        return fieldEffect;
    }

    public String getLandingDescription() {
        return landingDescription;
    }

    public int getValue() {
        return value;
    }

    public boolean grantsBonusTurn() {
        return grantsBonusTurn;
    }

    public static Field[] initializeFields() {
        // Laver et nyt array af fields
        Field[] newFields = new Field[Dicegame.noOfFields];

        // Kalder addFieldToList() og definerer fields'ne.
        addFieldToList(newFields, "Tower", 2, FieldEffects.positive, 250, false,
                "Nice! you found a lot of gold, you got 250 money =)"); // Her indtastes beskrivelse for feltet
        addFieldToList(newFields, "Crater", 3, FieldEffects.negative, 100, false,
                "You did not find anything, you lost 100 money for expenses");
        addFieldToList(newFields, "Palace Gates", 4, FieldEffects.positive, 100, false,
                "You found a bit of gold, you got 100 money");
        addFieldToList(newFields, "Cold Desert", 5, FieldEffects.negative, 20, false,
                "There are nothing in the dessert, you lost 20 money expenses");
        addFieldToList(newFields, "Walled City", 6, FieldEffects.positive, 180, false,
                "You found a nice share of gold, you got 180 money");
        addFieldToList(newFields, "Monastery", 7, FieldEffects.neutral, 0, false,
                "Its not your day, but you found enough to get even, you got 0 money");
        addFieldToList(newFields, "Black Cave", 8, FieldEffects.negative, 70, false,
                "Thats too bad, you found nothing, you lost 70 gold");
        addFieldToList(newFields, "Huts in the Mountain", 9, FieldEffects.positive, 60, false,
                "You did not find a lot of gold on top of the mountain but a bit, you got 60 money");
        addFieldToList(newFields, "The Werewall", 10, FieldEffects.negative, 80, true,
                "Unlucky, you lost 80 gold, but you get an extra turn =)");
        addFieldToList(newFields, "The Pit", 11, FieldEffects.negative, 50, false,
                "you fell in the Pit, you lost 50 money to get up");
        addFieldToList(newFields, "Goldmine", 12, FieldEffects.positive, 650, false,
                "\nYou hit the JACKPOT, You are rich!! you got 650 gold\n");

        // Returnerer den nye liste af fields
        return newFields;
    }

    private static void addFieldToList(Field[] fieldsArray, String name, int space, FieldEffects fieldEffect,
            int value,
            boolean grantsBonusTurn,
            String landingDescription) {
        // Denne funktion kigger bare i array'et 'fieldsArray', givet i starten af
        // funktionen, og leder efter den første tomme plads, og fylder den ind med
        // værdierne.
        // Dette gør det lettere at lave et nyt felt, uden at skulle tænke på, hvilken
        // plads i array'et den har. *host host* lists...
        for (int fieldIndex = 0; fieldIndex < fieldsArray.length; fieldIndex++) {
            if (fieldsArray[fieldIndex] == null) {
                fieldsArray[fieldIndex] = new Field(name, space, fieldEffect, value,
                        grantsBonusTurn, landingDescription);
                return; // retuner, så den kun bliver lagt i én plads
            }
        }
    }
}

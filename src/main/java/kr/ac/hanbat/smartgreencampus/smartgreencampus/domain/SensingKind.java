package kr.ac.hanbat.smartgreencampus.smartgreencampus.domain;

public enum SensingKind {
    SUNSHINE, HUMIDITY, RAINFALL;

    public static SensingKind transform(String str) {
        SensingKind sensingKind;
        switch (str) {
            case "SUNSHINE" -> sensingKind = SUNSHINE;
            case "HUMIDITY" -> sensingKind = HUMIDITY;
            case "RAINFALL" -> sensingKind = RAINFALL;
            default -> throw new IllegalStateException("Unexpected value: " + str);
        }

        return sensingKind;
    }
}

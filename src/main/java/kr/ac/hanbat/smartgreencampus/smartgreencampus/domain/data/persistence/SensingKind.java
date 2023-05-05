package kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.persistence;

public enum SensingKind {
    Irradi1, Irradi2, Thermostat_Amb_Humidity, Thermostat_Amb_Temperature;

    public static SensingKind of(final String str) {
        final SensingKind sensingKind;

        switch (str) {
            case "Irradi1" -> sensingKind = Irradi1;
            case "Irradi2" -> sensingKind = Irradi2;
            case "Thermostat_Amb_Humidity" -> sensingKind = Thermostat_Amb_Humidity;
            case "Thermostat_Amb_Temperature" -> sensingKind = Thermostat_Amb_Temperature;
            default -> throw new IllegalStateException("Unexpected value: " + str);
        }

        return sensingKind;
    }
}

package kr.ac.hanbat.smartgreencampus.smartgreencampus.domain.data.persistence;

public enum Measurement {
    Irradi1, Irradi2, Thermostat_Amb_Humidity, Thermostat_Amb_Temperature;

    public static Measurement of(final String str) {
        final Measurement measurement;

        switch (str) {
            case "Irradi1" -> measurement = Irradi1;
            case "Irradi2" -> measurement = Irradi2;
            case "Thermostat_Amb_Humidity" -> measurement = Thermostat_Amb_Humidity;
            case "Thermostat_Amb_Temperature" -> measurement = Thermostat_Amb_Temperature;
            default -> throw new IllegalStateException("Unexpected value: " + str);
        }

        return measurement;
    }
}

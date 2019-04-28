import java.time.LocalDateTime;

public class Weather {
    private static final String seasons[] = {
            "Winter", "Winter",
            "Spring", "Spring", "Spring",
            "Summer", "Summer", "Summer",
            "Fall", "Fall", "Fall",
            "Winter"
    };

    public String getSeason() {
        return seasons[LocalDateTime.now().getMonthValue()];
    }

    public double seasonDelay() {
        switch (getSeason()){
            case "Summer":
                return 1.0;

            case "Spring":
                return 0.85;

            case "Fall":
                return 0.70;

            default:
                return 0.65;
        }
    }
}

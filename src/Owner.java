import java.time.LocalDateTime;
import java.util.ArrayList;

public class Owner extends Users{
    private int rating;
    private ArrayList<String> rentalHistoric;

    public Owner(String email, String name, String address, LocalDateTime dateOfBirth) {
        super(email, name, address, dateOfBirth);
        this.rating = 0;
        this.rentalHistoric = new ArrayList<>();
    }
}

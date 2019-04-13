import java.util.ArrayList;

public class RentalBase {
    ArrayList<Rental> rentalBase;

    static private int id;
    public RentalBase() {
        this.rentalBase = new ArrayList<>();
        this.id = -1;
    }

    public int addRental(Rental r) {
        id++;
        this.rentalBase.add(id, r.clone());
        return id;
    }
}

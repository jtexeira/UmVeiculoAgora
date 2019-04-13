import java.util.ArrayList;

public class Car {
    private String numberPlate;
    private CarType type;
    private double avgSpeed;
    private double basePrice;
    private double gasMilage;
    private ArrayList<Rental> rentalHistoric;
    private int rating;

    private Point position;
    private double range;
    
    public enum CarType {
        Hybrid,
        Gas,
        Electric
    }

    public Car(String numberPlate, CarType type, double avgSpeed, double basePrice, double gasMilage, int rating, Point position, double range) {
        this.numberPlate = numberPlate;
        this.type = type;
        this.avgSpeed = avgSpeed;
        this.basePrice = basePrice;
        this.gasMilage = gasMilage;
        this.rentalHistoric = new ArrayList<>();
        this.rating = rating;
        this.position = position;
        this.range = range;
    }

    public Car(Car a) {
        this.numberPlate = a.numberPlate;
        this.type = a.type;
        this.avgSpeed = a.avgSpeed;
        this.basePrice = a.basePrice;
        this.gasMilage = a.gasMilage;
        this.rentalHistoric = new ArrayList<>(); //needs fix
        this.rating = a.rating;
        this.position = a.position;
        this.range = a.range;
    }

    public String getNumberPlate() {
        return this.numberPlate;
    }

    public CarType getType() {
        return this.type;
    }

    public Car clone() {
        return new Car(this);
    }
}

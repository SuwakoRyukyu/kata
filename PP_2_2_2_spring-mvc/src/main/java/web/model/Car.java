package web.model;


public class Car {

    private String model;
    private int series;
    private int quantity;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Car(String model, int series, int quantity) {
        this.model = model;
        this.series = series;
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "Car{" +
                "fieldString='" + model + '\'' +
                ", fieldInt=" + series +
                ", fieldBool=" + quantity +
                '}';
    }
}

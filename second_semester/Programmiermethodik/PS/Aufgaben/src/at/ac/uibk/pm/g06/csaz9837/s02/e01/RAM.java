package at.ac.uibk.pm.g06.csaz9837.s02.e01;

public class RAM {
    private String model;
    private int memory;
    private int clockRate;
    private int price;

    public RAM(String model, int memory, int clockRate, int price) {
        this.model = model;
        this.memory = memory;
        this.clockRate = clockRate;
        this.price = price;
    }

    public String getModel() {
        return model;
    }
    public int getMemory() {
        return memory;
    }
    public int getClockRate() {
        return clockRate;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return "RAM {" +
                "Model = '" + model + '\'' +
                "; Memory = " + memory + "GB" +
                "; Clock Rate = " + clockRate + "Mhz" +
                "; Price = " + price + 'c' +
                '}';
    }
}

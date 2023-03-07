package at.ac.uibk.pm.g06.csaz9837.s02.e01;

public class Processor {
    private String name;
    enum processorType {AMD, Intel};
    private processorType type;
    private int cores;
    private int price;

    public Processor(String name, processorType type, int cores, int price) {
        this.name = name;
        this.type = type;
        this.cores = cores;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public processorType getType() {
        return type;
    }
    public int getCores() {
        return cores;
    }
    public int getPrice() {
        return price;
    }
    @Override
    public String toString() {
        return "Processor {" +
                "Name = '" + name + '\'' +
                "; Type = " + type +
                "; Cores = " + cores +
                "; Price = " + price + 'c' +
                '}';
    }
}

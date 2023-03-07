package at.ac.uibk.pm.g06.csaz9837.s02.e01;

public class GPU {
    private int model;
    private String manufacturesName;
    private int price;

    public GPU(int model, String manufacturesName, int price) {
        this.model = model;
        this.manufacturesName = manufacturesName;
        this.price = price;
    }

    public int getModel() {
        return model;
    }
    public String getManufacturesName() {
        return manufacturesName;
    }
    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "GPU {" +
                "Model = " + model +
                "; Manufactures Name = '" + manufacturesName + '\'' +
                "; Price = " + price + 'c' +
                '}';
    }
}

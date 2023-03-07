package at.ac.uibk.pm.g06.csaz9837.s02.e01;

public class HardDisk {
    private String name;
    enum storageType {SSD,HDD};
    private storageType type;
    private int storageCapacity;
    private double dataWriteRate;
    private int price;

    public HardDisk(String name, storageType type, int storageCapacity, double dataWriteRate, int price) {
        this.name = name;
        this.type = type;
        this.storageCapacity = storageCapacity;
        this.dataWriteRate = dataWriteRate;
        this.price = price;
    }
    public String getName() {
        return name;
    }

    public storageType getType() {
        return type;
    }
    public int getStorageCapacity() {
        return storageCapacity;
    }

    public double getDataWriteRate() {
        return dataWriteRate;
    }

    public int getPrice() {
        return price;
    }


    @Override
    public String toString() {
        return "HardDisk {" +
                "Name = '" + name + '\'' +
                "; Type = '" + type + '\'' +
                "; Storage Capacity = " + storageCapacity +
                "; Data-Write-Rate = " + dataWriteRate +
                "; Price = " + price + 'c' +
                '}';
    }
}

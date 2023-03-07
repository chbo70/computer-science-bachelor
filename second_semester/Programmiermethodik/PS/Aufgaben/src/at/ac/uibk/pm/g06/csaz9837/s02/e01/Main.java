package at.ac.uibk.pm.g06.csaz9837.s02.e01;

public class Main {
    public static void main(String[] args) {
        Processor p1 = new Processor("Ryzen 7 9999", Processor.processorType.AMD, 16, 50000);
        HardDisk h1 = new HardDisk("Samsung EVO 970 Plus",HardDisk.storageType.SSD,2000, 0.5, 20000);
        GPU g1 = new GPU(120201, "ASUS", 75000);
        RAM r1_1 = new RAM("Corsair",16, 3600,16000);
        RAM r2_1 = new RAM("Corsair",16, 3600,16000);
        Computer alienware1 = new Computer(1, 2022, p1,h1,g1,r1_1,r2_1);

        System.out.println(alienware1 + "\n");
        System.out.println("Alienware1 costs: " + alienware1.calculatePrice() + " Cents");
        System.out.println("Estimated Data Transfer Duration of 500GB: " + alienware1.estimateDataTransferDuration(500));
        System.out.println("--------------------------------------------------------------------------------------------------------");

        Processor p2 = new Processor("Intel i90", Processor.processorType.Intel, 16, 50000);
        HardDisk h2 = new HardDisk("Samsung EVO 9999 Plus",HardDisk.storageType.SSD,5000, 2.5, 30000);
        GPU g2 = new GPU(64209, "MSI", 75000);
        RAM r1_2 = new RAM("Trident Z",32, 3600,20000);
        RAM r2_2 = new RAM("Trident Z",32, 3600,20000);
        Computer alienware2 = new Computer(2, 2022, p2,h2,g2,r1_2,r2_2);

        System.out.println(alienware2 + "\n");
        System.out.println("Alienware2 costs: " + alienware2.calculatePrice() + " Cents");
        System.out.println("Estimated Data Transfer Duration of 500GB: " + alienware2.estimateDataTransferDuration(500));
    }
}

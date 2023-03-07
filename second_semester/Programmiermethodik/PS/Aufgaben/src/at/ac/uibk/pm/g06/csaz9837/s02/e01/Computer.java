package at.ac.uibk.pm.g06.csaz9837.s02.e01;

public class Computer {
    private final int model;
    private final int manufactureYear;
    private final Processor processor;
    private final HardDisk drive;
    private final GPU graphics;
    private final RAM ram1;
    private final RAM ram2;

    public Computer(int model, int manufactureYear, Processor processor, HardDisk drive, GPU graphics, RAM ram1, RAM ram2) {
        this.model = model;
        this.manufactureYear = manufactureYear;
        this.processor = processor;
        this.drive = drive;
        this.graphics = graphics;
        this.ram1 = ram1;
        this.ram2 = ram2;
    }

    public int calculatePrice(){
        return processor.getPrice() + drive.getPrice() + graphics.getPrice() + ram1.getPrice() + ram2.getPrice();
    }

    @Override
    public String toString() {
        return "Computer {" +
                "\n Model            = " + model +
                "\n Manufacture Year = " + manufactureYear +
                "\n Processor        = " + processor +
                "\n Drive            = " + drive +
                "\n Graphics         = " + graphics +
                "\n Ram1             = " + ram1 +
                "\n Ram2             = " + ram2 +
                '}';
    }

    public double estimateDataTransferDuration(double dataToTransferInGB){
        return dataToTransferInGB/(double)drive.getDataWriteRate();
    }
}

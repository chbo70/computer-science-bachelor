package at.ac.uibk.pm.g06.csaz9837.s04.e02;

public class CourseApplication {

    public static void main(String[] args) throws CourseFullException, StudentAlreadyEnrolledException {
        Course course = new Course();
        String[] students = {"Donald Duck","Uncle Scrooge","Gyro Gearloose"};

        try {
            for (String student : students) {
                if (course.addStudent(student)) {
                    System.out.println("Successfully added " + student);
                }
            }
        } catch (CourseFullException e) {
            System.err.println("Course is full! No place for " + e.getStudent());
            //e.printStackTrace();
        } catch (StudentAlreadyEnrolledException e){
            System.err.println(e.getStudent() + " is already enrolled!");
            //e.printStackTrace();;
        }
    }
}

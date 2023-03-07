public class anweisungen {
    public static int computeGrade(int pointReached){
        int grade;
        if (pointReached < 50){
            grade = 5;
        } else if (pointReached < 65) {
            grade = 4;
        } else if (pointReached < 75) {
            grade = 3;
        } else if (pointReached < 85) {
            grade = 2;
        } else {
            grade = 1;
        }
        return grade;
    }
    public static String getGradeDescription(int grade) {
        String gradeString;
        switch (grade){
            case 1: 
                gradeString = "Sehr Gut";
                break;
            case 2:
                gradeString = "Gut";
                break;
            case 3:
                gradeString = "Befriedingend";
                break;
            case 4:
                gradeString = "Genügend"; 
                break;
            default:
                gradeString = "Nicht Genügend";
                break;

        }
        return gradeString;
    }
    
    public static void main (String[] args){
        System.out.println(computeGrade(66));
        System.out.println(getGradeDescription(4));
    }
}

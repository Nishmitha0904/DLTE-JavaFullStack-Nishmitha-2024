package basics.service;

public final class Student {
    final int age=20;
    public static void main(String[] args) {
        //final int age=20;
        Student student = new Student();
        //student.age=22;
        //age=22;
    }
    public void printAge(int age) {
        System.out.println("Age: "+age);
    }
}

class Enrolment {
    //Student student = new Student();
    Student student2;
//    student.age=22;
//    student.printAge(21);
}
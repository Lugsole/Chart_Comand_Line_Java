package chart_command_line;

import Chart_Components.*;

public class main {

    public static void main(String[] args) {
        System.out.println("This is a Java chart example!");
        Chart undergraduate = new Chart("Undergraduate");
        undergraduate.add(new Box("Freshman"));
        undergraduate.add(new Box("Sophomore"));
        undergraduate.add(new Box("Junior"));
        undergraduate.add(new Box("Senior"));
        Chart Grad = new Chart("Graduate");
        Grad.add(new Box("Master Student"));
        Grad.add(new Box("Doctoral Student"));
        Grad.add(new Box("Passed Student"));
        Chart student = new Chart("All\nStudents");
        student.add(undergraduate);
        student.add(Grad);
        System.out.println(student);
    }
    
}

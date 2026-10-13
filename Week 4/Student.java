package com.student;

public class Student {
    private int id;
    private String name;
    private int age;
    private String course;
    private Department department;

    public Student(int id, String name, int age, String course, Department department) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void displayStudent() {
        System.out.println("ID: " + id + " | Name: " + name + " | Age: " + age + " | Course: " + course + " | Dept: " + department);
    }
}
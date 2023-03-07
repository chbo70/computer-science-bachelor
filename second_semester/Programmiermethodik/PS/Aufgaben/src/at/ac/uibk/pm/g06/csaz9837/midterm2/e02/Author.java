package at.ac.uibk.pm.g06.csaz9837.midterm2.e02;

public class Author {
    private String surName;
    private String lastName;

    public Author(String surName, String name) {
        this.surName = surName;
        this.lastName = name;
    }

    public String getFullName(){
        return surName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Author: "+ getFullName();
    }
}

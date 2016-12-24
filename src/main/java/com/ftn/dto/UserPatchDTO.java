package com.ftn.dto;

/**
 * Created by Alex on 12/5/16.
 */
public class UserPatchDTO {

    private String firstName;

    private String lastName;

    private int dressSize;

    private int footwearSize;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDressSize() {
        return dressSize;
    }

    public int getFootwearSize() {
        return footwearSize;
    }

    @Override
    public String toString() {
        return "UserPatchDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dressSize=" + dressSize +
                ", footwearSize=" + footwearSize +
                '}';
    }
}

package com.example.userprofile.modal;

import java.io.Serializable;

public class User implements Serializable {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String bio;
    private byte[] photoURI;

    public User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public byte[] getPhotoURI() {
        return photoURI;
    }

    public void setPhotoURI(byte[] photoURI) {
        this.photoURI = photoURI;
    }

    public void initUser() {
        this.firstName = "Chaitanya";
        this.lastName = "Deshpande";
        this.phone = "9999999999";
        this.email = "loremEpsum@gmail.com";
        this.bio = "Hi I'm Chaitanya and I love to build Android and Web Applications";
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }
}

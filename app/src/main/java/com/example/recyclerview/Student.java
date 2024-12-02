package com.example.recyclerview;

import android.net.Uri;

public class Student {
    private String name;
    private String email;
    private String phone;
    private Uri imageUri;
    private String studentNumber;

    public Student(String name, String email, String phone, Uri imageUri, String studentNumber) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.imageUri = imageUri;
        this.studentNumber = studentNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }
}

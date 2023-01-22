package com.example.karori.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    public String name;
    public String email;
    public String idToken;



    protected User(Parcel in) {
        name = in.readString();
        email = in.readString();
        gender = in.readString();
        height = in.readInt();
        weight = in.readInt();
        goal = in.readInt();
        kilokalorie = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private int height;

    public int getHeight() {
        return height;
    }

    public void setheight(int height) {
        this.height = height;
    }

    private int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    private int goal;

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    private int kilokalorie;

    public int getKilokalorie() {
        return kilokalorie;
    }

    public void setKilokalorie(int kilokalorie) {
        this.kilokalorie = kilokalorie;
    }
    //completo 
    public User(String name, String email, String idToken, String gender, int altezza, int peso, int goal, int kilokalorie) {
        this.name = name;
        this.email = email;
        this.idToken = idToken;
        this.gender = gender;
        this.height = altezza;
        this.weight = peso;
        this.goal = goal;
        this.kilokalorie = kilokalorie;
    }
    //per la registrazione
    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.gender = "";
        this.height = 0;
        this.weight = 0;
        this.goal = 0;
        this.kilokalorie = 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", idToken='" + idToken + '\'' +
                ", gender='" + gender + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", goal=" + goal +
                ", kilokalorie=" + kilokalorie +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(idToken);
        parcel.writeString(gender);
        parcel.writeInt(height);
        parcel.writeInt(weight);
        parcel.writeInt(goal);
        parcel.writeInt(kilokalorie);
    }
}

package com.netcracker;

import java.util.Objects;

public class Author {

    private String name;
    private char gender;

    public Author(String name, char gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode(){
        int hashCode = 59;
        hashCode = 31* hashCode+ name.hashCode();
        hashCode = 31* hashCode + (int)gender;
        return hashCode;
    }

    @Override
    public boolean equals(Object object){
        if (this == object) return true;
        if (object == null || !(object instanceof Author)) return false;
        return Objects.equals(this.name,((Author) object).name) && this.gender == ((Author) object).gender;

    }
}

package com.netcracker;

import java.util.Objects;

public class Book {

    private String name;
    private String author;
    private boolean isAvailable;
    private long code;

    public Book(String name, String author, long code, boolean isAvailable) {
        this.name = name;
        this.author = author;
        this.code = code;
        this.isAvailable = isAvailable;
    }

    public Book(String name, String author,long code) {
        this.name = name;
        this.author = author;
        this.code = code;
        this.isAvailable = true;
    }

    public String getName() {
        return name;
    }

    public String getAuthorName() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    @Override
    public String toString() {
        String resultString = "Book[name="+name+
                ",author="+author.toString();
        return resultString;
    }


    @Override
    public int hashCode(){
        int hashCode = 59;
        hashCode = 31 * hashCode+ name.hashCode();
        hashCode = 31 * hashCode + author.hashCode();

        hashCode = 31 * hashCode + (int)(code^((code)>>>32));
        hashCode = (31 * hashCode) + (isAvailable ? 1 : 0);
        return hashCode;
    }

    @Override
    public boolean equals(Object object){
        if (this == object) return  true;
        if (object == null || !(object instanceof Book)) return false;
        return this.isAvailable == ((Book) object).isAvailable && this.code == ((Book) object).code &&
                Objects.equals(this.name,((Book) object).name) && Objects.equals(this.author,((Book) object).author);
    }


}

package com.netcracker;

import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Library extends AbstractTableModel {
    private List<Book> books = new ArrayList<>();


    public Library()
    {
        fillTable();
    }

    public void addBook(Book book){
        books.add(book);
        FileWriter writer = null;
        try {
            writer = new FileWriter("library.txt", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.append(book.getName()+","+book.getAuthorName()+","+book.getCode()+'\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        fireTableDataChanged();
    }

    public void deleteBook(int index){
        books.remove(index);
        FileWriter writer = null;
        try {
            writer = new FileWriter("library.txt", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            for(Book book: books) {
                writer.append(book.getName() + "," + book.getAuthorName() + "," + book.getCode() + '\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        fireTableDataChanged();
    }

    public void takeBooks(int[] selectedRows){
        for(int i: selectedRows){
            books.get(i).setAvailable(false);
        }
        fireTableDataChanged();
    }

    public void returnBooks(int[] selectedRows){
        for(int i: selectedRows){
            books.get(i).setAvailable(true);
        }
        fireTableDataChanged();
    }

    public boolean checkAvailability(int[] selectedRows, boolean checkCondition){
        boolean check = true;
        for(int i: selectedRows){
            if (books.get(i).isAvailable() != checkCondition) check = false;
        }
        return check;

    }

    @Override
    public int getRowCount() {
        //ystem.out.println(books.size());
        return books.size();

    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book cur=books.get(rowIndex);
        switch (columnIndex){
            case 0:
                return cur.getName();
            case 1:
                return cur.getAuthorName();
            case 2:
                return cur.isAvailable();
            case 3:
                return cur.getCode();

        }
        return null;
    }

    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "Book name";
            case 1:
                return "Authors";
            case 2:
                return "Available now";
            case 3:
                return "Code";

        }
        return "";
    }

    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return Boolean.class;
            case 3:
                return Long.class;

        }
        return Object.class;
    }

    public boolean isCodeAvailable(int code){
        boolean check = true;
        for(Book book: books){
            if (book.getCode() == code) check = false;
        }
        return check;
    }

    private void fillTable(){
        try{

            FileInputStream fstream = new FileInputStream("library.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine=br.readLine()) != null){
                StringTokenizer stk = new StringTokenizer(strLine,",");
                books.add(new Book(stk.nextToken(),stk.nextToken(),Integer.parseInt(stk.nextToken())));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

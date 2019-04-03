package com.netcracker;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.concurrent.Flow;

public class Swing extends JFrame{


    Library library=new Library();
    JTable table=new JTable(library);
    JScrollPane jScrollPane=new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    public Swing() {
        super("Library");

        setSize(550, 300);
        setLocation(150, 100);
        setDefaultCloseOperation( EXIT_ON_CLOSE );

        table.setPreferredScrollableViewportSize(new Dimension(500, 200));
        table.setFillsViewportHeight(true);
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        add(jScrollPane,BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(1, 4, 5, 5) );

        JButton addBtn = new JButton("Add new book");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewBook();
                }
        });
        grid.add(addBtn);

        JButton deleteBtn = new JButton("Delete a book");
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteExistingBook();
            }
        });
        grid.add (deleteBtn);

        JButton takeBtn = new JButton("Take books");
        takeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                takeNewBooks();
            }
        });
        grid.add (takeBtn);

        JButton returnBtn = new JButton("Return books");
        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnBooks();
            }
        });
        grid.add (returnBtn);

        JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        flow.add(grid);
        add(grid, BorderLayout.SOUTH);



        setVisible(true);
    }

    public void addNewBook(){
        JFrame inputFrame = new JFrame();
        inputFrame.setSize(400,200);
        inputFrame.setLocation(600,400);
        inputFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        inputFrame.setVisible(true);

        JTextField bookName = new JTextField(30);
        JTextField authorName = new JTextField(30);
        JTextField code = new JTextField(10);

        JLabel bookLabel = new JLabel("Enter book's name:");
        JLabel authorLabel = new JLabel("Enter author's name:");
        JLabel codeLabel = new JLabel("Enter book's code:");
        JButton okBtn = new JButton("OK");
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    if (bookName.getText().length()==0 || authorName.getText().length()==0 || code.getText().length()==0){

                        JOptionPane.showConfirmDialog(null,
                                "Please, fill all of the fields!", "", JOptionPane.DEFAULT_OPTION);
                    }
                    else{
                        if(code.getText().matches("[0-9]+")) {
                            if(library.isCodeAvailable(Integer.parseInt(code.getText()))){
                            library.addBook(new Book(bookName.getText(), authorName.getText(), Integer.parseInt(code.getText())));
                            inputFrame.dispose();
                            }
                            else{
                                code.setText("");
                                JOptionPane.showConfirmDialog(null,
                                        "Code already exists!", "", JOptionPane.DEFAULT_OPTION);
                            }
                        }
                        else
                        {
                            code.setText("");
                            JOptionPane.showConfirmDialog(null,
                                    "Code should not contains letters!", "", JOptionPane.DEFAULT_OPTION);
                        }
                    }
                }
        });



        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(bookLabel);
        panel.add(bookName);
        panel.add(authorLabel);
        panel.add(authorName);
        panel.add(codeLabel);
        panel.add(code);
        panel.add(okBtn);
        inputFrame.add(panel);

    }
    public void deleteExistingBook(){
        if (table.getSelectedRow() == -1){
            JOptionPane.showConfirmDialog(null,
                    "Please, choose a book you want to delete!", "", JOptionPane.DEFAULT_OPTION);
        }
        else{
            library.deleteBook(table.getSelectedRow());
        }
    }
    public void takeNewBooks(){
        if (table.getSelectedRow() == -1){
            JOptionPane.showConfirmDialog(null,
                    "Please, choose books you want to take!", "", JOptionPane.DEFAULT_OPTION);
        }
        else{
            if (library.checkAvailability(table.getSelectedRows(),true)) {
                library.takeBooks(table.getSelectedRows());
            }
            else
            {
                JOptionPane.showConfirmDialog(null,
                        "Please, choose available books!", "", JOptionPane.DEFAULT_OPTION);

            }
        }

    }
    public void returnBooks(){
        if (table.getSelectedRow() == -1){
            JOptionPane.showConfirmDialog(null,
                    "Please, choose books you want to return!", "", JOptionPane.DEFAULT_OPTION);
        }
        else{
            if(library.checkAvailability(table.getSelectedRows(),false)) {
                library.returnBooks(table.getSelectedRows());
            }
            else{
                JOptionPane.showConfirmDialog(null,
                        "Please, choose books that already taken!", "", JOptionPane.DEFAULT_OPTION);

            }
        }
    }


}

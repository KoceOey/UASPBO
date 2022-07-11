package uaspbo;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class Registrasi {

    String pathFoto;
    JLabel lblFoto;

    public ArrayList<CategoryUser> getCategoryList() {
        ArrayList<CategoryUser> temp = new ArrayList<>();
        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();
        try {
            java.sql.Statement stat = conn.con.createStatement();
            ResultSet result = stat.executeQuery("select * from categoryuser");
            while (result.next()) {
                int tempID = result.getInt("id");
                String tempNama = result.getString("nama");
                CategoryUser category = new CategoryUser(tempID, tempNama);
                temp.add(category);
            }
        } catch (SQLException e) {

        }
        conn.disconnect();
        return temp;
    }
    
    public void insertDatabase(String nama, String email,String password, String category){
        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();
        try {
            PreparedStatement stat = conn.con.prepareStatement("INSERT INTO user(nama,email,pass,id_category,photo) VALUES(?,?,?,?,?)");
            stat.setString(1,nama);
            stat.setString(2,email);
            stat.setString(3,password);
            int temp = 0;
            if(category.equals("Private Account")){
                temp = 1;
            }else if(category.equals("Creator Account")){
                temp = 2;
            }else{
                temp = 3;
            }
            stat.setInt(4, temp);
            stat.setString(5,pathFoto);
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Berhasil melakukan registrasi");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error!! Gagal melakukan registrasi");
        }
        conn.disconnect();
    }
    
    public Registrasi() {
        //font
        Font font = new Font("Serif", Font.PLAIN, 16);

        //frame
        JFrame frame = new JFrame("Registrasi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        //label registrasi
        JLabel registrasi = new JLabel("Registrasi");
        registrasi.setBounds(175, 50, 150, 30);
        registrasi.setFont(new Font("Serif", Font.BOLD, 24));

        //label email
        JLabel lblNama = new JLabel("Email :");
        lblNama.setBounds(50, 100, 100, 40);
        lblNama.setFont(font);

        //textfield email
        JTextField nama = new JTextField();
        nama.setBounds(150, 100, 300, 30);
        nama.setFont(font);

        //label email
        JLabel lblEmail = new JLabel("Email :");
        lblEmail.setBounds(50, 150, 100, 40);
        lblEmail.setFont(font);

        //textfield email
        JTextField email = new JTextField();
        email.setBounds(150, 150, 300, 30);
        email.setFont(font);

        //label password
        JLabel lblPassword = new JLabel("Password :");
        lblPassword.setBounds(50, 200, 100, 30);
        lblPassword.setFont(font);

        //password field
        JPasswordField password = new JPasswordField();
        password.setBounds(150, 200, 300, 30);
        password.setFont(font);

        //label foto
        lblFoto = new JLabel("No File Choosen");
        lblFoto.setBounds(250, 250, 200, 30);
        lblFoto.setFont(font);

        //filechooser
        JButton btnFoto = new JButton("Choose Photo");
        btnFoto.setBounds(100, 250, 150, 30);
        btnFoto.setFont(font);
        btnFoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int response = fileChooser.showOpenDialog(null);
                if (response == JFileChooser.APPROVE_OPTION) {
                    pathFoto = fileChooser.getSelectedFile().getAbsolutePath();
                    lblFoto.setText(pathFoto);
                }
            }
        });

        //label category
        JLabel lblCategory = new JLabel("Category :");
        lblCategory.setBounds(50, 300, 100, 30);
        lblCategory.setFont(font);

        //Combobox category
        ArrayList<CategoryUser> listCategory = getCategoryList();
        String[] listCat = new String[listCategory.size()];
        for (int i = 0; i < listCat.length; i++) {
            listCat[i] = listCategory.get(i).getNama();
        }
        JComboBox category = new JComboBox(listCat);
        category.setBounds(150, 300, 300, 30);
        category.setFont(font);

        //button back
        JButton back = new JButton("<-");
        back.setBounds(0, 0, 50, 50);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new MainMenu();
            }
        });

        //button login
        JButton btnRegistrasi = new JButton("Registrasi");
        btnRegistrasi.setBounds(200, 350, 100, 50);
        btnRegistrasi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertDatabase(nama.getText(),email.getText(),password.getText(), (String) category.getSelectedItem());
                frame.dispose();
                new MainMenu();
            }
        });

        frame.add(registrasi);
        frame.add(lblNama);
        frame.add(nama);
        frame.add(lblEmail);
        frame.add(email);
        frame.add(lblPassword);
        frame.add(password);
        frame.add(lblFoto);
        frame.add(btnFoto);
        frame.add(lblCategory);
        frame.add(category);
        frame.add(back);
        frame.add(btnRegistrasi);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}

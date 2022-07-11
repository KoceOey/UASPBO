package uaspbo;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Login {
    
    boolean statusLogin = false;
    
    public void tryLogin(String email, String pass){
        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();
        try {
            java.sql.Statement stat = conn.con.createStatement();
            ResultSet result = stat.executeQuery("select * from user where email='" + email + "'");
            if (result.next()) {
                if (pass.equals(result.getString("pass"))) {
                    statusLogin = true;
                    JOptionPane.showMessageDialog(null, "Berhasil melakukan login");
                } else {
                    JOptionPane.showMessageDialog(null, "password salah");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Email user tidak ditemukan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error!! Gagal melakukan login");
        }
    }
    
    public Login(){
        try {
            Font font = new Font("Serif", Font.PLAIN, 20);
            
            //frame
            JFrame frame = new JFrame("Log In");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500,400);
            
            //img
            BufferedImage foto = ImageIO.read(new File("E:\\school\\SP 2021\\Pemrograman Berbasis Objek\\UASPBO\\img\\logo.jpg"));
            Image fixedFoto = foto.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            JLabel picFoto = new JLabel(new ImageIcon(fixedFoto));
            picFoto.setBounds(200,50,100,100);
            
            //label email
            JLabel lblEmail = new JLabel("Email :");
            lblEmail.setBounds(50,150,100,30);
            lblEmail.setFont(font);
            
            //textfield email
            JTextField email = new JTextField();
            email.setBounds(150,150,300,30);
            email.setFont(font);
            
            //label password
            JLabel lblPassword = new JLabel("Password :");
            lblPassword.setBounds(50,200,100,30);
            lblPassword.setFont(font);
            
            //password field
            JPasswordField password = new JPasswordField();
            password.setBounds(150, 200, 300, 30);
            password.setFont(font);
            
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
            JButton login = new JButton("Login");
            login.setBounds(200,300,100,50);
            login.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tryLogin(email.getText(),password.getText());
                    if(statusLogin){
                        frame.dispose();
                        new DataPengguna();
                    }
                }
            });
            
            frame.add(picFoto);
            frame.add(lblEmail);
            frame.add(email);
            frame.add(lblPassword);
            frame.add(password);
            frame.add(back);
            frame.add(login);
            frame.setLayout(null);
            frame.setVisible(true);
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error!!");
        }
    }
}

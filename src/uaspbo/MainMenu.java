package uaspbo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainMenu {
    
    public MainMenu(){
        //frame
        JFrame frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,300);
        
        JButton btnLogin = new JButton("Login Pengguna");
        btnLogin.setBounds(100,40,200,50);
        btnLogin.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Login();
            }
        });
        
        JButton btnRegistrasi = new JButton("Registrasi Pengguna");
        btnRegistrasi.setBounds(100,110,200,50);
        btnRegistrasi.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Registrasi();
            }
        });
        
        JButton btnDataPengguna = new JButton("Lihat Data Pengguna");
        btnDataPengguna.setBounds(100,180,200,50);
        btnDataPengguna.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new DataPengguna();
            }
        });
        
        frame.add(btnLogin);
        frame.add(btnRegistrasi);
        frame.add(btnDataPengguna);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}

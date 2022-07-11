package uaspbo;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class DataPengguna {
    
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
    
    public DataPengguna(){
        //font
        Font font = new Font("Serif", Font.PLAIN, 16);

        //frame
        JFrame frame = new JFrame("Data Pengguna");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        
        //Combobox category
        ArrayList<CategoryUser> listCategory = getCategoryList();
        String[] listCat = new String[listCategory.size()];
        for (int i = 0; i < listCat.length; i++) {
            listCat[i] = listCategory.get(i).getNama();
        }
        JComboBox category = new JComboBox(listCat);
        category.setBounds(25, 100, 150, 30);
        category.setFont(font);
        
        //button tampilkan
        JButton show = new JButton("Show");
        show.setBounds(175, 100, 100, 30);
        show.setFont(font);
        show.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShowTabel((String) category.getSelectedItem());
            }
        });
        
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
        
        frame.add(category);
        frame.add(show);
        frame.add(back);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}

package uaspbo;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTable;

public class ShowTabel {
    
    JTable tabel;
    String[] kolom = {"ID","Nama","Email","Password"};
    
    public ShowTabel(String category){
        //font
        Font font = new Font("Serif", Font.PLAIN, 16);
        
        //frame
        JFrame frame = new JFrame("Registrasi");
        frame.setSize(500, 500);
        
        
       
        ArrayList<User> temp = new ArrayList<>();
        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();
        try {
            int index = 0;
            if(category.equals("Private Account")){
                index = 1;
            }else if(category.equals("Creator Account")){
                index = 2;
            }else{
                index = 3;
            }
            java.sql.Statement stat = conn.con.createStatement();
            ResultSet result = stat.executeQuery("select * from user WHERE id_category = " + index);
            while (result.next()) {
                int id = result.getInt("id");
                String nama = result.getString("nama");
                String email = result.getString("nama");
                String pass = result.getString("pass");
                int idCategory = result.getInt("id_category");
                String pic = result.getString("photo");
                User user = new User(id,nama,email,pass,idCategory,pic);
                temp.add(user);
            }
            String[][] arrIsi = new String[temp.size()][6];
            for (int i = 0; i < temp.size(); i++) {
                User tempUser = temp.get(i);
                arrIsi[i][0] = String.valueOf(tempUser.getId());
                arrIsi[i][1] = tempUser.getName();
                arrIsi[i][2] = tempUser.getEmail();
                arrIsi[i][3] = tempUser.getPassword();
                arrIsi[i][4] = category;
                arrIsi[i][5] = tempUser.getPhoto();
            }
            tabel = new JTable(arrIsi,kolom);
            frame.add(tabel);
            frame.invalidate();
            frame.validate();
            frame.repaint();
        } catch (SQLException e) {

        }
        conn.disconnect();
        
        //table
        tabel.setBounds(50,50,400,400);
        tabel.setFont(font);
        
        frame.add(tabel);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}

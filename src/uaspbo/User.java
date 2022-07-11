package uaspbo;

public class User {
    private int id;
    private int idCategory;
    private String name;
    private String email;
    private String password;
    private String photo;

    public User(int id, String name, String email, String password, int idCategory, String photo) {
        this.id = id;
        this.idCategory = idCategory;
        this.name = name;
        this.email = email;
        this.password = password;
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}

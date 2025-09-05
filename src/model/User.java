package model;

public abstract class User {
    protected String userId;
    protected String username;
    protected String password;
    protected String email;
    protected String phone;
    public User(String userId, String username, String password, String email, String phone){
        this.userId =userId;
        this.username = username;
        this.password = password;
        this.email =email;
        this.phone = phone;

    }

    public boolean login(String username,String password){
        return this.username.equals(username) && this.password.equals(password);
    }

    public void logout(){
        System.out.println(username + "Đã đăng xuất!");
    }
    

    



} 
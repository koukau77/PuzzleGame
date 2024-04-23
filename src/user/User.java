package user;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * 获取
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public static int findIndex(ArrayList<User> list, String username){
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            String rightName = user.getUsername();
            if (username.equals(rightName)){
                return i;
            }
        }
        return -1;
    }
    // determine if username is in list
    public static boolean contains(ArrayList<User> list, String username) {

        return findIndex(list, username) != -1;
    }

    // determine if password is true
    public static boolean checkUserInfo(ArrayList<User> list, User userInfo) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            String rightName = user.getUsername();
            String rightPassword = user.getPassword();
            if (userInfo.getUsername().equals(rightName) && userInfo.getPassword().equals(rightPassword)){
                return true;
            }
        }
        return false;
    }

}

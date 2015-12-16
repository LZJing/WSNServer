package com.buaa.sensorylab.Model;

/**
 * Created by LZJing on 2015/12/3.
 */
public class UserData {
    private int userId;
    private String userName;

    public UserData(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}

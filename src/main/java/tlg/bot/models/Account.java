package tlg.bot.models;

import java.util.ArrayList;

public class Account {
    private Integer telegramID;
    private String first_name;
    private String last_name;
    private String username;
    //private ArrayList<Request> requestHistory;

    public Account(Integer telegramID, String first_name, String last_name, String username) {
        this.telegramID = telegramID;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
    }

    public Integer getTelegramID() {
        return this.telegramID;
    }

    public void setTelegramID(int telegramID) {
        this.telegramID = telegramID;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTelegramID(Integer telegramID) {
        this.telegramID = telegramID;
    }

    //public ArrayList<Request> getRequestHistory() {
     //   return requestHistory;
   //}

    //public void setRequestHistory(ArrayList<Request> requestHistory) {
     //   this.requestHistory = requestHistory;
    //}
}

package tlg.bot.models;

public class Account {
    private Integer telegramId;
    private String first_name;
    private String last_name;
    private String username;
    //private ArrayList<Request> requestHistory;

    public Account(Integer telegramId, String first_name, String last_name, String username) {
        this.telegramId = telegramId;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
    }

    public Integer getTelegramId() {
        return this.telegramId;
    }

    public void setTelegramId(int telegramId) {
        this.telegramId = telegramId;
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
        this.telegramId = telegramID;
    }

    //public ArrayList<Request> getRequestHistory() {
     //   return requestHistory;
   //}

    //public void setRequestHistory(ArrayList<Request> requestHistory) {
     //   this.requestHistory = requestHistory;
    //}
}

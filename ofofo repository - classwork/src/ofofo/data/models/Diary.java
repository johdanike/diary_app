package ofofo.data.models;

public class Diary {
    private String diaryId;
    private String username;
    private String password;
    private boolean isLocked;
    private long lastEntryCount = 0;

    public Diary(String diaryId ,String username, String password) {
        this.username = username;
        this.password = password;
        this.diaryId = diaryId;
        this.isLocked = true;
    }

    public Diary(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public Diary(){

    }

    public boolean isLocked() {
        return isLocked;
    }

//    public void setLocked(boolean locked) {
//        isLocked = locked;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getLastEntryCount() {
        return lastEntryCount;
    }

    public void setLastEntryCount(long lastEntryCount) {
        this.lastEntryCount = lastEntryCount;
    }

    public String getDiaryId() {
        return diaryId;
    }
    public void setDiaryId(String diaryId) {
        this.diaryId = diaryId;
    }

    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    @Override
    public String toString() {
        return "\nDiary {id = "+ diaryId + "username= " + username + ", password= " + password + "}\n";
    }

}

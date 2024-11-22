package ofofo.data.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Entry {
    private String title;
    private String content;
    private String diaryId;
    private long entryId;
    private String entryDate;
    private String updateDate;


    public Entry(String diaryId, String title, String content, long entryId) {
        this.title = title;
        this.content = content;
        this.diaryId = diaryId;
        this.entryId = entryId;
        this.entryDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public Entry(){

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(String diaryId) {
        this.diaryId = diaryId;
    }

    public long getEntryId() {
        return entryId;
    }

    public void setEntryId(long entryId) {
        this.entryId = entryId;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }


    @Override
    public String toString() {
        return "{Diary id: " + diaryId +
                "\nEntry Id: " + entryId +
                "\nTitle: " + title +
                "\nContent: " + content +
                "\nDate created: " + entryDate + "}\n";
    }

}

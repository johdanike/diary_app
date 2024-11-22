package ofofo.services;

import ofofo.data.models.Diary;
import ofofo.data.models.Entry;

public interface DiaryService {
//    Diary register(String username, String password);
    String register(String username, String password);
    Diary login(String username, String password);
    Diary findDiaryById(String diaryId);
    void logOut(String diaryId);
    String deleteDiary(String diaryId, String password);
    long countDiary();
    Entry createEntry(String diaryId, Entry entry);
    long countEntries(String diaryId);
    boolean isLoggedIn();
    boolean isRegistered(String diaryId);
    Diary changePassword(String username, String password, String newPassword);
    Diary changeUsername(String username, String password, String newUsername);
}

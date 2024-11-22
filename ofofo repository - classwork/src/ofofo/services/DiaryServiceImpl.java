package ofofo.services;

import ofofo.data.models.Diary;
import ofofo.data.models.Entry;
import ofofo.data.repositories.DiaryRepository;
import ofofo.data.repositories.DiaryRepositoryImpl;
import ofofo.data.repositories.EntryRepositories;
import ofofo.data.repositories.EntryRepositoryImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DiaryServiceImpl implements DiaryService {
    private final DiaryRepository diaryRepository;
    private EntryService entryService;
    private long count = 0;
    private boolean isLoggedIn = false;


    public DiaryServiceImpl(DiaryRepository diaryRepository, EntryService entryService) {
        this.diaryRepository = diaryRepository;
        this.entryService = entryService;
    }
    public DiaryServiceImpl(){
        this.diaryRepository = new DiaryRepositoryImpl();
        this.entryService = new EntryServiceImpl();
    }

    @Override
    public String register(String username, String password) {
        if (validateUsernameAtAllTimes(username)) {
            throw new IllegalArgumentException("Username is already in use");
        } else {
            username = username.toLowerCase();
            Diary diary = newDiary(username,password);
            diaryRepository.save(diary);
            count++;
            isRegistered(username);
            isLoggedIn = false;
            return "Registration successful :)";
        }
    }

    private static Diary newDiary(String username, String password){
        Diary diary = new Diary();
        diary.setUsername(username);
        diary.setPassword(password);
        return diary;
    }

    private boolean validateUsernameAtAllTimes(String username) {
        return diaryRepository.findById(username.toLowerCase()) != null;
    }

    @Override
    public Diary login(String username, String password) {
        if(validateUsernameAtAllTimes(username)){
            Diary diary = getDiary(username);
            if(diary.getPassword().equals(password)){
                diary.setIsLocked(false);
                isLoggedIn = true;
                return diary;
            }
        }
        isLoggedIn = false;
        throw new IllegalArgumentException("incorrect username or password :(");
    }

    @Override
    public Diary findDiaryById(String diaryId) {
        if(isLoggedIn){
            return diaryRepository.findById(diaryId);
        }
        return null;
    }

    @Override
    public void logOut(String diaryId) {
        Diary diary = getDiary(diaryId);
        if(validateUsernameAtAllTimes(diaryId)){
            diary.setIsLocked(true);
            isLoggedIn = false;
        }
    }

    @Override
    public String deleteDiary(String diaryId, String password) {
        Diary diary = getDiary(diaryId);
        if(getDiary(diaryId).getPassword().equals(password)){
            diaryRepository.delete(diary);
            count--;
            isLoggedIn = false;
            return "Deletion successful :)";
        }
        throw new IllegalArgumentException("Username or password is incorrect");
    }

    private Diary getDiary(String diaryId) {
        return diaryRepository.findById(diaryId.toLowerCase());
    }

    @Override
    public long countDiary() {
        return count;
    }

    @Override
    public Entry createEntry(String diaryId, Entry entry) {
        if(!isLoggedIn || !validateUsernameAtAllTimes(diaryId)){
            throw new IllegalArgumentException("Username or password is incorrect");
        }
        return entryService.createEntry(diaryId, entry);
    }

    @Override
    public long countEntries(String diaryId) {
        if(!isLoggedIn) throw new IllegalStateException("Diary is not logged in");
        return entryService.countNumberOfEntries(diaryId);
    }

    @Override
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    @Override
    public boolean isRegistered(String diaryId) {
        return validateUsernameAtAllTimes(diaryId);
    }

    @Override
    public Diary changePassword(String username, String password, String newPassword) {
        Diary diary = getDiary(username);
        if(diary.getPassword().equals(password)){
            diary.setPassword(newPassword);
            diaryRepository.save(diary);
            logOut(diary.getUsername());
            return diary;
        }
        throw new IllegalArgumentException("incorrect old password :(");
    }

    @Override
    public Diary changeUsername(String username, String password, String newUsername) {
        if (validateUsernameAtAllTimes(newUsername)) {
            throw new IllegalArgumentException("New username is already taken");
        }
        Diary diary = getDiary(username);
        if (diary.getPassword().equals(password)) {
            diary.setUsername(newUsername.toLowerCase());
            diaryRepository.save(diary);
            logOut(username);
            return diary;
        }
        throw new IllegalArgumentException("Incorrect password");
    }

}




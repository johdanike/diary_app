package ofofo.controllers;

import ofofo.data.models.Diary;
import ofofo.services.DiaryService;
import ofofo.services.DiaryServiceImpl;

public class DiaryController {
   private final DiaryService diaryService = new DiaryServiceImpl();


   public String registerUser(String username, String password){
       return diaryService.register(username, password);
   }

    public Diary loginUser(String username, String password) {
        return diaryService.login(username, password);
    }

    public Diary logOutUser(String username) {
       diaryService.logOut(username);
       return null;
    }

}

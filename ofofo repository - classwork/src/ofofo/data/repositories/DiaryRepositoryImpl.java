package ofofo.data.repositories;

import ofofo.data.models.Diary;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DiaryRepositoryImpl implements DiaryRepository {
    private List<Diary> diaries = new ArrayList<>();

    @Override
    public long count() {
        return diaries.size();
    }

    @Override
    public void delete(Diary diary) {
        diaries.remove(diary);
    }

    @Override
    public void deleteAll() {
        diaries.clear();

    }

    @Override
    public void deleteById(String id) {
        for(Diary diary : diaries) {
            if(diary.getUsername().equals(id)){
                diaries.remove(diary);
            }
        }
    }

    @Override
    public void deleteAllById(String username) {
        Iterator<Diary> iterator = diaries.iterator();
        while (iterator.hasNext()) {
            Diary diary = iterator.next();
            if (username.equals(diary.getUsername())) {
                iterator.remove();
            }
        }
    }

    @Override
    public Diary findById(String id) {
        for(Diary diary: diaries){
            if(diary.getUsername().equals(id)) {
                return diary;
            }
        }
        return null;
    }

    @Override
    public boolean existsById(String id) {
        return false;
    }

    @Override
    public void save(Diary diary) {
        if(diary.getUsername() != null || diary.getPassword() != null) {
           diaries.add(diary);
        }
        else {
            throw new IllegalArgumentException("Diary is null");
        }
    }

}

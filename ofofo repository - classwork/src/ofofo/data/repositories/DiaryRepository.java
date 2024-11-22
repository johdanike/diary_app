package ofofo.data.repositories;

import ofofo.data.models.Diary;

public interface DiaryRepository {
    long count();
    void delete(Diary diary);
    void deleteAll();
    void deleteById(String id);
    void deleteAllById(String id);
    boolean existsById(String id);
    Diary findById(String id);
    void save(Diary diary);
}

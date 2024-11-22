package ofofo.data.repositories;

import ofofo.data.models.Diary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiaryRepositoryImplTest {
    DiaryRepository diaryRepository;
    Diary diary;
    @BeforeEach
    public void setUp() {
        diaryRepository = new DiaryRepositoryImpl();
        diary = new Diary();
    }

    @Test
    public void testThatDiaryRepositoryIsEmpty_noDiaryIsCreated(){
        assertEquals(0, diaryRepository.count());
    }

    @Test
    public void testThatDiaryRepositoryIsNotEmpty_oneDiaryIsCreated(){
        diary.setUsername("John Doe");
        diary.setPassword("password");
        diaryRepository.save(diary);
        assertEquals(1, diaryRepository.count());
    }
    @Test
    public void repositoryCannotAddANullDiary_test(){
        assertThrows(IllegalArgumentException.class, () -> diaryRepository.save(diary));
    }
    @Test
    public void allDiariesInRepositoryCanBeDeleted_test(){
        diary.setUsername("John Doe");
        diary.setPassword("password");
        diaryRepository.save(diary);
        Diary diary2 = new Diary();
        diary2.setUsername("John");
        diary2.setPassword("passwordi");
        diaryRepository.save(diary2);
        diaryRepository.deleteAll();
    }
    @Test
    public void diaryCanBeDeletedById_test(){
        diary.setUsername("John Doe");
        diary.setPassword("password");
        diaryRepository.save(diary);
        Diary diary2 = new Diary();
        diary2.setUsername("John");
        diary2.setPassword("passwordi");
        diaryRepository.save(diary2);
        assertEquals(2, diaryRepository.count());
        diaryRepository.deleteById("John Doe");
        assertEquals(1, diaryRepository.count());
        assertFalse(diaryRepository.existsById(String.valueOf(diary)));
    }
    @Test
    public void diariesWithSameId_canBeDeleted_allAtOnce_test(){
        diary.setUsername("John");
        diary.setPassword("password");
        diaryRepository.save(diary);
        assertEquals(1, diaryRepository.count());
        Diary diary2 = new Diary();
        diary2.setUsername("John");
        diary2.setPassword("password2");
        diaryRepository.save(diary2);
        assertEquals(2, diaryRepository.count());
        diaryRepository.deleteAllById("John");
        assertEquals(0, diaryRepository.count());
    }
    @Test
    public void diaryCanBeFoundById_test(){
        diary.setUsername("John Doe");
        diary.setPassword("password");
        diaryRepository.save(diary);
        assertEquals(1, diaryRepository.count());
        Diary diary2 = new Diary();
        diary2.setUsername("John");
        diary2.setPassword("password2");
        diaryRepository.save(diary2);
        assertEquals(2, diaryRepository.count());
        assertEquals("John Doe", diaryRepository.findById("John Doe").getUsername());
    }
    @Test
    public void diaryCanBeDeleted(){
        diary.setUsername("Joe");
        diary.setPassword("1234567890");
        diaryRepository.save(diary);
        assertEquals(1, diaryRepository.count());
        diaryRepository.delete(diary);
        assertEquals(0, diaryRepository.count());
    }

}
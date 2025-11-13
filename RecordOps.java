public interface RecordOps {
    void addRecord(Integer roll, String name, String email, String course, Double marks);
    Pupil searchByName(String name) throws PupilNotFoundException;
    void deleteByName(String name) throws PupilNotFoundException;
    void viewAll();
    void saveToFile();
}

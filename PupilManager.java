import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PupilManager implements RecordOps {
    private List<Pupil> pupils = new ArrayList<>();
    private final String dataFile;

    public PupilManager(String dataFile) {
        this.dataFile = dataFile == null ? "pupils_data_mod.txt" : dataFile;
        loadFromFile();
    }

    private void loadFromFile() {
        File f = new File(dataFile);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] p = line.split(",", -1);
                if (p.length < 5) continue;
                Integer roll = Integer.valueOf(p[0].trim());
                String name = p[1].trim();
                String email = p[2].trim();
                String course = p[3].trim();
                Double marks = Double.valueOf(p[4].trim());
                pupils.add(new Pupil(roll, name, email, course, marks));
            }
        } catch (Exception e) {
            System.out.println("Load error: " + e.getMessage());
        }
    }

    @Override
    public void addRecord(Integer roll, String name, String email, String course, Double marks) {
        Pupil p = new Pupil(roll, name, email, course, marks);
        p.validate();
        for (Pupil existing : pupils) {
            if (existing.getRollNo().equals(roll)) {
                throw new IllegalArgumentException("Duplicate roll number: " + roll);
            }
        }
        pupils.add(p);
    }

    @Override
    public Pupil searchByName(String name) throws PupilNotFoundException {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name required for search.");
        for (Pupil p : pupils) {
            if (p.getName().equalsIgnoreCase(name.trim())) {
                return p;
            }
        }
        throw new PupilNotFoundException("Pupil with name '" + name + "' not found.");
    }

    @Override
    public void deleteByName(String name) throws PupilNotFoundException {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name required for deletion.");
        Iterator<Pupil> it = pupils.iterator();
        while (it.hasNext()) {
            Pupil p = it.next();
            if (p.getName().equalsIgnoreCase(name.trim())) {
                it.remove();
                return;
            }
        }
        throw new PupilNotFoundException("Pupil with name '" + name + "' not found for deletion.");
    }

    @Override
    public void viewAll() {
        if (pupils.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        for (Pupil p : pupils) {
            System.out.println(p);
            System.out.println("Grade: " + gradeFor(p.getMarks()));
            System.out.println("-----------------------------");
        }
    }

    private String gradeFor(Double marks) {
        if (marks >= 90) return "A";
        if (marks >= 75) return "B";
        if (marks >= 50) return "C";
        return "D";
    }

    @Override
    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile))) {
            for (Pupil p : pupils) {
                bw.write(p.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Save error: " + e.getMessage());
        }
    }
}

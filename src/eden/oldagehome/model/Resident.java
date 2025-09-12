package eden.oldagehome.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a resident in the old age home.
 */
public class Resident {

    private String residentId;
    private String name;
    private int age;
    private String disease;
    private String diet;
    private List<Medication> medications;
    private List<DoctorVisit> doctorVisits;

    public Resident(String residentId, String name, int age, String disease, String diet) {
        this.residentId = residentId;
        this.name = name;
        this.age = age;
        this.disease = disease;
        this.diet = diet;
        this.medications = new ArrayList<>();
        this.doctorVisits = new ArrayList<>();
    }

    /**
     * Adds a new medication to the resident's list.
     * @param m The medication to add.
     */
    public void addMedication(Medication m) {
        this.medications.add(m);
    }

    /**
     * Removes a medication from the resident's list by name.
     * @param name The name of the medication to remove.
     */
    public void removeMedication(String name) {
        this.medications.removeIf(medication -> medication.getName().equalsIgnoreCase(name));
    }

    /**
     * Checks if any medication needs a refill.
     * @return true if at least one medication needs a refill, false otherwise.
     */
    public boolean needsRefill() {
        for (Medication med : medications) {
            if (med.isRefillNeeded()) {
                return true;
            }
        }
        return false;
    }

    // Getters and Setters

    public String getResidentId() {
        return residentId;
    }

    public void setResidentId(String residentId) {
        this.residentId = residentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public List<Medication> getMedicationList() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public List<DoctorVisit> getDoctorVisits() {
        return doctorVisits;
    }

    public void setDoctorVisits(List<DoctorVisit> doctorVisits) {
        this.doctorVisits = doctorVisits;
    }

    @Override
    public String toString() {
        return "Resident{" +
                "residentId='" + residentId + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", disease='" + disease + '\'' +
                '}';
    }
}
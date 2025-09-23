import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class Person {
    private String name;
    private String surname;
    private Date birthDate;
    private boolean anotherCompanyOwner;
    private boolean pensioner;
    private boolean publicServer;
    private float salary;

    public Person() {
    }

    public Person(String name, String surname, Date birthDate,
                  boolean anotherCompanyOwner, boolean pensioner, boolean publicServer,
                  float salary) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.anotherCompanyOwner = anotherCompanyOwner;
        this.pensioner = pensioner;
        this.publicServer = publicServer;
        this.salary = salary;
    }

    public String fullName() {
        return (name == null ? "" : name) + (surname == null ? "" : (" " + surname));
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public float calculateYearlySalary() {
        return this.salary * 12.0f;
    }

    public boolean isMEI() {
        // salary rule
        boolean salaryOk = (calculateYearlySalary() < 130000.0f);
        // age rule
        int age = calculateAge(this.birthDate);
        boolean ageOk = age > 18;
        // flags must be false
        boolean flagsOk = !anotherCompanyOwner && !pensioner && !publicServer;
        return salaryOk && ageOk && flagsOk;
    }

    private int calculateAge(Date birth) {
        if (birth == null) return 0;
        LocalDate birthLocal = Instant.ofEpochMilli(birth.getTime())
                .atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate today = LocalDate.now();
        return Period.between(birthLocal, today).getYears();
    }

    // Additional setters/getters to allow tests to set fields as needed
    public void setName(String name) { this.name = name; }
    public void setSurname(String surname) { this.surname = surname; }
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }
    public void setAnotherCompanyOwner(boolean anotherCompanyOwner) { this.anotherCompanyOwner = anotherCompanyOwner; }
    public void setPensioner(boolean pensioner) { this.pensioner = pensioner; }
    public void setPublicServer(boolean publicServer) { this.publicServer = publicServer; }
}


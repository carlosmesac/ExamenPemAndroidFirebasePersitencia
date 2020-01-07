package es.ulpgc.mesa.carlos.examenpem;

import java.util.zip.Inflater;

public class Person {
    public String name;
    public String surname;
    public String age;
    public String job;
    public String cv;
    public String dni;
    public String valoracion;


    public Person(String name, String surname, String age, String job, String cv, String dni, String valoracion) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.job = job;
        this.cv = cv;
        this.dni = dni;
        this.valoracion = valoracion;
    }

    public Person() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCV() {
        return cv;
    }

    public void setCV(String cv) {
        this.cv = cv;
    }
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }
}

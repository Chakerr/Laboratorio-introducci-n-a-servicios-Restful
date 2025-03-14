/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testws.services;

import com.mycompany.testws.entidad.Person;
import com.mycompany.testws.entidad.Salario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author RayAj
 */
@Path("service")
public class Service {

    private static Map<Integer, Person> persons = new HashMap<Integer, Person>();

    static {
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            int id = i + 1;
            person.setId(id);
            person.setFullName("My wonderfull Person " + id);
            person.setAge(new Random().nextInt(40) + 1);
            person.setSalario((int) (person.getAge() * 1423500 / 3));
            persons.put(id, person);
        }
    }

    @GET
    @Path("/getPersonByIdXML/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Person getPersonByIdXML(@PathParam("id") int id) {
        return persons.get(id);
    }

    @GET
    @Path("/getPersonByIdJson/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPersonByIdJson(@PathParam("id") int id) {
        return persons.get(id);
    }

    @GET
    @Path("getAllPersonsInXML")
    @Produces(MediaType.APPLICATION_XML)
    public List<Person> getAllpersonsInXML() {
        return new ArrayList<Person>(persons.values());
    }

    @GET
    @Path("/getAllPersonsInJson")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAllPersonsInJson() {
        return new ArrayList<Person>(persons.values());
    }

    @GET
    @Path("salarioPromedioInXML")
    @Produces(MediaType.APPLICATION_XML)
    public Salario salarioPromedioInXML() { 
        int salarioTotal = 0;
        List<Person> salarios = new ArrayList<>(persons.values());
        for (Person s : salarios) {
            salarioTotal += s.getSalario();
        }
        int promedio = salarioTotal / salarios.size();
        return new Salario(promedio);
    }

    @GET
    @Path("salarioPromedioInJson")
    @Produces(MediaType.APPLICATION_JSON)
    public int salarioPromedioInJson() {
        int salarioTotal = 0;
        List<Person> salarios = new ArrayList<>(persons.values());
        for (Person s : salarios) {
            salarioTotal += s.getSalario();
        }
        return salarioTotal / salarios.size();
    }

    @POST
    @Path("/addPersonInJson")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Person addPersonInJson(Person person) {
        System.out.println(person.getId());
        persons.put(new Integer(person.getId()), person);
        return person;
    }
}

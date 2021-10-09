/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uspg;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import edu.uspg.model.ListaAlumnos;
import edu.uspg.model.Alumno;

public class AdapterApplication {

    static int carnet;
    static String nombres;
    static String apellidos;
    static String correo;
    static int opcion = 0;

    static Scanner scn = new Scanner(System.in);
    static ArrayList<Alumno> listaAlumno = new ArrayList();

    static Alumno alumno;

    public static void main(String[] args) {
        menuPrincipal();
    }

    public static void listObjectTOXML(ListaAlumnos listaAlumnos) {
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(ListaAlumnos.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter sw = new StringWriter();

            jaxbMarshaller.marshal(listaAlumnos, sw);

            String xmlData = sw.toString();

            System.out.println(xmlData);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public static void agregarAlumno() {

        System.out.println("Por favor ingrese los datos solicitados:");

        System.out.println("   Ingrese carnet: ");
        System.out.print(" -");
        carnet = scn.nextInt();

        System.out.println("   Ingrese nombre: ");
        System.out.print(" -");
        nombres = scn.next();

        System.out.println("   Ingrese apellido: ");
        System.out.print(" -");
        apellidos = scn.next();

        System.out.println("   Ingrese correo electronico: ");
        System.out.print(" -");
        correo = scn.next();

        alumno = new Alumno(carnet, nombres, apellidos, correo);
        listaAlumno.add(alumno);

        menuPrincipal();
    }

    public static void menuPrincipal() {
        do {

            System.out.println(" ");
            System.out.println("\t ---Menú para registrar datos---");
            System.out.println("Escriba el número de la acción a realizar");
            System.out.println("1. Ingresar datos Alumnos");
            System.out.println("2. Mostrar datos en XML");
            System.out.println("3. Mostrar datos en JSON");
            System.out.println("4. Salir");
            System.out.print("--Opción:");
            opcion = scn.nextInt();

            switch (opcion) {

                case 1:

                    agregarAlumno();

                    break;

                case 2:

                    System.out.println("\t---Datos XML---");
                    objectToXML(alumno);
                    //listObjectTOXML(listaAlumno);

                    break;

                case 3:

                    System.out.println("\t---Datos JSON---");

            }

        } while (opcion != 4);
    }

    public static void objectToXML(Alumno alumno) {

        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(Alumno.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter sw = new StringWriter();

            jaxbMarshaller.marshal(alumno, sw);

            String xmlData = sw.toString();

            System.out.println(xmlData);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}

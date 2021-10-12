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
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

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
        
         // Creo una instancia de DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Creo un documentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Creo un DOMImplementation
            DOMImplementation implementation = builder.getDOMImplementation();
            
             Source source = new DOMSource(documento);
        
        // Creo un documento con un elemento raiz
             Document documento = implementation.createDocument(null, "AlumnosXML", null);
            documento.setXmlVersion("1.0");
            
            // Creo el Result, indicado que fichero se va a crear
            Result result = new StreamResult(new File("concesionario.xml"));

            // Creo un transformer, se crea el fichero XML
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
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

    public static void documento() {
        try {
            // Creo una instancia de DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Creo un documentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Creo un DOMImplementation
            DOMImplementation implementation = builder.getDOMImplementation();

            // Creo un documento con un elemento raiz
            Document documento = implementation.createDocument(null, "AlumnosXML", null);
            documento.setXmlVersion("1.0");

            /*
             matricula.appendChild(textMatricula);
             coche.appendChild(matricula);
            */
            
            // Creo los elementos
            Element coches = documento.createElement("coches");
            Element coche = documento.createElement("coche");

            // Matricula
            Element matricula = documento.createElement("matricula");
            Text textMatricula = documento.createTextNode("1111AAA");
            matricula.appendChild(textMatricula);
            coche.appendChild(matricula);

            // Marca
            Element marca = documento.createElement("marca");
            Text textMarca = documento.createTextNode("AUDI");
            marca.appendChild(textMarca);
            coche.appendChild(marca);

            // Precio
            Element precio = documento.createElement("precio");
            Text textPrecio = documento.createTextNode("30000");
            precio.appendChild(textPrecio);
            coche.appendChild(precio);

            // Añado al elemento coches el elemento coche
            coches.appendChild(coche);

            // Añado al root el elemento coches
            documento.getDocumentElement().appendChild(coches);

            // Asocio el source con el Document
            Source source = new DOMSource(documento);
            // Creo el Result, indicado que fichero se va a crear
            Result result = new StreamResult(new File("concesionario.xml"));

            // Creo un transformer, se crea el fichero XML
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);

            /*
            // Creo un documento con un elemento raiz
             Document documento = implementation.createDocument(null, "AlumnosXML", null);
            documento.setXmlVersion("1.0");
            
            // Creo el Result, indicado que fichero se va a crear
            Result result = new StreamResult(new File("concesionario.xml"));

            // Creo un transformer, se crea el fichero XML
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);

            */
            
        } catch (ParserConfigurationException | TransformerException ex) {
            System.out.println(ex.getMessage());
        }

    }
}

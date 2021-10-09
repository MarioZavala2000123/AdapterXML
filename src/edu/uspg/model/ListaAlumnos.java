/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uspg.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ListaAlumnos {

    int idLista;
    ArrayList<Alumno> listaAlumno;

    public ListaAlumnos() {
        super();
    }

    public int getIdLista() {
        return idLista;
    }

    @XmlAttribute
    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

    public ArrayList<Alumno> getListaAlumno() {
        return listaAlumno;
    }

    @XmlElement
    public void setListaAlumno(ArrayList<Alumno> listaAlumno) {
        this.listaAlumno = listaAlumno;
    }

}

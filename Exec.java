/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Fotoshop;

import Fotoshop.Imagen;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hulkiniano
 *
 */
public class Exec {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String opcion = "";

        Imagen imagen = new Imagen();
        boolean continuar = true;
        System.out.println("---------------INSERTAR IMAGEN INICIAL---------------");
        do {

            System.out.print("Pon que imagen quieres consultar: ");
            String img = sc.nextLine();
            try {
                imagen.InsertarImagen(img);
                continuar = true;
            } catch (Exception ex) {
                System.out.println("Error al cargar la imagen. Intentelo de nuevo");
                continuar = false;
            }
        } while (!continuar);
        do {
            System.out.println("---------------MENU---------------");
            System.out.println("Que modificaciones de las siguientes le quieres añadir a la imagen: ");

            System.out.println("I. Insertar nueva imagen");
            System.out.println("1. Girar izquierda");
            System.out.println("2. Girar derecha");
            System.out.println("3. Flip horizontal");
            System.out.println("4. Flip vertical");
            System.out.println("5. Obtener neativo");
            System.out.println("6. Filtro Caja");
            System.out.println("G. Guardar fichero");
            System.out.println("S. Salir");

            System.out.print(">");
            opcion = sc.nextLine();

            if (opcion.equalsIgnoreCase("I")) {
                System.out.println("---------------INSERTAR NUEVA IMAGEN---------------");
                System.out.print("Pon que imagen quieres consultar: ");
                String img = sc.nextLine();
                try {
                    imagen.InsertarImagen(img);
                } catch (Exception ex) {
                    System.out.println("Error al cargar la imagen");
                }

            } else if (opcion.equals("1")) {
                System.out.println("---------------APLICAR FILTRO IZQUIERDA---------------");
                imagen.filtroIzquierda();

            } else if (opcion.equals("2")) {
                System.out.println("---------------APLICAR FILTRO DERECHA---------------");
                imagen.filtroDerecha();

            } else if (opcion.equals("3")) {
                System.out.println("---------------APLICAR FLIP HORIZONTAL---------------");
                imagen.flipHorizontal();

            } else if (opcion.equals("4")) {
                System.out.println("---------------APLICAR FLIP VERTICAL---------------");
                imagen.flipVertical();

            } else if (opcion.equals("5")) {
                System.out.println("---------------APLICAR FILTRO NEGATIVO---------------");
                imagen.filtroNegativo();

            } else if (opcion.equals("6")) {
                System.out.println("---------------APLICAR FILTRO CAJA---------------");
                imagen.filtroCaja();

            } else if (opcion.equalsIgnoreCase("S")) {
                System.out.println("Saliendo...");

            } else if (opcion.equalsIgnoreCase("G")) {
                System.out.println("---------------GUARDANDO IMAGEN---------------");
                try {

                    imagen.GuardarFichero(sc);

                } catch (Exception e) {
                    System.out.println("Error al escribir el fichero");
                }

            } else {

                System.out.println("Pon una opcion valida");

            }

        } while (!opcion.equalsIgnoreCase("S"));
    }

}

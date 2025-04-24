import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @author Hulkiniano
 */
public class Imagen {

    private String directorio;
    private double[][] pixeles = null;
    private String info1;
    private String info2;
    private int valormax;

    public void InsertarImagen(String img) throws Exception {

        File fichero = new File(img);
        this.setDirectorio(img);

        try (Scanner scImg = new Scanner(fichero)) {

            this.setInfo1(scImg.nextLine());
            this.setInfo2(scImg.nextLine());
            int Ancho = Integer.parseInt(scImg.next());
            int Alto = Integer.parseInt(scImg.next());

            this.setPixeles(new double[Alto][Ancho]);
            this.setValormax(Integer.parseInt(scImg.next()));

            for (int i = 0; i < this.getPixeles().length; i++) {
                for (int j = 0; j < this.getPixeles()[i].length; j++) {
                    this.getPixeles()[i][j] = (Integer.parseInt(scImg.next()) * 255) / this.getValormax();
                }
            }

        } catch (Exception e) {
            throw e;
        }

    }

    public double[][] getPixeles() {
        return pixeles;
    }

    public void setPixeles(double[][] pixeles) {
        this.pixeles = pixeles;

    }

    public String getDirectorio() {
        return directorio;
    }

    public void setDirectorio(String directorio) {
        this.directorio = directorio;
    }

    public String getInfo1() {
        return info1;
    }

    public void setInfo1(String info1) {
        this.info1 = info1;
    }

    public String getInfo2() {
        return info2;
    }

    public void setInfo2(String info2) {
        this.info2 = info2;
    }

    public int getValormax() {
        return valormax;
    }

    public void setValormax(int valormax) {
        this.valormax = valormax;
    }

    public void GuardarFichero(Scanner sc) throws Exception {

        if (this.pixeles == null) {
            System.out.println("No has introducido ninguna imagen");
        } else {

            String nombre;
            System.out.print("Pon un nombre para guardar la nueva imagen: ");
            nombre = sc.nextLine();
            File fichero = new File(nombre + ".pgm");
            boolean escribir = true;

            if (fichero.exists()) {
                String respuesta;

                do {
                    System.out.print("La imagen ya existe. Quieres Sobreescribirla(S/N): ");
                    respuesta = sc.nextLine();

                    if (!respuesta.equalsIgnoreCase("s") && !respuesta.equalsIgnoreCase("n")) {
                        System.out.println("Pon un valor valido");
                    } else if (respuesta.equalsIgnoreCase("n")) {

                        escribir = false;

                    }
                } while (!respuesta.equalsIgnoreCase("s") && !respuesta.equalsIgnoreCase("n"));

            }

            if (escribir) {

                int dato;
                System.out.println("Guardando imagen...");
                try (FileWriter fw = new FileWriter(fichero)) {

                    fw.write(this.getInfo1() + "\n");
                    fw.write(this.getInfo2() + "\n");
                    fw.write(this.getPixeles()[0].length + " ");
                    fw.write(this.getPixeles().length + "\n");
                    fw.write(this.getValormax() + "\n");

                    for (int i = 0; i < this.getPixeles().length; i++) {
                        for (int j = 0; j < this.getPixeles()[i].length; j++) {
                            dato = (int) this.getPixeles()[i][j];
                            fw.write(dato + "\n");
                        }
                    }
                } catch (Exception e) {
                    throw e;
                }
            }
        }
    }

    public void flipVertical() {
        if (this.pixeles == null) {
            System.out.println("No has introducido ninguna imagen");
        } else {
            System.out.println("Aplicando flip Vertical...");
            int alto = this.getPixeles().length;
            int ancho = this.getPixeles()[0].length;

            double[][] tmp = new double[alto][ancho];

            for (int i = 0; i < alto; i++) {
                for (int j = 0; j < ancho; j++) {
                    tmp[i][ancho - 1 - j] = this.pixeles[i][j];
                }
            }
            this.pixeles = tmp;
        }
    }

    public void flipHorizontal() {
        if (this.pixeles == null) {
            System.out.println("No has introducido ninguna imagen");
        } else {
            System.out.println("Aplicando flip Horizontal...");
            int alto = this.getPixeles().length;
            int ancho = this.getPixeles()[0].length;

            double[][] tmp = new double[alto][ancho];

            for (int i = 0; i < alto; i++) {
                for (int j = 0; j < ancho; j++) {
                    tmp[alto - 1 - i][j] = this.pixeles[i][j];
                }
            }
            this.pixeles = tmp;
        }
    }

    public void filtroNegativo() {

        if (this.pixeles == null) {
            System.out.println("No has introducido ninguna imagen");
        } else {
            System.out.println("Aplicando filtro negativo...");
            for (int i = 0; i < this.getPixeles().length; i++) {
                for (int j = 0; j < this.getPixeles()[i].length; j++) {

                    this.pixeles[i][j] = 127.5 + (127.5 - this.pixeles[i][j]);

                }
            }
        }
    }

    public void filtroCaja() {

        if (this.pixeles == null) {
            System.out.println("No has introducido ninguna imagen");
        } else {
            System.out.println("Aplicando filtro Caja...");
            double[][] nuevo = this.pixeles;
            for (int i = 0; i < this.getPixeles().length; i++) {
                for (int j = 0; j < this.getPixeles()[i].length; j++) {

                    if (i == 0 && j == 0) {
                        nuevo[i][j] = (int) (this.pixeles[i][j] + this.pixeles[i][j + 1] + this.pixeles[i + 1][j] + this.pixeles[i + 1][j + 1]) / 4;
                    } else if (i == this.pixeles.length - 1 && j == this.pixeles[i].length - 1) {
                        nuevo[i][j] = (int) (this.pixeles[i][j] + this.pixeles[i][j - 1] + this.pixeles[i - 1][j] + this.pixeles[i - 1][j - 1]) / 4;
                    } else if (i == this.pixeles.length - 1 && j == 0) {
                        nuevo[i][j] = (int) (this.pixeles[i][j] + this.pixeles[i][j + 1] + this.pixeles[i - 1][j] + this.pixeles[i - 1][j + 1]) / 4;
                    } else if (i == 0 && j == this.pixeles[i].length - 1) {
                        nuevo[i][j] = (int) (this.pixeles[i][j] + this.pixeles[i][j - 1] + this.pixeles[i + 1][j] + this.pixeles[i + 1][j - 1]) / 4;
                    } else if (i == 0) {
                        nuevo[i][j] = (int) (this.pixeles[i][j] + this.pixeles[i + 1][j] + this.pixeles[i][j - 1] + this.pixeles[i][j + 1] + this.pixeles[i + 1][j + 1] + this.pixeles[i + 1][j - 1]) / 6;
                    } else if (i == this.pixeles.length - 1) {
                        nuevo[i][j] = (int) (this.pixeles[i][j] + this.pixeles[i - 1][j] + this.pixeles[i][j - 1] + this.pixeles[i][j + 1] + this.pixeles[i - 1][j + 1] + this.pixeles[i - 1][j - 1]) / 6;
                    } else if (j == 0) {
                        nuevo[i][j] = (int) (this.pixeles[i][j] + this.pixeles[i + 1][j] + this.pixeles[i - 1][j] + this.pixeles[i + 1][j + 1] + this.pixeles[i - 1][j + 1] + this.pixeles[i][j + 1]) / 6;
                    } else if (j == this.pixeles[i].length - 1) {
                        nuevo[i][j] = (int) (this.pixeles[i][j] + this.pixeles[i + 1][j] + this.pixeles[i - 1][j] + this.pixeles[i + 1][j - 1] + this.pixeles[i - 1][j - 1] + this.pixeles[i][j - 1]) / 6;
                    } else {
                        nuevo[i][j] = (int) (this.pixeles[i][j] + this.pixeles[i + 1][j] + this.pixeles[i - 1][j] + this.pixeles[i + 1][j - 1] + this.pixeles[i - 1][j - 1] + this.pixeles[i][j - 1] + this.pixeles[i][j + 1] + this.pixeles[i - 1][j + 1] + this.pixeles[i + 1][j + 1]) / 9;
                    }
                    this.pixeles = nuevo;

                }
            }
        }
    }

    public void filtroIzquierda() {

        if (this.pixeles == null) {
            System.out.println("No has introducido ninguna imagen");
        } else {
            System.out.println("Aplicando filtro Girar Izquierda...");
            double[][] tmp = new double[this.pixeles[0].length][this.pixeles.length];
            for (int i = 0; i < this.pixeles.length; i++) {
                for (int j = 0; j < this.pixeles[i].length; j++) {
                    tmp[j][this.pixeles.length - i - 1] = this.pixeles[i][j];
                }
            }
            this.setPixeles(tmp);
        }
    }

    public void filtroDerecha() {
        if (this.pixeles == null) {
            System.out.println("No has introducido ninguna imagen");
        } else {
            System.out.println("Aplicando filtro Girar Derecha...");
            double[][] tmp = new double[this.pixeles[0].length][this.pixeles.length];

            for (int i = 0; i < this.pixeles.length; i++) {
                for (int j = 0; j < this.pixeles[i].length; j++) {
                    tmp[this.pixeles[i].length - j - 1][i] = this.pixeles[i][j];
                }
            }
            this.setPixeles(tmp);
        }
    }
}

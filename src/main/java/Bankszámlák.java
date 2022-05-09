import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.err;

public class Bankszámlák {
    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        ArrayList<Bank> szamla = XmlBeolvasó.readPillsFromXml("src/main/resources/szamla.xml");
        int choice = -1;
        while (choice != 0) {
            switch (choice) {
                case 1 -> Listázás(szamla);
                case 2 -> Ujszamla(szamla);
                case 3 -> Modositás(szamla);
                case 4 -> Törlés(szamla);
            }
            System.out.println( "1 - Kilistázás\r\n2 - Hozzáadás\r\n"+ "3 - Modositás\r\n4 - Törlés\r\n0 - Exit\n" );
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice < 0 || choice > 4) {
                    err.println("Nem létező opció.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Nem létező opció.");
                scanner.nextLine();
            }
        }

        saveToXml(szamla, "src/main/resources/szamla.xml");
    }

    private static void Listázás(ArrayList<Bank> szamla) {
        for(Bank szamlak:szamla) {
            System.out.println(szamlak.getName() + " - " + szamlak.getId() + " - " + szamlak.getPenz());
        }
        System.out.println("\n");
    }
    private static void Ujszamla(ArrayList<Bank> szamla) {
        String név ="";
        while(true){

            System.out.println("Adja meg az uj számla tulajdonos nevét: ");
            try{
                név = scanner.nextLine();
                for (int i = 0; i < szamla.size(); i++) {
                    if(név.equals(szamla.get(i).getName())){
                        throw new HibasNev();
                    }
                    else if (név==" "){
                        throw new ÜresNév();
                    }
                }
                break;
            }catch (HibasNev hn){
                System.err.println("Már létezik ilyen nevü számla tulajdonos, elöbb törölje a már meglévőt és kezdje ujra a folyamatot");
            }catch (ÜresNév hn){
                System.err.println("nem adhat meg üres nevet");
            }


        }
        String id = "";
        String id2;
        boolean igaz=false;
        System.out.print("Üsse be az id-t (min 2, max 20 karakter): \n");
        while(igaz==false){

            try{
                id = scanner.next();

                for (int i = 0; i < szamla.size(); i++) {
                    if(id.equals(szamla.get(i).getId())){
                        throw new LetezoId();
                    }
                    else if(id.length()>20 || id.length()<2) {

                        throw new HibasId();
                    }
                    else if(i== szamla.size()-1){
                        igaz = true;
                        break;
                    }
                }
            }
            catch (HibasId hi){
                System.err.println("nem jo id hossz \nProbálja ujra");
            }
            catch (LetezoId hi){
                System.err.println("Már Létezik ilyen id-val rendelkező számla \nProbálja ujra");
            }

        }
        int pénz =0;
        while(true){

            System.out.println("Üsse be az új pénz összeget");
            pénz =0;
            try{
                pénz = scanner.nextInt();
                scanner.nextLine();

                break;
            }
            catch (InputMismatchException e){
                System.err.println("nem szám Pénz értéke");
                scanner.nextLine();
            }

        }


        szamla.add(new Bank(név,id,pénz));
        System.out.println("Uj számla sikeresen hozzáadva");
    }
    private static void Modositás(ArrayList<Bank> szamla) {
        System.out.println("Üsse be a számla id-ját a modositáshoz: ");
        String id = scanner.nextLine();
        int choice = -1;
        for (Bank szamlak : szamla) {

            if (szamlak.getId().equals(id)) {


                while (choice != 0) {
                    switch (choice) {
                        case 1 -> Modnev(szamla,id);
                        case 2 -> Modpenz(szamla,id);
                    }
                    System.out.println("1 - Név modositás\r\n2 - Pénz modositás\r\n"
                            + "\r\n0 - Cancel\n");
                    try {
                        choice = scanner.nextInt();
                        if (choice < 0 || choice > 2) {
                            System.err.println("Nem létező opció.");
                            break;
                        }
                        if(choice==0){
                            break;
                        }
                    } catch (InputMismatchException e) {
                        System.err.println("Nem létező opció.");
                        scanner.nextLine();
                    }
                }
                break;
            }

        }
        if(choice!=0) System.err.println("Nem létezik ilyen id-val rendelkező számla");

    }
    public static  void Modnev(ArrayList<Bank> szamla,String id){
        for (Bank szamlak : szamla) {
            if (szamlak.getId().equals(id)) {
                String név = "";
                boolean hamis=false;
                System.out.println("Üsse be a számla uj tulajdonosának nevét: ");

                while(hamis==false){
                    try{
                        while (név =="") név = scanner.nextLine();
                        System.out.println("log :" + név);
                        for (int i = 0; i < szamla.size(); i++) {
                            if(név.equals(szamla.get(i).getName())){
                                throw new LetezoNev();
                            }
                            else if (név==" "){
                                throw new ÜresNév();
                            }
                            else if(i== szamla.size()-1){
                                hamis = true;
                            }
                        }
                    }
                    catch (LetezoNev hn){
                        System.err.println("Már Létezik ilyen névvel számla \nProbálja ujra");
                    }catch (ÜresNév hn){
                        System.err.println("nem adhat meg üres nevet \nProbálja ujra");
                    }

                }
                szamla.set(szamla.indexOf(szamlak), new Bank(név, id, szamlak.getPenz()));
            }
        }
        System.out.println("Számla tulajdonos neve sikeresen modosítva\n");
        return;
    }

    public static  void Modpenz(ArrayList<Bank> szamla,String id){
        for (Bank szamlak : szamla) {
            if (szamlak.getId().equals(id)) {

                while(true){
                    System.out.println("Üsse be a számlán szereplő pénz összeget: ");
                    int pénz =0;
                    try{
                        pénz = scanner.nextInt();
                        szamla.set(szamla.indexOf(szamlak), new Bank(szamlak.getName(), id, pénz));
                        break;
                    }
                    catch (Exception e){
                        System.err.println("Nem szám Pénz érték!");
                        scanner.nextLine();
                    }
                }
            }
        }
        System.out.println("Számla pénz összege sikeresen módosítva.\n");
        return;
    }
    private static void Törlés(ArrayList<Bank> szamla) {
        System.out.println("Üsse be az id-t amelyikhez tartozó számlát törölni kívánja: ");
        String id = scanner.nextLine();
        for (Bank szamlak : szamla) {
            if (szamlak.getId().equals(id)) {
                szamla.remove(szamlak);
                System.out.println("Számla sikeresen törölve\n");
                return;
            }
        }
        System.err.println("Nincs ilyen id-val rendelkező számla.");
    }
    public static void saveToXml(ArrayList<Bank> szamla, String filepath) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element rootElement = document.createElement("szamla");
            document.appendChild(rootElement);

            for (Bank szamlak : szamla) {
                Element pillElement = document.createElement("szamla");
                rootElement.appendChild(pillElement);
                createChildElement(document, pillElement, "név", szamlak.getName());
                createChildElement(document, pillElement, "id", szamlak.getId());
                createChildElement(document, pillElement, "pénz",  String.valueOf(szamlak.getPenz()));

            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileOutputStream(filepath));

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createChildElement(Document document, Element parent,
                                           String tagName, String value) {
        Element element = document.createElement(tagName);
        element.setTextContent(value);
        parent.appendChild(element);
    }
}
class HibasPenz extends Exception{

}

class HibasId extends Exception{

}

class HibasNev extends Exception{

}
class LetezoNev extends Exception{

}
class LetezoId extends Exception{

}
class ÜresNév extends Exception{

}
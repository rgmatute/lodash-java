package rgmatute.lodash.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import rgmatute.lodash.java.__;

/**
 *
 * @author Matute
 */
@SuppressWarnings("unchecked")
public class Test {
    
    public static void main(String[] args) {

        ArrayList<String> lista = new ArrayList<>();
        lista.add("Ronny");
        lista.add("Gabriel");
        // ################################################################
        __.each(lista, (v) -> {
            System.out.println(v);
        });

        __.each(lista, (k, v) -> {
            System.out.println(k + ":" + v);
        });

        __.eachRight(lista, (item) -> {
            System.out.println(item);
        });
        // ################################################################
        __.forEach(lista, (v) -> {
            System.out.println(v);
        });
        __.forEach(lista, (k, v) -> {
            System.out.println(k + ":" + v);
        });
        __.forEachRight(lista, (v) -> {
            System.out.println(v);
        });
        // ################################################################
        ArrayList<String> x = (ArrayList<String>) __.filter(lista, (item) -> {
            return (item == "Ronny");
        });

        __.each(x, (v) -> {
            System.out.println(v);
        });
        // ################################################################
        if (__.isEmail("rgmatute91@gmail.com")) {
            System.out.println("Email correcto Fecha: " + __.currentDateTime());
        }
        // ################################################################
        System.out.println("isPar: " + __.isPar("2999999999921032"));
        System.out.println("currentTime: " + __.currentTime());
        System.out.println("currentDateTime: " + __.currentDateTime());
        System.out.println("currentDateTime: " + __.currentDate("yyyy"));
        System.out.println("isEmail: " + __.isEmail("rgmatute91@gmail.com"));
        System.out.println("isEmail: " + __.isEmail("rgmatute91"));
        // ################################################################
        ArrayList<Object> list = new ArrayList<>();
        list.add("Ronny");
        list.add("Gabriel");
        list.add("Matute");
        list.add("Granizo");
        list.add(2019);
        list.add("rgmatute91@gmail.com");
        list.add("+593981851214");
        System.out.println("Find 1: " + __.findValue(list, "RonnyTest"));
        System.out.println("Find 2: " + __.findValue(list, "Ronny"));
        System.out.println("Find 3: " + __.findValue(list, "rgmatute91@gmail.com"));
        // ################################################################
        HashMap<Object, Object> listMap = new HashMap<>();
        listMap.put("name", "Ronny matute");
        listMap.put("user", "rgmatute");
        listMap.put(2019, "year");
        listMap.put("whatsapp", "+593981851214");
        System.out.println(__.keys(listMap));
        __.each(list, (item) -> {
            System.out.println(item);
        });
        System.out.println(__.invoke(__.list(" foo", " bar "), "trim"));
        System.out.println(__.max(__.list(10, 5, 100, 2, 1000)));
        System.out.println(__.keys(listMap));
        System.out.println(__.values(listMap));
        System.out.println(__.isEqual("r", "r"));
        System.out.println(__.isEqual("r", 1902));
        // ###############################################################
        List l1 = __.list("A","B","C");
        List l2 = __.list("D");
        l1 = __.merge(l1, l2,l1);
        __.forEach(l1,(item)->{
            System.out.println(item);
        });
        
        __.each(__.fileRead("C:\\Users\\Matute\\Downloads\\AVISO_31032019.txt"),(item)->{
            __.println(item);
        });
        
        __.println(__.pluck(l1, (item)->{return (item == "C");}));
        
        __.println(__.pluck(list, (item)->{return (item == "rgmatute91@gmail.com");}));
        
        System.out.println(__.findKey(list, (o)->{ return (o == "rgmatute91@gmail.com"); }));
        
        __.println(__.reject(__.list("A","B","C","A","Z"), (item)->{return (!__.isVocal(item));}));
        __.println(__.object(__.list("moe", "larry", "curly"), __.list("30", "40", "50")));
        __.object(__.list("moe", "larry", "curly"), __.list("30", "40", "50"));
            
    }
}

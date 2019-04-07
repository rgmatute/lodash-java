# lodash-java
A modern Java utility library delivering modularity, performance, &amp; extras.

# Test

      ArrayList<String> lista = new ArrayList<>();
        lista.add("Ronny");
        lista.add("Gabriel");
        
          __.each(lista, (v) -> {
            System.out.println(v);
        });

        __.each(lista, (k, v) -> {
            System.out.println(k + ":" + v);
        });

        __.eachRight(lista, (item) -> {
            System.out.println(item);
        });
#
    ArrayList<String> lista = new ArrayList<>();
        lista.add("Ronny");
        lista.add("Gabriel");
        
        __.forEach(lista, (v) -> {
            System.out.println(v);
        });
        __.forEach(lista, (k, v) -> {
            System.out.println(k + ":" + v);
        });
        __.forEachRight(lista, (v) -> {
            System.out.println(v);
        });
#
        ArrayList<String> x = (ArrayList<String>) __.filter(lista, (item) -> {
            return (item == "Ronny");
        });

        __.each(x, (v) -> {
            System.out.println(v);
        });
#
        if (__.isEmail("rgmatute91@gmail.com")) {
            System.out.println("Email correcto Fecha: " + __.currentDateTime());
        }
#
        System.out.println("isPar: " + __.isPar("2999999999921032"));
        System.out.println("currentTime: " + __.currentTime());
        System.out.println("currentDateTime: " + __.currentDateTime());
        System.out.println("currentDateTime: " + __.currentDate("yyyy"));
        System.out.println("isEmail: " + __.isEmail("rgmatute91@gmail.com"));
        System.out.println("isEmail: " + __.isEmail("rgmatute91"));
#
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
#
        HashMap<Object, Object> listMap = new HashMap<>();
        listMap.put("name", "Ronny matute");
        listMap.put("user", "rgmatute");
        listMap.put(2019, "year");
        listMap.put("whatsapp", "+593981851214");
#
        System.out.println(__.keys(listMap));
#
        __.each(list, (item) -> {
            System.out.println(item);
        });
#
        System.out.println(__.invoke(__.list(" foo", " bar "), "trim"));
        System.out.println(__.max(__.list(10, 5, 100, 2, 1000)));
        System.out.println(__.keys(listMap));
        System.out.println(__.values(listMap));
        System.out.println(__.isEqual("r", "r"));
        System.out.println(__.isEqual("r", 1902));
#

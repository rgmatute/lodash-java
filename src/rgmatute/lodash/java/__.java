package rgmatute.lodash.java;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created By. Ronny Gabriel Matute Granizo  
 * @GitHub https://github.com/rgmatute/lodash-java
 * @Based on Underscore-java
 * @OriginalLibrary http://javadev.github.io/underscore-java/
 * Email: rgmatute91@gmail.com
 * Whatsapp: +593 981851214
 */
@SuppressWarnings("unchecked")
public class __<T> {

    private static final Map<String, Function<String, String>> FUNCTIONS = newLinkedHashMap();
    private static final Map<String, String> TEMPLATE_SETTINGS = new HashMap<String, String>();
    private static final int ARRAY_SIZE_2 = 2;
    private static final int MIN_PASSWORD_LENGTH_8 = 8;
    private static final long CAPACITY_SIZE_5 = 5L;
    private static final long CAPACITY_COEFF_2 = 2L;
    private static final long CAPACITY_SIZE_16 = 16L;
    private static final java.util.concurrent.atomic.AtomicInteger UNIQUE_ID
            = new java.util.concurrent.atomic.AtomicInteger(0);
    private static final String ALL_SYMBOLS = "([\\s\\S]+?)";
    private static final String EVALUATE = "evaluate";
    private static final String INTERPOLATE = "interpolate";
    private static final String ESCAPE = "escape";
    private static final String S_Q = "\\s*\\Q";
    private static final String E_S = "\\E\\s*";
    private static final java.util.regex.Pattern FORMAT_PATTERN
            = java.util.regex.Pattern.compile("\\{\\s*(\\d*)\\s*\\}");
    private static final Map<Character, String> ESCAPES = new HashMap<Character, String>();
    private final Iterable<T> iterable;
    private final Optional<String> string;

    static {
        TEMPLATE_SETTINGS.put(EVALUATE, "<%([\\s\\S]+?)%>");
        TEMPLATE_SETTINGS.put(INTERPOLATE, "<%=([\\s\\S]+?)%>");
        TEMPLATE_SETTINGS.put(ESCAPE, "<%-([\\s\\S]+?)%>");
        ESCAPES.put('&', "&amp;");
        ESCAPES.put('<', "&lt;");
        ESCAPES.put('>', "&gt;");
        ESCAPES.put('"', "&quot;");
        ESCAPES.put('\'', "&#x27;");
        ESCAPES.put('`', "&#x60;");
    }

    public __(final Iterable<T> iterable) {
        this.iterable = iterable;
        this.string = Optional.absent();
    }

    public __(final String string) {
        this.iterable = null;
        this.string = Optional.of(string);
    }

    public static boolean isDateTime_(String ddmmyyyy) {
        if (isDateTime(ddmmyyyy, "dd/MM/yyyy")) {
            return true;
        }
        return isDateTime(ddmmyyyy, "dd-MM-yyyy");
    }

    public static boolean isDateTime(String dateTime, String dateFromat) {
        if (dateTime == null) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(dateTime);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Valida si es Nulo y Empty a la vez
     */
    public static boolean isNullEmpty(Object value) {
        if (value != null) {
            return isMatch(value + "", "^$");
        }
        return true;
    }

    /**
     * Solo acepta formatos de Correo electronicos
     */
    public static boolean isEmail(String value) {
        return isMatch(value, "[-\\w\\.]+@\\w+\\.\\w+");
    }

    /**
     * sólo puede contener caracteres numéricos.
     */
    public static boolean isNumeric(String value) {
        return isMatch(value, "^[0-9]*$");
    }

    /**
     * sólo puede contener caracteres numéricos con hasta cuatro decimales
     * separados por punto.
     */
    public static boolean isDecimal(String value) {
        return isDecimal(value, 4);
    }

    /**
     * sólo puede contener caracteres numéricos con hasta 'N' decimales
     * separados por punto.
     */
    public static boolean isDecimal(String value, int length) {
        return isMatch(value, "^[0-9]+(.[0-9]{1," + length + "})?$");
    }

    /**
     * sólo puede contener caracteres alfabéticos. No acepta espacios.
     */
    public static boolean isAlpha(String value) {
        return isMatch(value, "^[a-zA-ZáéíóúÁÉÍÑÓÚÜ]*$");
    }

    /**
     * sólo puede contener caracteres alfabéticos y espacios.
     */
    public static boolean isAlphaSpace(String value) {
        return isMatch(value, "^[a-zA-ZáéíóúÁÉÍÑÓÚÜ\\s]*$");
    }

    /**
     * sólo puede contener caracteres alfanuméricos.
     */
    public static boolean isAlphaNum(String value) {
        return isMatch(value, "^[a-zA-Z0-9áéíóúÁÉÍÑÓÚÜ]*$");
    }

    /**
     * sólo puede contener caracteres alfanuméricos y espacios.
     */
    public static boolean isAlphaNumSpace(String value) {
        return isMatch(value, "^[a-zA-Z0-9áéíóúÁÉÍÑÓÚÜ\\s]*$");
    }

    /**
     * sólo permite letras, espacios, números, punto, numeral, y guión medio o
     * bajo.
     */
    public static boolean isAnyChar(String value) {
        return isMatch(value, "(^[A-Za-z0-9#-_.\\u00C0-\\u017F\\s]*$)+");
    }

    /**
     * es de tipo placa vehicular y debe constar de 3 letras guion (-) 4
     * números.
     */
    public static boolean isPlaca(String value) {
        return isMatch(value, "[a-zA-Z]{3}-[0-9]{4}");
    }

    /**
     * Solo acepta formato de fecha yyyy-mm-dd.
     */
    public static boolean isDate(String yyyymmdd) {
        return isDate(yyyymmdd, "^\\d{4}-\\d{2}-\\d{2}$");
    }

    /**
     * Acepta cualquier tipo de formato
     */
    public static boolean isDate(String value, String formatRegEx) {
        return isMatch(value, "^" + formatRegEx + "$");
    }

    /**
     * Acepta cualquier tipo de Expresion regular
     */
    public static boolean isMatch(String value, String PatternRegEx) {
        Pattern pattern = Pattern.compile(PatternRegEx);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
        // return value.matches(PatternRegEx);
    }

    /**
     * Acepta unicamente url validas
     */
    public static boolean isUrl(String value) {
        return isMatch(value, "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
    }

    /**
     * Acepta unicamente url validas
     */
    public static boolean isFullname(String value) {
        return isMatch(value, "^[a-zA-z ]*$");
    }

    /**
     * Acepta unicamente Numero de Telefono
     */
    public static boolean isPhone(String value) {
        return isMatch(value, "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}");
    }

    /**
     * Valida si esta vacio
     */
    public static boolean isEmpty(Object value) {
        return (value instanceof String) ? isMatch(value.toString(), "^$") : isNull(value);
    }

    /**
     * Acepta solo DateTime
     */
    public static boolean isDateTime(String value) {
        return isMatch(value, "\\d{4}-[01]\\d-[0-3]\\dT[0-2]\\d:[0-5]\\d:[0-5]\\d(?:\\.\\d+)?Z?");
    }

    /**
     * Acepta Direccion MAC-Address xx:xx:xx:xx:xx:xx
     */
    public static boolean isMacAddress(String value) {
        return isMatch(value, "^([a-fA-F0-9]{2}[:\\.-]?){5}[a-fA-F0-9]{2}$");
    }

    /**
     * Acepta solamente direcciones ipv4
     */
    public static boolean isIpv4(String value) {
        return isMatch(value,
                "^((25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.){3}(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])$");
    }

    /**
     * Acepta solamente direcciones Ipv6
     */
    public static boolean isIpv6(String value) {
        return isMatch(value, "^(([0-9a-fA-F]{0,4}:){1,7}[0-9a-fA-F]{0,4})$");
    }

    /**
     * Acepta solamente Latitudes validas
     */
    public static boolean isLatitude(String value) {
        return isMatch(value, "^(\\+|-)?(?:90(?:(?:\\.0{1,7})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,7})?))$");
    }

    /**
     * Acepta solamente Longitudes validas
     */
    public static boolean isLongitude(String value) {
        return isMatch(value,
                "^(\\+|-)?(?:180(?:(?:\\.0{1,7})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,7})?))$");
    }

    /**
     * Acepta solamente vocales
     */
    public static boolean isVocal(String value) {
        return isMatch(value, "^([aeiouAEIOU])+$");
    }

    /**
     * Acepta solamente consonantes
     */
    public static boolean isConsonante(String value) {
        return !isMatch(value, "^([aeiouAEIOU])+$");
    }

    /**
     * Acepta solamente Numeros pares
     */
    public static boolean isPar(String value) {
        return isMatch(value, "^[0-9]*[02468]$");
    }

    /**
     * Acepta solamente Numeros impares
     */
    public static boolean isImpar(String value) {
        return !isPar(value);
    }

    public static String currentDate(String formatt) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(formatt);
        LocalDateTime localDateTime = LocalDateTime.now();
        return (format.format(localDateTime));
    }

    public static String currentDate() {
        return currentDate("dd/MM/yyyy");
    }

    public static String currentDateTime() {
        return currentDate("dd/MM/yyyy HH:mm:ss");
    }

    public static String currentTime() {
        return currentDate("HH:mm:ss");
    }

    public static Object findValue(List<?> obj, Object b) {
        for (Object item : obj) {
            if (isEqual(item, b)) {
                return item;
            }
        }
        return false;
    }

    private static void setTemplateKey(final Map<String, String> templateSettings, final String key) {
        if (templateSettings.containsKey(key) && templateSettings.get(key).contains(ALL_SYMBOLS)) {
            TEMPLATE_SETTINGS.put(key, templateSettings.get(key));
        }
    }

    public static void templateSettings(final Map<String, String> templateSettings) {
        setTemplateKey(templateSettings, EVALUATE);
        setTemplateKey(templateSettings, INTERPOLATE);
        setTemplateKey(templateSettings, ESCAPE);
    }

    private static final class WherePredicate<E, T> implements Predicate<E> {

        private final List<Tuple<String, T>> properties;

        private WherePredicate(List<Tuple<String, T>> properties) {
            this.properties = properties;
        }

        public boolean test(final E elem) {
            for (Tuple<String, T> prop : properties) {
                try {
                    if (!elem.getClass().getField(prop.fst()).get(elem).equals(prop.snd())) {
                        return false;
                    }
                } catch (Exception ex) {
                    try {
                        if (!elem.getClass().getMethod(prop.fst()).invoke(elem).equals(prop.snd())) {
                            return false;
                        }
                    } catch (Exception e) {
                    }
                }
            }
            return true;
        }
    }

    private static final class TemplateImpl<K, V> implements Template<Map<K, V>> {

        private final String template;

        private TemplateImpl(String template) {
            this.template = template;
        }

        @SuppressWarnings("unchecked")
        public String apply(Map<K, V> value) {
            final String evaluate = TEMPLATE_SETTINGS.get(EVALUATE);
            final String interpolate = TEMPLATE_SETTINGS.get(INTERPOLATE);
            final String escape = TEMPLATE_SETTINGS.get(ESCAPE);
            String result = template;
            for (final Map.Entry<K, V> element : value.entrySet()) {
                result = java.util.regex.Pattern.compile(interpolate.replace(ALL_SYMBOLS,
                        S_Q + element.getKey()
                        + E_S)).matcher(result).replaceAll(String.valueOf(element.getValue()));
                result = java.util.regex.Pattern.compile(escape.replace(ALL_SYMBOLS,
                        S_Q + element.getKey()
                        + E_S)).matcher(result).replaceAll(escape(String.valueOf(element.getValue())));
                result = java.util.regex.Pattern.compile(evaluate.replace(ALL_SYMBOLS,
                        S_Q + element.getKey()
                        + E_S)).matcher(result).replaceAll(String.valueOf(element.getValue()));
            }
            return result;
        }

        public List<String> check(Map<K, V> value) {
            final String evaluate = TEMPLATE_SETTINGS.get(EVALUATE);
            final String interpolate = TEMPLATE_SETTINGS.get(INTERPOLATE);
            final String escape = TEMPLATE_SETTINGS.get(ESCAPE);
            String result = template;
            final List<String> notFound = new ArrayList<String>();
            final List<String> valueKeys = new ArrayList<String>();
            for (final Map.Entry<K, V> element : value.entrySet()) {
                final String key = "" + element.getKey();
                java.util.regex.Matcher matcher = java.util.regex.Pattern.compile(interpolate.replace(ALL_SYMBOLS,
                        S_Q + key + E_S)).matcher(result);
                boolean isFound = matcher.find();
                result = matcher.replaceAll(String.valueOf(element.getValue()));
                matcher = java.util.regex.Pattern.compile(escape.replace(ALL_SYMBOLS,
                        S_Q + key + E_S)).matcher(result);
                isFound |= matcher.find();
                result = matcher.replaceAll(escape(String.valueOf(element.getValue())));
                matcher = java.util.regex.Pattern.compile(evaluate.replace(ALL_SYMBOLS,
                        S_Q + key + E_S)).matcher(result);
                isFound |= matcher.find();
                result = matcher.replaceAll(String.valueOf(element.getValue()));
                if (!isFound) {
                    notFound.add(key);
                }
                valueKeys.add(key);
            }
            final List<String> templateVars = new ArrayList<String>();
            java.util.regex.Matcher matcher = java.util.regex.Pattern.compile(interpolate).matcher(result);
            while (matcher.find()) {
                templateVars.add(matcher.group(1).trim());
            }
            result = matcher.replaceAll("");
            matcher = java.util.regex.Pattern.compile(escape).matcher(result);
            while (matcher.find()) {
                templateVars.add(matcher.group(1).trim());
            }
            result = matcher.replaceAll("");
            matcher = java.util.regex.Pattern.compile(evaluate).matcher(result);
            while (matcher.find()) {
                templateVars.add(matcher.group(1).trim());
            }
            notFound.addAll(difference(templateVars, valueKeys));
            return notFound;
        }
    }

    private static final class MyIterable<T> implements Iterable<T> {

        private final UnaryOperator<T> unaryOperator;
        private boolean firstRun = true;
        private T value;

        MyIterable(final T seed, final UnaryOperator<T> unaryOperator) {
            this.value = seed;
            this.unaryOperator = unaryOperator;
        }

        public Iterator<T> iterator() {
            return new Iterator<T>() {
                @Override
                public boolean hasNext() {
                    return true;
                }

                @Override
                public T next() {
                    if (firstRun) {
                        firstRun = false;
                    } else {
                        value = unaryOperator.apply(value);
                    }
                    return value;
                }

                @Override
                public void remove() {
                }
            };
        }
    }

    public static <K, V> Function<Map<K, V>, V> iteratee(final K key) {
        return new Function<Map<K, V>, V>() {
            public V apply(Map<K, V> item) {
                return item.get(key);
            }
        };
    }

    public static <T> void each(final Iterable<T> iterable, final Consumer<? super T> func) {
        for (T element : iterable) {
            func.accept(element);
        }
    }
    
    public static <T> void each(final Iterable<T> iterable, final BiConsumer<Integer, ? super T> func) {
        eachIndexed(iterable, func);
    }

    private static <T> void eachIndexed(final Iterable<T> iterable, final BiConsumer<Integer, ? super T> func) {
        int index = 0;
        for (T element : iterable) {
            func.accept(index, element);
            index += 1;
        }
    }

    public void each(final Consumer<? super T> func) {
        each(iterable, func);
    }

    public static <T> void eachRight(final Iterable<T> iterable, final Consumer<? super T> func) {
        each(reverse(iterable), func);
    }

    public void eachRight(final Consumer<? super T> func) {
        eachRight(iterable, func);
    }

    public static <T> void forEach(final Iterable<T> iterable, final Consumer<? super T> func) {
        each(iterable, func);
    }
    
    public static <T> void forEach(final Iterable<T> iterable, final BiConsumer<Integer, ? super T> func) {
        eachIndexed(iterable, func);
    }

    public void forEach(final Consumer<? super T> func) {
        each(iterable, func);
    }
    
    public static <T> void forEachRight(final Iterable<T> iterable, final Consumer<? super T> func) {
        eachRight(iterable, func);
    }

    public void forEachRight(final Consumer<? super T> func) {
        eachRight(iterable, func);
    }

    public static <T, E> List<T> map(final List<E> list, final Function<? super E, T> func) {
        final List<T> transformed = newArrayListWithExpectedSize(list.size());
        for (E element : list) {
            transformed.add(func.apply(element));
        }
        return transformed;
    }

    public <F> List<F> map(final Function<? super T, F> func) {
        return map(newArrayList(iterable), func);
    }

    public static <T> List<T> map(final int[] array, final Function<? super Integer, T> func) {
        final List<T> transformed = newArrayListWithExpectedSize(array.length);
        for (int element : array) {
            transformed.add(func.apply(element));
        }
        return transformed;
    }

    public static <T, E> Set<T> map(final Set<E> set, final Function<? super E, T> func) {
        final Set<T> transformed = newLinkedHashSetWithExpectedSize(set.size());
        for (E element : set) {
            transformed.add(func.apply(element));
        }
        return transformed;
    }

    public static <T, E> List<T> mapIndexed(final List<E> list, final BiFunction<Integer, ? super E, T> func) {
        final List<T> transformed = newArrayListWithExpectedSize(list.size());
        int index = 0;
        for (E element : list) {
            transformed.add(func.apply(index, element));
            index += 1;
        }
        return transformed;
    }

    public <F> List<F> mapIndexed(final BiFunction<Integer, ? super T, F> func) {
        return mapIndexed(newArrayList(iterable), func);
    }

    public static <T, E> List<T> collect(final List<E> list, final Function<? super E, T> func) {
        return map(list, func);
    }

    public static <T, E> Set<T> collect(final Set<E> set, final Function<? super E, T> func) {
        return map(set, func);
    }

    public static <T, E> E reduce(final Iterable<T> iterable, final BiFunction<E, T, E> func, final E zeroElem) {
        E accum = zeroElem;
        for (T element : iterable) {
            accum = func.apply(accum, element);
        }
        return accum;
    }

    public static <T> Optional<T> reduce(final Iterable<T> iterable, final BinaryOperator<T> func) {
        boolean foundAny = false;
        T accum = null;
        for (T element : iterable) {
            if (foundAny) {
                accum = func.apply(accum, element);
            } else {
                foundAny = true;
                accum = element;
            }
        }
        return foundAny ? Optional.of(accum) : Optional.<T>absent();
    }

    public static <E> E reduce(final int[] array, final BiFunction<E, ? super Integer, E> func, final E zeroElem) {
        E accum = zeroElem;
        for (int element : array) {
            accum = func.apply(accum, element);
        }
        return accum;
    }

    public static <T, E> E reduce(final T[] array, final BiFunction<E, T, E> func, final E zeroElem) {
        E accum = zeroElem;
        for (T element : array) {
            accum = func.apply(accum, element);
        }
        return accum;
    }

    public static <T, E> E foldl(final Iterable<T> iterable, final BiFunction<E, T, E> func, final E zeroElem) {
        return reduce(iterable, func, zeroElem);
    }

    public static <T, E> E inject(final Iterable<T> iterable, final BiFunction<E, T, E> func, final E zeroElem) {
        return reduce(iterable, func, zeroElem);
    }

    public static <T, E> E reduceRight(final Iterable<T> iterable, final BiFunction<E, T, E> func, final E zeroElem) {
        return reduce(reverse(iterable), func, zeroElem);
    }

    public static <T> Optional<T> reduceRight(final Iterable<T> iterable, final BinaryOperator<T> func) {
        return reduce(reverse(iterable), func);
    }

    public static <E> E reduceRight(final int[] array, final BiFunction<E, ? super Integer, E> func, final E zeroElem) {
        E accum = zeroElem;
        for (Integer element : reverse(array)) {
            accum = func.apply(accum, element);
        }
        return accum;
    }

    public static <T, E> E reduceRight(final T[] array, final BiFunction<E, T, E> func, final E zeroElem) {
        return reduce(reverse(array), func, zeroElem);
    }

    public static <T, E> E foldr(final Iterable<T> iterable, final BiFunction<E, T, E> func, final E zeroElem) {
        return reduceRight(iterable, func, zeroElem);
    }

    public static <E> Optional<E> find(final Iterable<E> iterable, final Predicate<E> pred) {
        for (E element : iterable) {
            if (pred.test(element)) {
                return Optional.of(element);
            }
        }
        return Optional.absent();
    }

    public static <E> Optional<E> detect(final Iterable<E> iterable, final Predicate<E> pred) {
        return find(iterable, pred);
    }

    public static <E> Optional<E> findLast(final Iterable<E> iterable, final Predicate<E> pred) {
        return find(reverse(iterable), pred);
    }

    public static <E> List<E> filter(final List<E> list, final Predicate<E> pred) {
        final List<E> filtered = newArrayList();
        for (E element : list) {
            if (pred.test(element)) {
                filtered.add(element);
            }
        }
        return filtered;
    }
    

    public List<T> filter(final Predicate<T> pred) {
        final List<T> filtered = newArrayList();
        for (final T element : value()) {
            if (pred.test(element)) {
                filtered.add(element);
            }
        }
        return filtered;
    }

    public static <E> List<E> filterIndexed(final List<E> list, final PredicateIndexed<E> pred) {
        final List<E> filtered = newArrayList();
        int index = 0;
        for (E element : list) {
            if (pred.test(index, element)) {
                filtered.add(element);
            }
            index += 1;
        }
        return filtered;
    }

    public static <E> Set<E> filter(final Set<E> set, final Predicate<E> pred) {
        final Set<E> filtered = newLinkedHashSet();
        for (E element : set) {
            if (pred.test(element)) {
                filtered.add(element);
            }
        }
        return filtered;
    }

    public static <E> List<E> select(final List<E> list, final Predicate<E> pred) {
        return filter(list, pred);
    }

    public static <E> Set<E> select(final Set<E> set, final Predicate<E> pred) {
        return filter(set, pred);
    }

    public static <E> List<E> reject(final List<E> list, final Predicate<E> pred) {
        return filter(list, new Predicate<E>() {
            @Override
            public boolean test(E input) {
                return !pred.test(input);
            }
        });
    }

    public List<T> reject(final Predicate<T> pred) {
        return filter(new Predicate<T>() {
            @Override
            public boolean test(T input) {
                return !pred.test(input);
            }
        });
    }

    public static <E> List<E> rejectIndexed(final List<E> list, final PredicateIndexed<E> pred) {
        return filterIndexed(list, new PredicateIndexed<E>() {
            @Override
            public boolean test(int index, E input) {
                return !pred.test(index, input);
            }
        });
    }

    public static <E> Set<E> reject(final Set<E> set, final Predicate<E> pred) {
        return filter(set, new Predicate<E>() {
            @Override
            public boolean test(E input) {
                return !pred.test(input);
            }
        });
    }

    public static <E> List<E> filterFalse(final List<E> list, final Predicate<E> pred) {
        return reject(list, pred);
    }

    public List<T> filterFalse(final Predicate<T> pred) {
        return reject(pred);
    }

    public static <E> Set<E> filterFalse(final Set<E> set, final Predicate<E> pred) {
        return reject(set, pred);
    }

    public static <E> boolean every(final Iterable<E> iterable, final Predicate<E> pred) {
        for (E item : iterable) {
            if (!pred.test(item)) {
                return false;
            }
        }
        return true;
    }

    public boolean every(final Predicate<T> pred) {
        return every(iterable, pred);
    }

    public static <E> boolean all(final Iterable<E> iterable, final Predicate<E> pred) {
        return every(iterable, pred);
    }

    public boolean all(final Predicate<T> pred) {
        return every(iterable, pred);
    }

    public static <E> boolean some(final Iterable<E> iterable, final Predicate<E> pred) {
        return find(iterable, pred).isPresent();
    }

    public boolean some(final Predicate<T> pred) {
        return some(iterable, pred);
    }

    public static <E> boolean any(final Iterable<E> iterable, final Predicate<E> pred) {
        return some(iterable, pred);
    }

    public boolean any(final Predicate<T> pred) {
        return some(iterable, pred);
    }

    public static <E> int count(final Iterable<E> iterable, final Predicate<E> pred) {
        int result = 0;
        for (E item : iterable) {
            if (pred.test(item)) {
                result += 1;
            }
        }
        return result;
    }

    public int count(final Predicate<T> pred) {
        return count(iterable, pred);
    }

    public static <E> boolean contains(final Iterable<E> iterable, final E elem) {
        return some(iterable, new Predicate<E>() {
            @Override
            public boolean test(E e) {
                return elem == null ? e == null : elem.equals(e);
            }
        });
    }

    public boolean contains(final T elem) {
        return contains(iterable, elem);
    }

    public static <E> boolean contains(final Iterable<E> iterable, final E elem, final int fromIndex) {
        final List<E> list = newArrayList(iterable);
        return contains(list.subList(fromIndex, list.size()), elem);
    }

    public static <E> boolean include(final Iterable<E> iterable, final E elem) {
        return contains(iterable, elem);
    }

    @SuppressWarnings("unchecked")
    public static <E> List<E> invoke(final Iterable<E> iterable, final String methodName,
            final List<Object> args) {
        final List<E> result = newArrayList();
        final List<Class<?>> argTypes = map(args, new Function<Object, Class<?>>() {
            public Class<?> apply(Object input) {
                return input.getClass();
            }
        });
        try {
            final Method method = iterable.iterator().next().getClass().getMethod(methodName, argTypes.toArray(
                    new Class[argTypes.size()]));
            for (E arg : iterable) {
                try {
                    result.add((E) method.invoke(arg, args.toArray(new Object[args.size()])));
                } catch (Exception e) {
                    throw new IllegalArgumentException(e);
                }
            }
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        }
        return result;
    }

    public List<T> invoke(final String methodName, final List<Object> args) {
        return invoke(iterable, methodName, args);
    }

    public static <E> List<E> invoke(final Iterable<E> iterable, final String methodName) {
        return invoke(iterable, methodName, Collections.emptyList());
    }

    public List<T> invoke(final String methodName) {
        return invoke(iterable, methodName);
    }

    public static <E> List<Object> pluck(final List<E> list, final String propertyName) {
        if (list.isEmpty()) {
            return Collections.emptyList();
        }
        return map(list, new Function<E, Object>() {
            @Override
            public Object apply(E elem) {
                try {
                    return elem.getClass().getField(propertyName).get(elem);
                } catch (Exception e) {
                    try {
                        return elem.getClass().getMethod(propertyName).invoke(elem);
                    } catch (Exception ex) {
                        throw new IllegalArgumentException(ex);
                    }
                }
            }
        });
    }

    public List<Object> pluck(final String propertyName) {
        return pluck(newArrayList(iterable), propertyName);
    }

    public static <E> Set<Object> pluck(final Set<E> set, final String propertyName) {
        if (set.isEmpty()) {
            return Collections.emptySet();
        }
        return map(set, new Function<E, Object>() {
            @Override
            public Object apply(E elem) {
                try {
                    return elem.getClass().getField(propertyName).get(elem);
                } catch (Exception e) {
                    try {
                        return elem.getClass().getMethod(propertyName).invoke(elem);
                    } catch (Exception ex) {
                        throw new IllegalArgumentException(ex);
                    }
                }
            }
        });
    }

    public static <T, E> List<E> where(final List<E> list,
            final List<Tuple<String, T>> properties) {
        return filter(list, new WherePredicate<E, T>(properties));

    }

    public <E> List<T> where(final List<Tuple<String, E>> properties) {
        return where(newArrayList(iterable), properties);
    }

    public static <T, E> Set<E> where(final Set<E> set,
            final List<Tuple<String, T>> properties) {
        return filter(set, new WherePredicate<E, T>(properties));
    }

    public static <T, E> Optional<E> findWhere(final Iterable<E> iterable,
            final List<Tuple<String, T>> properties) {
        return find(iterable, new WherePredicate<E, T>(properties));
    }

    public <E> Optional<T> findWhere(final List<Tuple<String, E>> properties) {
        return findWhere(iterable, properties);
    }

    public static <E extends Comparable<? super E>> E max(final Collection<E> collection) {
        return Collections.max(collection);
    }

    @SuppressWarnings("unchecked")
    public T max() {
        return (T) max((Collection) iterable);
    }

    @SuppressWarnings("unchecked")
    public static <E, F extends Comparable> E max(final Collection<E> collection, final Function<E, F> func) {
        return Collections.max(collection, new Comparator<E>() {
            @Override
            public int compare(E o1, E o2) {
                return func.apply(o1).compareTo(func.apply(o2));
            }
        });
    }

    @SuppressWarnings("unchecked")
    public <F extends Comparable<? super F>> T max(final Function<T, F> func) {
        return (T) max((Collection) iterable, func);
    }

    public static <E extends Comparable<? super E>> E min(final Collection<E> collection) {
        return Collections.min(collection);
    }

    @SuppressWarnings("unchecked")
    public T min() {
        return (T) min((Collection) iterable);
    }

    @SuppressWarnings("unchecked")
    public static <E, F extends Comparable> E min(final Collection<E> collection, final Function<E, F> func) {
        return Collections.min(collection, new Comparator<E>() {
            @Override
            public int compare(E o1, E o2) {
                return func.apply(o1).compareTo(func.apply(o2));
            }
        });
    }

    @SuppressWarnings("unchecked")
    public <F extends Comparable<? super F>> T min(final Function<T, F> func) {
        return (T) min((Collection) iterable, func);
    }

    public static <E> List<E> shuffle(final Iterable<E> iterable) {
        final List<E> shuffled = newArrayList(iterable);
        Collections.shuffle(shuffled);
        return shuffled;
    }

    public List<T> shuffle() {
        return shuffle(iterable);
    }

    public static <E> E sample(final Iterable<E> iterable) {
        return newArrayList(iterable).get(new java.security.SecureRandom().nextInt(size(iterable)));
    }

    public T sample() {
        return sample(iterable);
    }

    public static <E> Set<E> sample(final List<E> list, final int howMany) {
        final int size = Math.min(howMany, list.size());
        final Set<E> samples = newLinkedHashSetWithExpectedSize(size);
        while (samples.size() < size) {
            E sample = sample(list);
            samples.add(sample);
        }
        return samples;
    }

    public static <T extends Comparable<? super T>> List<T> sortWith(final Iterable<T> iterable,
            final Comparator<T> comparator) {
        final List<T> sortedList = newArrayList(iterable);
        Collections.sort(sortedList, comparator);
        return sortedList;
    }

    @SuppressWarnings("unchecked")
    public <T extends Comparable<? super T>> List<T> sortWith(final Comparator<T> comparator) {
        return sortWith((Iterable<T>) iterable, comparator);
    }

    public static <E, T extends Comparable<? super T>> List<E> sortBy(final Iterable<E> iterable,
            final Function<E, T> func) {
        final List<E> sortedList = newArrayList(iterable);
        Collections.sort(sortedList, new Comparator<E>() {
            @Override
            public int compare(E o1, E o2) {
                return func.apply(o1).compareTo(func.apply(o2));
            }
        });
        return sortedList;
    }

    @SuppressWarnings("unchecked")
    public <E, V extends Comparable<? super V>> List<E> sortBy(final Function<E, V> func) {
        return sortBy((Iterable<E>) iterable, func);
    }

    public static <K, V extends Comparable<? super V>> List<Map<K, V>> sortBy(final Iterable<Map<K, V>> iterable,
            final K key) {
        final List<Map<K, V>> sortedList = newArrayList(iterable);
        Collections.sort(sortedList, new Comparator<Map<K, V>>() {
            @Override
            public int compare(Map<K, V> o1, Map<K, V> o2) {
                return o1.get(key).compareTo(o2.get(key));
            }
        });
        return sortedList;
    }

    public static <K, E> Map<K, List<E>> groupBy(final Iterable<E> iterable, final Function<E, K> func) {
        final Map<K, List<E>> retVal = newLinkedHashMap();
        for (E e : iterable) {
            final K key = func.apply(e);
            List<E> val;
            if (retVal.containsKey(key)) {
                val = retVal.get(key);
            } else {
                val = newArrayList();
            }
            val.add(e);
            retVal.put(key, val);
        }
        return retVal;
    }

    @SuppressWarnings("unchecked")
    public <K, E> Map<K, List<E>> groupBy(final Function<E, K> func) {
        return groupBy((Iterable<E>) iterable, func);
    }

    public static <K, E> Map<K, Optional<E>> groupBy(final Iterable<E> iterable, final Function<E, K> func,
            final BinaryOperator<E> binaryOperator) {
        final Map<K, Optional<E>> retVal = newLinkedHashMap();
        for (Map.Entry<K, List<E>> entry : groupBy(iterable, func).entrySet()) {
            retVal.put(entry.getKey(), reduce(entry.getValue(), binaryOperator));
        }
        return retVal;
    }

    @SuppressWarnings("unchecked")
    public <K, E> Map<K, Optional<E>> groupBy(final Function<E, K> func, final BinaryOperator<E> binaryOperator) {
        return groupBy((Iterable<E>) iterable, func, binaryOperator);
    }

    @SuppressWarnings("unchecked")
    public static <K, E> Map<K, List<E>> indexBy(final Iterable<E> iterable, final String property) {
        return groupBy(iterable, new Function<E, K>() {
            @Override
            public K apply(E elem) {
                try {
                    return (K) elem.getClass().getField(property).get(elem);
                } catch (Exception e) {
                    return null;
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    public <K, E> Map<K, List<E>> indexBy(final String property) {
        return indexBy((Iterable<E>) iterable, property);
    }

    public static <K, E> Map<K, Integer> countBy(final Iterable<E> iterable, Function<E, K> func) {
        final Map<K, Integer> retVal = newLinkedHashMap();
        for (E e : iterable) {
            final K key = func.apply(e);
            if (retVal.containsKey(key)) {
                retVal.put(key, 1 + retVal.get(key));
            } else {
                retVal.put(key, 1);
            }
        }
        return retVal;
    }

    @SuppressWarnings("unchecked")
    public <K, E> Map<K, Integer> countBy(Function<E, K> func) {
        return countBy((Iterable<E>) iterable, func);
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] toArray(final Iterable<E> iterable) {
        return (E[]) newArrayList(iterable).toArray();
    }

    @SuppressWarnings("unchecked")
    public <E> E[] toArray() {
        return toArray((Iterable<E>) iterable);
    }

    public static <K, V> Map<K, V> toMap(final Iterable<Map.Entry<K, V>> iterable) {
        final Map<K, V> result = newLinkedHashMap();
        for (Map.Entry<K, V> entry : iterable) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public <K, V> Map<K, V> toMap() {
        return toMap((Iterable<Map.Entry<K, V>>) iterable);
    }

    public static <K, V> Map<K, V> toMap(final List<Tuple<K, V>> tuples) {
        final Map<K, V> result = newLinkedHashMap();
        for (final Tuple<K, V> entry : tuples) {
            result.put(entry.fst(), entry.snd());
        }
        return result;
    }

    public static int size(final Iterable<?> iterable) {
        if (iterable instanceof Collection) {
            return ((Collection) iterable).size();
        }
        int size;
        final Iterator<?> iterator = iterable.iterator();
        for (size = 0; iterator.hasNext(); size += 1) {
            iterator.next();
        }
        return size;
    }

    public int size() {
        return size(iterable);
    }

    @SuppressWarnings("unchecked")
    public static <E> int size(final E... array) {
        return array.length;
    }

    @SuppressWarnings("unchecked")
    public static <E> List<List<E>> partition(final Iterable<E> iterable, final Predicate<E> pred) {
        final List<E> retVal1 = newArrayList();
        final List<E> retVal2 = newArrayList();
        for (final E e : iterable) {
            if (pred.test(e)) {
                retVal1.add(e);
            } else {
                retVal2.add(e);
            }
        }
        return Arrays.asList(retVal1, retVal2);
    }

    @SuppressWarnings("unchecked")
    public static <E> List<E>[] partition(final E[] iterable, final Predicate<E> pred) {
        return (List<E>[]) partition(Arrays.asList(iterable), pred).toArray(new ArrayList[ARRAY_SIZE_2]);
    }

    public static <E> E first(final Iterable<E> iterable) {
        return iterable.iterator().next();
    }

    @SuppressWarnings("unchecked")
    public static <E> E first(final E... array) {
        return array[0];
    }

    public static <E> List<E> first(final List<E> list, final int n) {
        return list.subList(0, Math.min(n, list.size()));
    }

    public T first() {
        return iterable.iterator().next();
    }

    public List<T> first(final int n) {
        return ((List<T>) iterable).subList(0, n);
    }

    public static <E> E first(final Iterable<E> iterable, final Predicate<E> pred) {
        return filter(newArrayList(iterable), pred).iterator().next();
    }

    public T first(final Predicate<T> pred) {
        return filter(newArrayList(iterable), pred).iterator().next();
    }

    public static <E> E firstOrNull(final Iterable<E> iterable) {
        final Iterator<E> iterator = iterable.iterator();
        return iterator.hasNext() ? iterator.next() : null;
    }

    public T firstOrNull() {
        return firstOrNull((List<T>) iterable);
    }

    public static <E> E firstOrNull(final Iterable<E> iterable, final Predicate<E> pred) {
        final Iterator<E> iterator = filter(newArrayList(iterable), pred).iterator();
        return iterator.hasNext() ? iterator.next() : null;
    }

    public T firstOrNull(final Predicate<T> pred) {
        return firstOrNull((List<T>) iterable, pred);
    }

    public static <E> E head(final Iterable<E> iterable) {
        return first(iterable);
    }

    @SuppressWarnings("unchecked")
    public static <E> E head(final E... array) {
        return first(array);
    }

    public static <E> List<E> head(final List<E> list, final int n) {
        return first(list, n);
    }

    public T head() {
        return first();
    }

    public List<T> head(final int n) {
        return first(n);
    }

    public static <E> List<E> initial(final List<E> list) {
        return initial(list, 1);
    }

    public static <E> List<E> initial(final List<E> list, final int n) {
        return list.subList(0, Math.max(0, list.size() - n));
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] initial(final E... array) {
        return initial(array, 1);
    }

    public static <E> E[] initial(final E[] array, final int n) {
        return Arrays.copyOf(array, array.length - n);
    }

    public List<T> initial() {
        return initial((List<T>) iterable, 1);
    }

    public List<T> initial(final int n) {
        return initial((List<T>) iterable, n);
    }

    @SuppressWarnings("unchecked")
    public static <E> E last(final E... array) {
        return array[array.length - 1];
    }

    public static <E> E last(final List<E> list) {
        return list.get(list.size() - 1);
    }

    public static <E> List<E> last(final List<E> list, final int n) {
        return list.subList(Math.max(0, list.size() - n), list.size());
    }

    public T last() {
        return last((List<T>) iterable);
    }

    public List<T> last(final int n) {
        return last((List<T>) iterable, n);
    }

    public static <E> E last(final List<E> list, final Predicate<E> pred) {
        final List<E> filteredList = filter(list, pred);
        return filteredList.get(filteredList.size() - 1);
    }

    public T last(final Predicate<T> pred) {
        return last((List<T>) iterable, pred);
    }

    public static <E> E lastOrNull(final List<E> list) {
        return list.isEmpty() ? null : list.get(list.size() - 1);
    }

    public T lastOrNull() {
        return lastOrNull((List<T>) iterable);
    }

    public static <E> E lastOrNull(final List<E> list, final Predicate<E> pred) {
        final List<E> filteredList = filter(list, pred);
        return filteredList.isEmpty() ? null : filteredList.get(filteredList.size() - 1);
    }

    public T lastOrNull(final Predicate<T> pred) {
        return lastOrNull((List<T>) iterable, pred);
    }

    public static <E> List<E> rest(final List<E> list) {
        return rest(list, 1);
    }

    public static <E> List<E> rest(final List<E> list, int n) {
        return list.subList(Math.min(n, list.size()), list.size());
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] rest(final E... array) {
        return rest(array, 1);
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] rest(final E[] array, final int n) {
        return (E[]) rest(Arrays.asList(array), n).toArray();
    }

    public List<T> rest() {
        return rest((List<T>) iterable);
    }

    public List<T> rest(int n) {
        return rest((List<T>) iterable, n);
    }

    public static <E> List<E> tail(final List<E> list) {
        return rest(list);
    }

    public static <E> List<E> tail(final List<E> list, final int n) {
        return rest(list, n);
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] tail(final E... array) {
        return rest(array);
    }

    public static <E> E[] tail(final E[] array, final int n) {
        return rest(array, n);
    }

    public List<T> tail() {
        return rest();
    }

    public List<T> tail(final int n) {
        return rest(n);
    }

    public static <E> List<E> drop(final List<E> list) {
        return rest(list);
    }

    public static <E> List<E> drop(final List<E> list, final int n) {
        return rest(list, n);
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] drop(final E... array) {
        return rest(array);
    }

    public static <E> E[] drop(final E[] array, final int n) {
        return rest(array, n);
    }

    public static <E> List<E> compact(final List<E> list) {
        return filter(list, new Predicate<E>() {
            @Override
            public boolean test(E arg) {
                return !String.valueOf(arg).equals("null") && !String.valueOf(arg).equals("0")
                        && !String.valueOf(arg).equals("false") && !String.valueOf(arg).equals("");
            }
        });
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] compact(final E... array) {
        return (E[]) compact(Arrays.asList(array)).toArray();
    }

    public static <E> List<E> compact(final List<E> list, final E falsyValue) {
        return filter(list, new Predicate<E>() {
            @Override
            public boolean test(E arg) {
                return !(arg == null ? falsyValue == null : arg.equals(falsyValue));
            }
        });
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] compact(final E[] array, final E falsyValue) {
        return (E[]) compact(Arrays.asList(array), falsyValue).toArray();
    }

    public List<T> compact() {
        return compact((List<T>) iterable);
    }

    @SuppressWarnings("unchecked")
    public List<T> compact(final T falsyValue) {
        return compact((List<T>) iterable, falsyValue);
    }

    public static <E> List<E> flatten(final List<?> list) {
        List<E> flattened = newArrayList();
        flatten(list, flattened, -1);
        return flattened;
    }

    public static <E> List<E> flatten(final List<?> list, final boolean shallow) {
        List<E> flattened = newArrayList();
        flatten(list, flattened, shallow ? 1 : -1);
        return flattened;
    }

    @SuppressWarnings("unchecked")
    private static <E> void flatten(final List<?> fromTreeList, final List<E> toFlatList, final int shallowLevel) {
        for (Object item : fromTreeList) {
            if (item instanceof List<?> && shallowLevel != 0) {
                flatten((List<?>) item, toFlatList, shallowLevel - 1);
            } else {
                toFlatList.add((E) item);
            }
        }
    }

    public List<T> flatten() {
        return flatten((List<T>) iterable);
    }

    public List<T> flatten(final boolean shallow) {
        return flatten((List<T>) iterable, shallow);
    }

    @SuppressWarnings("unchecked")
    public static <E> List<E> without(final List<E> list, E... values) {
        final List<E> valuesList = Arrays.asList(values);
        return filter(list, new Predicate<E>() {
            @Override
            public boolean test(E elem) {
                return !contains(valuesList, elem);
            }
        });
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] without(final E[] array, final E... values) {
        return (E[]) without(Arrays.asList(array), values).toArray();
    }

    public static <E> List<E> uniq(final List<E> list) {
        return newArrayList(newLinkedHashSet(list));
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] uniq(final E... array) {
        return (E[]) uniq(Arrays.asList(array)).toArray();
    }

    public static <K, E> Collection<E> uniq(final Iterable<E> iterable, final Function<E, K> func) {
        final Map<K, E> retVal = newLinkedHashMap();
        for (final E e : iterable) {
            final K key = func.apply(e);
            retVal.put(key, e);
        }
        return retVal.values();
    }

    @SuppressWarnings("unchecked")
    public static <K, E> E[] uniq(final E[] array, final Function<E, K> func) {
        return (E[]) uniq(Arrays.asList(array), func).toArray();
    }

    public static <E> List<E> distinct(final List<E> list) {
        return uniq(list);
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] distinct(final E... array) {
        return uniq(array);
    }

    public static <K, E> Collection<E> distinctBy(final Iterable<E> iterable, final Function<E, K> func) {
        return uniq(iterable, func);
    }

    @SuppressWarnings("unchecked")
    public static <K, E> E[] distinctBy(final E[] array, final Function<E, K> func) {
        return uniq(array, func);
    }

    @SuppressWarnings("unchecked")
    public static <E> List<E> union(final List<E> list, final List<E>... lists) {
        final Set<E> union = newLinkedHashSet();
        union.addAll(list);
        for (List<E> localList : lists) {
            union.addAll(localList);
        }
        return newArrayList(union);
    }

    @SuppressWarnings("unchecked")
    public List<T> unionWith(final List<T>... lists) {
        return union(newArrayList(iterable), lists);
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] union(final E[]... arrays) {
        final Set<E> union = newLinkedHashSet();
        for (E[] array : arrays) {
            union.addAll(Arrays.asList(array));
        }
        return (E[]) newArrayList(union).toArray();
    }

    public static <E> List<E> intersection(final List<E> list1, final List<E> list2) {
        final List<E> result = newArrayList();
        for (final E item : list1) {
            if (list2.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static <E> List<E> intersection(final List<E> list, final List<E>... lists) {
        final Deque<List<E>> stack = new ArrayDeque<List<E>>();
        stack.push(list);
        for (int index = 0; index < lists.length; index += 1) {
            stack.push(intersection(stack.peek(), lists[index]));
        }
        return stack.peek();
    }

    @SuppressWarnings("unchecked")
    public List<T> intersectionWith(final List<T>... lists) {
        return intersection(newArrayList(iterable), lists);
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] intersection(final E[]... arrays) {
        final Deque<List<E>> stack = new ArrayDeque<List<E>>();
        stack.push(Arrays.asList(arrays[0]));
        for (int index = 1; index < arrays.length; index += 1) {
            stack.push(intersection(stack.peek(), Arrays.asList(arrays[index])));
        }
        return (E[]) stack.peek().toArray();
    }

    public static <E> List<E> difference(final List<E> list1, final List<E> list2) {
        final List<E> result = newArrayList();
        for (final E item : list1) {
            if (!list2.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static <E> List<E> difference(final List<E> list, final List<E>... lists) {
        final Deque<List<E>> stack = new ArrayDeque<List<E>>();
        stack.push(list);
        for (int index = 0; index < lists.length; index += 1) {
            stack.push(difference(stack.peek(), lists[index]));
        }
        return stack.peek();
    }

    @SuppressWarnings("unchecked")
    public List<T> differenceWith(final List<T>... lists) {
        return difference(newArrayList(iterable), lists);
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] difference(final E[]... arrays) {
        final Deque<List<E>> stack = new ArrayDeque<List<E>>();
        stack.push(Arrays.asList(arrays[0]));
        for (int index = 1; index < arrays.length; index += 1) {
            stack.push(difference(stack.peek(), Arrays.asList(arrays[index])));
        }
        return (E[]) stack.peek().toArray();
    }

    @SuppressWarnings("unchecked")
    public static <T> List<List<T>> zip(final List<T>... lists) {
        final List<List<T>> zipped = newArrayList();
        each(Arrays.asList(lists), new Consumer<List<T>>() {
            @Override
            public void accept(final List<T> list) {
                int index = 0;
                for (T elem : list) {
                    final List<T> nTuple = index >= zipped.size() ? __.<T>newArrayList() : zipped.get(index);
                    if (index >= zipped.size()) {
                        zipped.add(nTuple);
                    }
                    index += 1;
                    nTuple.add(elem);
                }
            }
        });
        return zipped;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<List<T>> unzip(final List<T>... lists) {
        final List<List<T>> unzipped = newArrayList();
        for (int index = 0; index < lists[0].size(); index += 1) {
            final List<T> nTuple = newArrayList();
            for (int index2 = 0; index2 < lists.length; index2 += 1) {
                nTuple.add(lists[index2].get(index));
            }
            unzipped.add(nTuple);
        }
        return unzipped;
    }

    public static <K, V> List<Tuple<K, V>> object(final List<K> keys, final List<V> values) {
        return map(keys, new Function<K, Tuple<K, V>>() {
            private int index;

            @Override
            public Tuple<K, V> apply(K key) {
                return Tuple.create(key, values.get(index++));
            }
        });
    }

    public static <E> int findIndex(final List<E> list, final Predicate<E> pred) {
        for (int index = 0; index < list.size(); index++) {
            if (pred.test(list.get(index))) {
                return index;
            }
        }
        return -1;
    }

    public static <E> int findIndex(final E[] array, final Predicate<E> pred) {
        return findIndex(Arrays.asList(array), pred);
    }

    public static <E> int findLastIndex(final List<E> list, final Predicate<E> pred) {
        for (int index = list.size() - 1; index >= 0; index--) {
            if (pred.test(list.get(index))) {
                return index;
            }
        }
        return -1;
    }

    public static <E> int findLastIndex(final E[] array, final Predicate<E> pred) {
        return findLastIndex(Arrays.asList(array), pred);
    }

    public static <E extends Comparable<E>> int sortedIndex(final List<E> list, final E value) {
        int index = 0;
        for (E elem : list) {
            if (elem.compareTo(value) >= 0) {
                return index;
            }
            index += 1;
        }
        return -1;
    }

    public static <E extends Comparable<E>> int sortedIndex(final E[] array, final E value) {
        return sortedIndex(Arrays.asList(array), value);
    }

    @SuppressWarnings("unchecked")
    public static <E extends Comparable<E>> int sortedIndex(final List<E> list, final E value,
            final String propertyName) {
        try {
            final Field property = value.getClass().getField(propertyName);
            final Object valueProperty = property.get(value);
            int index = 0;
            for (E elem : list) {
                if (((Comparable) property.get(elem)).compareTo(valueProperty) >= 0) {
                    return index;
                }
                index += 1;
            }
            return -1;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static <E extends Comparable<E>> int sortedIndex(final E[] array, final E value,
            final String propertyName) {
        return sortedIndex(Arrays.asList(array), value, propertyName);
    }

    public static <E> int indexOf(final List<E> list, final E value) {
        return list.indexOf(value);
    }

    public static <E> int indexOf(final E[] array, final E value) {
        return indexOf(Arrays.asList(array), value);
    }

    public static <E> int lastIndexOf(final List<E> list, final E value) {
        return list.lastIndexOf(value);
    }

    public static <E> int lastIndexOf(final E[] array, final E value) {
        return lastIndexOf(Arrays.asList(array), value);
    }

    public static int[] range(int stop) {
        return range(0, stop, 1);
    }

    public static int[] range(int start, int stop) {
        return range(start, stop, start < stop ? 1 : -1);
    }

    public static int[] range(int start, int stop, int step) {
        int[] array = new int[Math.abs(stop - start) / Math.abs(step)];
        if (start < stop) {
            for (int index = start, index2 = 0; index < stop; index += step, index2 += 1) {
                array[index2] = index;
            }
        } else {
            for (int index = start, index2 = 0; index > stop; index += step, index2 += 1) {
                array[index2] = index;
            }
        }
        return array;
    }

    public static <T> List<List<T>> chunk(final Iterable<T> iterable, final int size) {
        int index = 0;
        int length = size(iterable);
        final List<List<T>> result = new ArrayList<List<T>>(length / size);
        while (index < length) {
            result.add(newArrayList(iterable).subList(index, Math.min(length, index + size)));
            index += size;
        }
        return result;
    }

    public List<List<T>> chunk(final int size) {
        return chunk(getIterable(), size);
    }

    public static <T, F> Function<F, T> bind(final Function<F, T> function) {
        return new Function<F, T>() {
            @Override
            public T apply(F arg) {
                return function.apply(arg);
            }
        };
    }

    public static <T, F> Function<F, T> memoize(final Function<F, T> function) {
        return new MemoizeFunction<F, T>() {
            @Override
            public T calc(F arg) {
                return function.apply(arg);
            }
        };
    }

    public static <T> java.util.concurrent.ScheduledFuture<T> delay(final Supplier<T> function,
            final int delayMilliseconds) {
        final java.util.concurrent.ScheduledExecutorService scheduler
                = java.util.concurrent.Executors.newSingleThreadScheduledExecutor();
        final java.util.concurrent.ScheduledFuture<T> future = scheduler.schedule(
                new java.util.concurrent.Callable<T>() {
            public T call() {
                return function.get();
            }
        }, delayMilliseconds, java.util.concurrent.TimeUnit.MILLISECONDS);
        scheduler.shutdown();
        return future;
    }

    public static <T> java.util.concurrent.ScheduledFuture<T> defer(final Supplier<T> function) {
        return delay(function, 0);
    }

    public static <T> Supplier<T> throttle(final Supplier<T> function, final int waitMilliseconds) {
        class ThrottleFunction<T> implements Supplier<T> {

            private final Supplier<T> localFunction;
            private long previous;
            private java.util.concurrent.ScheduledFuture<T> timeout;

            ThrottleFunction(final Supplier<T> function) {
                this.localFunction = function;
            }

            @Override
            public T get() {
                final long now = now();
                if (previous == 0L) {
                    previous = now;
                }
                final long remaining = waitMilliseconds - (now - previous);
                if (remaining <= 0) {
                    clearTimeout(timeout);
                    previous = now;
                    localFunction.get();
                } else {
                    timeout = delay(localFunction, waitMilliseconds);
                }
                return null;
            }
        }
        return new ThrottleFunction<T>(function);
    }

    public static <T> Supplier<T> debounce(final Supplier<T> function, final int delayMilliseconds) {
        return new Supplier<T>() {
            private java.util.concurrent.ScheduledFuture<T> timeout;

            @Override
            public T get() {
                clearTimeout(timeout);
                timeout = delay(function, delayMilliseconds);
                return null;
            }
        };
    }

    public static <T> Function<Void, T> wrap(final Function<T, T> function,
            final Function<Function<T, T>, T> wrapper) {
        return new Function<Void, T>() {
            public T apply(final Void arg) {
                return wrapper.apply(function);
            }
        };
    }

    public static <E> Predicate<E> negate(final Predicate<E> pred) {
        return new Predicate<E>() {
            public boolean test(final E item) {
                return !pred.test(item);
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static <T> Function<T, T> compose(final Function<T, T>... func) {
        return new Function<T, T>() {
            public T apply(final T arg) {
                T result = arg;
                for (int index = func.length - 1; index >= 0; index -= 1) {
                    result = func[index].apply(result);
                }
                return result;
            }
        };
    }

    public static <E> Supplier<E> after(final int count, final Supplier<E> function) {
        class AfterFunction<E> implements Supplier<E> {

            private final int count;
            private final Supplier<E> localFunction;
            private int index;
            private E result;

            AfterFunction(final int count, final Supplier<E> function) {
                this.count = count;
                this.localFunction = function;
            }

            public E get() {
                if (++index >= count) {
                    result = localFunction.get();
                }
                return result;
            }
        }
        return new AfterFunction<E>(count, function);
    }

    public static <E> Supplier<E> before(final int count, final Supplier<E> function) {
        class BeforeFunction<E> implements Supplier<E> {

            private final int count;
            private final Supplier<E> localFunction;
            private int index;
            private E result;

            BeforeFunction(final int count, final Supplier<E> function) {
                this.count = count;
                this.localFunction = function;
            }

            public E get() {
                if (++index <= count) {
                    result = localFunction.get();
                }
                return result;
            }
        }
        return new BeforeFunction<E>(count, function);
    }

    public static <T> Supplier<T> once(final Supplier<T> function) {
        return new Supplier<T>() {
            private volatile boolean executed;
            private T result;

            @Override
            public T get() {
                if (!executed) {
                    executed = true;
                    result = function.get();
                }
                return result;
            }
        };
    }

    public static <K, V> Set<K> keys(final Map<K, V> object) {
        return object.keySet();
    }

    public static <K, V> Collection<V> values(final Map<K, V> object) {
        return object.values();
    }

    public static <K, V> List<Tuple<K, V>> mapObject(final Map<K, V> object, final Function<? super V, V> func) {
        return map(newArrayList(object.entrySet()), new Function<Map.Entry<K, V>, Tuple<K, V>>() {
            @Override
            public Tuple<K, V> apply(Map.Entry<K, V> entry) {
                return Tuple.create(entry.getKey(), func.apply(entry.getValue()));
            }
        });
    }

    public static <K, V> List<Tuple<K, V>> pairs(final Map<K, V> object) {
        return map(newArrayList(object.entrySet()), new Function<Map.Entry<K, V>, Tuple<K, V>>() {
            @Override
            public Tuple<K, V> apply(Map.Entry<K, V> entry) {
                return Tuple.create(entry.getKey(), entry.getValue());
            }
        });
    }

    public static <K, V> List<Tuple<V, K>> invert(final Map<K, V> object) {
        return map(newArrayList(object.entrySet()), new Function<Map.Entry<K, V>, Tuple<V, K>>() {
            @Override
            public Tuple<V, K> apply(Map.Entry<K, V> entry) {
                return Tuple.create(entry.getValue(), entry.getKey());
            }
        });
    }

    public static List<String> functions(final Object object) {
        final List<String> result = newArrayList();
        for (final Method method : object.getClass().getDeclaredMethods()) {
            if (!method.getName().contains("$")) {
                result.add(method.getName());
            }
        }
        return sort(uniq(result));
    }

    public static List<String> methods(final Object object) {
        return functions(object);
    }

    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> extend(final Map<K, V> destination, final Map<K, V>... sources) {
        final Map<K, V> result = newLinkedHashMap();
        for (final Map.Entry<K, V> entry : destination.entrySet()) {
            result.put(entry.getKey(), entry.getValue());
        }
        for (final Map<K, V> source : sources) {
            for (final Map.Entry<K, V> entry : source.entrySet()) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    public static <E> E findKey(final List<E> list, final Predicate<E> pred) {
        for (int index = 0; index < list.size(); index++) {
            if (pred.test(list.get(index))) {
                return list.get(index);
            }
        }
        return null;
    }

    public static <E> E findKey(final E[] array, final Predicate<E> pred) {
        return findKey(Arrays.asList(array), pred);
    }

    public static <E> E findLastKey(final List<E> list, final Predicate<E> pred) {
        for (int index = list.size() - 1; index >= 0; index--) {
            if (pred.test(list.get(index))) {
                return list.get(index);
            }
        }
        return null;
    }

    public static <E> E findLastKey(final E[] array, final Predicate<E> pred) {
        return findLastKey(Arrays.asList(array), pred);
    }

    @SuppressWarnings("unchecked")
    public static <K, V> List<Tuple<K, V>> pick(final Map<K, V> object, final V... keys) {
        return without(map(newArrayList(object.entrySet()), new Function<Map.Entry<K, V>, Tuple<K, V>>() {
            @Override
            public Tuple<K, V> apply(Map.Entry<K, V> entry) {
                if (Arrays.asList(keys).contains(entry.getKey())) {
                    return Tuple.create(entry.getKey(), entry.getValue());
                } else {
                    return null;
                }
            }
        }), (Tuple<K, V>) null);
    }

    @SuppressWarnings("unchecked")
    public static <K, V> List<Tuple<K, V>> pick(final Map<K, V> object, final Predicate<V> pred) {
        return without(map(newArrayList(object.entrySet()), new Function<Map.Entry<K, V>, Tuple<K, V>>() {
            @Override
            public Tuple<K, V> apply(Map.Entry<K, V> entry) {
                if (pred.test(object.get(entry.getKey()))) {
                    return Tuple.create(entry.getKey(), entry.getValue());
                } else {
                    return null;
                }
            }
        }), (Tuple<K, V>) null);
    }

    @SuppressWarnings("unchecked")
    public static <K, V> List<Tuple<K, V>> omit(final Map<K, V> object, final V... keys) {
        return without(map(newArrayList(object.entrySet()), new Function<Map.Entry<K, V>, Tuple<K, V>>() {
            @Override
            public Tuple<K, V> apply(Map.Entry<K, V> entry) {
                if (Arrays.asList(keys).contains(entry.getKey())) {
                    return null;
                } else {
                    return Tuple.create(entry.getKey(), entry.getValue());
                }
            }
        }), (Tuple<K, V>) null);
    }

    @SuppressWarnings("unchecked")
    public static <K, V> List<Tuple<K, V>> omit(final Map<K, V> object, final Predicate<V> pred) {
        return without(map(newArrayList(object.entrySet()), new Function<Map.Entry<K, V>, Tuple<K, V>>() {
            @Override
            public Tuple<K, V> apply(Map.Entry<K, V> entry) {
                if (pred.test(entry.getValue())) {
                    return null;
                } else {
                    return Tuple.create(entry.getKey(), entry.getValue());
                }
            }
        }), (Tuple<K, V>) null);
    }

    public static <K, V> Map<K, V> defaults(final Map<K, V> object, final Map<K, V> defaults) {
        final Map<K, V> result = newLinkedHashMap();
        for (final Map.Entry<K, V> entry : defaults.entrySet()) {
            result.put(entry.getKey(), entry.getValue());
        }
        for (final Map.Entry<K, V> entry : object.entrySet()) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static Object clone(final Object obj) {
        try {
            if (obj instanceof Cloneable) {
                for (final Method method : obj.getClass().getMethods()) {
                    if (method.getName().equals("clone") && method.getParameterTypes().length == 0) {
                        return method.invoke(obj);
                    }
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        throw new IllegalArgumentException("Cannot clone object");
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] clone(final E... iterable) {
        return Arrays.copyOf(iterable, iterable.length);
    }

    public static <T> void tap(final Iterable<T> iterable, final Consumer<? super T> func) {
        each(iterable, func);
    }

    public static <K, V> boolean isMatch(final Map<K, V> object, final Map<K, V> properties) {
        for (final K key : keys(properties)) {
            if (!object.containsKey(key) || !object.get(key).equals(properties.get(key))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEqual(final Object object, final Object other) {
        return object == null ? other == null : object.equals(other);
    }

    public static <K, V> boolean isEmpty(final Map<K, V> object) {
        return object == null || object.isEmpty();
    }

    public static <T> boolean isEmpty(final Iterable<T> iterable) {
        return iterable == null || !iterable.iterator().hasNext();
    }

    public boolean isEmpty() {
        return iterable == null || !iterable.iterator().hasNext();
    }

    public static <K, V> boolean isNotEmpty(final Map<K, V> object) {
        return object != null && !object.isEmpty();
    }

    public static <T> boolean isNotEmpty(final Iterable<T> iterable) {
        return iterable != null && iterable.iterator().hasNext();
    }

    public boolean isNotEmpty() {
        return iterable != null && iterable.iterator().hasNext();
    }

    public static boolean isArray(final Object object) {
        return object != null && object.getClass().isArray();
    }

    public static boolean isObject(final Object object) {
        return object instanceof Map;
    }

    public static boolean isFunction(final Object object) {
        return object instanceof Function;
    }

    public static boolean isString(final Object object) {
        return object instanceof String;
    }

    public static boolean isNumber(final Object object) {
        return object instanceof Number;
    }

    public static boolean isDate(final Object object) {
        return object instanceof Date;
    }

    public static boolean isRegExp(final Object object) {
        return object instanceof java.util.regex.Pattern;
    }

    public static boolean isError(final Object object) {
        return object instanceof Throwable;
    }

    public static boolean isBoolean(final Object object) {
        return object instanceof Boolean;
    }

    public static boolean isNull(final Object object) {
        return object == null;
    }

    public static <K, V> boolean has(final Map<K, V> object, final K key) {
        return object.containsKey(key);
    }

    public static <E> E identity(final E value) {
        return value;
    }

    public static <E> Supplier<E> constant(final E value) {
        return new Supplier<E>() {
            public E get() {
                return value;
            }
        };
    }

    public static <K, V> Function<Map<K, V>, V> property(final K key) {
        return new Function<Map<K, V>, V>() {
            public V apply(final Map<K, V> object) {
                return object.get(key);
            }
        };
    }

    public static <K, V> Function<K, V> propertyOf(final Map<K, V> object) {
        return new Function<K, V>() {
            public V apply(final K key) {
                return object.get(key);
            }
        };
    }

    public static <K, V> Predicate<Map<K, V>> matcher(final Map<K, V> object) {
        return new Predicate<Map<K, V>>() {
            public boolean test(final Map<K, V> item) {
                for (final K key : keys(object)) {
                    if (!item.containsKey(key) || !item.get(key).equals(object.get(key))) {
                        return false;
                    }
                }
                return true;
            }
        };
    }

    public static <E> void times(final int count, final Supplier<E> function) {
        for (int index = 0; index < count; index += 1) {
            function.get();
        }
    }

    public static int random(final int min, final int max) {
        return min + new java.security.SecureRandom().nextInt(max - min + 1);
    }

    public static int random(final int max) {
        return new java.security.SecureRandom().nextInt(max + 1);
    }

    public static long now() {
        return new Date().getTime();
    }

    public static String escape(final String value) {
        final StringBuilder builder = new StringBuilder();
        for (final char ch : value.toCharArray()) {
            builder.append(ESCAPES.containsKey(ch) ? ESCAPES.get(ch) : ch);
        }
        return builder.toString();
    }

    public static String unescape(final String value) {
        return value.replaceAll("&#x60;", "`").replaceAll("&#x27;", "'").replaceAll("&lt;", "<")
                .replaceAll("&gt;", ">").replaceAll("&quot;", "\"").replaceAll("&amp;", "&");
    }

    public static <E> Object result(final Iterable<E> iterable, final Predicate<E> pred) {
        for (E element : iterable) {
            if (pred.test(element)) {
                if (element instanceof Map.Entry) {
                    if (((Map.Entry) element).getValue() instanceof Supplier) {
                        return ((Supplier) ((Map.Entry) element).getValue()).get();
                    }
                    return ((Map.Entry) element).getValue();
                }
                return element;
            }
        }
        return null;
    }

    public static String uniqueId(final String prefix) {
        return (prefix == null ? "" : prefix) + UNIQUE_ID.incrementAndGet();
    }

    @SafeVarargs
    @SuppressWarnings("varargs")
    public static <T> List<T> list(T... a) {
        return Arrays.asList(a);
    }

    public static String uniquePassword() {
        final String[] passwords = new String[]{
            "ALKJVBPIQYTUIWEBVPQALZVKQRWORTUYOYISHFLKAJMZNXBVMNFGAHKJSDFALAPOQIERIUYTGSFGKMZNXBVJAHGFAKX",
            "1234567890",
            "qpowiealksdjzmxnvbfghsdjtreiuowiruksfhksajmzxncbvlaksjdhgqwetytopskjhfgvbcnmzxalksjdfhgbvzm",
            ".@,-+/()#$%^&*!"
        };
        final StringBuilder result = new StringBuilder();
        final long passwordLength = Math.abs(UUID.randomUUID().getLeastSignificantBits() % MIN_PASSWORD_LENGTH_8)
                + MIN_PASSWORD_LENGTH_8;
        for (int index = 0; index < passwordLength; index += 1) {
            final int passIndex = (int) (passwords.length * (long) index / passwordLength);
            final int charIndex = (int) Math.abs(
                    UUID.randomUUID().getLeastSignificantBits() % passwords[passIndex].length());
            result.append(passwords[passIndex].charAt(charIndex));
        }
        return result.toString();
    }

    public static <K, V> Template<Map<K, V>> template(final String template) {
        return new TemplateImpl<K, V>(template);
    }

    public static String format(final String template, final Object... params) {
        final java.util.regex.Matcher matcher = FORMAT_PATTERN.matcher(template);
        final StringBuffer buffer = new StringBuffer();
        int index = 0;
        while (matcher.find()) {
            if (matcher.group(1).isEmpty()) {
                matcher.appendReplacement(buffer, "<%" + index++ + "%>");
            } else {
                matcher.appendReplacement(buffer, "<%" + matcher.group(1) + "%>");
            }
        }
        matcher.appendTail(buffer);
        final String newTemplate = buffer.toString();
        final Map<Integer, String> args = newLinkedHashMap();
        index = 0;
        for (Object param : params) {
            args.put(index, param.toString());
            index += 1;
        }
        return new TemplateImpl<Integer, String>(newTemplate).apply(args);
    }

    public static <T> Iterable<T> iterate(final T seed, final UnaryOperator<T> unaryOperator) {
        return new MyIterable<T>(seed, unaryOperator);
    }

    public static <T> Chain<T> chain(final List<T> list) {
        return new __.Chain<T>(list);
    }

    public static <T> Chain<T> chain(final Iterable<T> iterable) {
        return new __.Chain<T>(newArrayList(iterable));
    }

    public static <T> Chain<T> chain(final Iterable<T> iterable, int size) {
        return new __.Chain<T>(newArrayList(iterable, size));
    }

    @SuppressWarnings("unchecked")
    public static <T> Chain<T> chain(final T... array) {
        return new __.Chain<T>(Arrays.asList(array));
    }

    public static Chain<Integer> chain(final int[] array) {
        return new __.Chain<Integer>(newIntegerList(array));
    }

    public Chain<T> chain() {
        return new __.Chain<T>(newArrayList(iterable));
    }

    public static class Chain<T> {

        private final T item;
        private final List<T> list;

        @SuppressWarnings("unchecked")
        public Chain(final T item) {
            this.item = item;
            this.list = null;
        }

        public Chain(final List<T> list) {
            this.item = null;
            this.list = list;
        }

        public Chain<T> first() {
            return new Chain<T>(__.first(list));
        }

        public Chain<T> first(int n) {
            return new Chain<T>(__.first(list, n));
        }

        public Chain<T> firstOrNull() {
            return new Chain<T>(__.firstOrNull(list));
        }

        public Chain<T> firstOrNull(final Predicate<T> pred) {
            return new Chain<T>(__.firstOrNull(list, pred));
        }

        public Chain<T> initial() {
            return new Chain<T>(__.initial(list));
        }

        public Chain<T> initial(int n) {
            return new Chain<T>(__.initial(list, n));
        }

        public Chain<T> last() {
            return new Chain<T>(__.last(list));
        }

        public Chain<T> last(int n) {
            return new Chain<T>(__.last(list, n));
        }

        public Chain<T> lastOrNull() {
            return new Chain<T>(__.lastOrNull(list));
        }

        public Chain<T> lastOrNull(final Predicate<T> pred) {
            return new Chain<T>(__.lastOrNull(list, pred));
        }

        public Chain<T> rest() {
            return new Chain<T>(__.rest(list));
        }

        public Chain<T> rest(int n) {
            return new Chain<T>(__.rest(list, n));
        }

        public Chain<T> compact() {
            return new Chain<T>(__.compact(list));
        }

        public Chain<T> compact(final T falsyValue) {
            return new Chain<T>(__.compact(list, falsyValue));
        }

        @SuppressWarnings("unchecked")
        public Chain flatten() {
            return new Chain(__.flatten(list));
        }

        public <F> Chain<F> map(final Function<? super T, F> func) {
            return new Chain<F>(__.map(list, func));
        }

        public <F> Chain<F> mapIndexed(final BiFunction<Integer, ? super T, F> func) {
            return new Chain<F>(__.mapIndexed(list, func));
        }

        public Chain<T> filter(final Predicate<T> pred) {
            return new Chain<T>(__.filter(list, pred));
        }

        public Chain<T> filterIndexed(final PredicateIndexed<T> pred) {
            return new Chain<T>(__.filterIndexed(list, pred));
        }

        public Chain<T> reject(final Predicate<T> pred) {
            return new Chain<T>(__.reject(list, pred));
        }

        public Chain<T> rejectIndexed(final PredicateIndexed<T> pred) {
            return new Chain<T>(__.rejectIndexed(list, pred));
        }

        public Chain<T> filterFalse(final Predicate<T> pred) {
            return new Chain<T>(__.reject(list, pred));
        }

        public <F> Chain<F> reduce(final BiFunction<F, T, F> func, final F zeroElem) {
            return new Chain<F>(__.reduce(list, func, zeroElem));
        }

        public Chain<Optional<T>> reduce(final BinaryOperator<T> func) {
            return new Chain<Optional<T>>(__.reduce(list, func));
        }

        public <F> Chain<F> reduceRight(final BiFunction<F, T, F> func, final F zeroElem) {
            return new Chain<F>(__.reduceRight(list, func, zeroElem));
        }

        public Chain<Optional<T>> reduceRight(final BinaryOperator<T> func) {
            return new Chain<Optional<T>>(__.reduceRight(list, func));
        }

        public Chain<Optional<T>> find(final Predicate<T> pred) {
            return new Chain<Optional<T>>(__.find(list, pred));
        }

        public Chain<Optional<T>> findLast(final Predicate<T> pred) {
            return new Chain<Optional<T>>(__.findLast(list, pred));
        }

        @SuppressWarnings("unchecked")
        public Chain<Comparable> max() {
            return new Chain<Comparable>(__.max((Collection) list));
        }

        public <F extends Comparable<? super F>> Chain<T> max(final Function<T, F> func) {
            return new Chain<T>(__.max(list, func));
        }

        @SuppressWarnings("unchecked")
        public Chain<Comparable> min() {
            return new Chain<Comparable>(__.min((Collection) list));
        }

        public <F extends Comparable<? super F>> Chain<T> min(final Function<T, F> func) {
            return new Chain<T>(__.min(list, func));
        }

        @SuppressWarnings("unchecked")
        public Chain<Comparable> sort() {
            return new Chain<Comparable>(__.sort((List<Comparable>) list));
        }

        @SuppressWarnings("unchecked")
        public <F extends Comparable<? super F>> Chain<F> sortWith(final Comparator<F> comparator) {
            return new Chain<F>(__.sortWith((List<F>) list, comparator));
        }

        public <F extends Comparable<? super F>> Chain<T> sortBy(final Function<T, F> func) {
            return new Chain<T>(__.sortBy(list, func));
        }

        @SuppressWarnings("unchecked")
        public <K> Chain<Map<K, Comparable>> sortBy(final K key) {
            return new Chain<Map<K, Comparable>>(__.sortBy((List<Map<K, Comparable>>) list, key));
        }

        public <F> Chain<Map<F, List<T>>> groupBy(final Function<T, F> func) {
            return new Chain<Map<F, List<T>>>(__.groupBy(list, func));
        }

        public <F> Chain<Map<F, Optional<T>>> groupBy(final Function<T, F> func,
                final BinaryOperator<T> binaryOperator) {
            return new Chain<Map<F, Optional<T>>>(__.groupBy(list, func, binaryOperator));
        }

        public Chain<Map<Object, List<T>>> indexBy(final String property) {
            return new Chain<Map<Object, List<T>>>(__.indexBy(list, property));
        }

        public <F> Chain<Map<F, Integer>> countBy(final Function<T, F> func) {
            return new Chain<Map<F, Integer>>(__.countBy(list, func));
        }

        public Chain<T> shuffle() {
            return new Chain<T>(__.shuffle(list));
        }

        public Chain<T> sample() {
            return new Chain<T>(__.sample(list));
        }

        public Chain<T> sample(final int howMany) {
            return new Chain<T>(__.newArrayList(__.sample(list, howMany)));
        }

        public Chain<T> tap(final Consumer<T> func) {
            __.each(list, func);
            return new Chain<T>(list);
        }

        public Chain<T> forEach(final Consumer<T> func) {
            __.each(list, func);
            return new Chain<T>(list);
        }

        public Chain<T> forEachRight(final Consumer<T> func) {
            __.eachRight(list, func);
            return new Chain<T>(list);
        }

        public Chain<Boolean> every(final Predicate<T> pred) {
            return new Chain<Boolean>(__.every(list, pred));
        }

        public Chain<Boolean> some(final Predicate<T> pred) {
            return new Chain<Boolean>(__.some(list, pred));
        }

        public Chain<Integer> count(final Predicate<T> pred) {
            return new Chain<Integer>(__.count(list, pred));
        }

        public Chain<Boolean> contains(final T elem) {
            return new Chain<Boolean>(__.contains(list, elem));
        }

        public Chain<T> invoke(final String methodName, final List<Object> args) {
            return new Chain<T>(__.invoke(list, methodName, args));
        }

        public Chain<T> invoke(final String methodName) {
            return new Chain<T>(__.invoke(list, methodName));
        }

        public Chain<Object> pluck(final String propertyName) {
            return new Chain<Object>(__.pluck(list, propertyName));
        }

        public <E> Chain<T> where(final List<Tuple<String, E>> properties) {
            return new Chain<T>(__.where(list, properties));
        }

        public <E> Chain<Optional<T>> findWhere(final List<Tuple<String, E>> properties) {
            return new Chain<Optional<T>>(__.findWhere(list, properties));
        }

        public Chain<T> uniq() {
            return new Chain<T>(__.uniq(list));
        }

        @SuppressWarnings("unchecked")
        public <F> Chain<T> uniq(final Function<T, F> func) {
            return new Chain<T>(__.newArrayList(__.uniq(list, func)));
        }

        public Chain<T> distinct() {
            return new Chain<T>(__.uniq(list));
        }

        @SuppressWarnings("unchecked")
        public <F> Chain<F> distinctBy(final Function<T, F> func) {
            return new Chain<F>(__.newArrayList((Iterable<F>) __.uniq(list, func)));
        }

        @SuppressWarnings("unchecked")
        public Chain<T> union(final List<T>... lists) {
            return new Chain<T>(__.union(list, lists));
        }

        @SuppressWarnings("unchecked")
        public Chain<T> intersection(final List<T>... lists) {
            return new Chain<T>(__.intersection(list, lists));
        }

        @SuppressWarnings("unchecked")
        public Chain<T> difference(final List<T>... lists) {
            return new Chain<T>(__.difference(list, lists));
        }

        public Chain<Integer> range(final int stop) {
            return new Chain<Integer>(newIntegerList(__.range(stop)));
        }

        public Chain<Integer> range(final int start, final int stop) {
            return new Chain<Integer>(newIntegerList(__.range(start, stop)));
        }

        public Chain<Integer> range(final int start, final int stop, final int step) {
            return new Chain<Integer>(newIntegerList(__.range(start, stop, step)));
        }

        public Chain<List<T>> chunk(final int size) {
            return new Chain<List<T>>(__.chunk(value(), size));
        }

        @SuppressWarnings("unchecked")
        public Chain<T> concat(final List<T>... lists) {
            return new Chain<T>(__.concat(list, lists));
        }

        public Chain<T> slice(final int start) {
            return new Chain<T>(__.slice(list, start));
        }

        public Chain<T> slice(final int start, final int end) {
            return new Chain<T>(__.slice(list, start, end));
        }

        public Chain<T> reverse() {
            return new Chain<T>(__.reverse(list));
        }

        public Chain<String> join() {
            return new Chain<String>(__.join(list));
        }

        public Chain<String> join(final String separator) {
            return new Chain<String>(__.join(list, separator));
        }

        @SuppressWarnings("unchecked")
        public Chain<T> push(final T... values) {
            return new Chain<T>(__.push(value(), values));
        }

        public Chain<Tuple<T, List<T>>> pop() {
            return new Chain<Tuple<T, List<T>>>(__.pop(value()));
        }

        public Chain<Tuple<T, List<T>>> shift() {
            return new Chain<Tuple<T, List<T>>>(__.shift(value()));
        }

        @SuppressWarnings("unchecked")
        public Chain<T> unshift(final T... values) {
            return new Chain<T>(__.unshift(value(), values));
        }

        public Chain<T> skip(final int numberToSkip) {
            return new Chain<T>(list.subList(numberToSkip, list.size()));
        }

        public Chain<T> limit(final int size) {
            return new Chain<T>(__.first(list, size));
        }

        @SuppressWarnings("unchecked")
        public <K, V> Chain<Map<K, V>> toMap() {
            return new Chain<Map<K, V>>(__.toMap((Iterable<Map.Entry<K, V>>) list));
        }

        public boolean isEmpty() {
            return __.isEmpty(list);
        }

        public boolean isNotEmpty() {
            return __.isNotEmpty(list);
        }

        public int size() {
            return __.size(list);
        }

        public T item() {
            return item;
        }

        public List<T> value() {
            return list;
        }

        public String toString() {
            return String.valueOf(list);
        }
    }

    public static void mixin(final String funcName, final Function<String, String> func) {
        FUNCTIONS.put(funcName, func);
    }

    public Optional<String> call(final String funcName) {
        if (string.isPresent() && FUNCTIONS.containsKey(funcName)) {
            return Optional.of(FUNCTIONS.get(funcName).apply(string.get()));
        }
        return Optional.absent();
    }

    public static <T extends Comparable<T>> List<T> sort(final Iterable<T> iterable) {
        final List<T> localList = newArrayList(iterable);
        Collections.<T>sort(localList);
        return localList;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> T[] sort(final T... array) {
        final T[] localArray = array.clone();
        Arrays.sort(localArray);
        return localArray;
    }

    @SuppressWarnings("unchecked")
    public List<Comparable> sort() {
        return sort((Iterable<Comparable>) iterable);
    }

    public static <T> String join(final Iterable<T> iterable, final String separator) {
        final StringBuilder sb = new StringBuilder();
        int index = 0;
        for (final T item : iterable) {
            if (index > 0) {
                sb.append(separator);
            }
            sb.append(item.toString());
            index += 1;
        }
        return sb.toString();
    }

    public static <T> String join(final Iterable<T> iterable) {
        return join(iterable, " ");
    }

    public static <T> String join(final T[] array, final String separator) {
        return join(Arrays.asList(array), separator);
    }

    public static <T> String join(final T[] array) {
        return join(array, " ");
    }

    public String join(final String separator) {
        return join(iterable, separator);
    }

    public String join() {
        return join(iterable);
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> push(final List<T> list, final T... values) {
        final List<T> result = newArrayList(list);
        for (T value : values) {
            result.add(value);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public List<T> push(final T... values) {
        return push((List<T>) getIterable(), values);
    }

    public static <T> Tuple<T, List<T>> pop(final List<T> list) {
        return Tuple.create(last(list), initial(list));
    }

    public Tuple<T, List<T>> pop() {
        return pop((List<T>) getIterable());
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> unshift(final List<T> list, final T... values) {
        final List<T> result = newArrayList(list);
        int index = 0;
        for (T value : values) {
            result.add(index, value);
            index += 1;
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public List<T> unshift(final T... values) {
        return unshift((List<T>) getIterable(), values);
    }

    public static <T> Tuple<T, List<T>> shift(final List<T> list) {
        return Tuple.create(first(list), rest(list));
    }

    public Tuple<T, List<T>> shift() {
        return shift((List<T>) getIterable());
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] concat(final T[] first, final T[]... other) {
        int length = 0;
        for (T[] otherItem : other) {
            length += otherItem.length;
        }
        final T[] result = Arrays.copyOf(first, first.length + length);
        int index = 0;
        for (T[] otherItem : other) {
            System.arraycopy(otherItem, 0, result, first.length + index, otherItem.length);
            index += otherItem.length;
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> concat(final Iterable<T> first, final Iterable<T>... other) {
        int length = 0;
        for (Iterable<T> otherItem : other) {
            length += size(otherItem);
        }
        final T[] result = Arrays.copyOf(toArray(first), size(first) + length);
        int index = 0;
        for (Iterable<T> otherItem : other) {
            System.arraycopy(toArray(otherItem), 0, result, size(first) + index, size(otherItem));
            index += size(otherItem);
        }
        return Arrays.asList(result);
    }

    @SuppressWarnings("unchecked")
    public List<T> concatWith(final Iterable<T>... other) {
        return concat(iterable, other);
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> slice(final Iterable<T> iterable, final int start) {
        final List<T> result;
        if (start >= 0) {
            result = newArrayList(iterable).subList(start, size(iterable));
        } else {
            result = newArrayList(iterable).subList(size(iterable) + start, size(iterable));
        }
        return result;
    }

    public static <T> T[] slice(final T[] array, final int start) {
        final T[] result;
        if (start >= 0) {
            result = Arrays.copyOfRange(array, start, array.length);
        } else {
            result = Arrays.copyOfRange(array, array.length + start, array.length);
        }
        return result;
    }

    public List<T> slice(final int start) {
        return slice(iterable, start);
    }

    public static <T> List<T> slice(final Iterable<T> iterable, final int start, final int end) {
        final List<T> result;
        if (start >= 0) {
            if (end > 0) {
                result = newArrayList(iterable).subList(start, end);
            } else {
                result = newArrayList(iterable).subList(start, size(iterable) + end);
            }
        } else {
            if (end > 0) {
                result = newArrayList(iterable).subList(size(iterable) + start, end);
            } else {
                result = newArrayList(iterable).subList(size(iterable) + start, size(iterable) + end);
            }
        }
        return result;
    }

    public static <T> T[] slice(final T[] array, final int start, final int end) {
        final T[] result;
        if (start >= 0) {
            if (end > 0) {
                result = Arrays.copyOfRange(array, start, end);
            } else {
                result = Arrays.copyOfRange(array, start, array.length + end);
            }
        } else {
            if (end > 0) {
                result = Arrays.copyOfRange(array, array.length + start, end);
            } else {
                result = Arrays.copyOfRange(array, array.length + start, array.length + end);
            }
        }
        return result;
    }

    public List<T> slice(final int start, final int end) {
        return slice(iterable, start, end);
    }

    public static <T> List<T> reverse(final Iterable<T> iterable) {
        final List<T> result = newArrayList(iterable);
        Collections.reverse(result);
        return result;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] reverse(final T... array) {
        T temp;
        final T[] newArray = array.clone();
        for (int index = 0; index < array.length / 2; index += 1) {
            temp = newArray[index];
            newArray[index] = newArray[array.length - 1 - index];
            newArray[array.length - 1 - index] = temp;
        }
        return newArray;
    }

    public static List<Integer> reverse(final int[] array) {
        final List<Integer> result = newIntegerList(array);
        Collections.reverse(result);
        return result;
    }

    public List<T> reverse() {
        return reverse(iterable);
    }

    public Iterable<T> getIterable() {
        return iterable;
    }

    public Iterable<T> value() {
        return iterable;
    }

    public Optional<String> getString() {
        return string;
    }

    public static <T> java.util.concurrent.ScheduledFuture<T> setTimeout(final Supplier<T> function,
            final int delayMilliseconds) {
        return delay(function, delayMilliseconds);
    }

    public static void clearTimeout(java.util.concurrent.ScheduledFuture scheduledFuture) {
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
    }

    public static <T> java.util.concurrent.ScheduledFuture setInterval(final Supplier<T> function,
            final int delayMilliseconds) {
        final java.util.concurrent.ScheduledExecutorService scheduler
                = java.util.concurrent.Executors.newSingleThreadScheduledExecutor();
        return scheduler.scheduleAtFixedRate(
                new Runnable() {
            public void run() {
                function.get();
            }
        }, delayMilliseconds, delayMilliseconds, java.util.concurrent.TimeUnit.MILLISECONDS);
    }

    public static void clearInterval(java.util.concurrent.ScheduledFuture scheduledFuture) {
        clearTimeout(scheduledFuture);
    }

    public static <T> List<T> copyOf(final Iterable<T> iterable) {
        return newArrayList(iterable);
    }

    public List<T> copyOf() {
        return newArrayList(value());
    }

    public static <T> List<T> copyOfRange(final Iterable<T> iterable, final int start, final int end) {
        return slice(iterable, start, end);
    }

    public List<T> copyOfRange(final int start, final int end) {
        return slice(value(), start, end);
    }

    public static <T> T elementAt(final List<T> list, final int index) {
        return list.get(index);
    }

    @SuppressWarnings("unchecked")
    public T elementAt(final int index) {
        return elementAt((List<T>) value(), index);
    }

    public static <T> T get(final List<T> list, final int index) {
        return elementAt(list, index);
    }

    @SuppressWarnings("unchecked")
    public T get(final int index) {
        return elementAt((List<T>) value(), index);
    }

    public static <T> Tuple<T, List<T>> set(final List<T> list, final int index, final T value) {
        final List<T> newList = newArrayList(list);
        return Tuple.create(newList.set(index, value), newList);
    }

    @SuppressWarnings("unchecked")
    public Tuple<T, List<T>> set(final int index, final T value) {
        return set((List<T>) value(), index, value);
    }

    public static <T> T elementAtOrElse(final List<T> list, final int index, T defaultValue) {
        try {
            return list.get(index);
        } catch (IndexOutOfBoundsException ex) {
            return defaultValue;
        }
    }

    @SuppressWarnings("unchecked")
    public T elementAtOrElse(final int index, T defaultValue) {
        return elementAtOrElse((List<T>) value(), index, defaultValue);
    }

    public static <T> T elementAtOrNull(final List<T> list, final int index) {
        try {
            return list.get(index);
        } catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public T elementAtOrNull(final int index) {
        return elementAtOrNull((List<T>) value(), index);
    }

    public static <T> int lastIndex(final Iterable<T> iterable) {
        return size(iterable) - 1;
    }

    public static <T> int lastIndex(final T[] array) {
        return array.length - 1;
    }

    public static int lastIndex(final int[] array) {
        return array.length - 1;
    }

    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    public static <T> List<T> checkNotNullElements(List<T> references) {
        if (references == null) {
            throw new NullPointerException();
        }
        for (T reference : references) {
            checkNotNull(reference);
        }
        return references;
    }

    public static <T> T checkNotNull(T reference, Object errorMessage) {
        if (reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage));
        }
        return reference;
    }

    public static <T> T defaultTo(T value, T defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    @SuppressWarnings("unchecked")
    protected static <T> List<T> newArrayList() {
        return new ArrayList<T>();
    }

    @SuppressWarnings("unchecked")
    protected static <T> List<T> newArrayList(final Iterable<T> iterable) {
        final List<T> result;
        if (iterable instanceof Collection) {
            result = new ArrayList<T>((Collection) iterable);
        } else {
            result = new ArrayList<T>();
            for (final T item : iterable) {
                result.add(item);
            }
        }
        return result;
    }

    protected static <T> List<T> newArrayList(final Iterable<T> iterable, final int size) {
        final List<T> result = new ArrayList<T>();
        for (int index = 0; iterable.iterator().hasNext() && index < size; index += 1) {
            result.add(iterable.iterator().next());
        }
        return result;
    }

    protected static List<Integer> newIntegerList(int... array) {
        final List<Integer> result = new ArrayList<Integer>(array.length);
        for (final int item : array) {
            result.add(item);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    protected static <T> List<T> newArrayListWithExpectedSize(int size) {
        return new ArrayList<T>((int) (CAPACITY_SIZE_5 + size + (size / 10)));
    }

    @SuppressWarnings("unchecked")
    protected static <T> Set<T> newLinkedHashSet() {
        return new LinkedHashSet<T>();
    }

    @SuppressWarnings("unchecked")
    protected static <T> Set<T> newLinkedHashSet(Iterable<T> iterable) {
        final Set<T> result = new LinkedHashSet<T>();
        for (final T item : iterable) {
            result.add(item);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    protected static <T> Set<T> newLinkedHashSetWithExpectedSize(int size) {
        return new LinkedHashSet<T>((int) Math.max(size * CAPACITY_COEFF_2, CAPACITY_SIZE_16));
    }

    protected static <K, E> Map<K, E> newLinkedHashMap() {
        return new LinkedHashMap<K, E>();
    }

    @SuppressWarnings("unchecked")
    public static <T> Predicate<T> and(
            final Predicate<? super T> pred1,
            final Predicate<? super T> pred2,
            final Predicate<? super T>... rest) {
        checkNotNull(pred1);
        checkNotNull(pred2);
        checkNotNullElements(Arrays.asList(rest));
        return new Predicate<T>() {
            @Override
            public boolean test(T value) {
                boolean result = pred1.test(value) && pred2.test(value);
                if (!result) {
                    return false;
                }
                for (Predicate<? super T> predicate : rest) {
                    if (!predicate.test(value)) {
                        return false;
                    }
                }
                return true;
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static <T> Predicate<T> or(
            final Predicate<? super T> pred1,
            final Predicate<? super T> pred2,
            final Predicate<? super T>... rest) {
        checkNotNull(pred1);
        checkNotNull(pred2);
        checkNotNullElements(Arrays.asList(rest));
        return new Predicate<T>() {
            @Override
            public boolean test(T value) {
                boolean result = pred1.test(value) || pred2.test(value);
                if (result) {
                    return true;
                }
                for (Predicate<? super T> predicate : rest) {
                    if (predicate.test(value)) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    public interface Function<F, T> {
        T apply(F arg);
    }

    public static class Tuple<A, B> {
        private final A first;
        private final B second;

        public Tuple(final A first, final B second) {
            //super();
            this.first = first;
            this.second = second;
        }

        public static <A, B> Tuple<A, B> create(final A a, final B b) {
            return new Tuple<A, B>(a, b);
        }

        public A fst() {
            return first;
        }

        public B snd() {
            return second;
        }

        @Override
        public String toString() {
            return "(" + first + ", " + second + ")";
        }
    }

    public interface PredicateIndexed<T> {
        boolean test(int index, T arg);
    }

    public interface Template<T> extends Function<T, String> {
        List<String> check(T arg);
    }

    public static abstract class MemoizeFunction<F, T> implements Function<F, T> {
        private final Map<F, T> cache = new LinkedHashMap<F, T>();
        public abstract T calc(final F n);
        public T apply(final F key) {
            if (!cache.containsKey(key)) {
                cache.put(key, calc(key));
            }
            return cache.get(key);
        }
    }

    public static final class Optional<T> {
        private static final Optional<?> EMPTY = new Optional();
        private final T arg;
        private final boolean absent;

        private Optional() {
            this.arg = null;
            this.absent = true;
        }

        private Optional(final T arg) {
            this.arg = arg;
            this.absent = false;
        }

        public static <T> Optional<T> of(final T arg) {
            return new Optional<T>(arg);
        }

        public static <T> Optional<T> fromNullable(final T nullableReference) {
            return nullableReference == null ? Optional.<T>absent()
                    : new Optional<T>(nullableReference);
        }

        @SuppressWarnings("unchecked")
        public static <T> Optional<T> absent() {
            return (Optional<T>) EMPTY;
        }

        public T get() {
            if (absent) {
                throw new IllegalStateException("Optional.get() cannot be called on an absent value");
            }
            return arg;
        }

        public T or(final T defaultValue) {
            if (absent) {
                return defaultValue;
            }
            return arg;
        }

        public T orNull() {
            if (absent) {
                return null;
            }
            return arg;
        }

        public boolean isEmpty() {
            return absent;
        }

        public boolean isPresent() {
            return !absent;
        }

        public <F> Optional<F> map(Function<? super T, F> mapper) {
            __.checkNotNull(mapper);
            if (isPresent()) {
                return Optional.fromNullable(mapper.apply(arg));
            } else {
                return absent();
            }
        }

        public <X extends Throwable> T orThrow(Supplier<? extends X> exceptionFunction) throws X {
            if (absent) {
                throw exceptionFunction.get();
            } else {
                return arg;
            }
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            final Optional optional = (Optional) o;

            return absent == optional.absent && !(arg == null ? optional.arg != null : !arg.equals(optional.arg));
        }

        @Override
        public int hashCode() {
            int result = arg == null ? 0 : arg.hashCode();
            result = 31 * result + (absent ? 1 : 0);
            return result;
        }

        @Override
        public String toString() {
            return absent ? "Optional.absent()" : "Optional.of(" + arg + ")";
        }
    }
}
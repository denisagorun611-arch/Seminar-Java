import java.lang.reflect.*;

public class ReflectionDemo {

    // ── 1. Print class information ──────────────────────────────────
    static void printClassInfo(Class<?> clazz) {
        System.out.println("Class name:  " + clazz.getName());
        System.out.println("Simple name: " + clazz.getSimpleName());
        System.out.println("Package:     " + (clazz.getPackageName().isEmpty() ? "(default)" : clazz.getPackageName()));
        System.out.println("Superclass:  " + (clazz.getSuperclass() != null ? clazz.getSuperclass().getSimpleName() : "none"));
        System.out.print("Interfaces:  ");
        for (Class<?> iface : clazz.getInterfaces()) System.out.print(iface.getSimpleName() + " ");
        System.out.println();
    }

    // ── 2. List all fields ──────────────────────────────────────────
    static void printFields(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            System.out.println("  " + Modifier.toString(f.getModifiers()) + " " +
                    f.getType().getSimpleName() + " " + f.getName());
        }
    }

    // ── 3. List all methods ─────────────────────────────────────────
    static void printMethods(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            StringBuilder params = new StringBuilder();
            for (Class<?> p : m.getParameterTypes()) {
                if (params.length() > 0) params.append(", ");
                params.append(p.getSimpleName());
            }
            System.out.println("  " + Modifier.toString(m.getModifiers()) + " " +
                    m.getReturnType().getSimpleName() + " " + m.getName() + "(" + params + ")");
        }
    }

    // ── 9. Object Inspector ─────────────────────────────────────────
    static void inspect(Object obj) {
        Class<?> clazz = obj.getClass();
        System.out.println("Inspecting: " + clazz.getSimpleName());
        for (Field f : clazz.getDeclaredFields()) {
            f.setAccessible(true);
            try {
                System.out.println("  " + f.getName() + " = " + f.get(obj));
            } catch (IllegalAccessException e) {
                System.out.println("  " + f.getName() + " = [inaccessible]");
            }
        }
    }

    // ── 10. JSON Serializer ─────────────────────────────────────────
    static String toJson(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        StringBuilder sb = new StringBuilder("{");
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            sb.append("\"").append(fields[i].getName()).append("\":");
            Object value = fields[i].get(obj);
            if (value instanceof String) {
                sb.append("\"").append(value).append("\"");
            } else {
                sb.append(value);
            }
            if (i < fields.length - 1) sb.append(",");
        }
        sb.append("}");
        return sb.toString();
    }

    // ── 11. CSV Mapper ──────────────────────────────────────────────
    static Object fromCsv(String headerRow, String dataRow, Class<?> clazz) throws Exception {
        String[] headers = headerRow.split(",");
        String[] values  = dataRow.split(",");
        Object obj = clazz.getDeclaredConstructor().newInstance();
        for (int i = 0; i < headers.length; i++) {
            try {
                Field f = clazz.getDeclaredField(headers[i].trim());
                f.setAccessible(true);
                String val = values[i].trim();
                if (f.getType() == int.class) {
                    f.set(obj, Integer.parseInt(val));
                } else if (f.getType() == double.class) {
                    f.set(obj, Double.parseDouble(val));
                } else {
                    f.set(obj, val);
                }
            } catch (NoSuchFieldException e) {
                System.out.println("  Column '" + headers[i].trim() + "' not found, skipping.");
            }
        }
        return obj;
    }

    public static void main(String[] args) {
        try {
            Class<?> studentClass = Student.class;

            // 1. Class info
            System.out.println("=== 1. Class Information ===");
            printClassInfo(studentClass);

            // 2. Fields
            System.out.println("\n=== 2. Declared Fields ===");
            printFields(studentClass);

            // 3. Methods
            System.out.println("\n=== 3. Declared Methods ===");
            printMethods(studentClass);

            // 4. Create object with no-arg constructor
            System.out.println("\n=== 4. Dynamic Object Creation (no-arg) ===");
            Constructor<?> noArg = studentClass.getDeclaredConstructor();
            Object s1 = noArg.newInstance();
            System.out.println("Created: " + s1);

            // 5. Call public method
            System.out.println("\n=== 5. Call Public Method (sayHello) ===");
            Constructor<?> withName = studentClass.getDeclaredConstructor(String.class);
            Object s2 = withName.newInstance("Ana");
            Method sayHello = studentClass.getMethod("sayHello");
            sayHello.invoke(s2);

            // 6. Access & modify private field
            System.out.println("\n=== 6. Access & Modify Private Field ===");
            Field nameField = studentClass.getDeclaredField("name");
            nameField.setAccessible(true);
            System.out.println("Original name: " + nameField.get(s2));
            nameField.set(s2, "Maria");
            System.out.println("Modified name: " + nameField.get(s2));
            System.out.println("Object now: " + s2);

            // 7. Call private method
            System.out.println("\n=== 7. Call Private Method (secretInfo) ===");
            Method secretMethod = studentClass.getDeclaredMethod("secretInfo");
            secretMethod.setAccessible(true);
            Object result = secretMethod.invoke(s2);
            System.out.println("Secret method returned: " + result);

            // 8. Constructor selection
            System.out.println("\n=== 8. Constructor Selection ===");
            Object obj1 = studentClass.getDeclaredConstructor().newInstance();
            Object obj2 = studentClass.getDeclaredConstructor(String.class).newInstance("Ion");
            Object obj3 = studentClass.getDeclaredConstructor(String.class, int.class).newInstance("Elena", 21);
            System.out.println("Student()           -> " + obj1);
            System.out.println("Student(name)       -> " + obj2);
            System.out.println("Student(name, age)  -> " + obj3);

            // 9. Object Inspector
            System.out.println("\n=== 9. Object Inspector ===");
            inspect(obj3);

            // 10. JSON Serializer
            System.out.println("\n=== 10. JSON Serializer ===");
            Constructor<?> fullCtor = studentClass.getDeclaredConstructor(String.class, int.class);
            Object jsonStudent = fullCtor.newInstance("Ana", 20);
            Field gpaField = studentClass.getDeclaredField("gpa");
            gpaField.setAccessible(true);
            gpaField.set(jsonStudent, 9.5);
            System.out.println(toJson(jsonStudent));

            // 11. CSV Mapper
            System.out.println("\n=== 11. CSV Mapper ===");
            String header = "name, age, gpa";
            String data   = "Mihai, 22, 8.75";
            Object fromCsv = fromCsv(header, data, Student.class);
            System.out.println("From CSV: " + fromCsv);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
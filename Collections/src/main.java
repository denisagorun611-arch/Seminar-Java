import java.util.*;

    public static void main(String[] args) {

        // ── 1. ArrayList<String> of student names ───────────────────
        System.out.println("=== 1. Student Names ===");
        List<String> names = new ArrayList<>();
        names.add("Ana");
        names.add("Mihai");
        names.add("Elena");
        names.add("Andrei");
        names.add("Ioana");
        System.out.println("All names: " + names);
        names.remove(2);
        System.out.println("After removing 3rd: " + names);

        // ── 2. Sum & Average ────────────────────────────────────────
        System.out.println("\n=== 2. Sum and Average ===");
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) numbers.add(i * 5);
        int sum = 0;
        for (int n : numbers) sum += n;
        double average = (double) sum / numbers.size();
        System.out.println("Numbers: " + numbers);
        System.out.println("Sum: " + sum + " | Average: " + average);

        // ── 3. Reverse a List ───────────────────────────────────────
        System.out.println("\n=== 3. Reverse List ===");
        List<Integer> toReverse = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("Original: " + toReverse);
        int left = 0, right = toReverse.size() - 1;
        while (left < right) {
            int temp = toReverse.get(left);
            toReverse.set(left, toReverse.get(right));
            toReverse.set(right, temp);
            left++;
            right--;
        }
        System.out.println("Reversed: " + toReverse);

        // ── 4. Unique Words Counter ─────────────────────────────────
        System.out.println("\n=== 4. Unique Words Counter ===");
        String sentence = "java is great and java is fun and great";
        String[] words = sentence.split(" ");
        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));
        System.out.println("Unique words: " + uniqueWords);
        System.out.println("Count: " + uniqueWords.size());

        // ── 5. Word Frequency Counter ───────────────────────────────
        System.out.println("\n=== 5. Word Frequency Counter ===");
        String input = "apple banana apple orange banana apple";
        Map<String, Integer> frequency = new HashMap<>();
        for (String word : input.split(" ")) {
            frequency.put(word, frequency.getOrDefault(word, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : frequency.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // ── 6. Phone Book ───────────────────────────────────────────
        System.out.println("\n=== 6. Phone Book ===");
        Map<String, String> phoneBook = new HashMap<>();
        phoneBook.put("Ana", "0721-111-222");
        phoneBook.put("Mihai", "0733-333-444");
        phoneBook.put("Elena", "0744-555-666");
        String searchName = "Mihai";
        System.out.println("Search '" + searchName + "': " + phoneBook.get(searchName));
        System.out.println("All contacts:");
        for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }

        // ── 7. Student Management ───────────────────────────────────
        System.out.println("\n=== 7. Student Management ===");
        List<Student> students = new ArrayList<>();
        students.add(new Student("Ana", 9));
        students.add(new Student("Mihai", 7));
        students.add(new Student("Elena", 10));
        students.add(new Student("Andrei", 8));
        students.add(new Student("Ioana", 6));
        System.out.println("All students: " + students);

        Student best = students.get(0);
        for (Student s : students) {
            if (s.grade > best.grade) best = s;
        }
        System.out.println("Highest grade: " + best);

        // ── 8. Sort Students ────────────────────────────────────────
        System.out.println("\n=== 8. Sort Students ===");
        students.sort(Comparator.comparing(s -> s.name));
        System.out.println("By name (alphabetical): " + students);
        students.sort((a, b) -> b.grade - a.grade);
        System.out.println("By grade (descending): " + students);

        // ── 9. Remove Duplicates ────────────────────────────────────
        System.out.println("\n=== 9. Remove Duplicates ===");
        List<Student> withDuplicates = new ArrayList<>();
        withDuplicates.add(new Student("Ana", 9));
        withDuplicates.add(new Student("Ana", 9));
        withDuplicates.add(new Student("Mihai", 7));
        withDuplicates.add(new Student("Mihai", 7));
        withDuplicates.add(new Student("Elena", 10));
        Set<Student> uniqueStudents = new LinkedHashSet<>(withDuplicates);
        System.out.println("Without duplicates: " + uniqueStudents);

        // ── 10. LRU Cache ───────────────────────────────────────────
        System.out.println("\n=== 10. LRU Cache ===");
        final int CACHE_CAPACITY = 3;
        LinkedHashMap<Integer, String> lruCache = new LinkedHashMap<>(CACHE_CAPACITY, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
                return size() > CACHE_CAPACITY;
            }
        };
        lruCache.put(1, "Page A");
        lruCache.put(2, "Page B");
        lruCache.put(3, "Page C");
        System.out.println("Cache after 3 inserts: " + lruCache);
        lruCache.get(1);
        lruCache.put(4, "Page D");
        System.out.println("Cache after accessing 1 and adding 4: " + lruCache);

        // ── 11. Merge Two Maps ──────────────────────────────────────
        System.out.println("\n=== 11. Merge Two Maps ===");
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("apple", 3);
        map1.put("banana", 2);
        map1.put("orange", 5);

        Map<String, Integer> map2 = new HashMap<>();
        map2.put("banana", 4);
        map2.put("orange", 1);
        map2.put("grape", 6);

        Map<String, Integer> merged = new HashMap<>(map1);
        for (Map.Entry<String, Integer> entry : map2.entrySet()) {
            merged.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }
        System.out.println("Map1: " + map1);
        System.out.println("Map2: " + map2);
        System.out.println("Merged: " + merged);
    }

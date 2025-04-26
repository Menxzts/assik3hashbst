import java.util.Random;

/**
 * Testing class with a carefully tuned hash function
 * to work with fewer buckets while keeping good distribution
 */
class MyTestingClass {
    private int id;
    private String name;

    public MyTestingClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyTestingClass{id=" + id + ", name='" + name + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MyTestingClass that = (MyTestingClass) obj;
        return id == that.id && name.equals(that.name);
    }

    /**
     * Custom hash function designed for a smaller number of buckets
     * while ensuring no bucket has more than ~240 elements
     */
    @Override
    public int hashCode() {
        // For 10,000 elements and around 43-45 buckets,
        // we need to ensure uniform distribution of values modulo 43-45

        // Start with the basic hash calculation
        int hash = id;

        // Mix in the name hash
        if (name != null) {
            // Only use the first few characters of the name to reduce clustering
            int nameHash = 0;
            int len = Math.min(name.length(), 3);
            for (int i = 0; i < len; i++) {
                nameHash = 31 * nameHash + name.charAt(i);
            }
            hash = hash * 17 + nameHash;
        }

        // Apply some bit manipulation for better distribution
        hash = hash ^ (hash >>> 16);

        return hash;
    }
}

/**
 * Student class for testing
 */
class Student {
    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student that = (Student) obj;
        return id == that.id && (name == that.name || (name != null && name.equals(that.name)));
    }
}

/**
 * Main test class
 */
public class HashTableTest {
    public static void main(String[] args) {
        // Create hash table with 43 buckets (approx 10000/43 = 233 elements per bucket)
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>(43);

        // Create random generator with fixed seed
        Random random = new Random(42);

        System.out.println("Inserting 10000 random elements...");

        // Add 10000 random elements
        for (int i = 0; i < 10000; i++) {
            // Generate random values
            int randomId = random.nextInt(100000);
            String randomName = "Name" + random.nextInt(1000);

            MyTestingClass key = new MyTestingClass(randomId, randomName);
            Student value = new Student(randomId, "Student" + randomName);

            // Use put method from interface
            table.put(key, value);
        }

        // Count the number of elements in each bucket
        // using methods available in the interface
        System.out.println("\nDistribution in buckets:");
        int[] bucketCounts = new int[43];

        // Create new random generator with same seed to generate same keys
        random = new Random(42);
        for (int i = 0; i < 10000; i++) {
            int randomId = random.nextInt(100000);
            String randomName = "Name" + random.nextInt(1000);

            MyTestingClass key = new MyTestingClass(randomId, randomName);

            // Use the hash method to determine the bucket
            int bucketIndex = Math.abs(key.hashCode()) % 43;
            bucketCounts[bucketIndex]++;
        }

        // Print bucket distribution
        int maxBucketSize = 0;
        int totalElements = 0;
        for (int i = 0; i < bucketCounts.length; i++) {
            System.out.println("Bucket " + i + ": " + bucketCounts[i] + " elements");
            if (bucketCounts[i] > maxBucketSize) {
                maxBucketSize = bucketCounts[i];
            }
            totalElements += bucketCounts[i];
        }

        System.out.println("\nTotal elements: " + totalElements);
        System.out.println("Maximum bucket size: " + maxBucketSize);

        // Test the other interface methods
        System.out.println("\nTesting interface methods:");

        // Reset random to generate the same sequence
        random = new Random(42);

        // Select a key-value pair to test with
        int testId = random.nextInt(100000);
        String testName = "Name" + random.nextInt(1000);
        MyTestingClass testKey = new MyTestingClass(testId, testName);
        Student testStudent = new Student(testId, "Student" + testName);

        // Test get method
        Student retrievedStudent = table.get(testKey);
        System.out.println("get() test - Retrieved: " + retrievedStudent);

        // Test contains method
        boolean containsResult = table.contains(testStudent);
        System.out.println("contains() test: " + containsResult);

        // Test getKey method
        MyTestingClass retrievedKey = table.getKey(testStudent);
        System.out.println("getKey() test - Key: " + retrievedKey);

        // Test remove method
        Student removedStudent = table.remove(testKey);
        System.out.println("remove() test - Removed: " + removedStudent);

        // Verify removal
        retrievedStudent = table.get(testKey);
        System.out.println("After removal, get() returns: " + retrievedStudent);
    }
}
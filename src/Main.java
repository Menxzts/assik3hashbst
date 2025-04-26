import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, StudentVal> table = new MyHashTable<>(250);
        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            int id = random.nextInt(1_000_000);

            StringBuilder code = new StringBuilder();
            for (int j = 0; j < 10; j++) {
                code.append((char) ('A' + random.nextInt(26)));
            }
            code.append(random.nextInt(10000)); // добавляем число в конец кода

            MyTestingClass key = new MyTestingClass(id, code.toString());

            StudentVal value = new StudentVal(
                    "Student" + i,
                    18 + random.nextInt(10),
                    random.nextInt(101) // Теперь случайное целое значение для GPA от 0 до 100
            );

            table.put(key, value);
        }

        // Теперь этот метод просто выводит информацию на экран
        printBucketSizes(table);
    }

    private static void printBucketSizes(MyHashTable<?, ?> table) {
        int[] bucketSizes = table.getBucketSizes();  // Теперь вызываем getBucketSizes
        int total = 0, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        for (int i = 0; i < bucketSizes.length; i++) {
            System.out.println("Bucket " + i + ": " + bucketSizes[i] + " elements");
            total += bucketSizes[i];
            if (bucketSizes[i] < min) min = bucketSizes[i];
            if (bucketSizes[i] > max) max = bucketSizes[i];
        }

        System.out.println("Total elements: " + total);
        System.out.println("Min bucket size: " + min);
        System.out.println("Max bucket size: " + max);
        System.out.println("Average bucket size: " + (total / (double) bucketSizes.length));
        System.out.println("Difference between MIN and MAX bucket size: " + (max - min));
    }
}



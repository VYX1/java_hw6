import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class App{
    public static int[] createRandomArray(int arrayLength){
        int[] array = new int[arrayLength];
        Random random = new Random();
        for (int i = 0; i < arrayLength; i++){
            array[i] = random.nextInt(101);
        }
        return array;
    }

    public static void writeArrayToFile(int[] array, String filename){
        try (FileWriter writer = new FileWriter(filename)) {
            for (int num : array) {
                writer.write(num + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filename);
        }
    }

    public static int[] readFileToArray(String filename){ 
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            int count = 0;
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                count++;
            }
            scanner.close();
            
            Scanner scanner2 = new Scanner(file);
            int[] array = new int[count];
            for (int i = 0; i < count; i++) {
                String line = scanner2.nextLine().trim();
                array[i] = Integer.parseInt(line);
            }
            return array;
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filename);
            return new int[0];
        }
    }

    public static void bubbleSort(int[] array){
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n-1; i++){
            swapped = false;
            for (int j = 0; j<n-i-1; j++){
                if (array[j]>array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    swapped = true;
                }
            }
            if (!swapped){
                break;
            }
        }

    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("chose an option:");
        System.out.println("1. create a random array and save to file");
        System.out.println("2. read an array from file, sort it, and save back to file");
        System.out.println("choose option 1 or 2: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.print("enter size of array: ");
            int length = scanner.nextInt();
            scanner.nextLine();

            System.out.print("enter the filename to save the array: ");
            String filename = scanner.nextLine();
            
            int[] array = createRandomArray(length);
            writeArrayToFile(array, filename);
            System.out.println("array created and saved to " + filename);
        
        } else if (choice == 2) {
            System.out.print("Enter the filename to read the array from: ");
            String inputFilename = scanner.nextLine();
            
            System.out.print("Enter the filename to save the sorted array: ");
            String outputFilename = scanner.nextLine();
            
            int[] array = readFileToArray(inputFilename);
            if (array.length > 0) {
                bubbleSort(array);
                writeArrayToFile(array, outputFilename);
                System.out.println("Array sorted and saved to " + outputFilename);
            } else {
                System.out.println("No array was read from the file.");
            }
        } else {
            System.out.println("Invalid choice. Please enter 1 or 2.");
        }
    } 
}
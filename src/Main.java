import java.io.*;
import java.util.*;

public class Main {
    public static final  int INFINITY =10000000;
    static int iter = 0;
    public static int[][] algFloyda(int[][] matrix_smegnosti){
        iter = 0;
        for (int k = 0; k < matrix_smegnosti.length; k++) {
            for (int i = 0; i < matrix_smegnosti.length; i++) {
                for (int j = 0; j < matrix_smegnosti[i].length; j++) {
                    matrix_smegnosti[i][j] = Math.min(matrix_smegnosti[i][j], matrix_smegnosti[i][k]+matrix_smegnosti[k][j]);
                    iter++;
                }
            }

        }
        return matrix_smegnosti;
    }
    public static int[][] conertToNormalMatrix(int[][] matrix, int maxValue, int infinity){
        int[][] normalMatrix = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i==j){
                    normalMatrix[i][j]= 0;
                }
                if (matrix[i][j]>maxValue){
                    normalMatrix[i][j]= infinity;
                }
                else{
                    normalMatrix[i][j]= matrix[i][j];

                }


            }

        }
        return normalMatrix;
    }


    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("data_Floyd_2.csv"));
        for (int k=1; k<=50; k++) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader("data_set_" + k + ".txt"));
                int vertexes = Integer.parseInt(reader.readLine());
                int[][] matrix = new int[vertexes][vertexes];
                String line;
                int i = 0;
                while ((line = reader.readLine()) != null) {
                    matrix[i] = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
                    i++;
                }
                int[][] arr = conertToNormalMatrix(matrix, 70, INFINITY);

                long startTime = System.nanoTime();
                System.out.println(startTime);
                int[][] arr1 = algFloyda(arr);
                System.out.println(arr1);
                long endTime = System.nanoTime();
                writer.write(vertexes + ", " + (endTime-startTime) + ", " + iter +"\n");
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file.");
                e.printStackTrace();
            }
        }
        writer.close();
    }

}








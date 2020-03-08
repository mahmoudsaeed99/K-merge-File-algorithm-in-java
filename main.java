package assignment;

import java.io.IOException;
import java.io.RandomAccessFile;

public class main {
    public static void main(String[] args) throws IOException {
//        RandomAccessFile file1 = new RandomAccessFile("index.bin","rw");
//        file1.seek(0);
//        for(int i=0 ; i<file1.length();i++){
//            System.out.print(file1.readInt());
//            System.out.println("    "+file1.readInt());
//        }
//        file1.close();

//        for(int i=0;i<5;i++){
//            String x = "file"+i+".bin";
//            RandomAccessFile file = new RandomAccessFile(x,"rw");
//            file.close();
//        }
        DivideFile k1 = new DivideFile();
        String [] fileNames = k1.DivideInputFileIntoRuns("index.bin",31);
        sortEachFile s1 = new sortEachFile();
        String [] sortedFiles = s1.SortEachRunOnMemoryAndWriteItBack(fileNames);
        mergeFile m1 = new mergeFile();
//        System.out.println("StartMain");
//        for(int i=0;i<sortedFiles.length;i++){
//            RandomAccessFile file = new RandomAccessFile(sortedFiles[i] , "rw");
//            for(int j=0 ; j<4;j++){
//                System.out.println(file.readInt()+"    "+file.readInt());
//            }
//        }
//        System.out.println("EndMain");
        m1.DoKWayMergeAndWriteASortedFile(fileNames , 3 , "mahmoud.bin");
        SearchFile search = new SearchFile();
        System.out.println( search.BinarySearchOnSortedFile("mahmoud.bin",250));


    }
}

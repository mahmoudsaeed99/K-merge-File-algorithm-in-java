package assignment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DivideFile {
    String [] DivideInputFileIntoRuns (String Inputfilename, int runSize) throws IOException {
        RandomAccessFile file1 = new RandomAccessFile("index.bin","rw");
        // how many record will be stored in each file
        int divideRecordCount = (int) Math.floor(runSize/8);
//        System.out.println(divideRecordCount);
        //total file will be done to store records each record size = 8 and each file should contain divideRecordCount record
        double totalFileCount =(double) file1.length()/(divideRecordCount*8);
//        System.out.println(totalFileCount);
        int totalFile = (int) Math.ceil(totalFileCount);
        String [] filesName = new String[totalFile];
//        System.out.println(totalFile);
        file1.seek(0);
        for(int i=0;i<totalFile;i++){
            String fileName = "file"+i+".bin";
            filesName[i] = fileName;
            RandomAccessFile file = new RandomAccessFile(fileName,"rw");
            // to solve file problem if the size bidder than  remaining records
            if(i==totalFile-1&&runSize%8!=0){

                divideRecordCount = (int) ((file1.length()/8)%divideRecordCount);
//                System.out.println(divideRecordCount);
            }
            for(int j=0;j<divideRecordCount ;j++){
                int key = file1.readInt();
                int offset = file1.readInt();
                file.writeInt(key);
                file.writeInt(offset);
//                file.writeChars("\n");
                System.out.println(key+"   "+offset);

            }
            file.close();
            System.out.println("----------------------------------------------");
        }
        return  filesName;
    }
}

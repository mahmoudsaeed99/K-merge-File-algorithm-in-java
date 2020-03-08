package assignment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class sortEachFile {

    String [] SortEachRunOnMemoryAndWriteItBack (String [] RunsFilesNames) throws IOException {
        for(int i=0;i<RunsFilesNames.length;i++){ 
            RandomAccessFile file = new RandomAccessFile(RunsFilesNames[i],"rw");
            file.seek(0);
            int recordCount = (int) (file.length()/8);
            int [] [] keyOffset = new int[recordCount][2];
            for(int j=0;j<recordCount;j++){
                int key = file.readInt();
                int offset = file.readInt();
                keyOffset[j][0] = key;
                keyOffset[j][1] = offset;
            }
            for(int j =0 ;j<recordCount;j++){
                for(int k = j+1 ; k < recordCount ; k++){
                    if(keyOffset[k][0]<keyOffset[j][0]){
                        int key = keyOffset[k][0];
                        int offset = keyOffset[k][1];
                        keyOffset[k][0] = keyOffset[j][0];
                        keyOffset[k][1] = keyOffset[j][1];
                        keyOffset[j][0] = key;
                        keyOffset[j][1] = offset;
//                        System.out.println(keyOffset[j][0]+"    "+keyOffset[k][0]);
                    }
                }
            }
            file.seek(0);
            for(int j=0;j<recordCount;j++){
                System.out.println(keyOffset[j][0]+"    "+keyOffset[j][1]);
                file.writeInt(keyOffset[j][0]);
                file.writeInt(keyOffset[j][1]);
//                file.writeBytes("\n");
            }
            System.out.println("==================================================");
            file.close();


        }
        return RunsFilesNames;
    }
}

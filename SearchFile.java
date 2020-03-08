package assignment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class SearchFile {
    int BinarySearchOnSortedFile(String Sortedfilename, int RecordKey) throws IOException {
        RandomAccessFile file = new RandomAccessFile(Sortedfilename , "rw");
        int first = 0;
        int last = (int) ((file.length()/8)-1);
//        System.out.println(first+"   "+last);
        int middle;
        boolean found = false;
        int searchOffset = -1;
        while(first<=last && !found){
               middle = (first+last)/2;
               file.seek(middle*8);
               int key = file.readInt();
               int offset = file.readInt();
                System.out.println(key);
               if(RecordKey == key){
                   found=true;
                   searchOffset = offset;
               }
               else{
                   if(RecordKey > key){
                       first = middle+1;
                   }
                   else{
                       last = middle-1;
                   }
               }
        }
            return searchOffset;
    }
}

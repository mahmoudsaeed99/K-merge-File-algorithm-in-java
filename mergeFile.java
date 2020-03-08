package assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class mergeFile {

    void DoKWayMergeAndWriteASortedFile(String [] SortedRunsNames, int k ,String Sortedfilename) throws IOException {
        Queue<String> filesNames = new LinkedList<>(Arrays.asList(SortedRunsNames));
//        System.out.println(filesNames.size());
        int new_file = 0;
        boolean bool = true;
        while (filesNames.size()!=1)
        {
            // if the remaining files less than the k
            if (filesNames.size() < k)
            {
//                System.out.println("enter");
                k = filesNames.size();
            }

            String[] readyToMerge = new String[k];
            for (int i = 0; i < k; i++) {
                readyToMerge[i] = filesNames.poll();
                System.out.println(readyToMerge[i]);
            }
            String newMergeFile = mergeReadyFile(readyToMerge, new_file);
            filesNames.add(newMergeFile);
            new_file++;
        }
        // rename file to sortedFile
        RenameRandomFile rename = new RenameRandomFile();
        rename.rename(filesNames.peek() , Sortedfilename);

    }
    String mergeReadyFile(String [] readyFiles , int fileNumber) throws IOException {
        ArrayList<Data> data = new ArrayList<Data>();
        String fileName = "mergedFile"+fileNumber+".bin";
        RandomAccessFile mergedFile = new RandomAccessFile(fileName,"rw");
        int totalSize = 0;
        // store first k record form file into arrayList
        for(int i=0 ; i<readyFiles.length ; i++){
            RandomAccessFile file = new RandomAccessFile(readyFiles[i] , "rw");
            Data data1 = new Data();
            data1.key = file.readInt();
            data1.offset = file.readInt();
            data1.size = 0;
            data1.filename = readyFiles[i];
            data.add(data1);
            totalSize+=file.length()/8;
            file.close();

        }
//        Collections.sort(data, new sort());
        //loop to total size of all record to get all records in files to merge it together
        for (int i=0; i<totalSize; i++)
        {

            Collections.sort(data, new sort());
            mergedFile.writeInt(data.get(0).key);
            mergedFile.writeInt(data.get(0).offset);
            data.get(0).size++;
            System.out.println(data.get(0).filename+"   "+data.get(0).key);
            RandomAccessFile file = new RandomAccessFile(data.get(0).filename , "rw");
            int sizeOfRun = (int) (file.length()/8);
            // if we arrive to the end of file
            if (data.get(0).size == sizeOfRun){
                data.remove(data.get(0));
            }
            // else we get next record in this file
            else{
                file.seek(data.get(0).size*8);
                data.get(0).key = file.readInt();
                data.get(0).offset = file.readInt();
            }
            file.close();



        }
        System.out.println("=========================================================");
        // name of new file
        System.out.println(fileName);
        System.out.println("----------------------------------------------------------");
        return fileName;

    }

}
 class sort implements Comparator<Data> {

    @Override
    public int compare(Data data1, Data data2) {

        // for comparison
        if(data1.key < data2.key ||data1.key == data2.key  ){
            return -1;
        }
        else{
            return 1;
        }
    }

}

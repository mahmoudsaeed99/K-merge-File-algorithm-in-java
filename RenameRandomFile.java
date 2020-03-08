package assignment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RenameRandomFile {
    void rename(String oldFile , String newFile) throws IOException {
        RandomAccessFile oldfile = new RandomAccessFile(oldFile , "rw");
        RandomAccessFile newfile = new RandomAccessFile(newFile , "rw");
        for(int i=0 ; i<oldfile.length()/8 ; i++){
            newfile.writeInt(oldfile.readInt());
            newfile.writeInt(oldfile.readInt());
        }
    }
}

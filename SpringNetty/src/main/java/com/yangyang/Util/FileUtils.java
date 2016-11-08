package com.yangyang.Util;

import java.io.File;
import java.nio.file.Paths;

public class FileUtils {

    public static void renameFile(String filePath,String oldName,String newName){

        File file = Paths.get(filePath).toFile();
        String nname = file.getName().replace(oldName,newName);
        System.out.println("newName: "+nname);
        file.renameTo(new File(file.getParent()+File.separator+nname));
    }
}

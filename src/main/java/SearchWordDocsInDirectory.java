import org.apache.commons.io.FilenameUtils;

import java.io.*;

public class SearchWordDocsInDirectory {

    public static void main(String[] args) {
        File torahDirectory = new File("C:\\Users\\mayer\\Google Drive\\תורה\\הערות");
        File userDirectory = new File("C:\\Users\\mayer");
        String search = "";
        runSearch(userDirectory, search);
    }

    public static void runSearch(File directory, String search) {
        String[] containedWordFilePaths = getContainedFilePaths(directory, search);
        WordDoc[] allContainedWordDocs = new WordDoc[containedWordFilePaths.length];
        int docIndex = 0;
        for (String filePath : containedWordFilePaths) {
            allContainedWordDocs[docIndex] = new WordDoc(filePath);
            docIndex++;
        }
        for (WordDoc wordDoc : allContainedWordDocs){
            if (wordDoc.contains(search)){
                wordDoc.open();
            }
        }
    }

    public static String[] getContainedFilePaths(File directory, String search) {
        File[] containedFiles = directory.listFiles();
        String[] wordFilePaths = new String[containedFiles.length];
        int indexUpTo = 0;
        for (File containedFile : containedFiles) {
            if(containedFile.isDirectory()) {
                String[] filePaths = containedFile.list();
                if(filePaths != null && filePaths.length > 0) {
                    runSearch(containedFile, search);
                }
                continue;
            }
            String extension = FilenameUtils.getExtension(containedFile.getPath());
            if(extension.equals("docx") && !containedFile.getPath().contains("$")) {
                wordFilePaths[indexUpTo] = containedFile.getPath();
                indexUpTo++;
            }
        }
        String[] wordFilePathsNoNull = new String[indexUpTo];
        System.arraycopy(wordFilePaths, 0, wordFilePathsNoNull, 0, wordFilePathsNoNull.length);
        return wordFilePathsNoNull;
    }
}
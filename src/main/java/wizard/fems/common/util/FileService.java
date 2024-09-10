package wizard.fems.common.util;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileService {
    public void uploadFile(MultipartFile multipartFile, String fileName, String folderPath) throws IOException {

        if (multipartFile.isEmpty()) {
            return;
        }

        // 저장할 파일 명(원래파일명+확장자)
        // String uploadFileName = createFileName(fileName);

        Path path = Paths.get(folderPath);

        // 경로에 폴더 없으면 폴더 생성
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        // 파일이름 포함한 전체 경로(폴더경로+파일이름)
        String fullPath = folderPath + "\\" + fileName;

        // 파일 저장
        multipartFile.transferTo(new File(fullPath));

    }

    public void DeleteFile(String folderPath, String fileName) {

        String fullPath = folderPath + "\\" + fileName;

        File file = new File(fullPath);
        file.delete();

    }

    public void DeleteFolder(String folderPath){
        File folder = new File(folderPath);
        try {
            if(folder.exists()) {
                FileUtils.cleanDirectory(folder);
            }

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

     private String createFileName(String filename) {
         int pos = filename.lastIndexOf(".");
         String extention = filename.substring(pos, 1);
         return "." + extention;
     }
}

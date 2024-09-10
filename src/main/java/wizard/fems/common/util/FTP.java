package wizard.fems.common.util;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

@Component
@Slf4j
public class FTP {
    @Value("${wizardAS.ftp.server}")
    private String server;

    @Value("${wizardAS.ftp.port}")
    private int port;

    @Value("${wizardAS.ftp.username}")
    private String username;

    @Value("${wizardAS.ftp.password}")
    private String password;
    @Value("${wizardAS.ftp.localpath}")
    private String localpath;
    private FTPClient ftp;

    public void open() {
        ftp = new FTPClient();
        ftp.setControlEncoding("euc-kr");
        try {

            ftp.connect(server, port);

            int reply = ftp.getReplyCode();
            log.info("replyCode : {}", reply);

            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                log.info("FTP 연결 실패");
                return;
            }

            ftp.setSoTimeout(1000);

            if (!ftp.login(username, password)) {
                ftp.disconnect();
                log.info("로그인 실패");
                return;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            log.error(ex + ": FTP 연결 실패");
        }
    }

    public void close() {
        try {
            ftp.logout();
            ftp.disconnect();
        } catch (IOException ex) {
            ex.printStackTrace();
            log.info("FTP 서버 close 실패");
        }
    }

    public void uploadFile(MultipartFile file, String filepath) throws IOException {
        open();
        setFtp();

        try {
            InputStream inputStream = file.getInputStream();

            if (!ftp.changeWorkingDirectory(filepath)) {
                ftp.makeDirectory(filepath);
                ftp.changeWorkingDirectory(filepath);
            }

            boolean result = ftp.storeFile(file.getOriginalFilename(), inputStream);

            if (!result) {
                close();
                log.error("FTP 파일 저장 실패");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (ftp.isConnected()) {
                close();
            }
        }
    }

    public void downloadFile(String filename, String filepath) throws IOException {
        open();
        setFtp();
        ftp.changeWorkingDirectory(filepath);

        try {

            String fullpath = filepath + "/" + filename;
            String local = localpath + filename;
            FileOutputStream fileOutputStream = new FileOutputStream(local);

            FTPFile[] files = ftp.listFiles();

            Boolean exist = false;

            for (FTPFile file : files) {
                if (file.getName().equals(filename)) {

                    boolean result = ftp.retrieveFile(fullpath, fileOutputStream);
                    exist = true;

                    if (!result) {
                        close();
                        log.error("FTP 파일 다운로드 실패");
                    }
                    break;
                }
            }

            if (!exist) {
                log.error(filename + " 과(와) 일치하는 파일명이" + filepath + " 폴더 내에 없음");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (ftp.isConnected()) {
                close();
            }
        }
    }

    //    http img download
    public void httpDownload(String filename, String filepath, HttpServletResponse response) throws IOException {
        open();
        setFtp();
        ftp.changeWorkingDirectory(filepath);

        response.setHeader("Content-Type", MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "utf-8"));

        String fullpath = filepath + "/" + filename;

        try {

            InputStream inputStream = ftp.retrieveFileStream(fullpath);
            OutputStream fileOutputStream = response.getOutputStream();

            if (inputStream == null) {
                log.error(filename + " 과(와) 일치하는 파일명이" + filepath + " 폴더 내에 없음");
            }

            byte[] byteArray = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(byteArray)) != -1) {
                fileOutputStream.write(byteArray, 0, bytesRead);
            }

            boolean success = ftp.completePendingCommand();
            if (!success) {
                log.error("FTP 파일 다운로드 실패");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (ftp.isConnected()) {
                close();
            }
        }


    }

    public void setFtp() throws IOException {
        ftp.enterLocalPassiveMode();
        ftp.setFileTransferMode(ftp.BINARY_FILE_TYPE);
        ftp.setAutodetectUTF8(true);
        ftp.setFileType(ftp.BINARY_FILE_TYPE);
    }

}

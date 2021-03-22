package by.itacademy.todolist.service.impl;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.FileInfo;
import by.itacademy.todolist.persistence.dao.FileInfoDao;
import by.itacademy.todolist.service.FileService;

import javax.servlet.http.Part;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileServiceImpl implements FileService {

    private final FileInfoDao<FileInfo> fileInfoDao;

    public FileServiceImpl(FileInfoDao<FileInfo> fileInfoDao) {
        this.fileInfoDao = fileInfoDao;
    }

    public FileInfo addFileInfoForTask(Part part, long task_id, long userId, String path) {
        try {

            path = path + userId + "/";
            File fileSaveDir = new File(path);
            if (!fileSaveDir.exists()) {
                boolean a = fileSaveDir.mkdir();
            }

            path = path + task_id + "/";
            fileSaveDir = new File(path);
            if (!fileSaveDir.exists()) {
                boolean a = fileSaveDir.mkdir();
            }

            String fileName = extractFileName(part);
            String filePath;
            if (fileName != null && fileName.length() > 0) {
                filePath = path + File.separator + fileName;
                part.write(filePath);
            } else {
                throw new RuntimeException("Error save file");
            }
            String directory = ApplicationConstants.SAVE_DIRECTORY + userId + "/";
            FileInfo fileInfo = FileInfo.builder().name(fileName).directory(directory).path(filePath).build();
            return fileInfoDao.addFileInfoForTask(fileInfo, task_id);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error save file");
        }
    }

    @Override
    public void delete(FileInfo fileInfo) {
        try {
            Path path = Paths.get(fileInfo.getPath());
            Files.delete(path);
            fileInfoDao.deleteById(fileInfo.getId());
        } catch (Exception e) {
            throw new RuntimeException("Error delete file (fileId - " + fileInfo.getId()  + ")");
        }
    }

    @Override
    public FileInfo getById(long fileId) {
        return fileInfoDao.getById(fileId);
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                clientFileName = clientFileName.replace("\\", "/");
                int i = clientFileName.lastIndexOf('/');
                return clientFileName.substring(i + 1);
            }
        }
        return null;
    }
}
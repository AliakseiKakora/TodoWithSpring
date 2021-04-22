package by.itacademy.todolist.service.impl;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.FileInfo;
import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.persistence.FileInfoRepository;
import by.itacademy.todolist.persistence.TaskRepository;
import by.itacademy.todolist.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {

    private final FileInfoRepository fileInfoRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public FileServiceImpl(FileInfoRepository fileInfoRepository, TaskRepository taskRepository) {
        this.fileInfoRepository = fileInfoRepository;
        this.taskRepository = taskRepository;
    }

    public FileInfo addFileInfoForTask(Part part, long taskId, long userId, String path) {
        try {
            path = path + userId + "/";
            File fileSaveDir = new File(path);

            createDirectory(fileSaveDir);

            path = path + taskId + "/";
            fileSaveDir = new File(path);

            createDirectory(fileSaveDir);

            String fileName = extractFileName(part);
            String filePath;
            if (fileName != null && fileName.length() > 0) {
                filePath = path + File.separator + fileName;
                part.write(filePath);
            } else {
                throw new RuntimeException("Error save file");
            }
            String directory = ApplicationConstants.SAVE_DIRECTORY + userId + "/" + taskId + "/";
            FileInfo fileInfo = FileInfo.builder().name(fileName).directory(directory).path(filePath).build();

            Task task = taskRepository.findById(taskId)
                    .orElseThrow(() -> new RuntimeException("task with id" + taskId + "not found"));
            fileInfo.addTask(task);

            return taskRepository.save(task).getFileInfo();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error save file");
        }
    }

    private void createDirectory(File fileSaveDir) {
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
    }

    @Override
    public void delete(FileInfo fileInfo) {
        try {
            Path path = Paths.get(fileInfo.getPath());
            Files.delete(path);
            fileInfoRepository.deleteById(fileInfo.getId());
        } catch (Exception e) {
            throw new RuntimeException("Error delete file (fileId - " + fileInfo.getId() + ")", e);
        }
    }

    @Override
    public FileInfo getById(long fileId) {
        return fileInfoRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("file with id " + fileId + " not found"));
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
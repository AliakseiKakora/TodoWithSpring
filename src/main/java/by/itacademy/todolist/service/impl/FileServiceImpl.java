package by.itacademy.todolist.service.impl;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.FileInfo;
import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.persistence.FileInfoRepository;
import by.itacademy.todolist.persistence.TaskRepository;
import by.itacademy.todolist.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
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

    public FileInfo addFileInfoForTask(MultipartFile file, long taskId, long userId, HttpServletRequest request) {
        try {
            String path = getPath(request, taskId, userId);

            String fileName = file.getOriginalFilename();
            String filePath;
            if (fileName != null && fileName.length() > 0) {
                filePath = path + File.separator + fileName;
                FileOutputStream stream = new FileOutputStream(filePath);
                stream.write(file.getBytes());
                stream.close();
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

    private String getPath(HttpServletRequest request, long taskId, long userId) {
        String path = request.getServletContext().getRealPath("");
        path = path.replace('\\', '/');

        String fullSavePath;
        if (path.endsWith("/")) {
            fullSavePath = path + ApplicationConstants.SAVE_DIRECTORY;
        } else {
            fullSavePath = path + "/" + ApplicationConstants.SAVE_DIRECTORY;
        }
        fullSavePath = fullSavePath + userId + "/";
        File fileSaveDir = new File(fullSavePath);

        createDirectory(fileSaveDir);

        fullSavePath = fullSavePath + taskId + "/";
        fileSaveDir = new File(fullSavePath);

        createDirectory(fileSaveDir);
        return fullSavePath;
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

}
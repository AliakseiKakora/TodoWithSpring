package by.itacademy.todolist.service.impl;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.FileInfo;
import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.persistence.FileInfoRepository;
import by.itacademy.todolist.persistence.TaskRepository;
import by.itacademy.todolist.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor

@Service
public class FileServiceImpl implements FileService {

    private final FileInfoRepository fileInfoRepository;
    private final TaskRepository taskRepository;

    public FileInfo addFileInfoForTask(MultipartFile file, long taskId, long userId) {
        try {
            String path = getPath(taskId, userId);
            String fileName = file.getOriginalFilename();
            path = path + File.separator + fileName;
            if (fileName != null && fileName.length() > 0) {
                FileOutputStream stream = new FileOutputStream(path);
                stream.write(file.getBytes());
                stream.close();
            } else {
                throw new RuntimeException("Error save file");
            }
            String directory = ApplicationConstants.SAVE_DIRECTORY + userId + "/" + taskId + "/";
            FileInfo fileInfo = FileInfo.builder().name(fileName).directory(directory).path(path).build();

            Task task = taskRepository.findById(taskId)
                    .orElseThrow(() -> new RuntimeException("task with id" + taskId + "not found"));
            fileInfo.addTask(task);

            return taskRepository.save(task).getFileInfo();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error save file");
        }
    }

    private String getPath(long taskId, long userId) {
        File absolutPathFile = new File(ApplicationConstants.SAVE_DIRECTORY);
        String path = absolutPathFile.getAbsolutePath() + "/" + userId + "/";
        File fileSaveDir = new File(path);
        createDirectory(fileSaveDir);
        path = path + taskId + "/";
        fileSaveDir = new File(path);
        createDirectory(fileSaveDir);
        return path;
    }

    private void createDirectory(File fileSaveDir) {
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
    }

    @Override
    @PreAuthorize("#fileInfo.task.user.id == authentication.principal.id or hasAnyRole('ADMIN')")
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
    @PostAuthorize("returnObject.task.user.id == authentication.principal.id")
    public FileInfo getById(long fileId) {
        return fileInfoRepository.findById(fileId)
                .orElseThrow(() -> new RuntimeException("file with id " + fileId + " not found"));
    }
}
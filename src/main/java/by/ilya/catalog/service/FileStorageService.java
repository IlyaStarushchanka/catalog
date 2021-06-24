package by.ilya.catalog.service;

import by.ilya.catalog.domain.FileDB;
import by.ilya.catalog.domain.Submission;
import by.ilya.catalog.repository.FileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileStorageService {

    @Autowired
    private FileDBRepository fileDBRepository;
    @Autowired
    private SubmissionServiceImpl submissionServiceImpl;

    @Transactional
    public FileDB store(MultipartFile file, Long submissionId) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB fileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
        Submission submission = submissionServiceImpl.getById(submissionId);
        if (submission != null) {
            fileDB.setSubmission(submission);
            submission.getFiles().add(fileDB);
            return fileDBRepository.save(fileDB);
        }
        return fileDB;
    }

    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).orElse(null);
    }

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }

    @Transactional
    public void delete(String id){
        FileDB fileDB = fileDBRepository.getById(id);
        Submission submission = fileDB.getSubmission();
        submission.getFiles().remove(fileDB);
        fileDBRepository.deleteById(id);
    }

}

package by.ilya.catalog.endpoint;

import by.ilya.catalog.domain.FileDB;
import by.ilya.catalog.dto.admin.ResponseFile;
import by.ilya.catalog.facade.admin.AdminSubmissionFacade;
import by.ilya.catalog.service.admin.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@Controller
@CrossOrigin
public class FileEndpoint {

    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private AdminSubmissionFacade adminSubmissionFacade;

    @PostMapping("/admin/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("submissionId") Long submissionId, Model model) {
        try {
            fileStorageService.store(file, submissionId);
        } catch (Exception e) {

        }
        model.addAttribute("submission", adminSubmissionFacade.getById(submissionId));
        return "admin/submission/submission-view";
    }

    @PostMapping("/admin/upload/submission/img")
    public String uploadSubmissionImg(@RequestParam("file") MultipartFile file, @RequestParam("submissionId") Long submissionId, Model model) {
        try {
            fileStorageService.storeSubmissionImg(file, submissionId);
        } catch (Exception e) {

        }
        model.addAttribute("submission", adminSubmissionFacade.getById(submissionId));
        return "admin/submission/submission-view";
    }

    @GetMapping("/admin/files/delete")
    @ResponseBody
    public void delete(@RequestParam(value = "id") String id) {
        fileStorageService.delete(id);
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles(@RequestParam("submissionId") Long submissionId) {
        List<ResponseFile> files = fileStorageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length,
                    dbFile.getId());
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        FileDB fileDB = fileStorageService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }

    @GetMapping("/files/submission/img/{id}")
    public ResponseEntity<byte[]> getSubmissionImage(@PathVariable Long id) {
        byte[] image = adminSubmissionFacade.getSubmissionImage(id);
        if (image != null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=image")
                    .body(image);
        }
        return new ResponseEntity(NO_CONTENT);
    }
}
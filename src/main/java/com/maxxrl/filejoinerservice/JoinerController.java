package com.maxxrl.filejoinerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class JoinerController {

    private final JoinerService joinerService;

    @Autowired
    public JoinerController(JoinerService joinerService) {
        this.joinerService = joinerService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/upload")
    public ResponseEntity<MergePdf> upload(@RequestParam("pdf") MultipartFile pdf, @RequestParam("mergeId") String mergeId) throws IOException {
        return ResponseEntity.ok(joinerService.savePdf(pdf, mergeId));
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/merge")
    public ResponseEntity<ByteArrayResource> merge(@RequestParam("mergeId") String mergeId) throws IOException {
        byte[] merge = joinerService.merge(mergeId);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\"" + "merged" + "\"")
                .body(new ByteArrayResource(merge));
    }

}

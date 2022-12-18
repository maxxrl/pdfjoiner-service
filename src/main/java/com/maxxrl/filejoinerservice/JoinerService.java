package com.maxxrl.filejoinerservice;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Service
public class JoinerService {


    private final MergePdfRepository repository;

    @Autowired
    public JoinerService(MergePdfRepository repository) {
        this.repository = repository;
    }

    public MergePdf savePdf(final MultipartFile pdf, final String mergeId) throws IOException {
        MergePdf mergePdf = new MergePdf();
        mergePdf.setMergeId(mergeId);
        mergePdf.setData(pdf.getBytes());
        mergePdf.setName(pdf.getName());
        return repository.save(mergePdf);
    }

    public byte[] merge(final String mergeId) throws IOException {
        PDFMergerUtility PDFmerger = new PDFMergerUtility();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PDFmerger.setDestinationStream(baos);
        List<MergePdf> allByMergeId = repository.findAllByMergeId(mergeId);
        allByMergeId.forEach(mergePdf -> PDFmerger.addSource(new ByteArrayInputStream(mergePdf.getData())));
        PDFmerger.mergeDocuments(null);
        return baos.toByteArray();
    }
}

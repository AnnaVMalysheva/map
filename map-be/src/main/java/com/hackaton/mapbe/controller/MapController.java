package com.hackaton.mapbe.controller;

import com.hackaton.mapbe.dto.RecordDto;
import com.hackaton.mapbe.service.LoadService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@CrossOrigin
public class MapController {

    private final LoadService loadService;

    @PostMapping("/coordinates")
    public void uploadCoordinates(@RequestParam("file") MultipartFile file) throws IOException {
        loadService.uploadCoordinates(file.getInputStream());
    }

    @GetMapping("/coordinates")
    public Set<RecordDto> getCoordinates() throws IOException {
        return loadService.getCoordinates();
    }
}

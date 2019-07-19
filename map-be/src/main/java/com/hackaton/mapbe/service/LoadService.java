package com.hackaton.mapbe.service;

import com.hackaton.mapbe.dto.RecordDto;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

public interface LoadService {
    void uploadCoordinates(InputStream inputStream) throws IOException;

    Set<RecordDto> getCoordinates();
}

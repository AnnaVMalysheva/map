package com.hackaton.mapbe.service.impl;

import com.hackaton.mapbe.dto.RecordDto;
import com.hackaton.mapbe.entity.Record;
import com.hackaton.mapbe.repository.RecordRepository;
import com.hackaton.mapbe.service.LoadService;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LoadServiceImpl implements LoadService {

    private final GeometryFactory geometryFactory = new GeometryFactory();

    private final RecordRepository recordRepository;

    public LoadServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public void uploadCoordinates(InputStream inputStream) throws IOException {
        CSVParser csvParser = CSVFormat.TDF.withFirstRecordAsHeader().parse(new InputStreamReader(inputStream));
        List<Record> records = new ArrayList<>();
        for (CSVRecord record : csvParser) {
            String[] result = record.get(0).split("\\s+");

            Point point = geometryFactory.createPoint(new Coordinate(Double.valueOf(result[3]), Double.valueOf(result[4])));
            records.add(Record.builder().coordinates(point).phoneId(Long.valueOf(result[0])).date(result[1]).build());

        }
        recordRepository.saveAll(records);
    }

    @Override
    public Set<RecordDto> getCoordinates() {
//        List<Point> records = recordRepository.findReports();
//        return records.stream().map(record -> RecordDto.builder().lan(record.getX()).log(record.getY()).build()).collect(Collectors.toList());
          List<Record> records = recordRepository.findReports();
          return records.stream().map(record -> RecordDto.builder().latlng(new Double[] {record.getCoordinates().getX(), record.getCoordinates().getY()}).build()).collect(Collectors.toSet());

    }
}

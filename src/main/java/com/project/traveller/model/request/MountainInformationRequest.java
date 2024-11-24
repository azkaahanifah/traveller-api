package com.project.traveller.model.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MountainInformationRequest {

    private Long id;

    private String mountainName;

    private String description;

    private String location;

    private Integer mdpl;

    private String rate;

    private List<String> images;

}

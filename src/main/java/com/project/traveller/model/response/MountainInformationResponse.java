package com.project.traveller.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MountainInformationResponse {

    private Long id;

    private String mountainName;

    private String location;

    private String description;

    private String rate;

    private Integer elevation;

}

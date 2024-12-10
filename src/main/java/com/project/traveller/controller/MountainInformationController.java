package com.project.traveller.controller;

import com.project.traveller.entity.MountainInformationEntity;
import com.project.traveller.model.request.MountainInformationRequest;
import com.project.traveller.model.response.MountainInformationResponse;
import com.project.traveller.service.IMountainInformationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.base.path}")
public class MountainInformationController {

    /**
     * Autowired's annotation can be replaced with CONSTRUCTOR
     * Here I give you two option: used Autowired or Constructor
     * So, that's why you can see sometimes I used Autowired in other class
     * But remember, Autowired annotation is Deprecated
     * When you install the SonarLint plugin in your IntelliJ, they will detect and suggest
     * to change the Autowired annotation
     */
    private final IMountainInformationService mountainInformationService;

    public MountainInformationController(IMountainInformationService service) {
        this.mountainInformationService = service;
    }

    @Operation(summary = "This method/API is used to create Mountain Information")
    @PostMapping(value = "/mountain")
    public MountainInformationEntity create(@RequestBody MountainInformationRequest request) {
        return mountainInformationService.create(request);
    }

    @Operation(summary = "This method/API is used to get Mountain Information by ID")
    @GetMapping(value = "/mountain/{id}")
    public MountainInformationResponse get(@PathVariable Long id) {
        return mountainInformationService.get(id);
    }

    @Operation(summary = "This method/API is used to get all Mountain Information")
    @GetMapping(value = "/mountains")
    public List<MountainInformationResponse> getMountainInformations() {
        return mountainInformationService.getMountainInformations();
    }

    @Operation(summary = "This method/API is used to update Mountain Information")
    @PutMapping(value = "/mountain")
    public MountainInformationEntity update(@RequestBody MountainInformationRequest request) {
        return mountainInformationService.update(request);
    }

    @Operation(summary = "This method/API is used to delete Mountain Information by ID")
    @DeleteMapping(value = "/mountain/{id}")
    public String delete(@PathVariable Long id) {
        return mountainInformationService.delete(id);
    }
}

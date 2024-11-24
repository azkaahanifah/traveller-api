package com.project.traveller.controller;

import com.project.traveller.entity.MountainInformationEntity;
import com.project.traveller.model.request.MountainInformationRequest;
import com.project.traveller.model.response.MountainInformationResponse;
import com.project.traveller.service.IMountainInformationService;
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

    @PostMapping(value = "/mountain")
    public MountainInformationEntity create(@RequestBody MountainInformationRequest request) {
        return mountainInformationService.create(request);
    }

    @GetMapping(value = "/mountain/{id}")
    public MountainInformationResponse get(@PathVariable Long id) {
        return mountainInformationService.get(id);
    }

    @GetMapping(value = "/mountains")
    public List<MountainInformationResponse> getMountainInformations() {
        return mountainInformationService.getMountainInformations();
    }

    @PutMapping(value = "/mountain")
    public MountainInformationEntity update(@RequestBody MountainInformationRequest request) {
        return mountainInformationService.update(request);
    }

    @DeleteMapping(value = "/mountain/{id}")
    public String delete(@PathVariable Long id) {
        return mountainInformationService.delete(id);
    }
}

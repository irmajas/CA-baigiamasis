package lt.codeacademy.cauzduotis.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lt.codeacademy.cauzduotis.models.KomentarasView;
import lt.codeacademy.cauzduotis.services.KomentarasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/komentaras")
@CrossOrigin("*")
@Api(value = "Comment Management System")
public class KomentarasController {

    private final KomentarasService service;

    @Autowired
    public KomentarasController(KomentarasService service) {
        this.service = service;
    }

    @ApiOperation(value = "Add Comment")
    @PostMapping(path = "/add/{id}")
    public KomentarasView addKomentras(@PathVariable("id") int id, @RequestBody KomentarasView komentarasView) {

        return service.addKomentaras(komentarasView, id);
    }

    @ApiOperation(value = "View session params", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(path = "/gauti/{id}")
    public List<KomentarasView> getKomentarai(@PathVariable("id") int id) {
        return service.findallForIrasas(id);
    }

    @ApiOperation(value = "Add Answer to comment")
    @PutMapping(path = "/update/{id}")
    public KomentarasView updateKomentras(@PathVariable("id") int id, @RequestBody KomentarasView komentarasView) {

        return   service.updateAtsakymas(id, komentarasView);
    }
}

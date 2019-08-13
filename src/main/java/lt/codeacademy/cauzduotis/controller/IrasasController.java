package lt.codeacademy.cauzduotis.controller;

import io.swagger.annotations.*;
import lt.codeacademy.cauzduotis.models.Irasas;
import lt.codeacademy.cauzduotis.models.IrasasSimple;
import lt.codeacademy.cauzduotis.models.IrasasView;
import lt.codeacademy.cauzduotis.models.Latest;
import lt.codeacademy.cauzduotis.services.IrasasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import lt.codeacademy.cauzduotis.services.NotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/irasas")
@CrossOrigin("*")
@Api(value = "Blog(Irasas) Management System")

public class IrasasController {

    private final IrasasService service;

    @Autowired
    public IrasasController(IrasasService service) {
        this.service = service;
    }

    @ApiOperation(value = "View session params", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping
    public List<IrasasSimple> getIrasai() {
        return service.getAllIrasas();
    }
    @GetMapping (path="/gautinaujausia")
    public Latest getLatestID(){
        return service.getLatestId();
    }

    @ApiOperation(value = "Get a Irasas  by Id")
    @GetMapping(path = "/gauti/{id}")
    public  IrasasView getOneIrasas(@ApiParam(value = "Get Blog Irasas by Id", required = true)@PathVariable("id") int id){
;
        return service.getOne(id);
    }

    @ApiOperation(value = "Add Irasas")
    @PostMapping(path = "/add")
    public IrasasView addIrasas(@ApiParam(value = "Irasas to update", required = true)@RequestBody IrasasView irasasView) {
        return service.addIrasas(irasasView);
    }

    @ApiOperation(value = "Update Irasas")
    @PutMapping(path = "/update/{id}")
    public IrasasView updateIrasas(@ApiParam(value = "Irasas id", required = true)@PathVariable("id") int id,
                                   @ApiParam(value = "Update irasas object", required = true) @RequestBody IrasasView irasasView) {
        return service.updateIrass(id, irasasView);
    }
    @ApiOperation(value = "Delete irasas")
    @DeleteMapping(path = "/delete/{id}")
    public void deleteIrasas(@ApiParam(value = "delete irasas by Id", required = true)@PathVariable("id") int id) {
        service.deleteIrasas(id);
    }

    @ExceptionHandler({EmptyResultDataAccessException.class, NotFoundException.class})
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public void handleNotFoundException() {

    }
}

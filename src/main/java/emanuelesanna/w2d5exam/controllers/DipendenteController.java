package emanuelesanna.w2d5exam.controllers;

import emanuelesanna.w2d5exam.entities.Dipendente;
import emanuelesanna.w2d5exam.exceptions.ValidationException;
import emanuelesanna.w2d5exam.payload.NewDipendenteDTO;
import emanuelesanna.w2d5exam.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    // 1 GET http://localhost:3001/dipendenti 200 OK

    @GetMapping
    public Page<Dipendente> findAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "idDipendente") String sortBy) {
        return this.dipendenteService.findAllDipendenti(page, size, sortBy);
    }

    // 2 POST http://localhost:3001/dipendenti (+ payload) 201 CREATED

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente createDipendente(@RequestBody @Validated NewDipendenteDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors()
                    .stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        }
        return this.dipendenteService.saveDipendente(payload);
    }

    // 3 GET http://localhost:3001/dipendenti/{dipendenteId} 200 OK

    @GetMapping("/{dipendenteId}")
    public Dipendente findById(@PathVariable UUID dipendenteId) {
        return this.dipendenteService.findById(dipendenteId);
    }

    // 4 PUT http://localhost:3001/dipendenti/{dipendenteId} + payload 200 OK

    @PutMapping("/{dipendenteId}")
    public Dipendente findByIdAndUpdate(@PathVariable UUID dipendenteId, @RequestBody @Validated NewDipendenteDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors()
                    .stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        }
        return this.dipendenteService.findByIdAndUpdate(dipendenteId, payload);
    }

    // 5 DELETE http://localhost:3001/dipendenti/{dipendenteId} 204 NC

    @DeleteMapping("/{dipendenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID dipendenteId) {
        this.dipendenteService.findByIdAndDelete(dipendenteId);
    }

//    http://localhost:3001/dipendenti/id/avatar in body metto formdata e key value dell'immagine in locale da ricordare
    // PATCH: Endpoint per l'upload dell'avatar

    @PatchMapping("/{dipendenteId}/avatar")
    @ResponseStatus(HttpStatus.OK)
    public Dipendente uploadImage(@PathVariable UUID dipendenteId, @RequestParam("avatar") MultipartFile file) {

        System.out.println(file.getSize());
        System.out.println(file.getOriginalFilename());

        //  Passo l'ID dell'autore al service per aggiornare il record corretto.
        return this.dipendenteService.uploadAvatar(dipendenteId, file);
    }
}

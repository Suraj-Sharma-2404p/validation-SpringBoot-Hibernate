package validation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import validation.exceptions.ResourceNotFoundException;
import validation.model.Reservation;
import validation.model.User;
import validation.model.dtos.CreateReservationDto;
import validation.service.ReservationService;
import validation.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private UserService userService;
    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@Valid @RequestBody CreateReservationDto createReservationDto) throws ResourceNotFoundException {
        Optional<User> optionalUser = userService.findById(createReservationDto.getUserId());
        if (!optionalUser.isPresent()) {
            throw new ResourceNotFoundException("No user found with the Id: " + createReservationDto.getUserId());
        }
        Reservation reservationInput = createReservationDto.toReservation().setUser(optionalUser.get());
        Reservation createdReservation = reservationService.create(reservationInput);
        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<Reservation>> allReservations() {
        return new ResponseEntity<>(reservationService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{code}")
    public ResponseEntity<Reservation> oneReservation(
            @Pattern(regexp = "^RSV(-\\d{4,}){2}$", message = "The reservation code is invalid") @PathVariable String code
    ) throws ResourceNotFoundException {
        Optional<Reservation> optionalReservation = reservationService.findByCode(code);

        if (!optionalReservation.isPresent()) {
            throw new ResourceNotFoundException("No reservation found with the code: " + code);
        }

        return new ResponseEntity<>(optionalReservation.get(), HttpStatus.OK);
    }
}

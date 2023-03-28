package validation.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import validation.model.Reservation;
import validation.repository.ReservationRepository;
import validation.service.ReservationService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
@Service
public class RegistrationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    private String generateCode(){
        Optional<Reservation> reservation = reservationRepository.findFirstByOrderByIdDesc();
        int lastId = reservation.map(Reservation::getId).orElse(0);
        return "RSV-" + Calendar.getInstance().get(Calendar.YEAR) + "-" + (1000 + lastId + 1);
    }

    @Override
    public Reservation create(Reservation reservation) {
        reservation.setCode(generateCode());

        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> findAll() {
        List<Reservation> reservations = new ArrayList<>();

        reservationRepository.findAll().forEach(reservations::add);

        return reservations;
    }

    @Override
    public Optional<Reservation> findByCode(String code) {
        return reservationRepository.findByCode(code);
    }
}

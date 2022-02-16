package me.inanc.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.inanc.domain.Booking;
import me.inanc.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    Iterable<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> findById(Long id) {
        return bookingRepository.findById(id);
    }

    public Booking save(Booking booking) {
        return bookingRepository.save(new Booking(booking.getPassengerName(), booking.getNumber(), booking.getRating(), booking.getPrice(),
                booking.getNumberOfPassengers(), booking.getPickupDate(), booking.getTripWaypoints()));
    }

    void delete(Long id) {
        bookingRepository.deleteById(id);
    }

    @Transactional
    public void update(Booking booking) {
        Optional<Booking> optionalBooking = this.findById(booking.getId());
        optionalBooking.ifPresent(persistedBooking -> {
            persistedBooking.setNumber(booking.getNumber());
            persistedBooking.setPassengerName(booking.getPassengerName());

            log.info("update booking.. " + booking);
        });

    }

}

package qlassalle.booking;


import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/bookings")
@Api(name = "Hotel Booking API", description = "Provides  list of methods that manage hotel bookings", stage = ApiStage.RC)
public class BookingController {

    BookingRepository bookingRepository;

    @Autowired
    public BookingController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ApiMethod(description = "Get all hotel bookings from the database")
    public List<HotelBooking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @RequestMapping(value = "/affordable/{price}")
    @ApiMethod(description = "Get all hotel bookings from the database having a price per night which is less than parameter")
    public List<HotelBooking> getAffordable(@PathVariable double price) {
        return bookingRepository.findByPricePerNightLessThan(price);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ApiMethod(description = "Add an hotel booking to the database")
    public List<HotelBooking> create(@RequestBody HotelBooking hotelBooking) {
        bookingRepository.save(hotelBooking);

        return bookingRepository.findAll();
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.POST)
    @ApiMethod(description = "Remove a hotel from database")
    public List<HotelBooking> remove(@ApiPathParam(name = "id", description = "The id of the hotel to remove") @PathVariable long id) {
        bookingRepository.delete(id);

        return bookingRepository.findAll();
    }
}

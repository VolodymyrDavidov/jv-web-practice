package mate.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.lib.Injector;
import mate.model.Car;
import mate.model.Manufacturer;
import mate.service.CarService;
import mate.service.ManufacturerService;

@WebServlet("/cars/add")
public class CreateCarController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate");
    private final CarService carService = (CarService)injector.getInstance(CarService.class);
    private final ManufacturerService manufacturerService = (ManufacturerService)injector
            .getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/cars/add.jsp").forward(req,resp);
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Car car = new Car();
        Manufacturer manufacturer = manufacturerService.get(Long.parseLong(req
                .getParameter("manufacturerId")));
        car.setManufacturer(manufacturer);
        car.setModel(req.getParameter("model"));
        carService.create(car);
        resp.sendRedirect(req.getContextPath() + "/cars");
    }
}
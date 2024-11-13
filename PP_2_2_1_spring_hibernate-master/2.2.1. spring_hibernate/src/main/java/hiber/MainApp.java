package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car lada = new Car("Lada", 2);
        Car ford = new Car("Ford", 3);
        Car audi = new Car("Audi", 4);
        Car mercedes = new Car("Mercedes", 5);

        userService.add(new User("Petr", "Jan", "jan21@mail.ru", lada), lada);
        userService.add(new User("Ilya", "Caska", "caskass@mail.ru", ford), ford);
        userService.add(new User("Andrey", "Potyaev", "pot322@mail.ru", audi), audi);
        userService.add(new User("Shock", "Kaktotak", "almaz@mail.ru", mercedes), mercedes);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user);
        }

        System.out.println(userService.getUserByModelAndSeries("Lada", 2));
        System.out.println(userService.getUserByModelAndSeries("Ford", 3));
        System.out.println(userService.getUserByModelAndSeries("Audi", 4));
        System.out.println(userService.getUserByModelAndSeries("Mercedes", 5));

        context.close();
    }
}

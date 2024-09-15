package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("Ivan", "Deduskin", "ivan@mail.ru");
        Car car1 = new Car(21, "gaz");
        user1.setCar(car1);
        userService.add(user1);
        User user2 = new User("Maria", "Babushkina", "maria@mail.ru");
        Car car2 = new Car(307, "pezho");
        user2.setCar(car2);
        userService.add(user2);
        User user3 = new User("Pavel", "Lisicin", "pavel4@mail.ru");
        Car car3 = new Car(10, "lamborgini");
        user3.setCar(car3);
        userService.add(user3);
        User user4 = new User("Artur", "Kolobkov", "artur@mail.ru");
        Car car4 = new Car(666, "harley-davidson");
        user4.setCar(car4);
        userService.add(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car serie = " + user.getCar().getSeries());
            System.out.println("Car model = " + user.getCar().getModel());
            System.out.println();
        }

        System.out.println(userService.findUserByCar(car1).getFirstName());
        context.close();
    }
}

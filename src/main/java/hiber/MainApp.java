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

         Car car1 = new Car("Vaz", 2107);
         Car car2 = new Car("Vaz", 2110);

         User user1 = new User("User1", "Lastname1", "user1@mail.ru");
         User user2 = new User("User2", "Lastname2", "user2@mail.ru");

         userService.addCar(car1);
         userService.addCar(car2);

         user1.setUserCar(car1);
         user2.setUserCar(car2);

         userService.add(user1);
         userService.add(user2);

         List<User> users = userService.listUsers();
         for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getUserCar().getModel());
            System.out.println();
         }

         context.close();
      }
   }

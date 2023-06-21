import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class task {
    // Напишите приложение, которое будет запрашивать у пользователя следующие
    // данные в произвольном порядке, разделенные пробелом:
    // Фамилия Имя Отчество датарождения номертелефона пол
    // Форматы данных:
    // фамилия, имя, отчество - строки
    // датарождения - строка формата dd.mm.yyyy
    // номертелефона - целое беззнаковое число без форматирования
    // пол - символ латиницей f или m.
    // Приложение должно проверить введенные данные по количеству. Если количество
    // не совпадает с требуемым, вернуть код ошибки, обработать его и показать
    // пользователю сообщение, что он ввел меньше и больше данных, чем требуется.
    // Приложение должно попытаться распарсить полученные значения и выделить из них
    // требуемые параметры. Если форматы данных не совпадают, нужно бросить
    // исключение, соответствующее типу проблемы. Можно использовать встроенные типы
    // java и создать свои. Исключение должно быть корректно обработано,
    // пользователю выведено сообщение с информацией, что именно неверно.
    // Если всё введено и обработано верно, должен создаться файл с названием,
    // равным фамилии, в него в одну строку должны записаться полученные данные,
    // вида <Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>
    // Однофамильцы должны записаться в один и тот же файл, в отдельные строки.
    // Не забудьте закрыть соединение с файлом.
    // При возникновении проблемы с чтением-записью в файл, исключение должно быть
    // корректно обработано, пользователь должен увидеть стектрейс ошибки.
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println(
                    "Введите следующие данные через пробел: ФИО, дата рождения(dd.mm.yyyy), номер телефона, пол(f или m):");
            String userData = scanner.nextLine();
            String[] dataArray = userData.split(" ");
            if (checkLength(dataArray) == -1) {
                System.out.println("Вы ввели недостаточно данных!");
            } else if (checkLength(dataArray) == 1) {
                System.out.println("Вы ввели слишком много данных!");
            }
            String lastName = dataArray[0];
            String firstName = dataArray[1];
            String patronymicName = dataArray[2];
            String dateOfBirth = dataArray[3];
            String telephoneNumber = dataArray[4];
            String gender = dataArray[5];

            if (!dateOfBirth.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
                throw new IllegalArgumentException("Неверный формат даты рождения");
            }
            if (!telephoneNumber.matches("\\d+")) {
                throw new IllegalArgumentException("Неверный формат номера телефона");
            }
            if (!gender.matches("[mf]")) {
                throw new IllegalArgumentException("Неверный формат пола");
            }
            File file = new File(lastName);
            try (FileWriter writer = new FileWriter(file)) {
                String data = lastName + " " + firstName + " " + patronymicName + " " + dateOfBirth + " "
                        + telephoneNumber + " " + gender + " \n";
                writer.write(data);
                System.out.println("Данные сохранены в файл " + file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int checkLength(String[] enterData) {
        if (enterData.length < 6) {
            return -1;
        } else if (enterData.length > 6) {
            return 1;
        }
        return 0;
    }

}

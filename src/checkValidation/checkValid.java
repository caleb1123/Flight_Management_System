package checkValidation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class checkValid {

    public static Scanner sc = new Scanner(System.in);

    
   public static LocalTime inputTime(String message) {
    Scanner sc = new Scanner(System.in);

    while (true) {
        try {
            System.out.print(message + "[hh:mm]: ");
            String timeString = sc.nextLine();
            String[] result = timeString.split(":");

            if (isValidTime(LocalTime.parse(timeString))) {
                return LocalTime.parse(timeString);
            } else {
                throw new Exception();
            }
        } catch (Exception ex) {
            System.out.println("Wrong format. Please enter a valid time.");
        }
    }
}

    public static boolean isValidTime(LocalTime time) {
        return time.getHour() >= 0 && time.getHour() < 24 && time.getMinute() >= 0 && time.getMinute() < 60;
    }
    
    public static String date(Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    return sdf.format(date);
}


public static String date1(String msg) {
    System.out.println(msg + ": ");
    
    String dateStr = sc.nextLine().trim();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    sdf.setLenient(false); // Đảm bảo ngày tháng hợp lệ

    try {
        Date date = sdf.parse(dateStr);
        return sdf.format(date);
    } catch (ParseException e) {
        System.out.println("Invalid date format");
        return null;
    }
}
    
    
    public static Date inputDate(String message) {
        boolean check = true;
        String date;
        do {
            try {
                System.out.print(message + "[dd/mm/yyyy]: ");
                date = sc.nextLine();
                String[] result = date.split("[/-]");

                check = isDate(date);
                if (check == true) {
                    // Tạo đối tượng Calendar mô tả thời điểm hiện tại
                    // với Locale (khu vực) và TimeZone (múi giờ) 
                    // của máy tính đang chạy.
                    Calendar cal = Calendar.getInstance();
                    cal.set(Integer.parseInt(result[2].trim()), Integer.parseInt(result[1].trim()) - 1, Integer.parseInt(result[0].trim()));
                    // trong java chỉ có 11 tháng
                    return cal.getTime();
                }
                throw new Exception();
            } catch (Exception ex) {
                System.out.println("Wrong format");
            }
        } while (!check);
        return null;
    }

    public static boolean isDate(String date) {
        boolean check = false;
        try {
            String[] result = date.split("[/-]");
            if (result.length != 3) {
                return false;
            }
            switch (result[1].trim()) {
                case "1":
                case "3":
                case "5":
                case "7":
                case "8":
                case "10":
                case "12":
                    check = Integer.parseInt(result[0].trim()) <= 31;
                    break;

                case "4":
                case "6":
                case "9":
                case "11":
                    check = Integer.parseInt(result[0].trim()) <= 30;
                    break;

                case "2":
                    if (Integer.parseInt(result[2]) % 4 == 0) {
                        check = Integer.parseInt(result[0].trim()) <= 29;
                    } else {
                        check = Integer.parseInt(result[0].trim()) <= 28;
                    }
                    break;

            }

        } catch (NumberFormatException e) {
            System.out.println("Error input");
        }
        return check;
    }

   

    public static String checkPhoneNumber(String msg) {
        System.out.println(msg + ": ");
        String input = sc.nextLine().trim();
        while (input.length() < 10 || input.length() > 12) {
            System.out.println("Invalid input! Please enter a phone number with 10 to 12 digits!");
            System.out.println(msg + ": ");
            input = sc.nextLine().trim();
        }
        try {
            Long.parseLong(input);
            return input;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid phone number!");
            return checkPhoneNumber(msg);
        }
    }

    public static int checkInt(String msg, int min, int max) {
        int t;
        String input;
        if (min > max) {
            t = min;
            min = max;
            max = t;
        }

        boolean OK;
        do {
            System.out.println(msg + ": ");
            input = sc.nextLine().trim();
            t = Integer.parseInt(input);
            OK = (t >= min && t <= max);
            if (!OK) {
                System.out.println("Invalid input!!");
            }
        } while (!OK);
        return t;
    }

    public static int checkInt(String msg, int min) {
        return checkInt(msg, min, Integer.MAX_VALUE);
    }

    public static double checkDouble(String msg, double min) {
        double result;
        String input;
        boolean OK;
        do {
            System.out.println(msg + ": ");
            input = sc.nextLine().trim();
            result = Double.parseDouble(input);
            OK = result >= min;
        } while (!OK);
        return result;
    }

    public static String checkLength(String msg, int min, int max) {
        int t = 0;
        String s = "";
        boolean OK;
        if (min > max) {
            t = min;
            min = max;
            max = t;
        }
        do {
            System.out.println(msg + ": ");
            s = sc.nextLine().trim();
            int L = s.length();
            OK = (L >= min && L <= max);
            if (!OK) {
                System.out.println("Invalid input!!");
            }
        } while (!OK);
        return s;
    }

    public static boolean validStr(String Str, String regex) {
        return Str.matches(regex);
    }

    public static String InputPattern(String msg, String regex) {
        String s = "";
        boolean OK;
        do {
            System.out.println(msg + ": ");
            s = sc.nextLine().trim();
            OK = validStr(s, regex);
            if (!OK) {
                System.out.println("Invalid input!!");
            }
        } while (!OK);
        return s;
    }
}

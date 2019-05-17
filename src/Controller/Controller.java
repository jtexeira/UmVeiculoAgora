package Controller;

import Exceptions.InvalidUserException;
import Exceptions.UnknownCompareTypeException;
import Exceptions.WrongPasswordExecption;
import Model.*;
import Utils.Point;
import Utils.StringBetter;
import View.Menu;

import java.util.AbstractMap;
import java.util.Scanner;

import static java.lang.System.out;

public class Controller {
    private UMCarroJa model;
    private User user;
    private Menu menu;

    public Controller(UMCarroJa model) {
        this.menu = new Menu();
        this.model = model;
    }

    public void run(){
        Scanner scanner = new Scanner(System.in);
        String error = "";
        while(this.menu.getRun()) {
            switch (menu.getMenu()) {
                case Login:
                    try {
                        AbstractMap.SimpleEntry<String, String> r = menu.newLogin(error);
                        user = model.logIn(Integer.parseInt(r.getKey()), r.getValue());
                        menu.selectOption((user instanceof Client)? Menu.MenuInd.Cliente : Menu.MenuInd.Proprietário);
                        error = "";
                    }
                    catch (InvalidUserException e){
                        error = new StringBetter("Invalid Username").under().grey().toString();
                    }
                    catch (WrongPasswordExecption e){
                        error = new StringBetter("Invalid Password").under().grey().toString();
                    }
                    break;
                case Closest_Car:
                    try{
                        Rental rental = model.rental((Client)user, new Point(0.d, 0.d), Car.CarType.Electric, "MaisPerto");
                        menu.showRental(rental);
                        menu.back();
                    }
                    catch (UnknownCompareTypeException e){}
                    break;

                case Cheapest_Car:
                    try{
                        Rental rental = model.rental((Client)user, new Point(0.d, 0.d), Car.CarType.Electric, "MaisBarato");
                        menu.showRental(rental);
                        menu.back();
                    }
                    catch (UnknownCompareTypeException e){}
                    break;

                    default:
                        out.println(menu);
                        menu.parser(scanner.nextLine());
                        break;
            }

        }

    }
}

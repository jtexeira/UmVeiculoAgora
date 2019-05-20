package Controller;

import Exceptions.*;
import Model.*;
import Utils.Point;
import View.Menu;
import View.ViewModel.Register;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;

public class Controller {
    private final UMCarroJa model;
    private User user;
    private final Menu menu;

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
                        user = model.logIn(r.getKey(), r.getValue());
                        menu.selectOption((user instanceof Client)? Menu.MenuInd.Cliente : Menu.MenuInd.Proprietario);
                        error = "";
                    }
                    catch (InvalidUserException e){ error = "Invalid Username"; }
                    catch (WrongPasswordExecption e){ error = "Invalid Password"; }
                    break;
                case Registar_Cliente:
                    try {
                        Register registerCli = menu.newRegister(error);
                        Client client = new Client(
                                registerCli.getPos(),
                                registerCli.getEmail(),
                                registerCli.getPasswd(),
                                registerCli.getName(),
                                registerCli.getAddress(),
                                registerCli.getNif()
                        );
                        this.model.addUser(client);
                        menu.back();
                        error = "";
                    }
                    catch (InvalidNewRegister e){ error = "Parametros Inválidos"; }
                    catch (UserExistsException e){ error = "Utilizador já existe"; }
                    break;
                case Registar_Proprietario:
                    try {
                        Register registerProp = menu.newRegister(error);
                        Owner owner = new Owner(
                                registerProp.getEmail(),
                                registerProp.getName(),
                                registerProp.getAddress(),
                                registerProp.getNif(),
                                registerProp.getPasswd()
                        );
                        this.model.addUser(owner);
                        menu.back();
                        error = "";
                    }
                    catch (InvalidNewRegister e){ error = "Parametros Inválidos"; }
                    catch (UserExistsException e){ error = "Utilizador já existe"; }
                    break;
                case Closest_Car:
                    try{
                        Rental rental = model.rental((Client)user, new Point(0.d, 0.d), "MaisPerto", Car.CarType.Any);
                        menu.showRental(rental);
                        menu.back();
                    }
                    catch (UnknownCompareTypeException | NoCarAvaliableException ignored){}
                    break;

                case Cheapest_Car:
                    try{
                        Rental rental = model.rental((Client)user, new Point(0.d, 0.d), "MaisBarato", Car.CarType.Any);
                        menu.showRental(rental);
                        menu.back();
                    }
                    catch (UnknownCompareTypeException | NoCarAvaliableException ignored){}
                    break;
                case Review_Rent:
                    Owner owner = (Owner)this.user;
                    ArrayList<Rental> lR = owner.getPending();
                    for(int i = 0; i < lR.size() && i < 4; i++){
                        out.println(i + 1 + ".");
                        out.println(lR.get(i));
                    }
                    String v = scanner.nextLine();
                    try {
                        switch (v.charAt(0)) {
                            case 'A':
                                this.model.rent(lR.get(Integer.parseInt(v.substring(1)) - 1));
                                break;
                            case 'R':
                                owner.refuse(lR.get(Integer.parseInt(v.substring(1)) - 1));
                                break;
                            case 'b':
                                this.menu.back();
                                break;
                        }
                    }
                    catch(NumberFormatException ignored){}
                    break;

                    default:
                        out.println(menu);
                        menu.parser(scanner.nextLine());
                        break;
            }
        }
    }
}

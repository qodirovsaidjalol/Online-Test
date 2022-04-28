package uz.qodirov.ui;

import org.bson.types.ObjectId;
import uz.jl.utils.Color;
import uz.jl.utils.Input;
import uz.jl.utils.Print;
import uz.qodirov.dto.user.UserCreateDto;
import uz.qodirov.dto.user.UserUpdateDto;
import uz.qodirov.enums.HttpStatus;
import uz.qodirov.enums.language.Language;
import uz.qodirov.enums.roles.Role;
import uz.qodirov.response.Data;
import uz.qodirov.response.ResponseEntity;
import uz.qodirov.services.SessionService;
import uz.qodirov.services.users.UserService;


public class UserUI extends BaseUI<UserService> {


    public UserUI(UserService service) {
        super(service);
    }

    public  void login() {
        String username = Input.getStr("Username :");
        String password = Input.getStr("Password :");

        ResponseEntity<Data<String>> response = service.login(username, password);
//        if (!response.getStatus().equals(HttpStatus.HTTP_200.getCode())) {
//            Print.println(uz.jl.utils.Color.RED, response.getData());
//        } else {
            Print.println(Color.GREEN,response.getData().getBody());
       // }
    }

    public void register() {
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .username ( Input.getStr("Username :"))
                .password ( Input.getStr("Password :"))
                .fullName ( Input.getStr("FullName :"))
                .role(String.valueOf(Role.STUDENT))
                .language ( Input.getStr("Language :")).build();
        ResponseEntity<Data<ObjectId>> response = service.create(userCreateDto);
     /*   if (!response.getStatus().equals(HttpStatus.HTTP_200.getCode())) {
            Print.println(uz.jl.utils.Color.RED, response.getData());
        } else {*/
            Print.println(uz.jl.utils.Color.GREEN, "Successfully registered");
        //}
    }

    public  void logout() {
        SessionService.setSession(null);

    }



    @Override
    public void create() {
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .username ( Input.getStr("Username :"))
                .password ( Input.getStr("Password :"))
                .fullName ( Input.getStr("FullName :"))
                .role ( Input.getStr("Role :"))
                .language ( Input.getStr("Language :")).build();
        ResponseEntity<Data<ObjectId>> response = service.create(userCreateDto);
        if (!response.getStatus().equals(HttpStatus.HTTP_200.getCode())) {
            Print.println(uz.jl.utils.Color.RED, response.getData());
        } else {
            Print.println(uz.jl.utils.Color.GREEN, "Successfully created");
        }

    }

    @Override
    public void delete() {


    }

    @Override
    public void update() {
        UserUpdateDto userUpdateDto = UserUpdateDto.childbuilder()
                .id(Input.getStr("ID :"))
                .username ( Input.getStr("Username :"))
                .fullName ( Input.getStr("FullName :"))
                .language (Language.valueOf(Input.getStr("Language :"))).build();

        ResponseEntity<Data<Void>> response = service.update(userUpdateDto);
        if (!response.getStatus().equals(HttpStatus.HTTP_200.getCode())) {
            Print.println(uz.jl.utils.Color.RED, response.getData());
        } else {
            Print.println(uz.jl.utils.Color.GREEN, "Successfully updated");
        }
    }

    @Override
    public void get() {

    }

    @Override
    public void list() {

    }




}

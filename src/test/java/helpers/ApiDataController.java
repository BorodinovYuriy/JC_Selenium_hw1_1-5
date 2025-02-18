package helpers;

import lombok.Getter;

@Getter
public class ApiDataController {

    private static final String userExample;

    static {
        userExample = createUser();
    }

    private ApiDataController() {    }

    private static String createUser(){
        return "user";
    }
}

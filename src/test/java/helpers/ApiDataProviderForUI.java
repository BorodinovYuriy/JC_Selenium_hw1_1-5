package helpers;

import lombok.Getter;
//todo продумать так же и методы по удалению(чистки после себя!!! и после уже сделанных тестов 1-5)
@Getter
public class ApiDataProviderForUI {

    private static final String userExample;

    static {
        userExample = createUser();
    }

    private ApiDataProviderForUI() {    }

    private static String createUser(){
        return "user";
    }
}

# README #

### Requests for registration ###

- URL: http://localhost:8089/registration;
- requestType: POST;
- bodyType: raw/json;
- requestBody:

        String login (not null, min = 1, max = 25, latin symbols and numbers);    
        String password (not null, min = 6, latin symbols and numbers without spaces);

- Valid request/response example:

        request:  
        { "login" :   "Mike", "password" : "qwerty1234"}

        response:  
        Status: 200 OK;     
        body: Registered;

- Invalid requests/response example:

1) Login pattern mismatch

        request     
        { "login" :  "qwerГty123", "password" : "qwerrty"  }

        response    
        Status: 400 Bad request;    
        body : "Только латинские символы и цифры, от 1 до 25 символов";

2) Null login

        request     
        { "login" :  null, "password" : "qwerty"  }

        response    
        Status: 400 Bad request;    
        body : "Поле не может отсутствовать";

3) Not unique login

    - first step

            request:  
            { "login" :   "Mike", "password" : "qwerty1234"}

            response:   
            Status: 200 OK;   
            body: Registered;

    - second step

            request:  
            { "login" :   "Mike", "password" : "qwerty1234"}

            response:   
            Status: 400 Bad Request;   
            body: This username is busy;


4) Password pattern mismatch

        request     
        { "login" :  "qwerty123", "password" : "qwe"  }

        response    
        Status: 400 Bad request;    
        body : "Только латинские символы и цифры, без пробелов, больше 6-ти символов";

5) Null password

        request     
        { "login" :  "trolan", "password" : null  }

        response    
        Status: 400 Bad request;    
        body : "Поле не может отсутствовать";

### Requests for authorization ###

- URL: http://localhost:8089/authorization;
- requestType: POST;
- bodyType: raw/json;
- requestBody:

        String login (not null, min = 1, max = 25, latin symbols and numbers);    
        String password (not null, min = 6, latin symbols and numbers without spaces);

- Valid request/response example:

        request:  
        { "login" :   "Mike", "password" : "qwerty1234"}

        response:  
        Status: 200 OK;     
        body: Authorized;

- Invalid requests/response example:

1) User is not registered

        request     
        { "login" :  "trolan123", "password" : "qwerrty"  }

        response    
        Status: 401 Unauthorized;    
        body : "User trolan123 was not found";

2) Null login

        request     
        { "login" :  null, "password" : "qwerty"  }

        response    
        Status: 400 Bad request;    
        body : "Поле не может отсутствовать";
3) Null password

        request     
        { "login" :  "trolan", "password" : null  }

        response    
        Status: 400 Bad request;    
        body : "Поле не может отсутствовать";

4) Incorrect credentials

        request     
        { "login" :  "trolan", "password" : "1234F456"  }

        response    
        Status: 401 Unauthorized;    
        body : "Incorrect credentials";



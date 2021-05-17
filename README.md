# README #

### Requests for registration ###

- URL: http://localhost:8081/registration;
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
        body : "Only latin symbols and numbers available, from 1 to 25 symbols";

2) Null login

        request     
        { "login" :  null, "password" : "qwerty"  }

        response    
        Status: 400 Bad request;    
        body : "Field cannot be empty";

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
            body: "This username is busy";


4) Password pattern mismatch

        request     
        { "login" :  "qwerty123", "password" : "qwe"  }

        response    
        Status: 400 Bad request;    
        body : "Only latin symbols and numbers available without spaces, more than 6 symbols";

5) Null password

        request     
        { "login" :  "trolan", "password" : null  }

        response    
        Status: 400 Bad request;    
        body : "Field cannot be empty";

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
        body : "Field cannot be empty";
3) Null password

        request     
        { "login" :  "trolan", "password" : null  }

        response    
        Status: 400 Bad request;    
        body : "Field cannot be empty";

4) Incorrect credentials

        request     
        { "login" :  "trolan", "password" : "1234F456"  }

        response    
        Status: 401 Unauthorized;    
        body : "Incorrect credentials";

### Requests for creating tournament ###

- URL: http://localhost:8081/createTournament;
- requestType: POST;
- bodyType: raw/json;
- requestBody:

        String name;    
        String description;
        String mode;
        String place;
        String startDate;
        String endDate;
        String tournamentLevel;
        String participants;
        

- Valid request/response example:

        request:  
        {"name":"four","description":"123werwerwerwer456", "mode":0, "place":"first" , "startDate":1102020202 , "endDate": 45454554, "tournamentLevel":0, "participants":4 }
        response:  
        Status: 200 OK;     
        body: "tournament four was successfully created";
;

- Invalid requests/response example:

1) 

        request     
        {"name":"four","description":"123werwerwerwer456", "mode":0, "place":"first" , "startDate":1102020202 , "endDate": 45454554, "tournamentLevel":0, "participants":4 }

        response    
        Status: 400 Bad request;    
        body : "Tournament name four is busy";


### Requests for delete tournament ###

- URL: http://localhost:8081/deleteTournament;
- requestType: POST;
- bodyType: raw/json;
- requestBody:

        String name;    
        String role;


- Valid request/response example:

        request:  
        {"name":"four","mode":"user"}
        response:  
        Status: 200 OK;     
        body: "tournament four was successfully created";
;

- Invalid requests/response example:

1)

     request     
     {"name":"four", "mode":"user"}

     response    
     Status: 400 Bad request;    
     body : "Tournament four was not found";

### Requests for delete tournament ###

- URL: http://localhost:8081/getTournament;
- requestType: POST;
- bodyType: raw/json;
- requestBody:

        int status;


- Valid request/response example:

        request:  
        {"tournamentStatus":0}
        response:  
        Status: 200 OK;     
;

- Invalid requests/response example:

1)

     request     
     {"tournamentStatus":0}

     response    
     Status: 400 Bad request;    



### Requests for add participants ###

- URL: http://localhost:8081/addParticipants;
- requestType: POST;
- bodyType: raw/json;
- requestBody:

        String login;
        String nameTournament;

- Valid request/response example:

        request:  
        {"login":"smile","nameTournament":"trolan"}
        response:  
        Status: 200 OK;

- Invalid requests/response example:

1) TOURNAMENT_WAS_NOT_FOUND

     request     
   {"login":"smile","nameTournament":"trolan"}

     response    
     Status: 400 Bad request;
    
2) USER_WAS_NOT_FOUND

     request     
   {"login":"smile","nameTournament":"trolan"}

     response    
     Status: 400 Bad request;
   
3) USER_ALREADY_IN_TOURNAMENT

     request     
   {"login":"smile","nameTournament":"trolan"}

     response    
     Status: 400 Bad request;   





### Requests for get starter grid ###

- URL: http://localhost:8081/getStarterGrid;
- requestType: POST;
- bodyType: raw/json;
- requestBody:

        String tournamentName;

- Valid request/response example:

        request:  
        {"tournamentName":"four"}
        response:  
        Status: 200 OK;

- Invalid requests/response example:


1) TOURNAMENT_WAS_NOT_FOUND

     request     
   {"login":"smile","nameTournament":"trolan"}

     response    
     Status: 400 Bad request;
   
   
### Requests for get final grid ###

- URL: http://localhost:8081/getFinalGrid;
- requestType: POST;
- bodyType: raw/json;
- requestBody:

        String tournamentName;

- Valid request/response example:

        request:  
        {"tournamentName":"four"}
        response:  
        Status: 200 OK;

- Invalid requests/response example:


1) TOURNAMENT_WAS_NOT_FOUND

     request
     {"tournamentName":"four"}
     response    
     Status: 400 Bad request;
    




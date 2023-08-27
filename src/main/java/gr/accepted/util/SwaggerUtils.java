package gr.accepted.util;

public class SwaggerUtils {

    public static final String ALL_MATCHES_RESPONSE = """
            {
                 "matches": [
                     {
                         "id": 1,
                         "description": "My description",
                         "matchDate": "2023-08-24",
                         "matchTime": "12:00:00",
                         "teamA": "OSFP",
                         "teamB": "PAO",
                         "sport": "FOOTBALL"
                     },
                     {
                         "id": 2,
                         "description": "My description",
                         "matchDate": "2023-08-24",
                         "matchTime": "12:00:00",
                         "teamA": "OSFP",
                         "teamB": "PAO",
                         "sport": "FOOTBALL"
                     }
                 ]
             }""";

    public static final String ALL_MATCHES_WITH_DETAILS_RESPONSE = """
            {
                 "matches": [
                     {
                         "id": 1,
                         "description": "My description",
                         "matchDate": "2023-08-24",
                         "matchTime": "12:00:00",
                         "teamA": "OSFP",
                         "teamB": "PAO",
                         "sport": "FOOTBALL",
                         "matchOdds": [
                             {
                                 "id": 1,
                                 "specifier": "X",
                                 "odd": 2.50,
                                 "matchId": 1
                             },
                             {
                                 "id": 2,
                                 "specifier": "W",
                                 "odd": 2.00,
                                 "matchId": 1
                             },
                             {
                                 "id": 3,
                                 "specifier": "L",
                                 "odd": 2.00,
                                 "matchId": 1
                             }
                         ]
                     },
                     {
                         "id": 2,
                         "description": "My description",
                         "matchDate": "2023-08-24",
                         "matchTime": "12:00:00",
                         "teamA": "OSFP",
                         "teamB": "PAO",
                         "sport": "FOOTBALL",
                         "matchOdds": []
                     }
                 ]
             }""";

    public static final String MATCH_ODD_NOT_FOUND_RESPONSE = """
            {
                "message": "Match odd with id 45354 does not exist",
                "errorCode": "entityNotFound",
                "requestPath": "/match-odds/45354"
            }""";

    public static final String MATCH_NOT_FOUND_RESPONSE = """
            {
                "message": "Match with id 55 does not exist",
                "errorCode": "entityNotFound",
                "requestPath": "/matches/55"
            }""";

    public static final String CREATE_MATCH_INVALID_INPUT_RESPONSE = """
                {
                "message": "Invalid Input",
                "errorCode": "invalidInput",
                "requestPath": "/matches",
                "validationErrors": [
                    "teamA: size must be between 2 and 10",
                    "teamB: size must be between 2 and 10"
                ]
            }
            """;

    public static final String CREATE_MATCH_ODD_MISSING_MATCH_ID_RESPONSE = """
                {
                "message": "Invalid Input",
                "errorCode": "invalidInput",
                "requestPath": "/match-odds",
                "validationErrors": [
                    "matchId: must not be null"
                ]
            }
            """;

    public static final String UPDATE_MATCH_ODD_MISSING_MATCH_ID_RESPONSE = """
                {
                "message": "Invalid Input",
                "errorCode": "invalidInput",
                "requestPath": "/match-odds",
                "validationErrors": [
                    "matchId: must not be null"
                ]
            }
            """;

    public static final String ID_MISMATCH_RESPONSE = """
            {
                "message": "Match odd id (655) non-existent or referring to different match",
                "errorCode": "idMismatch",
                "requestPath": "/matches/7"
            }""";

    public static final String MATCH_ODD_INVALID_RESPONSE = """
            {
                "message": "Invalid Input",
                "errorCode": "invalidInput",
                "requestPath": "/matches/7",
                "validationErrors": [
                    "matchOdds[0].specifier: size must be between 1 and 1"
                ]
            }""";
}

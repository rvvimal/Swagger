package com.school_management.util;

public class Swagger {
    private Swagger() {
    }

    public static final String School_Retrieved = """
                {
                     "statusCode": 200,
                     "message": "Retrieved Successfully",
                     "data": [
                         {
                             "id": 1,
                             "name": "SRM school",
                             "address": "Kallakurichi",
                             "contact": 7942699116
                         },
                         {
                             "id": 2,
                             "name": "AKT school",
                             "address": "Salem",
                             "contact": 9894932323
                         },
                         {
                             "id": 3,
                             "name": "Bharathi school",
                             "address": "Erode",
                             "contact": 9742699108
                         },
                         {
                             "id": 4,
                             "name": "CBSE school",
                             "address": "Chinnasalem",
                             "contact": 9742691023
                         }
                     ]
                 }
            """;
    public static final String School_Retrieved_ID = """
                        {
                             "statusCode": 200,
                             "message": "Retrieved Successfully",
                             "data": [
                                 {
                                     "id": 1,
                                     "name": "SRM school",
                                     "address": "Kallakurichi",
                                     "contact": 7942699116
                                 }
                             ]
                        }
            """;

    public static final String BAD_REQUEST = """
            {
            "statusCode":400,
            "message":"Bad Request"
            }
            
            """;
    public static final String INTERNAL_SERVER_ERROR_RESPONSE = """ 
                 {
                 "statusCode": 500,
                  "message": "Internal Server Error",
                  "data": null
            }""";

    public static final String Student_Retrieved= """
            {
            "statusCode": 200,
                "message": "Retrieved Successfully",
                "data": [
                    {
                        "id": 1,
                        "firstName": "Kavinkumar",
                        "lastName": "G",
                        "email": "kavikumar@gmail.com",
                        "contactNumber": 9543631987,
                        "enrollmentDate": "25.06.2025",
                        "school": {
                            "id": 1,
                            "name": "SRM school",
                            "address": "Kallakurichi",
                            "contact": 7942699116
                        }
                    },
                    {
                        "id": 2,
                        "firstName": "Kavin",
                        "lastName": "A",
                        "email": "kavin@gmail.com",
                        "contactNumber": 9629514036,
                        "enrollmentDate": "05.06.2025",
                        "school": {
                            "id": 3,
                            "name": "Bharathi school",
                            "address": "Erode",
                            "contact": 9742699108
                        }
                    },
                    {
                        "id": 3,
                        "firstName": "Arun",
                        "lastName": "A",
                        "email": "arun23@gmail.com",
                        "contactNumber": 9629784152,
                        "enrollmentDate": "15.06.2025",
                        "school": {
                            "id": 2,
                            "name": "AKT school",
                            "address": "Salem",
                            "contact": 9894932323
                        }
                    },
                    {
                        "id": 4,
                        "firstName": "Babu",
                        "lastName": "B",
                        "email": "babu29@gmail.com",
                        "contactNumber": 8073564352,
                        "enrollmentDate": "12.06.2025",
                        "school": {
                            "id": 4,
                            "name": "CBSE school",
                            "address": "Chinnasalem",
                            "contact": 9742691023
                        }
                    },
                    {
                        "id": 5,
                        "firstName": "Dinesh",
                        "lastName": "S",
                        "email": "dinesh25@gmail.com",
                        "contactNumber": 9565230671,
                        "enrollmentDate": "01.06.2025",
                        "school": {
                            "id": 1,
                            "name": "SRM school",
                            "address": "Kallakurichi",
                            "contact": 7942699116
                        }
                    }
                ]
            }
            """;
    public static final String Student_Retrieved_ID= """
        {
        "statusCode": 200,
            "message": "Retrieved Successfully",
            "data": [
                {
                    "id": 1,
                    "firstName": "Kavinkumar",
                    "lastName": "G",
                    "email": "kavikumar@gmail.com",
                    "contactNumber": 9543631987,
                    "enrollmentDate": "25.06.2025",
                    "school": {
                        "id": 1,
                        "name": "SRM school",
                        "address": "Kallakurichi",
                        "contact": 7942699116
                    }
                }
            ]
        }
        """;


    public static final String Tutor_Retrieved= """
            {
                "statusCode": 200,
                "message": "Retrieved Successfully",
                "data": [
                    {
                        "id": 1,
                        "name": "Suresh",
                        "email": "suresh12@gmail.com",
                        "contactNumber": 9876432623,
                        "school": {
                            "id": 1,
                            "name": "SRM school",
                            "address": "Kallakurichi",
                            "contact": 7942699116
                        }
                    },
                    {
                        "id": 2,
                        "name": "Ravi",
                        "email": "ravi23@gmail.com",
                        "contactNumber": 9553037081,
                        "school": {
                            "id": 2,
                            "name": "AKT school",
                            "address": "Salem",
                            "contact": 9894932323
                        }
                    },
                    {
                        "id": 3,
                        "name": "Rajesh",
                        "email": "rajesh23@gmail.com",
                        "contactNumber": 9553037081,
                        "school": {
                            "id": 3,
                            "name": "Bharathi school",
                            "address": "Erode",
                            "contact": 9742699108
                        }
                    },
                    {
                        "id": 4,
                        "name": "Vino",
                        "email": "vino34@gmail.com",
                        "contactNumber": 9692505138,
                        "school": {
                            "id": 4,
                            "name": "CBSE school",
                            "address": "Chinnasalem",
                            "contact": 9742691023
                        }
                    },
                    {
                        "id": 5,
                        "name": "Ramya",
                        "email": "ramya43@gmail.com",
                        "contactNumber": 9641922306,
                        "school": {
                            "id": 2,
                            "name": "AKT school",
                            "address": "Salem",
                            "contact": 9894932323
                        }
                    }
                ]
            }
            """;
    public static final String Tutor_Retrieved_ID = """
        {
            "statusCode": 200,
            "message": "Retrieved Successfully",
            "data": [
                {
                    "id": 1,
                    "name": "Suresh",
                    "email": "suresh12@gmail.com",
                    "contactNumber": 9876432623,
                    "school": {
                        "id": 1,
                        "name": "SRM school",
                        "address": "Kallakurichi",
                        "contact": 7942699116
                    }
                }
            ]
        }
        """;

    public static final String Course_Retrieved= """
            {
                "statusCode": 200,
                "message": "Retrieved Successfully",
                "data": [
                    {
                        "id": 1,
                        "name": "Tamil"
                    },
                    {
                        "id": 2,
                        "name": "Maths"
                    },
                    {
                        "id": 3,
                        "name": "Science"
                    },
                    {
                        "id": 4,
                        "name": "Computer Science"
                    },
                    {
                        "id": 5,
                        "name": "Data Science"
                    },
                    {
                        "id": 6,
                        "name": "Physics"
                    },
                    {
                        "id": 7,
                        "name": "Chemistry"
                    }
                ]
            }
            """;

    public static final String Course_Retrieved_ID = """
        {
            "statusCode": 200,
            "message": "Retrieved Successfully",
            "data": [
                {
                    "id": 1,
                    "name": "Tamil"
                }
            ]
        }
        """;

    public static final String Student_Course_Retrieved= """
            {
                "statusCode": 200,
                "message": "Retrieved Successfully",
                "data": [
                    {
                        "id": 1,
                        "student": {
                            "id": 1,
                            "firstName": "Kavinkumar",
                            "lastName": "G",
                            "email": "kavikumar@gmail.com",
                            "contactNumber": 9543631987,
                            "enrollmentDate": "25.06.2025",
                            "school": {
                                "id": 1,
                                "name": "SRM school",
                                "address": "Kallakurichi",
                                "contact": 7942699116
                            }
                        },
                        "course": {
                            "id": 1,
                            "name": "Tamil"
                        },
                        "tutor": {
                            "id": 2,
                            "name": "Ravi",
                            "email": "ravi23@gmail.com",
                            "contactNumber": 9553037081,
                            "school": {
                                "id": 2,
                                "name": "AKT school",
                                "address": "Salem",
                                "contact": 9894932323
                            }
                        }
                    },
                    {
                        "id": 2,
                        "student": {
                            "id": 2,
                            "firstName": "Kavin",
                            "lastName": "A",
                            "email": "kavin@gmail.com",
                            "contactNumber": 9629514036,
                            "enrollmentDate": "05.06.2025",
                            "school": {
                                "id": 3,
                                "name": "Bharathi school",
                                "address": "Erode",
                                "contact": 9742699108
                            }
                        },
                        "course": {
                            "id": 3,
                            "name": "Science"
                        },
                        "tutor": {
                            "id": 1,
                            "name": "Suresh",
                            "email": "suresh12@gmail.com",
                            "contactNumber": 9876432623,
                            "school": {
                                "id": 1,
                                "name": "SRM school",
                                "address": "Kallakurichi",
                                "contact": 7942699116
                            }
                        }
                    },
                    {
                        "id": 3,
                        "student": {
                            "id": 3,
                            "firstName": "Arun",
                            "lastName": "A",
                            "email": "arun23@gmail.com",
                            "contactNumber": 9629784152,
                            "enrollmentDate": "15.06.2025",
                            "school": {
                                "id": 2,
                                "name": "AKT school",
                                "address": "Salem",
                                "contact": 9894932323
                            }
                        },
                        "course": {
                            "id": 2,
                            "name": "Maths"
                        },
                        "tutor": {
                            "id": 3,
                            "name": "Rajesh",
                            "email": "rajesh23@gmail.com",
                            "contactNumber": 9553037081,
                            "school": {
                                "id": 3,
                                "name": "Bharathi school",
                                "address": "Erode",
                                "contact": 9742699108
                            }
                        }
                    },
                    {
                        "id": 4,
                        "student": {
                            "id": 5,
                            "firstName": "Dinesh",
                            "lastName": "S",
                            "email": "dinesh25@gmail.com",
                            "contactNumber": 9565230671,
                            "enrollmentDate": "01.06.2025",
                            "school": {
                                "id": 1,
                                "name": "SRM school",
                                "address": "Kallakurichi",
                                "contact": 7942699116
                            }
                        },
                        "course": {
                            "id": 4,
                            "name": "Computer Science"
                        },
                        "tutor": {
                            "id": 5,
                            "name": "Ramya",
                            "email": "ramya43@gmail.com",
                            "contactNumber": 9641922306,
                            "school": {
                                "id": 2,
                                "name": "AKT school",
                                "address": "Salem",
                                "contact": 9894932323
                            }
                        }
                    },
                    {
                        "id": 5,
                        "student": {
                            "id": 4,
                            "firstName": "Babu",
                            "lastName": "B",
                            "email": "babu29@gmail.com",
                            "contactNumber": 8073564352,
                            "enrollmentDate": "12.06.2025",
                            "school": {
                                "id": 4,
                                "name": "CBSE school",
                                "address": "Chinnasalem",
                                "contact": 9742691023
                            }
                        },
                        "course": {
                            "id": 5,
                            "name": "Data Science"
                        },
                        "tutor": {
                            "id": 4,
                            "name": "Vino",
                            "email": "vino34@gmail.com",
                            "contactNumber": 9692505138,
                            "school": {
                                "id": 4,
                                "name": "CBSE school",
                                "address": "Chinnasalem",
                                "contact": 9742691023
                            }
                        }
                    },
                    {
                        "id": 6,
                        "student": {
                            "id": 2,
                            "firstName": "Kavin",
                            "lastName": "A",
                            "email": "kavin@gmail.com",
                            "contactNumber": 9629514036,
                            "enrollmentDate": "05.06.2025",
                            "school": {
                                "id": 3,
                                "name": "Bharathi school",
                                "address": "Erode",
                                "contact": 9742699108
                            }
                        },
                        "course": {
                            "id": 7,
                            "name": "Chemistry"
                        },
                        "tutor": {
                            "id": 3,
                            "name": "Rajesh",
                            "email": "rajesh23@gmail.com",
                            "contactNumber": 9553037081,
                            "school": {
                                "id": 3,
                                "name": "Bharathi school",
                                "address": "Erode",
                                "contact": 9742699108
                            }
                        }
                    },
                    {
                        "id": 7,
                        "student": {
                            "id": 3,
                            "firstName": "Arun",
                            "lastName": "A",
                            "email": "arun23@gmail.com",
                            "contactNumber": 9629784152,
                            "enrollmentDate": "15.06.2025",
                            "school": {
                                "id": 2,
                                "name": "AKT school",
                                "address": "Salem",
                                "contact": 9894932323
                            }
                        },
                        "course": {
                            "id": 6,
                            "name": "Physics"
                        },
                        "tutor": {
                            "id": 5,
                            "name": "Ramya",
                            "email": "ramya43@gmail.com",
                            "contactNumber": 9641922306,
                            "school": {
                                "id": 2,
                                "name": "AKT school",
                                "address": "Salem",
                                "contact": 9894932323
                            }
                        }
                    }
                ]
            }
            """;
    public static final String Student_Course_Retrieved_ID = """
        {
            "statusCode": 200,
            "message": "Retrieved Successfully",
            "data": [
                {
                    "id": 1,
                    "student": {
                        "id": 1,
                        "firstName": "Kavinkumar",
                        "lastName": "G",
                        "email": "kavikumar@gmail.com",
                        "contactNumber": 9543631987,
                        "enrollmentDate": "25.06.2025",
                        "school": {
                            "id": 1,
                            "name": "SRM school",
                            "address": "Kallakurichi",
                            "contact": 7942699116
                        }
                    },
                    "course": {
                        "id": 1,
                        "name": "Tamil"
                    },
                    "tutor": {
                        "id": 2,
                        "name": "Ravi",
                        "email": "ravi23@gmail.com",
                        "contactNumber": 9553037081,
                        "school": {
                            "id": 2,
                            "name": "AKT school",
                            "address": "Salem",
                            "contact": 9894932323
                        }
                    }
                }
            ]
        }
        """;

    public static final String Tutor_Course_Retrieved= """
            {
                "statusCode": 200,
                "message": "Retrieved Successfully",
                "data": [
                    {
                        "id": 1,
                        "course": {
                            "id": 1,
                            "name": "Tamil"
                        },
                        "tutor": {
                            "id": 2,
                            "name": "Ravi",
                            "email": "ravi23@gmail.com",
                            "contactNumber": 9553037081,
                            "school": {
                                "id": 2,
                                "name": "AKT school",
                                "address": "Salem",
                                "contact": 9894932323
                            }
                        }
                    },
                    {
                        "id": 2,
                        "course": {
                            "id": 3,
                            "name": "Science"
                        },
                        "tutor": {
                            "id": 1,
                            "name": "Suresh",
                            "email": "suresh12@gmail.com",
                            "contactNumber": 9876432623,
                            "school": {
                                "id": 1,
                                "name": "SRM school",
                                "address": "Kallakurichi",
                                "contact": 7942699116
                            }
                        }
                    },
                    {
                        "id": 3,
                        "course": {
                            "id": 2,
                            "name": "Maths"
                        },
                        "tutor": {
                            "id": 3,
                            "name": "Rajesh",
                            "email": "rajesh23@gmail.com",
                            "contactNumber": 9553037081,
                            "school": {
                                "id": 3,
                                "name": "Bharathi school",
                                "address": "Erode",
                                "contact": 9742699108
                            }
                        }
                    },
                    {
                        "id": 4,
                        "course": {
                            "id": 4,
                            "name": "Computer Science"
                        },
                        "tutor": {
                            "id": 5,
                            "name": "Ramya",
                            "email": "ramya43@gmail.com",
                            "contactNumber": 9641922306,
                            "school": {
                                "id": 2,
                                "name": "AKT school",
                                "address": "Salem",
                                "contact": 9894932323
                            }
                        }
                    },
                    {
                        "id": 5,
                        "course": {
                            "id": 5,
                            "name": "Data Science"
                        },
                        "tutor": {
                            "id": 4,
                            "name": "Vino",
                            "email": "vino34@gmail.com",
                            "contactNumber": 9692505138,
                            "school": {
                                "id": 4,
                                "name": "CBSE school",
                                "address": "Chinnasalem",
                                "contact": 9742691023
                            }
                        }
                    },
                    {
                        "id": 6,
                        "course": {
                            "id": 7,
                            "name": "Chemistry"
                        },
                        "tutor": {
                            "id": 3,
                            "name": "Rajesh",
                            "email": "rajesh23@gmail.com",
                            "contactNumber": 9553037081,
                            "school": {
                                "id": 3,
                                "name": "Bharathi school",
                                "address": "Erode",
                                "contact": 9742699108
                            }
                        }
                    },
                    {
                        "id": 7,
                        "course": {
                            "id": 6,
                            "name": "Physics"
                        },
                        "tutor": {
                            "id": 5,
                            "name": "Ramya",
                            "email": "ramya43@gmail.com",
                            "contactNumber": 9641922306,
                            "school": {
                                "id": 2,
                                "name": "AKT school",
                                "address": "Salem",
                                "contact": 9894932323
                            }
                        }
                    }
                ]
            }
            """;
    public static final String Tutor_Course_Retrieved_ID = """
        {
            "statusCode": 200,
            "message": "Retrieved Successfully",
            "data": [
                {
                    "id": 1,
                    "course": {
                        "id": 1,
                        "name": "Tamil"
                    },
                    "tutor": {
                        "id": 2,
                        "name": "Ravi",
                        "email": "ravi23@gmail.com",
                        "contactNumber": 9553037081,
                        "school": {
                            "id": 2,
                            "name": "AKT school",
                            "address": "Salem",
                            "contact": 9894932323
                        }
                    }
                }
            ]
        }
        """;

    public static final  String Tutor_Salary_Retrieved= """
             {
                 "statusCode": 200,
                 "message": "Retrieved Successfully",
                 "data": [
                     {
                         "id": 1,
                         "month": "April",
                         "paid": "paid",
                         "amount": 3500.0,
                         "tutor": {
                             "id": 1,
                             "name": "Suresh",
                             "email": "suresh12@gmail.com",
                             "contactNumber": 9876432623,
                             "school": {
                                 "id": 1,
                                 "name": "SRM school",
                                 "address": "Kallakurichi",
                                 "contact": 7942699116
                             }
                         }
                     },
                     {
                         "id": 2,
                         "month": "July",
                         "paid": "paid",
                         "amount": 2500.0,
                         "tutor": {
                             "id": 2,
                             "name": "Ravi",
                             "email": "ravi23@gmail.com",
                             "contactNumber": 9553037081,
                             "school": {
                                 "id": 2,
                                 "name": "AKT school",
                                 "address": "Salem",
                                 "contact": 9894932323
                             }
                         }
                     },
                     {
                         "id": 3,
                         "month": "November",
                         "paid": "unpaid",
                         "amount": 5000.0,
                         "tutor": {
                             "id": 3,
                             "name": "Rajesh",
                             "email": "rajesh23@gmail.com",
                             "contactNumber": 9553037081,
                             "school": {
                                 "id": 3,
                                 "name": "Bharathi school",
                                 "address": "Erode",
                                 "contact": 9742699108
                             }
                         }
                     },
                     {
                         "id": 4,
                         "month": "December",
                         "paid": "paid",
                         "amount": 15000.0,
                         "tutor": {
                             "id": 4,
                             "name": "Vino",
                             "email": "vino34@gmail.com",
                             "contactNumber": 9692505138,
                             "school": {
                                 "id": 4,
                                 "name": "CBSE school",
                                 "address": "Chinnasalem",
                                 "contact": 9742691023
                             }
                         }
                     },
                     {
                         "id": 5,
                         "month": "June",
                         "paid": "paid",
                         "amount": 1000.0,
                         "tutor": {
                             "id": 5,
                             "name": "Ramya",
                             "email": "ramya43@gmail.com",
                             "contactNumber": 9641922306,
                             "school": {
                                 "id": 2,
                                 "name": "AKT school",
                                 "address": "Salem",
                                 "contact": 9894932323
                             }
                         }
                     }
                 ]
             }
            """;

    public static final String Tutor_Salary_Retrieved_ID = """
        {
             "statusCode": 200,
             "message": "Retrieved Successfully",
              "data": {
                          "tutorId": 4,
                          "tutorName": "Vino",
                          "amount": 15000.0,
                          "schoolId": 4,
                          "schoolName": "CBSE school"
                      }
        }
        """;

    public static final String FreePayment_Retrieved= """
            {
                "statusCode": 200,
                "message": "Retrieved Successfully",
                "data": [
                    {
                        "id": 1,
                        "date": "2022-03-04T00:00:00.000+00:00",
                        "fee_Term": "first term",
                        "status": "paid",
                        "student": {
                            "id": 1,
                            "firstName": "Kavinkumar",
                            "lastName": "G",
                            "email": "kavikumar@gmail.com",
                            "contactNumber": 9543631987,
                            "enrollmentDate": "25.06.2025",
                            "school": {
                                "id": 1,
                                "name": "SRM school",
                                "address": "Kallakurichi",
                                "contact": 7942699116
                            }
                        },
                        "amount": 12000.0
                    },
                    {
                        "id": 2,
                        "date": "2022-03-29T00:00:00.000+00:00",
                        "fee_Term": "first term",
                        "status": "paid",
                        "student": {
                            "id": 2,
                            "firstName": "Kavin",
                            "lastName": "A",
                            "email": "kavin@gmail.com",
                            "contactNumber": 9629514036,
                            "enrollmentDate": "05.06.2025",
                            "school": {
                                "id": 3,
                                "name": "Bharathi school",
                                "address": "Erode",
                                "contact": 9742699108
                            }
                        },
                        "amount": 2000.0
                    },
                    {
                        "id": 3,
                        "date": "2022-06-09T00:00:00.000+00:00",
                        "fee_Term": "full term",
                        "status": "paid",
                        "student": {
                            "id": 3,
                            "firstName": "Arun",
                            "lastName": "A",
                            "email": "arun23@gmail.com",
                            "contactNumber": 9629784152,
                            "enrollmentDate": "15.06.2025",
                            "school": {
                                "id": 2,
                                "name": "AKT school",
                                "address": "Salem",
                                "contact": 9894932323
                            }
                        },
                        "amount": 60000.0
                    },
                    {
                        "id": 4,
                        "date": "2022-06-09T00:00:00.000+00:00",
                        "fee_Term": "first term",
                        "status": "unpaid",
                        "student": {
                            "id": 4,
                            "firstName": "Babu",
                            "lastName": "B",
                            "email": "babu29@gmail.com",
                            "contactNumber": 8073564352,
                            "enrollmentDate": "12.06.2025",
                            "school": {
                                "id": 4,
                                "name": "CBSE school",
                                "address": "Chinnasalem",
                                "contact": 9742691023
                            }
                        },
                        "amount": 2000.0
                    },
                    {
                        "id": 5,
                        "date": "2022-07-29T00:00:00.000+00:00",
                        "fee_Term": "first term",
                        "status": "unpaid",
                        "student": {
                            "id": 5,
                            "firstName": "Dinesh",
                            "lastName": "S",
                            "email": "dinesh25@gmail.com",
                            "contactNumber": 9565230671,
                            "enrollmentDate": "01.06.2025",
                            "school": {
                                "id": 1,
                                "name": "SRM school",
                                "address": "Kallakurichi",
                                "contact": 7942699116
                            }
                        },
                        "amount": 12000.0
                    }
                ]
            }
            """;

    public static final String FreePayment_Retrieved_ID = """
        {
            "statusCode": 200,
            "message": "Retrieved Successfully",
            "data": [
                {
                    "id": 1,
                    "date": "2022-03-04T00:00:00.000+00:00",
                    "fee_Term": "first term",
                    "status": "paid",
                    "student": {
                        "id": 1,
                        "firstName": "Kavinkumar",
                        "lastName": "G",
                        "email": "kavikumar@gmail.com",
                        "contactNumber": 9543631987,
                        "enrollmentDate": "25.06.2025",
                        "school": {
                            "id": 1,
                            "name": "SRM school",
                            "address": "Kallakurichi",
                            "contact": 7942699116
                        }
                    },
                    "amount": 12000.0
                }
            ]
        }
        """;
    public static final String FreePayment_Retrieved_Mini_Max = """
        {
             "statusCode": 200,
                     "message": "Retrieved Successfully",
                     "data": [
                         {
                             "schoolId": 1,
                             "schoolName": "SRM school",
                             "minimumFee": 12000.0,
                             "maximumFee": 12000.0
                         }
                     ]
        }
        """;
    public static final String User_Retrieved= """
            {
                "statusCode": 200,
                "message": "Retrieved Successfully",
                "data": [
                    {
                        "id": 1,
                        "name": "Ravi",
                        "emailId": "ravi12@gmail.com",
                        "password": "$2a$12$szZMcpMY8UZtMql542CvYuNFWdQ6ZrReKxZrHk85Y50lKw0cRVYp.",
                        "role": "ADMIN",
                        "enabled": true,
                        "credentialsNonExpired": true,
                        "authorities": [
                            {
                                "authority": "ADMIN"
                            }
                        ],
                        "username": "ravi12@gmail.com",
                        "accountNonLocked": true,
                        "accountNonExpired": true
                    },
                    {
                        "id": 2,
                        "name": "Gokul",
                        "emailId": "gokul23@gmail.com",
                        "password": "$2a$12$/C/bLqQ4ZWJOFpA0BXfTIeK/oWf0OgsxaxiAS7nFggEElKpVxtcmq",
                        "role": "ADMIN",
                        "enabled": true,
                        "credentialsNonExpired": true,
                        "authorities": [
                            {
                                "authority": "ADMIN"
                            }
                        ],
                        "username": "gokul23@gmail.com",
                        "accountNonLocked": true,
                        "accountNonExpired": true
                    },
                    {
                        "id": 3,
                        "name": "Rajesh",
                        "emailId": "rajesh23@gmail.com",
                        "password": "$2a$12$0Nbg1nVVMzsNy.OI9XBZruyuL/gYSJfHA4Hww.2/1s4VsG11O22lW",
                        "role": "TEACHER",
                        "enabled": true,
                        "credentialsNonExpired": true,
                        "authorities": [
                            {
                                "authority": "TEACHER"
                            }
                        ],
                        "username": "rajesh23@gmail.com",
                        "accountNonLocked": true,
                        "accountNonExpired": true
                    },
                    {
                        "id": 4,
                        "name": "Vino",
                        "emailId": "vino34@gmail.com",
                        "password": "$2a$12$Gi7lQReoCplxBY8nw8Ukh.cByo6GwONucrmPra2Byq2JhjL6yTfD.",
                        "role": "TEACHER",
                        "enabled": true,
                        "credentialsNonExpired": true,
                        "authorities": [
                            {
                                "authority": "TEACHER"
                            }
                        ],
                        "username": "vino34@gmail.com",
                        "accountNonLocked": true,
                        "accountNonExpired": true
                    },
                    {
                        "id": 5,
                        "name": "Kavinkumar",
                        "emailId": "kavikumar@gmail.com",
                        "password": "$2a$12$eoaxuej07uKFgqs.yDNxZuYKJ/d29Ixdq2nA3FBrOet2hKAad0aTS",
                        "role": "STUDENT",
                        "enabled": true,
                        "credentialsNonExpired": true,
                        "authorities": [
                            {
                                "authority": "STUDENT"
                            }
                        ],
                        "username": "kavikumar@gmail.com",
                        "accountNonLocked": true,
                        "accountNonExpired": true
                    },
                    {
                        "id": 6,
                        "name": "Arun",
                        "emailId": "arun23@gmail.com",
                        "password": "$2a$12$sh./oPhPh6mss91Im0MMvOEblFcYjfAYxHt1NoeBRsGRa1.Hi80N.",
                        "role": "STUDENT",
                        "enabled": true,
                        "credentialsNonExpired": true,
                        "authorities": [
                            {
                                "authority": "STUDENT"
                            }
                        ],
                        "username": "arun23@gmail.com",
                        "accountNonLocked": true,
                        "accountNonExpired": true
                    }
                ]
            }
            """;
    public static final String User_Retrieved_ID = """
        {
            "statusCode": 200,
            "message": "Retrieved Successfully",
            "data": [
                {
                    "id": 1,
                    "name": "Ravi",
                    "emailId": "ravi12@gmail.com",
                    "password": "$2a$12$szZMcpMY8UZtMql542CvYuNFWdQ6ZrReKxZrHk85Y50lKw0cRVYp.",
                    "role": "ADMIN",
                    "enabled": true,
                    "credentialsNonExpired": true,
                    "authorities": [
                        {
                            "authority": "ADMIN"
                        }
                    ],
                    "username": "ravi12@gmail.com",
                    "accountNonLocked": true,
                    "accountNonExpired": true
                }
            ]
        }
        """;

}
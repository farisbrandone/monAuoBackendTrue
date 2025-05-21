package com.example.monauto.sec;

public class JwtUtils {
    public static final String SECRET = "mySecret";
    public static final String CONFIRM_EMAIL_SECRET = "email_confirm_secret";
    public static final String AUTH_HEADER = "Authorization";
    public static final String BEARER_HEADER = "Bearer ";
    public static final long EXPIRE_ACCESS_TOKEN = 60*60*1000;
    public static final long EXPIRE_REFRESH_TOKEN = 5*24*60*60*1000;
    public static final String ADMIN_PASSWORD = "abcd";
    public static final String ADMIN_EMAIL= "admin.email@email.com";
   public static  String valueEmail(String url){
       return "<!DOCTYPE html>\n" +
           "<html>\n" +
           "  <head>\n" +
           "    <title>Centered HTML Email</title>\n" +
           "    <style>\n" +
           "      .container {\n" +
           "        width: 100%;\n" +
           "      }\n" +
           "      .content {\n" +
           "        margin: auto auto;\n" +
           "      }\n" +
           "      .text-content1 {\n" +
           "        margin: 0 auto;\n" +
           "        text-align: center;\n" +
           "      }\n" +
           "      .a-content1 {\n" +
           "        display: block;\n" +
           "        width: 260px;\n" +
           "        margin: 0 auto;\n" +
           "        border-radius: 5px;\n" +
           "        font-weight: 900;\n" +
           "        background-color: #e6c068;\n" +
           "        color: white;\n" +
           "        padding: 15px 20px;\n" +
           "        text-align: center;\n" +
           "        font-size: 16px;\n" +
           "        margin-bottom: 40px;\n" +
           "        margin-top: 20px;\n" +
           "      }\n" +
           "      .img-content {\n" +
           "        margin: 0 auto;\n" +
           "        display: block;\n" +
           "        width: 150px;\n" +
           "        background-color: transparent;\n" +
           "        margin-bottom: 30px;\n" +
           "        margin-top: 30px;\n" +
           "      }\n" +
           "      .child4 {\n" +
           "        width: 100%;\n" +
           "        text-align: center;\n" +
           "      }\n" +
           "      .text-child4 {\n" +
           "        color: rgb(53, 81, 240);\n" +
           "        margin-left: 5px;\n" +
           "      }\n" +
           "      .text-textfinal {\n" +
           "        margin-top: 2px;\n" +
           "        margin-left: -250px;\n" +
           "      }\n" +
           "      .fil {\n" +
           "        display: block;\n" +
           "        margin-left: -130px;\n" +
           "      }\n" +
           "    </style>\n" +
           "  </head>\n" +
           "  <body>\n" +
           "    <div class=\"container\">\n" +
           "      <div class=\"content\">\n" +
           "       \n" +
           "        <p class=\"text-content1\">\n" +
           "          Merci de vous être inscrit. Veuillez confirmer votre courriel en cliquant sur le boutton ci-dessous :\n" +
           "        </p>\n" +
           "        <a\n" +
           "          class=\"a-content1\"\n" +
           "          href="+"\""+url+"\""+"\n" +
           "        >\n" +
           "          Cliquez simplement ici\n" +
           "        </a>\n" +
           "      </div>\n" +
           "    </div>\n" +
           "  </body>\n" +
           "</html>";
   }

}

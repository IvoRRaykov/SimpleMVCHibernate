package util;

/**
 * Created by Ivo Raykov on 14.10.2016 г..
 */
public interface Constants {

    String PRODUCT_SERVICE = "productService";
    String USER_SERVICE = "userService";
    String MESSAGE_SERVICE = "messageService";

    String USER_ATTRIBUTE = "user";
    String USER_ROLE = "ROLE_USER";
    String ADMIN_ROLE = "ROLE_ADMIN";
    String ROLES_ATTRIBUTE = "roles";
    String CONFIRMED_MESSAGE_ATTRIBUTE = "confirmedMessage";
    String AVATAR_ATTRIBUTE = "avatar";
    String AVATAR_PREFIX = "http://api.adorable.io/avatar/150/";
    String LOGGED_USER_NAME_ATTRIBUTE = "loggedUserName";
    String LOGGED_USER_ID_ATTRIBUTE = "loggedUserId";
    String USER_NAME_ATTRIBUTE = "username";
    String LOGOUT_MESSAGE_ATTRIBUTE = "msg";

    String CONFIRM_PATH = "http://localhost:8080/account/confirm/";

    String PRODUCT_TO_UPDATE_ATTRIBUTE = "productToUpdate";
    String PRODUCT_TO_CREATE_ATTRIBUTE = "productToCreate";
    String PRODUCT_TO_CREATE_CODE_ATTRIBUTE = "productToCreateCode";
    String PRODUCT_LIST_ATTRIBUTE = "productList";
    String LOGGED_USER_MONEY_ATTRIBUTE = "loggedUserMoney";
    String PRODUCT_PICTURE_ATTRIBUTE = "productPicture";

    String MESSAGE_ATTRIBUTE = "message";
    String UNREAD_MESSAGES = "unreadMessages";
    String SIMILAR_NAMES_ATTRIBUTE = "similarNames";
    String TO_ATTRIBUTE = "to";
    String TEXT_ATTRIBUTE = "text";
    String SENT_MESSAGES_LIST_ATTRIBUTE = "sentMessagesList";
    String RECEIVED_MESSAGES_LIST_ATTRIBUTE = "receivedMessagesList";
    String SENT_URL_MARKER = "s";
    String RECEIVED_URL_MARKER = "r";
    String PRODUCT_CREATE_URL_MARKER = "c";
    String PRODUCT_UPDATE_URL_MARKER = "u";
    String GENRES_LIST_ATTRIBUTE = "genresList";

    String EMPTY = "";
    String FILE_FOR_SAVE_NAME = "productList";

    String USER_LIST_ATTRIBUTE = "listUsers";
    String USERS_NAMES_LIST_ATTRIBUTE = "listUsersNames";


    String ERROR_STRING_ATTRIBUTE = "errorString";
}

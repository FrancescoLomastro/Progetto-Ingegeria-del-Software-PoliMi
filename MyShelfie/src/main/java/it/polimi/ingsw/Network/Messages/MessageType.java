package it.polimi.ingsw.Network.Messages;


public enum MessageType
{
    PLAYER_NUMBER_ANSWER,
    LOGIN_REQUEST,
    PLAYER_NUMBER_REQUEST,
    ACCEPTED_LOGIN_MESSAGE,
    INVALID_USERNAME_MESSAGE,
    LOBBY_UPDATE_MESSAGE,
    UPDATE_GRID_MESSAGE,
    UPDATE_LIBRARY_MESSAGE,
    MY_MOVE_ANSWER,
    MY_MOVE_REQUEST,
    INIT_PLAYER_MESSAGE,
    NEW_GAME_SERVER_MESSAGE,
    START_GAME_MESSAGE,
    SOCKET_LOGIN_REQUEST,
    RMI_LOGIN_REQUEST, ERROR,
    AFTER_MOVE_POSITIVE,
    AFTER_MOVE_NEGATIVE,
    WINNER,
    COMMON_GOAL,
    CHAT_MESSAGE,
    ALMOST_OVER,
    INIT_PERSONAL_GOAL,
    INIT_COMMON_GOAL;
}

package com.example.basicspring;

public interface MessageService {
    /**
     *
     * @param message El mensaje ingresado por el usuario.
     * @return El mensaje procesado.
     */
    String processMessage(String message);
}

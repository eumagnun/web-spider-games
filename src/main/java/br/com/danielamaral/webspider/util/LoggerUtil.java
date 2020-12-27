package br.com.danielamaral.webspider.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger("LoggerUtil");
    private static final String MENSAGEM = "Serviço: %s - Mensagem: %s";

    private LoggerUtil() {
    }

    public static void logInfo(String msg, String servico) {
        LOGGER.info(String.format(MENSAGEM, servico, msg));
    }

    public static void logError(String msg, String servico, String valor) {
        LOGGER.error(String.format(MENSAGEM, servico, msg), valor);
    }

}

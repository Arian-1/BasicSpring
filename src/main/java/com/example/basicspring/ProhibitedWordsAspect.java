package com.example.basicspring;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Aspect
@Component
public class ProhibitedWordsAspect {


    private List<String> bannedWords = Arrays.asList("malo", "idiota", "perdedor", "nigga", "heribe");


    private String replacement = "!#?%@";

    @Around("execution(* com.example.basicspring.MessageService.processMessage(..))")
    public Object sanitizeMessage(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0 && args[0] instanceof String) {
            String message = (String) args[0];
            int bannedCount = 0;

            for (String word : bannedWords) {
                Pattern pattern = Pattern.compile("\\b" + Pattern.quote(word) + "\\b", Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(message);
                while (matcher.find()) {
                    bannedCount++;
                }
            }

            if (bannedCount > 3) {
                return "Advertencia: El mensaje contiene demasiadas palabras prohibidas.";
            }

            for (String word : bannedWords) {
                Pattern pattern = Pattern.compile("\\b" + Pattern.quote(word) + "\\b", Pattern.CASE_INSENSITIVE);
                message = pattern.matcher(message).replaceAll(replacement);
            }

            return message;
        }

        return joinPoint.proceed();
    }
}


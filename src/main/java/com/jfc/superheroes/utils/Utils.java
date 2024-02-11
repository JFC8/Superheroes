package com.jfc.superheroes.utils;

import com.jfc.superheroes.utils.data.Pair;
import com.jfc.superheroes.utils.data.Parameter;
import com.jfc.superheroes.utils.data.Single;
import com.jfc.superheroes.utils.data.Triple;
import jakarta.servlet.http.HttpServletRequest;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class Utils {

    public static final String UTC = "UTC";

    private Utils() {
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public static List<String> getAllTimeZones() {
        return ZoneId.getAvailableZoneIds().stream().sorted().toList();
    }

    public static LocalDateTime getLocalDateTime() {
        return LocalDateTime.now();
    }

    public static LocalDateTime getLocalDateTime(String zone) {
        return isNullOrEmpty(zone) ? LocalDateTime.now() : LocalDateTime.now(ZoneId.of(zone));
    }

    public static LocalDateTime getUTCLocalDateTime() {
        return getLocalDateTime("UTC");
    }

    public static LocalDateTime fromUTCLocalDateTime(LocalDateTime utcLocalDateTime, String targetZone) {
        return convertLocalDateTime("UTC", utcLocalDateTime, targetZone);
    }

    public static LocalDateTime convertLocalDateTime(String sourceZone, LocalDateTime localDateTime, String targetZone) {
        ZonedDateTime sourceZonedDateTime = localDateTime.atZone(ZoneId.of(sourceZone));
        ZonedDateTime targetZonedDateTime = sourceZonedDateTime.withZoneSameInstant(ZoneId.of(targetZone));
        return targetZonedDateTime.toLocalDateTime();
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException var3) {
            Thread.currentThread().interrupt();
        }

    }

    public static String getCurrentMethodName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        return stackTrace.length >= 3 ? stackTrace[2].getMethodName() : "";
    }

    public static String getCurrentMethodName(String errorId) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String methodName = stackTrace.length >= 3 ? stackTrace[2].getMethodName() : "";
        return methodName + " [errorId=" + errorId + "]";
    }

    public static String getClientIp(HttpServletRequest request) {
        String[] IP_HEADER_CANDIDATES = new String[]{"X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP", "HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR"};
        String[] var2 = IP_HEADER_CANDIDATES;
        int var3 = IP_HEADER_CANDIDATES.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String ipHeaderCandidate = var2[var4];
            String ip = request.getHeader(ipHeaderCandidate);
            if (ip != null && !ip.trim().isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }

        return request.getRemoteAddr();
    }

    public static boolean isNullOrEmpty(Object value) {
        if (value == null) {
            return true;
        } else if (value instanceof String) {
            return ((String)value).trim().isEmpty();
        } else if (value.getClass().isArray()) {
            return Array.getLength(value) == 0;
        } else if (value instanceof Collection) {
            return ((Collection)value).isEmpty();
        } else if (value instanceof Map) {
            return ((Map)value).isEmpty();
        } else if (value instanceof Single) {
            return ((Single)value).isEmpty();
        } else if (value instanceof Pair) {
            return ((Pair)value).isEmpty();
        } else if (value instanceof Triple) {
            return ((Triple)value).isEmpty();
        } else {
            return value instanceof Parameter ? ((Parameter)value).isEmpty() : false;
        }
    }

    public static String toJavaPath(String path) {
        path = path != null ? path.trim().replace("\\", "/") : "";
        return !path.endsWith("/") ? path + "/" : path;
    }

    protected <T> int getSize(Collection<T> collection) {
        return isNullOrEmpty(collection) ? 0 : collection.size();
    }

    public static <T> T getFirst(List<T> list) {
        return isNullOrEmpty(list) ? null : list.get(0);
    }

    public static <T> T getLast(List<T> list) {
        return isNullOrEmpty(list) ? null : list.get(list.size() - 1);
    }

    public static <T> void removeNulls(Collection<T> collection) {
        if (collection != null) {
            collection.removeIf(Objects::isNull);
        }

    }

    public static void removeNullsAndBlanks(Collection<String> collection) {
        if (collection != null) {
            collection.removeIf((str) -> {
                return str == null || str.trim().isEmpty();
            });
        }

    }


}

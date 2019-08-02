package com.somePackege.utils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Utils {

    private final BigInteger bi1000 = BigInteger.valueOf(1000);
    private final BigInteger bi2000 = BigInteger.valueOf(2000);

    private final Long TICKS_AT_EPOCH = 621355968000000000L;

    @Nonnull
    public static <T, R> R ifNullOrElse(@Nullable final T val, @Nonnull final Supplier<R> ifNull, @Nonnull final Function<T, R> ifNotNull) {
        return val == null
                ? ifNull.get()
                : ifNotNull.apply(val);
    }

    @Nonnull
    public static <T, R> R extractOrIfNullGet(@Nullable final T val, @Nonnull final Function<T, R> extract, final R ifNull) {
        return val == null
                ? ifNull
                : extract.apply(val);
    }

    @Nonnull
    public static <T> T ifNull(@Nullable final T val, @Nonnull final T def) {
        return val == null
                ? def
                : val;
    }

    @Nullable
    public static <T, R> R ifNull(@Nullable final T val, @Nonnull final Function<T, R> get) {
        return val == null
                ? null
                : get.apply(val);
    }

    @Nonnull
    public static <T, R> R ifNull(@Nullable final T val, @Nonnull final Function<T, R> get, @Nonnull final R def) {
        return val == null
                ? def
                : ifNull(get.apply(val), def);
    }

    @Nonnull
    public static <T> String ifNullConvertToString(@Nullable final T val, @Nonnull final String def) {
        return val == null
                ? def
                : val.toString();
    }

    public static LocalDate ifDateNullConvert(@Nullable final Date val){
        return val == null
                ? null
                : val.toLocalDate();
    }

    @Nonnull
    public static String getThrowableMessage(@Nonnull final Throwable throwable) {
        return ifNull(throwable.getMessage(), getThrowableCauseMessage(throwable));
    }

    @Nonnull
    public static String getThrowableMessage(@Nullable final Throwable throwable, final String def) {
        return ifNull(throwable, Throwable::getMessage, def);
    }

    @Nonnull
    public static String getThrowableCauseMessage(@Nonnull final Throwable throwable) {
        return getThrowableMessage(throwable.getCause(), "Произошла ошибка");
    }
    @Nonnull
    public static <T> Function<Object, Stream<T>> ofType(@Nonnull final Class<T> clazz) {
        return object -> clazz.isInstance(object)
                ? Stream.of(clazz.cast(object))
                : Stream.empty();
    }

    public static boolean isPositive(@Nullable final BigDecimal bigDecimal) {
        return bigDecimal != null && bigDecimal.signum() > 0;
    }

    public static boolean isNegative(@Nullable final BigDecimal bigDecimal) {
        return bigDecimal != null && bigDecimal.signum() < 0;
    }

    public static boolean isEmpty(@Nullable final String value) {
        return "".equals(value);
    }

    @Nonnull
    public static Optional<Integer> tryParseInteger(@Nullable final String stringValue) {
        try {
            return Optional.ofNullable(stringValue).map(Integer::valueOf);
        } catch (final NumberFormatException e) {
            return Optional.empty();
        }
    }

    @Nonnull
    public static Optional<BigDecimal> tryParseBigDecimal(@Nullable final String stringValue) {
        try {
            return Optional.ofNullable(stringValue).map(BigDecimal::new);
        } catch (final NumberFormatException e) {
            return Optional.empty();
        }
    }

    @Nonnull
    public static BigDecimal zeroIfNull(@Nullable final BigDecimal value) {
        return ifNull(value, BigDecimal.ZERO);
    }

    @Nullable
    public static BigDecimal nullIfZero(@Nullable final BigDecimal value) {
        return isZero(value)
                ? null
                : value;
    }

    public static boolean isZero(@Nullable final BigDecimal value) {
        return value != null && value.signum() == 0;
    }

    public static boolean isNullOrZero(@Nullable final BigDecimal value) {
        return value == null || value.signum() == 0;
    }

    public static boolean isOne(@Nullable final BigDecimal value) {
        return equals(value, BigDecimal.ONE);
    }

    public static boolean equals(@Nullable final BigDecimal value1, @Nullable final BigDecimal value2) {
        if (value1 == null && value2 == null) {
            return true;
        }
        return value1 != null && value2 != null && value1.compareTo(value2) == 0;
    }

    public static boolean equals(@Nullable final BigDecimal value1, final int value2) {
        return equals(value1, BigDecimal.valueOf(value2)); // We cannot use BigDecimal::equals
    }

    public static boolean equalsReplacedStrings(@Nullable final String value1, final String value2) {
        if (value1 == null && value2 == null) {
            return true;
        }
        return value1 != null && value2 != null && value1.replaceAll(",", ".").equals(value2.replace(",", "."));

    }

    public static <T extends Comparable<T>> boolean lessThan(
            @Nonnull final T value,
            @Nonnull final T to
    ) {
        return value.compareTo(to) < 0;
    }

    public static boolean lessThan(
            @Nonnull final BigDecimal value,
            final int to
    ) {
        return lessThan(value, BigDecimal.valueOf(to));
    }

    public static <T extends Comparable<T>> boolean lessOrEqual(
            @Nonnull final T value,
            @Nonnull final T to
    ) {
        return value.compareTo(to) <= 0;
    }

    public static boolean lessOrEqual(
            @Nonnull final BigDecimal value,
            final int to
    ) {
        return lessOrEqual(value, BigDecimal.valueOf(to));
    }

    public static <T extends Comparable<T>> boolean greaterThan(
            @Nonnull final T value,
            @Nonnull final T to
    ) {
        return value.compareTo(to) > 0;
    }

    public static boolean greaterThan(
            @Nonnull final BigDecimal value,
            final long to
    ) {
        return greaterThan(value, BigDecimal.valueOf(to));
    }

    public static <T extends Comparable<T>> boolean greaterOrEqual(
            @Nonnull final T value,
            @Nonnull final T to
    ) {
        return value.compareTo(to) >= 0;
    }

    public static boolean greaterOrEqual(
            @Nonnull final BigDecimal value,
            final long to
    ) {
        return greaterOrEqual(value, BigDecimal.valueOf(to));
    }

    public static <T extends Comparable<T>> boolean betweenStrictly(
            @Nonnull final T value,
            @Nonnull final T from,
            @Nonnull final T to
    ) {
        return greaterThan(value, from) && lessThan(value, to);
    }

    public static boolean betweenStrictly(
            @Nonnull final BigDecimal value,
            final int from,
            final int to
    ) {
        return greaterThan(value, from) && lessThan(value, to);
    }

    public static <T extends Comparable<T>> boolean betweenOrEqual(
            @Nonnull final T value,
            @Nonnull final T from,
            @Nonnull final T to
    ) {
        return greaterOrEqual(value, from) && lessOrEqual(value, to);
    }

    public static boolean betweenOrEqual(
            @Nonnull final BigDecimal value,
            final int from,
            final int to
    ) {
        return greaterOrEqual(value, from) && lessOrEqual(value, to);
    }

    public static boolean isBlank(@Nullable final String value) {
        return value == null || value.trim().isEmpty();
    }

    public static String ifBlank(@Nullable final String value, @Nullable final String def) {
        return isBlank(value) ? def : value;
    }

    public static boolean anyIsBlank(@Nonnull final String... values) {
        return Stream.of(values).anyMatch(Utils::isBlank);
    }

    public static boolean notBlank(@Nullable final String value) {
        return !isBlank(value);
    }

    public static boolean isZeroString(@Nullable final String value) {
        if (isBlank(value)) {
            return false;
        }
        final BigDecimal bigDecimal = toBigDecimal(value);

        return bigDecimal != null && equals(bigDecimal, BigDecimal.ZERO);

    }

    public static boolean isMainDistrict(@Nonnull final BigDecimal districtCode) {
        return lessThan(districtCode, BigDecimal.valueOf(1000));
    }

    public static boolean isOneOf(@Nonnull final String t, final String... ts) {
        return Arrays.stream(ts).anyMatch(o -> Objects.equals(t, o));
    }

    public static boolean isOneOf(@Nonnull final BigDecimal t, final BigDecimal... ts) {
        return Arrays.stream(ts).anyMatch(o -> Utils.equals(t, o));
    }

    public static boolean isOneOf(@Nonnull final BigDecimal t, final int... ts) {
        return Arrays.stream(ts).anyMatch(o -> Utils.equals(t, o));
    }

    public static boolean nullOrEqual(@Nullable final BigDecimal value1, @Nullable final BigDecimal value2) {
        return value1 == null && value2 == null || Utils.equals(value1, value2);
    }

    public static boolean toBoolean(@Nullable final BigDecimal value) {
        return value != null && !isZero(value);
    }

    @Nullable
    public static String toString(@Nullable final BigDecimal value) {
        return ifNull(value, BigDecimal::toPlainString);
    }

    @Nullable
    public static BigDecimal toBigDecimal(@Nullable final String value) {
        String clearValue = null;
        if (notBlank(value)) {
            clearValue = getClearStringToParse(value);
        }
        return tryParseBigDecimal(clearValue).orElse(null);
    }

    @Nullable
    public static BigDecimal toOnlyPositiveBigDecimal(@Nullable final String value) {
        return tryParseBigDecimal(value).map(BigDecimal::abs).orElse(null);
    }

    @Nullable
    public static BigDecimal getBigDecimal(final @Nullable BigDecimal value, final int scale) {
        return ifNull(value, bigDecimal -> bigDecimal.setScale(scale, RoundingMode.HALF_UP));
    }

    @Nullable
    public static BigDecimal getBigDecimal(final @Nullable BigDecimal value, final int scale, RoundingMode roundingMode) {
        return ifNull(value, bigDecimal -> bigDecimal.setScale(scale, roundingMode));
    }

    @Nonnull
    public static BigDecimal getBigDecimal(final @Nullable BigDecimal value) {
        return ifNull(value, BigDecimal.ONE);
    }

    @Nonnull
    public static String getStringAsBigDecimal(final @Nullable String value, final int scale) {
        return ifNull(value, s -> new BigDecimal(isBlank(value) ? "0" : value)
                .setScale(scale, RoundingMode.HALF_UP).toPlainString(), "");
    }

    @Nonnull
    public static BigDecimal getBigDecimalOrZero(final @Nonnull String value) {
        return getBigDecimalOrZero(value, 3);

    }

    @Nonnull
    public static BigDecimal getBigDecimalOrZero(final @Nonnull String value, int scale) {
        final BigDecimal decimalValue = toBigDecimal(value);
        return ifNull(decimalValue, bigDecimal -> getBigDecimal(bigDecimal, scale), BigDecimal.ZERO);
    }
    @Nonnull
    public static String getEmptyIfNull(@Nullable final String value) {
        return ifNull(value, "");
    }

    @Nonnull
    public static String getStringFromBoolean(boolean value) {
        return value ? "1" : "0";

    }

    @Nonnull
    public static boolean getBooleanFromString(@Nullable final String value) {
        return !isBlank(value) && "1".equals(value);

    }

    public static String trimAllNoneBreakingSpaces(final @Nonnull String value) {
        return value.replaceAll("\\u00a0", "");
    }

    public static boolean isAllBlanks(@Nonnull final String... value) {
        return Stream.of(value).allMatch(Utils::isBlank);
    }

    public static <T> T getValueIfTrueOrNull(final boolean trueOrFalse, @Nonnull final T value) {
        return trueOrFalse ? value : null;
    }

    public static <T> T getNullIfTrueOrValue(final boolean trueOrFalse, @Nonnull final T value) {
        return trueOrFalse ? null : value;
    }

    public static String getClearStringToParse(@Nonnull final String value) {
        return trimAllNoneBreakingSpaces(value).replaceAll(",", ".").replaceAll(" ", "");

    }

    @Nullable
    public static <T> String getMultiStringWithSeparator(final @Nonnull Collection<T> items,
                                                         final @Nonnull Function<T, Object> function,
                                                         final char separator) {

        if (items.isEmpty()) {
            return null;
        }

        final StringBuilder stringBuilder = new StringBuilder();

        items.forEach(t -> {
            final Object valueToAppend = function.apply(t);
            if (valueToAppend instanceof String) {
                final String stringToAppend = (String) valueToAppend;
                if (notBlank(stringToAppend)) {
                    stringBuilder.append(stringToAppend).append(separator);
                }
            } else {
                if (valueToAppend != null) {
                    stringBuilder.append(valueToAppend.toString()).append(separator);
                }
            }
        });

        final String result = stringBuilder.toString();

        return result.isEmpty() ? "" : result.substring(0, result.length() - 1);

    }

    public static String getEndingOfNumeral(int count, String firstEnding, String secondEnding, String thirdEnding) {
        if (count < 0) count *= -1;
        if (count < 11 || 19 < count) {
            count = count % 10;
            if (count == 1) return firstEnding;
            if (2 <= count && count <= 4) return secondEnding;
        }
        return thirdEnding;
    }

    public static <T extends Object> T copyObject(T objSource) {
        Object objDest = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(objSource);
            oos.flush();
            oos.close();
            bos.close();
            byte[] byteData = bos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
            try {
                objDest = new ObjectInputStream(bais).readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (T) objDest;
    }

}

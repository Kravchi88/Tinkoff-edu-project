package exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Getter
public class WrongRequestParameterException extends RuntimeException {}

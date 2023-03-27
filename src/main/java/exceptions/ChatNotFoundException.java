package exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Getter
public class ChatNotFoundException extends ResourceNotFoundException {}

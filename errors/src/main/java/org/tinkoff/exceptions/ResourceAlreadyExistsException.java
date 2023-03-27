package org.tinkoff.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Getter
public abstract class ResourceAlreadyExistsException extends RuntimeException {}

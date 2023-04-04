package org.tinkoff.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Getter
public class LinkAlreadyExistsException extends ResourceAlreadyExistsException {}

package ru.comavp.dto;

import java.util.Map;

public record TestRequest(String data, Map<String, String> additionalFields) {}

package org.example.webapp.controller.common;

import jakarta.servlet.http.HttpServletRequest;

public interface Action {
	ActionForward execute(HttpServletRequest request);
}


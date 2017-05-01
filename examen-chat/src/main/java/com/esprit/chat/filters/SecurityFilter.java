package com.esprit.chat.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.esprit.chat.beans.ConnectedUserBean;

@WebFilter(urlPatterns = "/secure/*")
public class SecurityFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		HttpSession session = httpRequest.getSession();

		ConnectedUserBean connectedUserBean = (ConnectedUserBean) session
				.getAttribute("connectedUserBean");

		String contextPath = httpRequest.getContextPath();

		if (connectedUserBean == null
				|| connectedUserBean.getConnectedUser() == null) {

			((HttpServletResponse) response).sendRedirect(contextPath
					+ "/inscription.jsf");
			;

		}

		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {

	}

}

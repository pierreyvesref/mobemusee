package fr.miage.orleans.isi.projet.authentificationws.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {

        /*httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.addHeader("Access-Control-Allow-Headers", "*");*/
        //httpServletResponse.addHeader("Access-Control-Expose-Headers", "Authorization");

        LOGGER.info(httpServletRequest.getRemoteAddr() + " executed a request on " + httpServletRequest.getRequestURI());



        return true;
    }
}

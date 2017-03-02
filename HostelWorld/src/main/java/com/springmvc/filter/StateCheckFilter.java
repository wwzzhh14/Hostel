package com.springmvc.filter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by wzh on 21/01/2017.
 */
@WebFilter(filterName = "StateCheckFilter")
public class StateCheckFilter implements Filter {
    private static final String fileName="/WEB-INF/filter-url-config.xml";
    private Set<String> urlSet = new HashSet<>();
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        String path = request.getServletPath();
        if(!urlSet.contains(path)){
            HttpSession session = request.getSession();
            if(session.getAttribute("Id")==null)
                response.sendRedirect("/");
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
        String realPath=config.getServletContext().getRealPath("");
        final String xmlFile=realPath+fileName;
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder= null;
        try {
            builder = factory.newDocumentBuilder();
            File file=new File(xmlFile);
            Document doc= null;
            doc = builder.parse(file);
            NodeList nodeList=doc.getElementsByTagName("url");
            int len=nodeList.getLength();
            for (int i = 0;i<len;i++){
                urlSet.add(nodeList.item(i).getFirstChild().getNodeValue());
                System.out.println();
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

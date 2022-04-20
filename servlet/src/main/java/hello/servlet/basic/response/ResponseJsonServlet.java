package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet{

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Content-Type: application/json
        response.setContentType("application/json");
        request.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("nam");
        helloData.setAge(20);

        //{"username":"nam", "age":20} -> 사용하기 위해서는 ObjectMapper를 사용해야된다.

        String result = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result);
    }
}

package examples;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ipn.WsoIpn;


// Access through http://<host>/WSO_IPN_for_Java/IpnListenerServlet
 


@WebServlet("/IpnListenerServlet")
public class IpnListenerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    public IpnListenerServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String secretKey = "secret";
		Map<String,String> ipnData = new HashMap<String, String>();
		for(Map.Entry<String, String[]> entry:request.getParameterMap().entrySet()){
			ipnData.put(entry.getKey(), entry.getValue()[0]);
		}
		WsoIpn ipn = new WsoIpn(ipnData,secretKey);
		if(ipn.isValid()){
			response.getWriter().append(new Date().toString() + "\tValid IPN Received").
				append(request.getContextPath());
		}else{
			response.getWriter().append(new Date().toString() + "\tINVALID Valid IPN Received").
			append(request.getContextPath());
		}
	}

}

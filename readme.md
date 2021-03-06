# Warrior Special Offers IPN Library

This repository contains libraries for different web programming languages for handling WSO IPN (Instant  Payment Notification) service.

### WSO IPN Library for Java
- This contains the class WsoIpn that accepts the http request's parameter names mapped to its value and/or the secret key and instantiates the WsoIpn. The class includes methods for signature validation and simple field getters.
- This folder also includes unit tests for all the classes used in WsoIpn class.
- An example on how to instantiate and use the WsoIpn class is shown in the servlet IPNListenerServlet.
- The WsoIpn class can be extended to include functionalities (i.e., logging, persistence, etc.) necessary for other programs.

*Note: The WsoIpn class was written to include very little to no dependency to third party libraries.*

### WSO IPN for Python
- This contains the class WsoIpn that accepts the http request's dictionary of parameter and values. The class includes methods for signature validation and getter of necessary IPN data.
- This also contains unit tests for signature validation and an example of a Listener that uses Flask.

### WSO IPN for PHP
- This contains the class WsoIpn that accepts the http request's dictionary of parameter and values. The class includes methods for signature validation and getter of necessary IPN data.
- This also contains unit tests for signature validation and an example of a Listener.

### *Note:*
1. The classes can be extended to include methods necessary for a program.
2. The provided code only serves as a guideline and example as to how to handle a WSO IPN. The way any of the code was structured should not be treated as standards.
3. The author is not liable for any errors caused by any WSO IPN library and shall not be responsible for any financial loss.

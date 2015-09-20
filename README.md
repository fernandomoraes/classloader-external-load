# classloader-external-load

Example of loading external impls with customizable classloader.

About:
 - dynamic-services-api: Interfaces of distributed services
 - dynamic-services-servlet: Servlet that invoke distributed services
 - SimpleService.java: Service impl

Running:
- deploy dynamic-services-servlet
- get in {ip}:{port}/external/execute?repository={location_external_class}&impl={impl_name}

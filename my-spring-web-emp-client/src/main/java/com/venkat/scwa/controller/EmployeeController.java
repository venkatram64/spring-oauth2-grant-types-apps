package com.venkat.scwa.controller;

import com.venkat.scwa.model.Employee;
import com.venkat.scwa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient webClient;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public String getEmployeesWebClient(Model model, @AuthenticationPrincipal OidcUser principal){

        String uri = "http://localhost:8082/employees/";
        List<Employee> employeeList = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Employee>>() {})
                .block();
        model.addAttribute("employees", employeeList);
        return "employees";
    }

    @GetMapping("/employees2")
    public String getEmployeesRestClient(Model model, @AuthenticationPrincipal OidcUser principal){
        System.out.println("Principal=" + principal);
        OidcIdToken oidcIdToken = principal.getIdToken();
        String idToken = oidcIdToken.getTokenValue();
        System.out.println("Token value=" + idToken);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        OAuth2AuthorizedClient oAuth2AuthorizedClient =
                oAuth2AuthorizedClientService.loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(),
                oauthToken.getName());
        String jwtToken = oAuth2AuthorizedClient.getAccessToken().getTokenValue();
        System.out.println("JwtToken = " + jwtToken);

        String uri = "http://localhost:8082/employees/";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtToken);
        HttpEntity<List<Employee>> entity = new HttpEntity(headers);
        ResponseEntity<List<Employee>> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Employee>>(){});
        List<Employee> employeeList = responseEntity.getBody();
        model.addAttribute("employees", employeeList);
        return "employees";
    }

    /*

    @GetMapping("/employees")
    public String getEmployees(Model model, @AuthenticationPrincipal OidcUser principal){
        System.out.println("Principal=" + principal);
        OidcIdToken oidcIdToken = principal.getIdToken();
        String idToken = oidcIdToken.getTokenValue();
        System.out.println("Token value=" + idToken);
        List<Employee> employeeList = employeeService.getAll();
        model.addAttribute("employees", employeeList);
        return "employees";
    }
     */
}

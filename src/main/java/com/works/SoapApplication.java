package com.works;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.consumingwebservice.wsdl.TCKimlikNoDogrulaResponse;
import com.example.consumingwebservice.wsdl.TCKimlikNoDogrula;
import org.springframework.context.annotation.Bean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

@SpringBootApplication
public class SoapApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoapApplication.class, args);
        soapRun();
    }

    public static void soapRun(){
        WebServiceTemplate webServiceTemplate = createWebServiceTemplate();

        TCClient tcClient = new TCClient(webServiceTemplate);
        TCKimlikNoDogrula obj = new TCKimlikNoDogrula();
        obj.setAd("Ertan");
        obj.setSoyad("Kaya");
        obj.setDogumYili(1997);
        obj.setTCKimlikNo(26167660802L);


        TCKimlikNoDogrulaResponse tcResponse = tcClient.tcKimlikNoDogrula(obj);
        boolean verificationResult = tcResponse.isTCKimlikNoDogrulaResult();
        System.out.println("Response: " + verificationResult);
    }

    @Bean
    public static WebServiceTemplate createWebServiceTemplate() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.example.consumingwebservice.wsdl");

        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setMarshaller(marshaller);
        webServiceTemplate.setUnmarshaller(marshaller);

        return webServiceTemplate;
    }
}

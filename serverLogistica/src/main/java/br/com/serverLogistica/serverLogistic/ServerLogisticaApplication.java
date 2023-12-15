package br.com.serverLogistica.serverLogistic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import br.com.serverLogistica.serverLogistic.config.DataBaseConfig;

@SpringBootApplication
public class ServerLogisticaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerLogisticaApplication.class, args);
		DataBaseConfig.dataBaseConfiguration();
	}

}

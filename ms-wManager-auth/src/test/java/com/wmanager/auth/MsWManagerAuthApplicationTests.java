package com.wmanager.auth;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MsWManagerAuthApplicationTests {
	
	public static Date formatarData(Date data) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = sdf.format(data);
        return sdf.parse(dataFormatada);
    }

	@Test
	void contextLoads() throws ParseException {
		System.out.println(formatarData(new Date()));
	}

}

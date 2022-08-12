import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class DataTeste {

	//Validar Mes
	@Test
	public void testMesVaidar() {
		Data m = new Data();
		m.setMes(11);
		int mes = m.getMes();
		assertEquals(11, mes);
	}

	//Validar Dia
	@Test
	public void testDiaValidarf() {
		Data d = new Data();
		d.setDia(02);
		int dia = d.getDia();
		assertEquals(02, dia);
	}

	//Validar Ano
	@Test
	public void testAnoValidar() {
		Data a = new Data();
		a.setAno(2019);
		int ano = a.getAno();
		assertEquals(2019, ano);
	}

	//Validar Data
	@Test
	public void testDataValidar() {
		
	}

	@Test
	public void testGetMaxDias() {
		Data MaxDia = new Data();
		int mes = 0, ano = MaxDia.getMaxDias(02, 2019);
		assertEquals(28, mes, ano);
	}
	

	//Adicionar Dias
	@Test
	public void testAdicionarDias() {
		Data adicDias = new Data();
		adicDias.setDia(13);
		adicDias = adicDias.adicionarDias(55);
		assertEquals(9,adicDias.getDia());
		assertEquals(3,adicDias.getMes());
	}

	//Comparar Datas
	@Test
	public void testCompararDatas() {
		Data primCom = new Data();
		Data segCom = new Data(25,02,2005);
		int comparador = Data.compararDatas(primCom, segCom);
		assertEquals(1,comparador);
	}

}

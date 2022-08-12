import java.util.Scanner;

public class Data {

	public static void main(String [] args){

	       Scanner leitorScanner = new Scanner(System.in);
	       Data[] vetorDatas = new Data[2];

	       for (int i = 0; i < 2; i++) {
	        System.out.println("Informe o valor da DATA:");
	        String entrada = leitorScanner.nextLine();
	        vetorDatas[i] = new Data(entrada);
	    }

	       Data dataMaior = compararDatas(vetorDatas[0], vetorDatas[1]);
	       System.out.println("A maior data é: ");
	       dataMaior.imprimir();

	    System.out.println("Qual data deseja efetuar a soma? 1 ou 2?");
			int entrada = leitorScanner.nextInt();
			Data data;
			if (entrada == 1) {
				data = vetorDatas[0];
			} else {
				data = vetorDatas[1];
			}

	        System.out.println("Dias há adicionar: ");
			entrada = leitorScanner.nextInt();
			Data adicionar = data.adicionarDias(entrada);

			System.out.println("Data final: ");
			adicionar.imprimir();
			leitorScanner.close();

	    }

	    //#region ATRIBUTOS
	    private int Dia;
	    private int Mes;
	    private int Ano;
	    //#endregion

	    //#region GET E SET
	    public int getDia(){
	        return this.Dia;
	    }

	    public int getMes(){
	        return this.Mes;
	    }

	    public int getAno(){
	        return this.Ano;
	    }

	    public void setDia(int dia){
	        this.Dia = dia;
	    }

	    public void setMes(int mes){
	        this.Mes = mes;
	    }

	    public void setAno(int ano){
	        this.Ano = ano;
	    }
	    //#endregion

	    public Data(String string){
	        int[] data = dataFromString(string);
	        if (dataValidar(data)){
	            this.setDia(data[0]);
	            this.setMes(data[1]);
	            this.setAno(data[2]);
	        }
	    }
	    
	    public Data(int d, int m, int a) {
	    	this.Dia = d;
	    	this.Mes = m;
	    	this.Ano = a;
	    }
	    
	    public Data(Data other){
	    	this.Dia = other.getDia();
	    	this.Mes = other.getMes();
	    	this.Ano = other.getAno();
	    }
	    
	    //#region CONTRUTORES
	    public Data(){
	        this(01,01,2005);
	    }

	    //#endregion

	    //#region AJUSTAR A DATA 00/00/0000
	    /**
	     * ajustar data
	     * @param d dia
	     * @param m mes
	     * @param a ano
	     */

	    //#region CONVERTER STRING
	    
	    public static int[] dataFromString(String string) {
	    	String[] aux = string.split("/");
	    	int[] data = new int[3];
	    	data[0] =Integer.parseInt(aux[0]);
	    	data[1] =Integer.parseInt(aux[1]);
	    	data[2] =Integer.parseInt(aux[2]);
	    	
	    	return data;
	    }

	   
	    //#endregion


	    //#endregion

	    // #region VALIDAR DATA
		public boolean verfBissex(int ano) {
			return (ano % 400 == 0 || (ano % 100 != 0 && ano % 4 == 0));
		}

		public boolean mesVaidar(int mes) {
			return (mes > 0 && mes <= 12);
		}

		public boolean diaValidarf(int dia, int max_dias) {
			return (dia > 0 && dia <= max_dias);
		}

		public boolean anoValidar(int ano) {
			return (ano >= 0 && ano < 3000);
		}

		public boolean dataValidar(int[] dataTemp) {
			int max_dias;
			if (mesVaidar(dataTemp[1])) {
				max_dias = getMaxDias(dataTemp[1], dataTemp[2]);
			} else {
				System.out.println("Mês Inválido!");
				return false;
			}

			if (!diaValidarf(dataTemp[0], max_dias)) {
				if (dataTemp[0] == 29 && dataTemp[1] == 2)
					System.out.println("Ano não Bissexto!");
				else
					System.out.println("Dia Inválido!");
				return false;
			}

			if (!anoValidar(dataTemp[2])) {
				System.out.println("Ano Inválido!");
				return false;
			}

			return true;
		}

		public int getMaxDias(int mes, int ano) {
			if (mes == 2)
				return (verfBissex(ano)) ? 29 : 28;
			else if (mes == 4 || mes == 6 || mes == 9 || mes == 11)
				return 30;
			return 31;
		}
		// #endregion

	    //#region ADICIONAR DIAS A DATA
	    public Data adicionarDias(int num){
	        Data adiciona = new Data(this);
	        int somaDia = adiciona.getDia() + num;
	        if(somaDia > getMaxDias(adiciona.getMes(), adiciona.getAno())) {
	        	while (somaDia > getMaxDias(adiciona.getMes(),adiciona.getAno())) {
	        		adiciona.setDia(somaDia - getMaxDias(adiciona.getMes(),adiciona.getAno()));
	        		somaDia -= getMaxDias(adiciona.getMes(),adiciona.getAno());
	        		if (adiciona.getMes() + 1 > 12) {
	        			adiciona.setMes(1);
	        			adiciona.setAno(adiciona.getAno() + 1);
	        		} else
	        			adiciona.setMes(adiciona.getMes() + 1);
	        	}
	        } else {
	        	adiciona.setDia(somaDia);
	        }
	        return adiciona;
	    } 
	    //#endregion

	    //#region COPARARDOR
	    public static Data compararDatas(Data data1, Data data2){
	        Data dataMaior = new Data();
	        if (data1.getAno() > data2.getAno()) {
	            dataMaior = data1;
	        }else if (data1.getAno() < data2.getAno()){
	            dataMaior = data2;
	        }else {
	            if (data1.getMes() > data2.getMes()){
	                dataMaior = data1;
	            } else if (data1.getMes() > data2.getAno()) {
	                dataMaior = data2;
	            }else {
	                if (data1.getDia() > data2.getDia()) {
	                    dataMaior = data1;
	                }else {
	                    dataMaior = data2;
	                }
	            }
	        }
	        return dataMaior;
	    }

	    // #region IMPRIMIR DATA
		public void imprimir() {
			
			if(Dia != 0) {
				String d = String.format("%02d", this.Dia);
		        String m = String.format("%02d", this.Mes);
		        String a = String.format("%04d", this.Ano);
				System.out.println(d + "/" + m + "/" + a);
			} else {
				System.out.println("Data Inexistente");
			}
			
			
			
		}
		// #endregion

	}

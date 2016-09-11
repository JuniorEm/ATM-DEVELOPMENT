package br.umc.view.option;

public enum OptionEnum {
	CONSULT("1"), WITHDRAW("2"), DEPOSIT("3"), EXIT("4"), BACK("6");
	
	private String code;
	
	OptionEnum(final String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
}
